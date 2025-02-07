package com.newrta.putholi.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.InvoiceDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface InvoiceDetailsService {

	/**
	 * @param loggedUser
	 * @param invoiceDetails
	 * @return
	 */
	ApiResultDTO saveInvoiceInfo(String loggedUser, InvoiceDetailsDTO invoiceDetailsDTO);

	/**
	 * @param loggedUser
	 * @param invoiceDetails
	 * @return
	 */
	ApiResultDTO modifyInvoiceInfo(String loggedUser, InvoiceDetailsDTO invoiceDetailsDTO);

	/**
	 * @param loggedUser
	 * @param requirementId
	 * @return
	 */
	List<InvoiceDetails> findByRequirementId(String loggedUser, Long requirementId);

	/**
	 * @param loggedUser
	 * @param invoiceId
	 * @return
	 */
	InvoiceDetails findByInvoiceId(String loggedUser, Long invoiceId);

	/**
	 * @param loggedUser
	 * @param invoiceId
	 * @return
	 */
	ApiResultDTO removeInvoiceByInvoiceId(String loggedUser, Long invoiceId);

	/**
	 * @param invoiceId
	 * @param invoiceStatus
	 */
	void updateApprovalDetails(Long invoiceId, String invoiceStatus);

	/**
	 * @param requirementId
	 * @param invoiceStatus
	 * @return
	 */
	int checkPendingStatus(Long requirementId, String invoiceStatus);

	/**
	 * @param requirementId
	 * @param invoiceStatus
	 * @return
	 */
	int invoiceNotInStatus(Long requirementId, List<String> invoiceStatus);

	/**
	 * @param invoiceDetails
	 */
	void updateRequirementStatus(InvoiceDetails invoiceDetails);

	/**
	 * @param invoiceStatus
	 * @return
	 */
	List<InvoiceDetails> findByInvoiceStatus(String invoiceStatus);

	/**
	 * @param invoiceId
	 * @param invoiceStatus
	 * @param utrDate
	 * @param utrNumber
	 */
	Long updateInvoicePayment(InvoiceDetailsDTO invoiceDTo);

	/**
	 *
	 */
	ResponseEntity<ApiResultDTO> invoiceApprovedEmail(HttpServletRequest request, String locale, String authorization,
			String emailId, String isReject, String address, Long invoiceId);

}
