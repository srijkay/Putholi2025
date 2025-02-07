package com.newrta.putholi.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface SchoolApprovalHistoryService {

	/**
	 * @param approvalHistDtls
	 * @return
	 */
	ApiResultDTO saveSchoolApprovalHistoryDetails(SchoolApprovalHistoryDetails schoolApprovalHistDtls);

	/**
	 * @param schoolId
	 * @param type
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> fetchSchoolApprovalHistoryDetails(Long schoolId, String type);

	/**
	 * @param consolidateId
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByConsolidateId(Long consolidateId);

	/**
	 * @param quotationId
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByQuotationId(Long quotationId);

	/**
	 * 
	 * @param requirementId
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByRequirementId(Long requirementId, String type);

	/**
	 * @param invoiceId
	 * @param type
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByInvoiceIdAndType(Long invoiceId, String type);

	/**
	 * 
	 * @param requirementId
	 * @param type
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByRequirementIdAndType(Long requirementId, String type);

	/**
	 * @param ExpensesId
	 * @param type
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByExpensesId(Long expensesId, String type);

	/**
	 * @param request
	 * @param address
	 * @param emailId
	 * @param isReject
	 * @return
	 */
	ResponseEntity<ApiResultDTO> sentEmailForApprovals(HttpServletRequest request, String address, String emailId,
			String isReject, String roleName, String configValue, String description);

	/**
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param address
	 * @param emailId
	 * @param type
	 * @return
	 */
	ResponseEntity<ApiResultDTO> sendMailtoTrust(HttpServletRequest request, String locale,String attachmentFilePath,
			String address, String emailId, String type, String name);

}
