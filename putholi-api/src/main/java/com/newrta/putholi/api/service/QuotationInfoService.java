package com.newrta.putholi.api.service;

import java.util.List;

import com.newrta.putholi.api.domain.QuotationInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.QuotationInfoDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface QuotationInfoService {

	/**
	 * @param loggedUser
	 * @param quotationInfoDTO
	 * @return
	 */
	ApiResultDTO saveQuotationInfo(String loggedUser, QuotationInfoDTO quotationInfoDTO);

	/**
	 * @param loggedUser
	 * @param quotationInfoDTO
	 * @return
	 */
	ApiResultDTO modifyQuotationInfo(String loggedUser, QuotationInfoDTO quotationInfoDTO);

	/**
	 * @param loggedUser
	 * @param quotationId
	 * @return
	 */
	QuotationInfo findByQuotationId(String loggedUser, Long quotationId);

	/**
	 * @param loggedUser
	 * @param requirementId
	 * @return
	 */
	List<QuotationInfo> findQuotationInfo(String loggedUser, Long requirementId);

	/**
	 * @param loggedUser
	 * @param quotationId
	 * @return
	 */
	ApiResultDTO removeQuotationInfo(String loggedUser, Long quotationId);

	/**
	 * @param quotationId
	 * @param quotateStatus
	 */
	void updateApprovalDetails(Long quotationId, String quotateStatus);

	
	/**
	 * @param quotateStatus
	 * @return
	 */
	List<QuotationInfo> getQuotationVendorInfo(List<String> quotateStatus);

}
