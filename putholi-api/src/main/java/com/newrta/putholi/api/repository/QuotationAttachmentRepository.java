package com.newrta.putholi.api.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.QuotationAttachments;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
@Transactional
public interface QuotationAttachmentRepository extends JpaRepository<QuotationAttachments, Long> {

	/**
	 * @param requirementId
	 * @return
	 */
	List<QuotationAttachments> findByRequirementIdAndUploadFor(Long requirementId, String uploadFor);

	/**
	 * @param invoiceId
	 * @param uploadFor
	 * @return
	 */
	List<QuotationAttachments> findByInvoiceIdAndUploadFor(long invoiceId, String uploadFor);

	/**
	 * @param quotationId
	 * @return
	 */
	QuotationAttachments findByQuotationId(long quotationId);

	/**
	 * @param attachmentId
	 * @return
	 */
	QuotationAttachments findByAttachmentId(Long attachmentId);

	/**
	 * @param requirementId
	 * @param uploadFor
	 */
	List<QuotationAttachments> deleteByRequirementIdAndUploadFor(Long requirementId, String uploadFor);

	/**
	 * @param quotationId
	 */
	void deleteByQuotationId(long quotationId);

	/**
	 * @param schoolInfoId
	 * @return
	 */
	List<QuotationAttachments> findBySchoolInfoIdAndUploadFor(long schoolInfoId, String uploadFor);

	/**
	 * @param invoiceId
	 */
	void deleteByInvoiceId(long invoiceId);

	/**
	 * @param uploadFor
	 * @return
	 */

	List<QuotationAttachments> findByUploadFor(String uploadFor);

	/**
	 * @param uploadFor
	 * @return
	 */
	@Query("From QuotationAttachments e WHERE e.uploadFor=:uploadFor and e.createdDate >= :sixMonthsAgo")
	List<QuotationAttachments> findByLastSixMonthsUploadFor(String uploadFor, @Param("sixMonthsAgo") Date sixMonthsAgo);

	/**
	 * @param expensesId
	 * @return
	 */
	QuotationAttachments findByExpensesId(Long expensesId);

}
