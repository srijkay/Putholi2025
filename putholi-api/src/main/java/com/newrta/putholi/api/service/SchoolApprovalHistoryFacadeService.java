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
public interface SchoolApprovalHistoryFacadeService {

	/**
	 * @param schoolHistoryDetails
	 * @return
	 */
	ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO);

	/**
	 * @param schoolHistoryDetailsDTO
	 * @return
	 */
	ApiResultDTO updateVolunteerHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO);

	/**
	 * @param schoolHistoryDetailsDTO
	 * @return
	 */
	ApiResultDTO updateDeleteHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO);

	/**
	 * @param schoolId
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchDetailsForApproval(Long schoolInfoId, String type);

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param emailid
	 * @param address
	 * @param schoolInfoId
	 * @param mailBody
	 * @param consolidateId
	 * @param mailCc
	 * @return
	 */
	ResponseEntity<ApiResultDTO> sentEmailtoDEO(String authorization, String loggedUser, String emailid, String address,
			Long schoolInfoId, String mailBody, Long consolidateId, String[] mailCc);

	/**
	 * @param schoolHistoryDetailsDTO
	 * @return
	 */
	ApiResultDTO updateDEOApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO);

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param address
	 * @param schoolInfoId
	 * @param isReject
	 * @return
	 */
	ResponseEntity<ApiResultDTO> sendMailtoBeneficiary(String authorization, String loggedUser, String address,
			Long schoolInfoId, boolean isReject);

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

	ResponseEntity<ApiResultDTO> schoolRegistrationEmail(HttpServletRequest request, String locale,
			String authorization, String address, String emailId, boolean isReject);

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param address
	 * @param emailId
	 * @return
	 */
	ResponseEntity<ApiResultDTO> deleteSchoolEmail(String authorization, String loggedUser, String address,
			String emailId);

	/**
	 * @param schoolHistoryDetailsDTO
	 * @return
	 */
	ApiResultDTO updateExpensesApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO);

	/**
	 * @param expensesId
	 * @param type
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchDetailsForExpensesApproval(Long expensesId, String type);
}
