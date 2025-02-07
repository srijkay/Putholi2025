package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.InvoicePaymentDetailsDTO;
import com.newrta.putholi.api.service.InvoicePaymentDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Slf4j
@RestController
@RequestMapping(value = "/v1/api/invoicepaymentdetails")
public class InvoicePaymentDetailsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	InvoicePaymentDetailsService invoicePaymentService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param invoiceDetailsDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/add")
	public ResponseEntity<ApiResultDTO> saveInvoicePaymentInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody InvoicePaymentDetailsDTO invoiceDetailsDTO) {
		log.info("InvoicePaymentDetailsResource-saveInvoicePaymentInfo");

		return new ResponseEntity<>(invoicePaymentService.saveInvoicePaymentInfo(loggedUser, invoiceDetailsDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param locale
	 * @param quotation
	 * @return
	 */
	@CrossOrigin
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> applicantAttachments(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,

			@RequestPart("quotation") MultipartFile file) {
		log.info("InvoiceDetailsService-applicantAttachments");

		return new ResponseEntity<>(invoicePaymentService.updateInvoicePayment(file), HttpStatus.OK);
	}

}
