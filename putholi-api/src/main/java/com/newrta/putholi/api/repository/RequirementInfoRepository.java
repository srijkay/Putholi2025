package com.newrta.putholi.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.RequirementInfoCustomRepo;
import com.newrta.putholi.api.domain.RequirementInfo;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Repository
@Transactional
public interface RequirementInfoRepository extends JpaRepository<RequirementInfo, Long>, RequirementInfoCustomRepo {

	/**
	 * @param requirementId
	 * @return
	 */

	RequirementInfo findByRequirementId(Long requirementId);

	/**
	 * 
	 * @param consolidateId
	 * @return
	 */
	@Query("From RequirementInfo r where r.consolidateRef.consolidateId = :consolidateId and r.active=:active")
	List<RequirementInfo> findAllByRequirementInfoList(@Param("consolidateId") Long consolidateId,
			@Param("active") String active);

	/**
	 * @param consolidateId
	 * @param reqStatus
	 * @return
	 */
	@Query("From RequirementInfo r where r.consolidateRef.consolidateId = :consolidateId and r.reqStatus=:reqStatus and r.active=:active")
	List<RequirementInfo> findAllRequirementByStatus(@Param("consolidateId") Long consolidateId,
			@Param("reqStatus") String reqStatus, @Param("active") String active);

	/**
	 * 
	 * @param requirementId
	 * @param reqStatus
	 */

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE RequirementInfo r SET r.reqStatus =:reqStatus  WHERE r.requirementId =:requirementId")
	void updateApprovalDetails(@Param("requirementId") Long requirementId, @Param("reqStatus") String reqStatus);

	/**
	 * @param requirementId
	 * @param reqStatus
	 * @param active
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE RequirementInfo r SET r.reqStatus =:reqStatus, r.active=:active  WHERE r.requirementId =:requirementId")
	void updateRequirementDetails(@Param("requirementId") Long requirementId, @Param("reqStatus") String reqStatus,
			@Param("active") String active);

	/**
	 * @param consolidateId
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM RequirementInfo r WHERE r.consolidateRef.consolidateId = :consolidateId AND r.reqStatus = :reqStatus AND active=:active")
	int checkPendingStatus(@Param("consolidateId") Long consolidateId, @Param("reqStatus") String reqStatus,
			@Param("active") String active);

	/**
	 * @param consolidateId
	 * @param reqStatus
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM RequirementInfo r WHERE r.consolidateRef.consolidateId = :consolidateId AND r.reqStatus NOT IN (:reqStatus) AND active=:active")
	int requirementNotInStatus(@Param("consolidateId") Long consolidateId, @Param("reqStatus") List<String> reqStatus,
			@Param("active") String active);

	/**
	 * 
	 * @param reqStatus
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM RequirementInfo r WHERE r.reqStatus IN (:reqStatus) AND active=:active")
	int checkPendingReqStatus(@Param("reqStatus") List<String> reqStatus, @Param("active") String active);

	/**
	 * @param reqStatusCode
	 * @param volunteerName
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM RequirementInfoDetails r WHERE r.reqStatusCode IN :reqStatusCode AND volunteerName=:volunteerName And r.consolidateStatus NOT IN :consolidateStatus AND active=:active")
	int pendingStatusCount(@Param("reqStatusCode") List<String> reqStatusCode,
			@Param("volunteerName") String volunteerName, @Param("consolidateStatus") String consolidateStatus,
			@Param("active") String active);

	/**
	 * @param requirementId
	 */
	void deleteByRequirementId(long requirementId);

	/**
	 * @param requirementId
	 * @param reqStatus
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE RequirementInfo r SET r.reqStatus =:reqStatus  WHERE r.consolidateRef.consolidateId =:consolidateId")
	void updateRequirementDetails(@Param("consolidateId") Long consolidateId, @Param("reqStatus") String reqStatus);

	/**
	 * @param consolidateId
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE RequirementInfo r SET r.active =:active, r.reqStatus =:reqStatus  WHERE r.consolidateRef.consolidateId =:consolidateId")
	void deleteRequirementDetails(@Param("active") String active, @Param("reqStatus") String reqStatus,
			@Param("consolidateId") Long consolidateId);

	/**
	 * @param consolidateId
	 * @return
	 */
	@Query(value = "SELECT new com.newrta.putholi.api.domain.RequirementInfo( (SELECT m.description FROM MasterCodeDetails m WHERE m.code = r.requirementType AND m.codeType ='REQTY') as requirementType,"
			+ "(SELECT m.description FROM MasterCodeTypeDetails m WHERE m.codeType = r.assetType AND m.codeType IN ('INF', 'SPR', 'OTH')) as assetType, CASE WHEN r.assetType = 'OTH' THEN assetName ELSE (SELECT m.description FROM MasterCodeDetails m  WHERE m.code = r.assetName AND m.codeType in ('INF', 'SPR')) END as assetName,  r.quantity, (SELECT m.description FROM MasterCodeDetails m WHERE m.code = r.reqStatus AND m.codeType ='STS') as reqStatus) From RequirementInfo r where r.consolidateRef.consolidateId =:consolidateId and r.active in ('Y')")
	List<RequirementInfo> findAllByRequirementInfo(@Param("consolidateId") Long consolidateId);

	/**
	 * 
	 * @param requirementId
	 * @param reqStatus
	 * @return
	 */
	@Query(value = "SELECT new com.newrta.putholi.api.domain.RequirementInfo( "
			+ "(SELECT m.description FROM MasterCodeDetails m WHERE m.code = r.requirementType AND m.codeType ='REQTY') as requirementType, "
			+ "(SELECT m.description FROM MasterCodeTypeDetails m WHERE m.codeType = r.assetType AND m.codeType IN ('INF', 'SPR', 'OTH')) as assetType,  CASE WHEN r.assetType = 'OTH' THEN assetName ELSE (SELECT m.description FROM MasterCodeDetails m  WHERE m.code = r.assetName AND m.codeType in ('INF', 'SPR')) END as assetName,  r.quantity, (SELECT m.description FROM MasterCodeDetails m WHERE m.code = r.reqStatus AND m.codeType ='STS') as reqStatus) "
			+ "From RequirementInfo r where  r.requirementId =:requirementId and r.reqStatus =:reqStatus")
	List<RequirementInfo> findByReqDescription(@Param("reqStatus") String reqStatus, @Param("requirementId") Long requirementId);

	/**
	 * @param assetName
	 * @return
	 */
	@Query(value = "select case when count(r)> 0 then true else false end From RequirementInfo r where r.assetName=:assetName and r.createdBy=:createdBy and  r.consolidateRef.consolidateId =:consolidateId and r.active=:active")
	boolean existsByAssetNameAndCreatedByIgnoreCase(@Param("assetName") String assetName,
			@Param("createdBy") String createdBy, @Param("consolidateId") Long consolidateId,
			@Param("active") String active);

	/**
	 * @param reqStatus
	 * @param invoiceStatus
	 * @return
	 */

	@Query(value = "SELECT count(*) FROM RequirementInfo e WHERE e.reqStatus IN :reqStatus"
			+ " and EXISTS (select 1 FROM InvoiceDetails f WHERE f.invoiceStatus IN :invoiceStatus and e.requirementId= f.requirementDetails.requirementId)")
	int checkInvoiceAndRequirementStatus(@Param("reqStatus") List<String> reqStatus,
			@Param("invoiceStatus") List<String> invoiceStatus);
}
