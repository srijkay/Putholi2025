package com.newrta.putholi.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface QuotationInfoServiceFacade {

	/**
	 * @param approvalHistDtls
	 * @return
	 */
	ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO approvalHistDtls);

	/**
	 * @param quotationId
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchDetailsByQuotationId(Long quotationId);

	/**
	 *
	 */
	ResponseEntity<ApiResultDTO> quotationApprovedEmail(HttpServletRequest request, String locale, String authorization,
			String emailId, boolean isReject, String address);

}
