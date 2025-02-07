package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.customsrepo.ConsolidateInfoCustomRepo;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
@Transactional
public interface ConsolidateRefRepository extends JpaRepository<ConsolidateRefInfo, Long>, ConsolidateInfoCustomRepo {

	/**
	 * @param consolidateId
	 * @return
	 */
	ConsolidateRefInfo findByConsolidateId(Long consolidateId);

	/**
	 * @param consolidateId
	 * @param status
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ConsolidateRefInfo c SET c.status =:status  WHERE c.consolidateId =:consolidateId")
	void updateApprovalDetails(@Param("consolidateId") Long consolidateId, @Param("status") String status);

	/**
	 * @param consolidateId
	 * @return
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ConsolidateRefInfo c SET c.active =:active, c.status =:status  WHERE c.schoolInfo.schoolInfoId = :schoolInfoId")
	void deleteConsolidateDetails(@Param("active") String active, @Param("status") String status,
			@Param("schoolInfoId") Long schoolInfoId);

	/**
	 * @param status
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM ConsolidateInfoDetails c WHERE c.consolidateStatusCode IN :status")
	int checkPendingStatus(@Param("status") List<String> status);

	/**
	 * @param consolidateStatusCode
	 * @param volunteerName
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM ConsolidateInfoDetails r WHERE r.consolidateStatusCode = :consolidateStatusCode AND volunteerName=:volunteerName")
	int pendingStatusCount(@Param("consolidateStatusCode") String consolidateStatusCode,
			@Param("volunteerName") String volunteerName);

	/**
	 * @param schoolInfoId
	 * @return
	 */
	@Query("From ConsolidateRefInfo c where c.schoolInfo.schoolInfoId = :schoolInfoId and c.status =:status")
	List<ConsolidateRefInfo> findConsolidateDetailsBySchoolId(@Param("schoolInfoId") Long schoolInfoId, @Param("status") String status);

}
