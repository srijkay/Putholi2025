package com.newrta.putholi.api.service;

import java.util.List;


import com.newrta.putholi.api.domain.ApproverLevels;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApproverLevelsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

public interface ApproverLevelDetailsService {

	/**
	 * @param loggedUser
	 * @param approverLevelsDTO
	 * @return
	 */
	ApiResultDTO saveApproverDetails(String loggedUser, ApproverLevelsDTO approverLevelsDTO);

	/**
	 * @param loggedUser
	 * @param approverLevelsDTO
	 * @return
	 */
	ApiResultDTO modifyApproverDetails(String loggedUser, ApproverLevelsDTO approverLevelsDTO);

	/**
	 * @param loggedUser
	 * @param featureId
	 * @return
	 */
	ApproverLevels fetchApproverDetails(String loggedUser, Long approverId);

	/**
	 * @param loggedUser
	 * @param approverId
	 * @return
	 */
	ApiResultDTO removeApproverDetails(String loggedUser, Long approverId);

	/**
	 * @param loggedUser
	 * @param featureId
	 * @return
	 */
	List<ApproverLevels> findByAllApproverLevels(String loggedUser, Long featureId);
}
