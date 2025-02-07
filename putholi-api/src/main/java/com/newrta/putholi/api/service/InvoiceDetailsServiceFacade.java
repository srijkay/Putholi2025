package com.newrta.putholi.api.service;

import java.util.List;

import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface InvoiceDetailsServiceFacade {

	/**
	 * @param requirementId
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchDetailsForApproval(Long invoiceId, String type);

	/**
	 * @param approvalHistoryService
	 * @return
	 */

	ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO approvalHistoryDetailsDTO);

	/**
	 * @param requirementInfo
	 */
	void updateRequrementStatusByInvoiceStatus(RequirementInfo requirementInfo,
			SchoolApprovalHistoryDetailsDTO approvalHistoryDetailsDTO);

	/**
	 * @param invoices
	 */
	void createPaymentFile(List<InvoiceDetails> invoice);

	/**
	 * 
	 */
	void generateInvoiceCSV();

}
