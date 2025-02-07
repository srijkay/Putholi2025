package com.newrta.putholi.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.InvoicePaymentDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface InvoicePaymentDetailsService {

	/**
	 * @param loggedUser
	 * @param invoicePaymentDetailsDTO
	 * @return
	 */
	ApiResultDTO saveInvoicePaymentInfo(String loggedUser, InvoicePaymentDetailsDTO invoicePaymentDetailsDTO);

	/**
	 * @param loggedUser
	 * @param file
	 * @return
	 */
	ApiResultDTO updateInvoicePayment(MultipartFile file);
}
