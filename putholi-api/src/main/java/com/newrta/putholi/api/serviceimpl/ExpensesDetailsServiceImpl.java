package com.newrta.putholi.api.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ExpensesDetailsDTO;
import com.newrta.putholi.api.model.ExpensesViewDetailsSearchDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.repository.ExpensesDetailsRepository;
import com.newrta.putholi.api.service.ExpensesDetailsService;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class ExpensesDetailsServiceImpl implements ExpensesDetailsService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ExpensesDetailsRepository expensesRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Value("${payment.fileName}")
	private String filePath;

	/**
	 * 
	 */
	@Value("${corporate.account.number}")
	private String corporateAccountNumber;

	/**
	 * 
	 */
	@Value("${mail.support}")
	private String mailAddress;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeDetailsService masterCodeService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveExpensesDetails(String loggedUser, ExpensesDetailsDTO expensesDetailsDTO) {
		log.info("ExpensesDetailsServiceImpl-saveExpensesDetails");

		ApiResultDTO apiResultDTO;

		try {
			ExpensesDetails expensesDetails = modelMapper.map(expensesDetailsDTO, ExpensesDetails.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.EXPENSES).auditDesc("ExpensesDetailsServiceImpl-saveExpensesDetails")
					.auditValue(new ObjectMapper().writeValueAsString(expensesDetailsDTO)).createdBy(loggedUser)
					.build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			ExpensesDetails details = expensesRepo.save(expensesDetails);

			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW, "ADD", "Admin",
					"reviewer_email_count", "Expenses");

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(details.getExpensesId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("FeatureManagementServiceImpl-saveFeatureManagement-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyExpensesDetails(String loggedUser, ExpensesDetailsDTO expensesDetailsDTO) {
		log.info("ExpensesDetailsServiceImpl-modifyExpensesDetails");

		ApiResultDTO apiResultDTO;

		try {
			ExpensesDetails expensesDetails = modelMapper.map(expensesDetailsDTO, ExpensesDetails.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
					.auditModule(CommonsConstants.EXPENSES)
					.auditDesc("ExpensesDetailsServiceImpl-modifyExpensesDetails")
					.auditValue(new ObjectMapper().writeValueAsString(expensesDetailsDTO)).createdBy(loggedUser)
					.build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			ExpensesDetails details = expensesRepo.save(expensesDetails);

			if (expensesDetails.getStatus().equals("EXPREV")) {
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW, "ADD", "Admin",
						"reviewer_email_count", "Expenses");
			}
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Updated Info Successfully").id(details.getExpensesId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("FeatureManagementServiceImpl-modifyExpensesDetails-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ExpensesDetails findByExpensesId(String loggedUser, Long expressId) {
		log.info("ExpensesDetailsServiceImpl-findByExpensesId");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.EXPENSES).auditDesc("ExpensesDetailsServiceImpl-findByExpensesId")
				.auditValue(expressId != null ? Long.toString(expressId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return expensesRepo.findByExpensesId(expressId);
	}

	/**
	 *
	 */
	@Override
	public Page<ExpensesDetails> searchExpensesDetails(String loggedUser, ExpensesViewDetailsSearchDTO searchDTO) {
		log.info("ExpensesDetailsServiceImpl-searchExpensesDetails");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule("EXPENSES").auditDesc("ExpensesDetailsServiceImpl-searchExpensesDetails")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("ExpensesDetailsServiceImpl-searchExpensesDetails-JsonProcessingException {} {}", jpe.getCause(),
					jpe);
		}
		return expensesRepo.searchExpensesDetails(searchDTO);
	}

	/**
	 *
	 */
	@Override
	public void updateStatusByExpensesId(Long expensesId, String status) {
		log.info("ExpensesDetailsServiceImpl-updateStatusByExpensesId");

		expensesRepo.updateStatusByExpensesId(expensesId, status);
	}

	/**
	 *
	 */
	@Override
	public List<ExpensesDetails> findByStatus(String status) {
		log.info("ExpensesDetailsServiceImpl-updateStatusByExpensesId");
		return expensesRepo.findByStatus(status);
	}

	@Override
	@Scheduled(cron = "${cronExpression}")
	public void generatedExcelFile() {
		log.info("ExpensesDetailsServiceImpl-generatedExcelFile");

		List<ExpensesDetails> expensesDetails = findByStatus("APR");
		if (expensesDetails != null) {
			createExpensesFile(expensesDetails);
		}

	}

	/**
	 * @param expensesDetails
	 * @return
	 */
	private void createExpensesFile(List<ExpensesDetails> expensesDetails) {
		log.info("ExpensesDetailsServiceImpl-createExpensesFile");

		// Create the folder if it doesn't exist
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		// Generate the file name
		String fileName = "trust_expenses.xlsx";
		try {
			// Create the file path by combining the folder path and file name
			File file = new File(folder, fileName);

			try (Workbook workbook = new XSSFWorkbook()) {
				Sheet sheet = workbook.createSheet(fileName);

				Row headerRow = sheet.createRow(0);

				// Create a cell style with border settings
				CellStyle borderStyle = workbook.createCellStyle();
				borderStyle.setBorderTop(BorderStyle.THIN);
				borderStyle.setBorderBottom(BorderStyle.THIN);
				borderStyle.setBorderLeft(BorderStyle.THIN);
				borderStyle.setBorderRight(BorderStyle.THIN);

				// Create a header row and cell style for the red font
				cellStyle(headerRow, workbook, borderStyle);
				// Populate the data
				int rowNum = 1;
				for (ExpensesDetails expensesData : expensesDetails) {
					Row row = sheet.createRow(rowNum++);

					// mapping the data into the rows
					populatedData(row, workbook, expensesData, borderStyle);
					updateStatusByExpensesId(expensesData.getExpensesId(), "PAYINI");

				}

				// Adjust column widths to fit the content
				for (int i = 0; i < headerRow.getLastCellNum(); i++) {
					sheet.autoSizeColumn(i);
				}

				FileOutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);

				approvalHistoryService.sendMailtoTrust(null, null, filePath + fileName, null, mailAddress,
						"Trust Invoice", fileName);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param headerRow
	 * @param workbook
	 */
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
	private void populatedData(Row row, Workbook workbook, ExpensesDetails expensesData, CellStyle borderStyle) {

		MasterCodeResultDTO category = masterCodeService.findMasterCodesByCodeTypeAndCode("CATGY",
				expensesData.getCategory());

		row.createCell(0).setCellValue("PAB_VENDOR");
		row.createCell(1).setCellValue(expensesData.getBankName().equals("ICICI") ? "FT" : "IMPS");
		row.createCell(2).setCellValue(corporateAccountNumber);
		row.createCell(3).setCellValue(String.valueOf(expensesData.getVendorName()));
		row.createCell(4).setCellValue(String.valueOf(expensesData.getAccountNum()));
		row.createCell(5).setCellValue(String.valueOf(expensesData.getIfscCode()));
		row.createCell(6).setCellValue(String.valueOf(expensesData.getAmount()));
		row.createCell(7).setCellValue(String.valueOf(category.getDescription()));
		row.createCell(8).setCellValue(String.valueOf(category.getDescription()));
		row.createCell(9).setCellValue(String.valueOf(expensesData.getMobileNumber()));
		row.createCell(10).setCellValue("");
		row.createCell(11).setCellValue(expensesData.getDescription());
		row.createCell(12).setCellValue(new Date());
		row.createCell(13).setCellValue(String.valueOf(expensesData.getExpensesId()));
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

	/**
	 *
	 */
	@Override
	public int checkPendingStatus(List<String> status) {
		log.info("ExpensesDetailsServiceImpl-checkPendingStatus");
		return expensesRepo.checkPendingStatus(status);
	}

	/**
	 *
	 */
	@Override
	public Long updateExpensesPayment(ExpensesDetailsDTO expensesDTO) {
		log.info("ExpensesDetailsServiceImpl-updateExpensesPayment");

		ExpensesDetails expensesDetails = modelMapper.map(expensesDTO, ExpensesDetails.class);

		ExpensesDetails details = findByExpensesId(null, expensesDetails.getExpensesId());

		if (details != null && details.getStatus().equals("PAYINI")) {
			expensesRepo.updateExpensesPayment(expensesDetails.getExpensesId(), expensesDetails.getStatus(),
					expensesDetails.getUtrDate(), expensesDetails.getUtrNumber(), expensesDetails.getRejectedReason());
		} else if (details == null) {
			return expensesDetails.getExpensesId();
		}
		return null;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateExpensesPayment(MultipartFile file) {
		log.info("ExpensesDetailsServiceImpl-updateExpensesPayment");

		File directory = new File("/putholi/response/");
		boolean success = false;
		if (directory.exists()) {
			log.info("Directory already exists ...");
		} else {
			log.info("Directory not exists, creating now");
			success = directory.mkdir();
			if (success) {
				log.info("Successfully created new directory : {}", success);
			} else {
				log.info("Failed to create new directory: {}", success);
			}
		}
		try {
			// Get the bytes of the uploaded file
			byte[] bytes = file.getBytes();
			// Define the file path
			Path path = Paths.get("/putholi/response/trustexpenses_payment_response.xlsx");

			// Save the file
			Files.write(path, bytes);

			return readExcelFile("/putholi/response/trustexpenses_payment_response.xlsx");

		} catch (IOException e) {
			e.printStackTrace();

			return ApiResultDTO.builder().apiStatusCode("ERROR").apiStatusDesc("Failed to uploaded file !!").build();
		}
	}

	/**
	 * @param filePath
	 * @return
	 */
	private ApiResultDTO readExcelFile(String filePath) throws IOException {

		FileInputStream excelFile = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(excelFile);

		List<Long> notFoundIds = new ArrayList<>();

		// Assuming you want to read the first sheet
		Sheet sheet = workbook.getSheetAt(0);
		int successCount = 0;
		int failureCount = 0;
		// Start from the second row (0-based index)
		int startRowIndex = 1;

		try {

			for (int rowIndex = startRowIndex; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);

				for (Cell cell : row) {
					log.info("cell----{}", cell);
				}

				// get the UTR date
				String dateString = row.getCell(26).toString();
				Date inputDateFormat = null;

				if (dateString != null && !dateString.isEmpty()) {
					SimpleDateFormat format1 = new SimpleDateFormat("d-MMM-yyyy");
					if (isDateFormat(format1, dateString)) {
						inputDateFormat = new SimpleDateFormat("dd-MMM-yyyy").parse(dateString);
					} else {
						inputDateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
					}
				}

				// get the UTR Number
				long longValueutr;
				long longValue;
				if (row.getCell(28).getCellType() == CellType.NUMERIC) {
					double doubleUtrValue = row.getCell(28).getNumericCellValue();
					longValueutr = (long) doubleUtrValue;

					// get invoice id
					double doubleValue = row.getCell(14).getNumericCellValue();
					longValue = (long) doubleValue;

				} else {
					String utrCellValue = row.getCell(28).toString();
					double doubleUtrValue = Double.parseDouble(utrCellValue);
					longValueutr = (long) doubleUtrValue;

					// get invoice id
					String cellValue = row.getCell(14).toString();
					double doubleValue = Double.parseDouble(cellValue);
					longValue = (long) doubleValue;
				}
				Cell statusCell = row.getCell(21);

				if (statusCell != null && statusCell.toString().equalsIgnoreCase("Success")) {
					successCount++;
				} else {
					failureCount++;
				}

				ExpensesDetailsDTO details = ExpensesDetailsDTO.builder().expensesId(longValue)
						.status(row.getCell(21).toString().equals("Success") ? "PAYCMP" : "PAYFAL")
						.utrDate(inputDateFormat).utrNumber(Long.toString(longValueutr))
						.rejectedReason(row.getCell(25).toString()).build();

				if (details != null) {
					Long value = updateExpensesPayment(details);
					if (value != null) {
						notFoundIds.add(value);
					}
				}
			}

			workbook.close();
			excelFile.close();

		} catch (IOException io) {
			io.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String notFoundIdsString = notFoundIds.stream().filter(Objects::nonNull).map(Object::toString)
				.collect(Collectors.joining(", "));

		return ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("File uploaded successfully !!")
				.id((long) (sheet.getLastRowNum())).failureCount(failureCount).successCount(successCount)
				.refNo(notFoundIdsString.isEmpty() ? null : notFoundIdsString + " Id doen't exists in the system")
				.build();
	}

	/**
	 * @param format
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	private static boolean isDateFormat(SimpleDateFormat format, String dateString) throws ParseException {
		try {
			format.parse(dateString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
