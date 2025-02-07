package com.newrta.putholi.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.model.UserApprovalDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserApprovalServiceFacade {
    
    /**
     * @param approvalHistDtls
     * @return
     */
    ApiResultDTO updateUserApproval(ApprovalHistoryDetailsDTO approvalHistDtlsDTO);

    /**
     * @param username
     * @return
     */
    UserApprovalDTO fetchUserDetailsForApproval(String username, String type);

    
    /**
     * @param approvalHistDtlsDTO
     * @return
     */
    ApiResultDTO updateRejectionUserApproval(ApprovalHistoryDetailsDTO approvalHistDtlsDTO);

	/**
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @param password
	 * @param isShowPassword
	 * @param isReject
	 * @return
	 */
	ResponseEntity<ApiResultDTO> ChangeRole(HttpServletRequest request, String locale, String authorization,
			String emailId, String address, String role);
	
	/**
	 * 
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @return
	 */

	ResponseEntity<ApiResultDTO> userAccountDeletionEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address);
}
