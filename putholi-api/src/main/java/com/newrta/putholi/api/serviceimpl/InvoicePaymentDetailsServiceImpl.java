package com.newrta.putholi.api.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.InvoicePaymentDetails;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.InvoiceDetailsDTO;
import com.newrta.putholi.api.model.InvoicePaymentDetailsDTO;
import com.newrta.putholi.api.repository.InvoicePaymentDetailsRepository;
import com.newrta.putholi.api.service.InvoiceDetailsService;
import com.newrta.putholi.api.service.InvoicePaymentDetailsService;
import com.newrta.putholi.api.service.RequirementService;
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
public class InvoicePaymentDetailsServiceImpl implements InvoicePaymentDetailsService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private InvoicePaymentDetailsRepository invoicePaymentRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	InvoiceDetailsService invoiceDetailsService;

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
	private RequirementService requirementService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveInvoicePaymentInfo(String loggedUser, InvoicePaymentDetailsDTO invoicePaymentDetailsDTO) {
		log.info("InvoicePaymentDetailsServiceImpl-saveInvoicePaymentInfo {}", invoicePaymentDetailsDTO);

		ApiResultDTO apiResultDTO;
		try {
			InvoicePaymentDetails invoicePaymentDetails = modelMapper.map(invoicePaymentDetailsDTO,
					InvoicePaymentDetails.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule("INVPAYDET").auditDesc("InvoiceDetailsServiceImpl-saveInvoiceInfo")
					.auditValue(new ObjectMapper().writeValueAsString(invoicePaymentDetailsDTO)).createdBy(loggedUser)
					.build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			InvoicePaymentDetails insertedInvoice = invoicePaymentRepo.save(invoicePaymentDetails);

			InvoiceDetails invoiceDetails = invoiceDetailsService.findByInvoiceId(null,
					invoicePaymentDetailsDTO.getInvoiceId());

			RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
					invoiceDetails.getRequirementDetails().getRequirementId());

			List<InvoiceDetails> invoiceInfo = invoiceDetailsService.findByRequirementId(null,
					requirement.getRequirementId());

			String invoiceStatus;
			if (requirement.getReqStatus().equals("PROCES")) {
				int count = invoiceDetailsService.checkPendingStatus(requirement.getRequirementId(), "PAYINI");
				invoiceStatus = count == invoiceInfo.size() ? "PAYINI" : "PARPAY";
			} else {
				invoiceStatus = requirement.getReqStatus();
			}
			requirementService.updateApprovalDetails(invoiceDetails.getRequirementDetails().getRequirementId(),
					invoiceStatus);

			invoiceDetailsService.updateApprovalDetails(invoicePaymentDetails.getInvoiceId(), CommonsConstants.PAYINI);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(insertedInvoice.getInvoicePaymentId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("InvoiceDetailsServiceImpl-saveInvoiceInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateInvoicePayment(MultipartFile file) {
		log.info("InvoicePaymentDetailsServiceImpl-saveInvoicePaymentInfo {}", file);

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
			Path path = Paths.get("/putholi/response/school_invoice_payment_response.xlsx");

			// Save the file
			Files.write(path, bytes);

			return readExcelFile("/putholi/response/school_invoice_payment_response.xlsx");

		} catch (IOException e) {
			e.printStackTrace();

			return ApiResultDTO.builder().apiStatusCode("ERROR").apiStatusDesc("Failed to uploaded file !!").build();
		}
	}

	/**
	 * @param filePath
	 * @throws IOException
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

		for (int rowIndex = startRowIndex; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {

				for (Cell cell : row) {
					log.info("cell----{}", cell);
				}

				try {

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

					// get reference number (Invoice Id)
					;

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

					InvoiceDetailsDTO details = InvoiceDetailsDTO.builder().invoiceId(longValue)
							.invoiceStatus(row.getCell(21).toString().equals("Success") ? "PAYCMP" : "PAYFAL")
							.utrNumber(Long.toString(longValueutr)).utrDate(inputDateFormat)
							.rejectedReason(row.getCell(25).toString()).build();

					if (details != null) {
						Long value = invoiceDetailsService.updateInvoicePayment(details);
						if (value != null) {
							notFoundIds.add(value);
						}
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		workbook.close();
		excelFile.close();

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
