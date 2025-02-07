package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.ConsolidateInfoDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ConsolidateRefDTO;
import com.newrta.putholi.api.model.ConsolidateSearchDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ConsolidateRefService {

	/**
	 * 
	 * @param loggedUser
	 * @param consolidateRefDTO
	 * @return
	 */
	ApiResultDTO modifyConsolidateInfo(String loggedUser, ConsolidateRefDTO consolidateRefDTO);

	/**
	 * @param loggedUser
	 * @param consolidateId
	 * @return
	 */
	ConsolidateRefInfo fetchConsolidateInfo(String loggedUser, Long consolidateId);

	/**
	 * @param loggedUser
	 * @param consolidateDTO
	 * @return
	 */
	Page<ConsolidateInfoDetails> searchConsolidateInfo(String loggedUser, ConsolidateSearchDTO searchDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param consolidateRefDTO
	 * @return
	 */
	ApiResultDTO saveConsolidateInfo(String loggedUser, ConsolidateRefDTO consolidateRefDTO);

	/**
	 * @param consolidateId
	 * @param status
	 */
	void updateConsolidateStatus(Long consolidateId, String status);

	/**
	 * @param status
	 * @return
	 */
	int checkPendingStatus(List<String> status);

	/**
	 * @param status
	 * @param volunteerName
	 * @return
	 */
	int pendingStatusCount(String status, String volunteerName);

	/**
	 * @param schoolId
	 * @param type
	 * @return
	 */
	SchoolApprovalHistoryDTO fetchDetailsForApproval(Long consolidateId);

	/**
	 * @param loggedUser
	 * @param consolidateRefDTO
	 * @return
	 */
	void deleteConsolidateInfo(String loggedUser, String active, String status, Long consolidateId);

	/**
	 * @param schoolInfoId
	 * @return
	 */
	List<ConsolidateRefInfo> findConsolidateDetailsBySchoolId(Long schoolInfoId, String status);

	/**
	 * @param consolidateId
	 * @param reqStatus
	 * @return
	 */
	int checkRequirementPendingStatus(Long consolidateId, String reqStatus);
}
