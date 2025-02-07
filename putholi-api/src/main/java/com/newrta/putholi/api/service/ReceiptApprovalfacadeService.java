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
public interface ReceiptApprovalfacadeService {

	/**
	 * @param schoolHistoryDetails
	 * @return
	 */
	ApiResultDTO updateReceiptApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO);

	/**
	 * @param schoolInfoId
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchSchoolDetailsForApproval(Long invoiceId, String type);

	/**
	 * 
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param address
	 * @param emailId
	 * @param isReject
	 * @return
	 */
	ResponseEntity<ApiResultDTO> receiptApprovedEmail(HttpServletRequest request, String locale, String authorization,
			String address, String emailId, boolean isReject);
}
