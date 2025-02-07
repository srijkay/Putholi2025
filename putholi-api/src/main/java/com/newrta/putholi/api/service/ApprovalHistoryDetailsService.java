package com.newrta.putholi.api.service;

import java.util.List;

import com.newrta.putholi.api.domain.ApprovalHistoryDetails;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ApprovalHistoryDetailsService {

	/**
	 * @param approvalHistDtls
	 * @return
	 */
	ApiResultDTO saveApprovalHistoryDetails(ApprovalHistoryDetails approvalHistDtls);

	/**
	 * @param username
	 * @return
	 */
	List<ApprovalHistoryDetails> fetchApprovalHistoryDetails(String username, String type);
}
