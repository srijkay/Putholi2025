package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.RequirementInfoDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RequirementDTO;
import com.newrta.putholi.api.model.RequirementSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

public interface RequirementService {

	/**
	 * @param loggedUser
	 * @param requirementId
	 * @return
	 */

	RequirementInfo fetchRequirementInfo(String loggedUser, Long requirementId);

	/**
	 * @param loggedUser
	 * @param RequirementId
	 * @return
	 */
	ApiResultDTO removeRequirementInfo(String loggedUser, Long requirementId);

	/**
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	Page<RequirementInfoDetails> searchRequirement(String loggedUser, RequirementSearchDTO searchDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param consolidateRefDTO
	 * @return
	 */

	ApiResultDTO saveRequirementInfo(String loggedUser, RequirementDTO requirementDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param requirementDTO
	 * @return
	 */
	ApiResultDTO modifyRequirementInfo(String loggedUser, RequirementDTO requirementDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param consolidateId
	 * @param reqStatus
	 * @return
	 */

	List<RequirementInfo> findAllRequirementByStatus(String loggedUser, Long consolidateId, String reqStatus);

	/**
	 * 
	 * @param requirementId
	 * @param status
	 * @return 
	 */

	void updateApprovalDetails(Long requirementId, String reqStatus);

	/**
	 * @param loggedUser
	 * @param consolidateId
	 * @return
	 */
	List<RequirementInfo> findAllByRequirementInfoList(String loggedUser, Long consolidateId);
	
	
	/**
	 * @param loggedUser
	 * @return
	 */
	List<RequirementInfoDetails> getAllRequirements(String loggedUser);

	/**
	 * @param consolidateId
	 * @return
	 */
	int checkPendingStatus(Long consolidateId, String reqStatus);

	/**
	 * @param consolidateId
	 * @param reqStatus
	 * @return
	 */
	int requirementNotInStatus(Long consolidateId, List<String> reqStatus);

	/**
	 * @param consolidateId
	 * @param requirementId
	 */
	ApiResultDTO checkingStatus(Long consolidateId);

	/**
	 * 
	 * @param reqStatus
	 * @return
	 */
	int checkPenStatus(List<String> reqStatus);

	/**
	 * @param reqStatus
	 * @param volunteerName
	 * @return
	 */
	int pendingStatusCount(List<String> reqStatus, String volunteerName, String consolidateStatus);

	/**
	 * @param consolidateId
	 * @param reqStatus
	 */
	void updateRequirementDetails(Long consolidateId, String reqStatus);

	/**
	 * @param loggedUser
	 * @param consolidateId
	 * @return
	 */
	void deleteRequirementDetails(String loggedUser, String active,String reqStatus, Long consolidateId);

	/**
	 * @param requirementId
	 * @param reqStatus
	 * @param active
	 */
	void updateRequirementDetails(Long requirementId, String reqStatus, String active);

	/**
	 * @param loggedUser
	 * @param reqStatus
	 * @param invoiceStatus
	 * @return
	 */
	int checkInvoiceAndRequirementStatus(String loggedUser, List<String> reqStatus, List<String> invoiceStatus);

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param address
	 * @param emailId
	 * @return
	 */
	ResponseEntity<ApiResultDTO> workOrderInitiatedEmail(String authorization, String loggedUser, String address,
			String emailId, Long assetname);

	/**
	 * @param loggedUser
	 * @param requirementId
	 * @return
	 */
	RequirementInfo fetchRequirementInfoDescription(Long requirementId);

	/**
	 * @param loggedUser
	 * @param consolidateId
	 * @return
	 */
	List<RequirementInfo> findRequirementsByConsolidateId(String loggedUser, Long consolidateId);
	
	
	
}
