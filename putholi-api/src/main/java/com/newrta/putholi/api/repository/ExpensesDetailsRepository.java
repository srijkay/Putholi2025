package com.newrta.putholi.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.customsrepo.ExpensesDetailsCustomRepo;
import com.newrta.putholi.api.domain.ExpensesDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
@Transactional
public interface ExpensesDetailsRepository extends JpaRepository<ExpensesDetails, Long>, ExpensesDetailsCustomRepo {

	/**
	 * @param expensesId
	 * @return
	 */
	ExpensesDetails findByExpensesId(Long expensesId);

	/**
	 * @param expensesId
	 * @param status
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE From ExpensesDetails e set e.status=:status where e.expensesId=:expensesId")
	void updateStatusByExpensesId(@Param("expensesId") Long expensesId, @Param("status") String status);

	/**
	 * @param status
	 * @return
	 */
	List<ExpensesDetails> findByStatus(String status);

	/**
	 * @param status
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM ExpensesDetails e WHERE e.status IN :status")
	int checkPendingStatus(@Param("status") List<String> status);

	/**
	 * @param expensesId
	 * @param status
	 * @param utrDate
	 * @param utrNumber
	 */
	@Modifying
	@Transactional
	@Query("update ExpensesDetails i set i.status = :status, i.utrNumber=:utrNumber, i.utrDate=:utrDate, i.rejectedReason=:rejectedReason where i.expensesId =:expensesId")
	void updateExpensesPayment(@Param("expensesId") Long expensesId, @Param("status") String status,
			@Param("utrDate") Date utrDate, @Param("utrNumber") String utrNumber, @Param("rejectedReason") String rejectedReason);

}
