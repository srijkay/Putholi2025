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
public interface RequirementApprovalHistoryFacadeService {

	/**
	 * @param requirementId
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchDetailsForApproval(Long requirementId, String type);

	/**
	 * @param approvalHistoryService
	 * @return
	 */

	ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO approvalHistoryDetailsDTO);

	/**
	 * 
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param address
	 * @param emailId
	 * @param requirementType
	 * @param assetName
	 * @param requirementId
	 * @param reqStatus
	 * @param isReject
	 * @return
	 */

	ResponseEntity<ApiResultDTO> requirementApprovalEmail(HttpServletRequest request, String authorization,
			String address, String emailId, Long requirementId, String reqStatus, String isReject);

	/**
	 * 
	 * @param request
	 * @param authorization
	 * @param address
	 * @param emailId
	 * @param requirementId
	 * @param reqStatus
	 * @return
	 */
	ResponseEntity<ApiResultDTO> requirementAssignedAdminEmail(HttpServletRequest request, String authorization,
			String address, String emailId, String type);
}
