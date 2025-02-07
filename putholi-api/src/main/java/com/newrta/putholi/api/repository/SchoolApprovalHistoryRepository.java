package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface SchoolApprovalHistoryRepository extends JpaRepository<SchoolApprovalHistoryDetails, Long> {

	/**
	 * @param schoolInfoId
	 * @param type
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findBySchoolInfoIdAndType(Long schoolInfoId, String type);

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
	 * @param requirementId
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByRequirementId(Long requirementId);

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
	 * @param expensesId
	 * @param type
	 * @return
	 */
	List<SchoolApprovalHistoryDetails> findByExpensesIdAndType(Long expensesId, String type);
}
