package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.InvoiceDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface IncomingPaymentService {

	

	/**
	 * @param invoiceId
	 * @param statusCode
	 */
	void updateInvoiceRecords(String invoiceId, String statusCode);

	/**
	 * @param details
	 */
	void updateRequirementStatus(InvoiceDetails details);

}
