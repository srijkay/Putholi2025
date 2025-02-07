package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.domain.QuotationInfo;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
@Transactional
public interface QuotationInfoRepository extends JpaRepository<QuotationInfo, Long> {

	/**
	 * @param quotationId
	 * @return
	 */
	QuotationInfo findByQuotationId(Long quotationId);

	/**
	 * @param requirementId
	 * @return
	 */
	@Query("From QuotationInfo u where u.requirementInfo.requirementId = :requirementId")
	List<QuotationInfo> findByQuotatonInfo(@Param("requirementId") Long requirementId);

	/**
	 * @param quotationId
	 * @param quotateStatus
	 */
	@Modifying
	@Transactional
	@Query("update QuotationInfo u set u.quotateStatus = :quotateStatus where u.quotationId =:quotationId")
	void updateApprovalDetails(@Param("quotationId") Long quotationId, @Param("quotateStatus") String quotateStatus);

	/**
	 * @param quotationId
	 */
	void deleteByQuotationId(long quotationId);

	/**
	 * @param quotateStatus
	 * @return
	 */
	@Query("SELECT q FROM QuotationInfo q WHERE q.quotateStatus IN :quotateStatus")
	List<QuotationInfo> findDistinctCompanyNames(@Param("quotateStatus") List<String> quotateStatus);
}
