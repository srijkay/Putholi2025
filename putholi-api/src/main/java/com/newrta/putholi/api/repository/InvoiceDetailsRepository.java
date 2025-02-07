package com.newrta.putholi.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.domain.InvoiceDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Long> {

	/**
	 * @param requirementId
	 * @return
	 */
	@Query("From InvoiceDetails i where i.requirementDetails.requirementId = :requirementId")
	List<InvoiceDetails> findByInvoiceDetails(@Param("requirementId") Long requirementId);

	/**
	 * @param invoiceId
	 * @return
	 */
	InvoiceDetails findByInvoiceId(Long invoiceId);

	/**
	 * @param invoiceId
	 * @param invoiceStatus
	 */
	@Modifying
	@Transactional
	@Query("update InvoiceDetails i set i.invoiceStatus = :invoiceStatus where i.invoiceId =:invoiceId")
	void updateApprovalDetails(@Param("invoiceId") Long invoiceId, @Param("invoiceStatus") String invoiceStatus);

	/**
	 * @param requirementId
	 * @param invoiceStatus
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM InvoiceDetails r WHERE r.requirementDetails.requirementId = :requirementId AND r.invoiceStatus = :invoiceStatus ")
	int checkPendingStatus(@Param("requirementId") Long requirementId, @Param("invoiceStatus") String invoiceStatus);

	/**
	 * @param requirementId
	 * @param invoiceStatus
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM InvoiceDetails r WHERE r.requirementDetails.requirementId = :requirementId AND r.invoiceStatus NOT IN (:invoiceStatus)")
	int invoiceNotInStatus(@Param("requirementId") Long requirementId,
			@Param("invoiceStatus") List<String> invoiceStatus);

	/**
	 * @param invoiceStatus
	 * @return
	 */
	List<InvoiceDetails> findByInvoiceStatus(String invoiceStatus);

	/**
	 * @param invoiceId
	 * @param invoiceStatus
	 * @param utrDate
	 * @param utrNumber
	 */
	@Modifying
	@Transactional
	@Query("update InvoiceDetails i set i.invoiceStatus = :invoiceStatus, i.utrNumber=:utrNumber, i.utrDate=:utrDate, i.rejectedReason=:rejectedReason where i.invoiceId =:invoiceId")
	void updateInvoicePayment(@Param("invoiceId") Long invoiceId, @Param("invoiceStatus") String invoiceStatus,
			@Param("utrDate") Date utrDate, @Param("utrNumber") String utrNumber, @Param("rejectedReason") String rejectedReason);

}
