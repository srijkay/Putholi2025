package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.service.IncomingPaymentService;
import com.newrta.putholi.api.service.InvoiceDetailsService;
import com.newrta.putholi.api.service.RequirementService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTONS
 *
 */
@Service
@Slf4j
public class IncomingPaymentServiceImpl implements IncomingPaymentService {


	/**
	 * 
	 */
	@Autowired(required = true)
	private InvoiceDetailsService invoiceService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * @param invoiceId
	 * @param statusCode
	 */
	@Override
	public void updateInvoiceRecords(String invoiceId, String statusCode) {
		log.info("IncomingPaymentServiceImpl-updateInvoiceRecords {} {}", statusCode);

		InvoiceDetails details = invoiceService.findByInvoiceId(null, Long.valueOf(invoiceId));

		if (details.getInvoiceStatus().equals("APR") || details.getInvoiceStatus().equals(CommonsConstants.PAYFAL)) {
			if ("SUCCESS".equals(statusCode)) {
				invoiceService.updateApprovalDetails(Long.valueOf(invoiceId), CommonsConstants.PAYINI);
			} else {
				invoiceService.updateApprovalDetails(Long.valueOf(invoiceId), CommonsConstants.PAYFAL);
			}
			updateRequirementStatus(details);
		}
	}

	/**
	 * @param details
	 */
	@Override
	public void updateRequirementStatus(InvoiceDetails details) {
		log.info("IncomingPaymentServiceImpl-updateRequirementStatus");
		String invoiceStatus;

		List<InvoiceDetails> invoiceInfo = invoiceService.findByRequirementId(null,
				details.getRequirementDetails().getRequirementId());

		// update the requirement Status
		int rejectedCount = invoiceService.checkPendingStatus(details.getRequirementDetails().getRequirementId(),
				(CommonsConstants.PAYFAL));
		if (rejectedCount == 0) {
			int aprCount = invoiceService.checkPendingStatus(details.getRequirementDetails().getRequirementId(), "APR");
			invoiceStatus = aprCount == 0 ? CommonsConstants.PAYINI : "PARPAY";
		} else {
			invoiceStatus = rejectedCount == invoiceInfo.size() ? CommonsConstants.PAYFAL : "PARFAL";
		}
		requirementService.updateApprovalDetails(details.getRequirementDetails().getRequirementId(), invoiceStatus);

	}
}
