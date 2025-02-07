package com.newrta.putholi.api.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.InvoiceDetailsService;
import com.newrta.putholi.api.service.InvoiceDetailsServiceFacade;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Service
@Data
@Slf4j
public class InvoiceDetailsServiceFacadeImpl implements InvoiceDetailsServiceFacade {

	/**
	 * 
	 */
	@Value("${payment.fileName}")
	private String filePath;

	/**
	 * 
	 */
	@Value("${mail.support}")
	private String mailAddress;

	/**
	 * 
	 */
	@Value("${corporate.account.number}")
	private String corporateAccountNumber;

	/**
	 * 
	 */
	@Autowired(required = true)
	InvoiceDetailsService invoiceDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	private static final String INVOICE_DETAILS = "Invoice Details";

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO approvalHistoryDetailsDTO) {
		log.info("InvoiceDetailsServiceFacadeImpl-updateApprovalHistory {}",
				approvalHistoryDetailsDTO.getRequirementId());

		SchoolApprovalHistoryDetails approvalHistoryDetails = modelMapper.map(approvalHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		InvoiceDetails invoice = invoiceDetailsService.findByInvoiceId(null, approvalHistoryDetails.getInvoiceId());

		RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
				invoice.getRequirementDetails().getRequirementId());

		String status = CommonsConstants.ADMINV;
		if (approvalHistoryDetails.getStatus().equals("REJ")) {
			status = CommonsConstants.REJINV;

			UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserRegisterDetailsByUserName(
					requirement.getConsolidateRef().getSchoolInfo().getVolunteerName());

			if (approvalHistoryDetails.getRole().equals(CommonsConstants.ADMIN)) {
				invoiceDetailsService.invoiceApprovedEmail(null, null, null, userRegisterDetails.getEmailId(), "true",
						null, approvalHistoryDetailsDTO.getInvoiceId());

			}

			if (approvalHistoryDetails.getRole().equals("REVIEW") || approvalHistoryDetails.getRole().equals("APPRV")) {
				status = CommonsConstants.ADMINV;

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "true",
						approvalHistoryDetails.getRole().equals(CommonsConstants.REVIEW) ? "Reviewer" : "Approver",
						"admin_email_count", INVOICE_DETAILS);
			}

		} else if (approvalHistoryDetails.getStatus().equals("APR")) {

			if (approvalHistoryDetails.getRole().equals("ADMIN")) {
				status = CommonsConstants.INVREV;

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW,
						CommonsConstants.FALSE, "Admin", "reviewer_email_count", INVOICE_DETAILS);

			} else if (approvalHistoryDetails.getRole().equals("REVIEW")) {
				status = CommonsConstants.INVAPR;

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, CommonsConstants.FALSE,
						"Reviewer", "approver_email_count", INVOICE_DETAILS);

			} else if (approvalHistoryDetails.getRole().equals("APPRV")) {
				status = "APR";
				UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserRegisterDetailsByUserName(
						requirement.getConsolidateRef().getSchoolInfo().getVolunteerName());

				invoiceDetailsService.invoiceApprovedEmail(null, null, null, userRegisterDetails.getEmailId(), "false",
						null, approvalHistoryDetailsDTO.getInvoiceId());

			}
		}

		// update invoice status
		invoiceDetailsService.updateApprovalDetails(approvalHistoryDetails.getInvoiceId(), status);
		// save approval history
		approvalHistoryService.saveSchoolApprovalHistoryDetails(approvalHistoryDetails);

		updateReqStatus(approvalHistoryDetailsDTO, invoice);

		return ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Approval Details Updated Successfully!")
				.build();
	}

	/**
	 * @param approvalHistoryDetailsDTO
	 * @param invoice
	 */
	private void updateReqStatus(SchoolApprovalHistoryDetailsDTO approvalHistoryDetailsDTO, InvoiceDetails invoice) {

		// fetch the requirement info based on requirement id
		RequirementInfo requirementInfo = requirementService.fetchRequirementInfo(null,
				invoice.getRequirementDetails().getRequirementId());

		// get all invoices list based on requirement id
		List<InvoiceDetails> invoiceDetails = invoiceDetailsService.findByRequirementId(null,
				invoice.getRequirementDetails().getRequirementId());

		// get the count based on invoice status
		int rej = invoiceDetailsService.checkPendingStatus(requirementInfo.getRequirementId(), CommonsConstants.REJINV);
		if (rej == 0) {
			updateRequrementStatusByInvoiceStatus(requirementInfo, approvalHistoryDetailsDTO);
		} else {
			String reqStatus = rej == invoiceDetails.size() ? CommonsConstants.REJINV : "PARREJ";
			requirementService.updateApprovalDetails(requirementInfo.getRequirementId(), reqStatus);
		}
	}

	/**
	 *
	 */
	@Override
	public SchoolApprovalHistoryDTO fetchDetailsForApproval(Long invoiceId, String type) {
		log.info("InvoiceDetailsServiceFacadeImpl-fetchDetailsForApproval");

		InvoiceDetails invoiceDetails = invoiceDetailsService.findByInvoiceId(null, invoiceId);
		List<SchoolApprovalHistoryDetails> apprHistoryDetails = approvalHistoryService.findByInvoiceIdAndType(invoiceId,
				type);

		return SchoolApprovalHistoryDTO.builder().invoiceDetails(invoiceDetails)
				.schoolApprovalHistoryDetails(apprHistoryDetails).build();
	}

	/**
	 *
	 */
	@Override
	public void updateRequrementStatusByInvoiceStatus(RequirementInfo requirementInfo,
			SchoolApprovalHistoryDetailsDTO approvalHistoryDetailsDTO) {
		int penadm = invoiceDetailsService.checkPendingStatus(requirementInfo.getRequirementId(),
				CommonsConstants.ADMINV);
		int penrev = invoiceDetailsService.checkPendingStatus(requirementInfo.getRequirementId(), "INVREV");
		int penapr = invoiceDetailsService.checkPendingStatus(requirementInfo.getRequirementId(), "INVAPR");
		String reqStatus = CommonsConstants.ADMINV;

		if (approvalHistoryDetailsDTO.getRole().equals("ADMIN")) {
			reqStatus = penadm != 0 ? CommonsConstants.PARAPR : CommonsConstants.INVREV;
		} else if (approvalHistoryDetailsDTO.getRole().equals(CommonsConstants.REVIEW)) {
			reqStatus = penapr == 0 || penadm == 0 ? CommonsConstants.INVAPR : CommonsConstants.PARAPR;
		} else if (approvalHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
			reqStatus = penadm == 0 || penrev == 0 ? "PROCES" : CommonsConstants.PARAPR;
		}

		requirementService.updateApprovalDetails(requirementInfo.getRequirementId(), reqStatus);

	}

	/*
	 * 
	 */
	@Override
	@Scheduled(cron = "${cronExpression}")
	public void generateInvoiceCSV() {
		// Retrieve the approved invoices
		List<InvoiceDetails> approvedInvoices = invoiceDetailsService.findByInvoiceStatus("APR");

		// Generate CSV for each approved invoice
		if (approvedInvoices != null)
			createPaymentFile(approvedInvoices);
	}

	/**
	 *
	 */
	@Override
	public void createPaymentFile(List<InvoiceDetails> invoice) {
		log.info("ExpensesDetailsServiceImpl-createExpensesFile");

		// Create the folder if it doesn't exist
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		// Generate the file name
		String fileName = "school_invoice.xlsx";
		try {
			// Create the file path by combining the folder path and file name
			File file = new File(folder, fileName);

			try (Workbook workbook = new XSSFWorkbook()) {
				Sheet sheet = workbook.createSheet(fileName);

				Row headerRow = sheet.createRow(0);

				CellStyle borderStyle = workbook.createCellStyle();
				borderStyle.setBorderTop(BorderStyle.THIN);
				borderStyle.setBorderBottom(BorderStyle.THIN);
				borderStyle.setBorderLeft(BorderStyle.THIN);
				borderStyle.setBorderRight(BorderStyle.THIN);

				// Create a header row and cell style for the red font
				cellStyle(headerRow, workbook, borderStyle);

				// Populate the data
				int rowNum = 1;
				for (InvoiceDetails invoiceData : invoice) {
					Row row = sheet.createRow(rowNum++);
					// mapping the data into the rows
					populatedData(row, workbook, invoiceData, borderStyle);

					String status = "PAYINI";
					invoiceDetailsService.updateApprovalDetails(invoiceData.getInvoiceId(), status);

					// update the requirement Status
					int aprCount = invoiceDetailsService.invoiceNotInStatus(
							invoiceData.getRequirementDetails().getRequirementId(),
							Arrays.asList(status, "ADMINV", "INVREV", "INVAPR", "REJINV", "PARAPR", "PARREJ"));
					String invoiceStatus = aprCount == 0 ? status : "PARPAY";

					requirementService.updateApprovalDetails(invoiceData.getRequirementDetails().getRequirementId(),
							invoiceStatus);

				}
				// Adjust column widths to fit the content
				for (int i = 0; i < headerRow.getLastCellNum(); i++) {
					sheet.autoSizeColumn(i);
				}

				FileOutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);

				approvalHistoryService.sendMailtoTrust(null, null, filePath + fileName, null, mailAddress,
						"Requirements Invoice", fileName);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void cellStyle(Row headerRow, Workbook workbook, CellStyle borderStyle) {

		headerRow.createCell(0).setCellValue("PYMT_PROD_TYPE_CODE");
		headerRow.createCell(1).setCellValue("PYMT_MODE");
		headerRow.createCell(2).setCellValue("DEBIT_ACC_NO");
		headerRow.createCell(3).setCellValue("BNF_NAME");
		headerRow.createCell(4).setCellValue("BENE_ACC_NO");
		headerRow.createCell(5).setCellValue("BENE_IFSC");
		headerRow.createCell(6).setCellValue("AMOUNT");
		headerRow.createCell(7).setCellValue("DEBIT_NARR");
		headerRow.createCell(8).setCellValue("CREDIT_NARR");
		headerRow.createCell(9).setCellValue("MOBILE_NUM");
		headerRow.createCell(10).setCellValue("EMAIL_ID");
		headerRow.createCell(11).setCellValue("REMARK");
		headerRow.createCell(12).setCellValue("PYMT_DATE");
		headerRow.createCell(13).setCellValue("REF_NO");
		headerRow.createCell(14).setCellValue("ADDL_INFO1");
		headerRow.createCell(15).setCellValue("ADDL_INFO2");
		headerRow.createCell(16).setCellValue("ADDL_INFO3");
		headerRow.createCell(17).setCellValue("ADDL_INFO4");
		headerRow.createCell(18).setCellValue("ADDL_INFO5");

		CellStyle redFontStyle = workbook.createCellStyle();
		redFontStyle.cloneStyleFrom(borderStyle);
		Font redFont = workbook.createFont();
		redFont.setColor(IndexedColors.RED.getIndex());
		redFontStyle.setFont(redFont);

		// Apply the border style to all cells in the header row
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			headerRow.getCell(i).setCellStyle(borderStyle);
		}

		// Apply the red font style to the first and second header cells
		headerRow.getCell(0).setCellStyle(redFontStyle);
		headerRow.getCell(1).setCellStyle(redFontStyle);
		headerRow.getCell(2).setCellStyle(redFontStyle);
		headerRow.getCell(3).setCellStyle(redFontStyle);
		headerRow.getCell(4).setCellStyle(redFontStyle);
		headerRow.getCell(5).setCellStyle(redFontStyle);
		headerRow.getCell(6).setCellStyle(redFontStyle);
		headerRow.getCell(12).setCellStyle(redFontStyle);

	}

	/**
	 * @param headerRow
	 * @param workbook
	 * @param expensesData
	 */
	private void populatedData(Row row, Workbook workbook, InvoiceDetails invoiceData, CellStyle borderStyle) {

		RequirementInfo reqInfo = requirementService.fetchRequirementInfo(null,
				invoiceData.getRequirementDetails().getRequirementId());

		ConsolidateRefInfo consolidateInfo = consolidateService.fetchConsolidateInfo(null,
				reqInfo.getConsolidateRef().getConsolidateId());

		SchoolInfo schoolInfo = schoolService.fetchSchoolInfo(consolidateInfo.getSchoolInfo().getSchoolInfoId());

		row.createCell(0).setCellValue("PAB_VENDOR");
		row.createCell(1).setCellValue(invoiceData.getBankName().equals("ICICI") ? "FT" : "IMPS");
		row.createCell(2).setCellValue(corporateAccountNumber);
		row.createCell(3).setCellValue(String.valueOf(invoiceData.getCompanyName()));
		row.createCell(4).setCellValue(String.valueOf(invoiceData.getAccountNumber()));
		row.createCell(5).setCellValue(String.valueOf(invoiceData.getIfscCode()));
		row.createCell(6).setCellValue(String.valueOf(invoiceData.getInvoiceAmount()));
		row.createCell(7).setCellValue(schoolInfo.getSchoolName() + "_" + reqInfo.getAssetName());
		row.createCell(8).setCellValue(schoolInfo.getSchoolName() + "_" + reqInfo.getAssetName());
		row.createCell(9).setCellValue(String.valueOf(invoiceData.getPhoneNumber()));
		row.createCell(10).setCellValue("");
		row.createCell(11).setCellValue(invoiceData.getItemDescription());
		row.createCell(12).setCellValue(new Date());
		row.createCell(13).setCellValue(String.valueOf(invoiceData.getInvoiceId()));
		row.createCell(14).setCellValue("");
		row.createCell(15).setCellValue("");
		row.createCell(16).setCellValue("");
		row.createCell(17).setCellValue("");
		row.createCell(18).setCellValue("");

		// Apply the border style to all cells in the header row
		for (int i = 0; i < row.getLastCellNum(); i++) {
			row.getCell(i).setCellStyle(borderStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.cloneStyleFrom(borderStyle);
		dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));

		row.getCell(12).setCellStyle(dateCellStyle);

	}

}
