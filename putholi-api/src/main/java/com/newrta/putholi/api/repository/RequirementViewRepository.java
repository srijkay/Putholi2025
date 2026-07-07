package com.newrta.putholi.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.RequirementInfoDetails;
import com.newrta.putholi.api.model.CompletedProjectDto;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
@Transactional
public interface RequirementViewRepository extends JpaRepository<RequirementInfoDetails, Long> {

	/**
	 * @param reqStatusCode
	 * @return
	 */
	@Query(value = "SELECT new com.newrta.putholi.api.model.CompletedProjectDto( r.schoolName, "
			+ "r.locality, r.districtDesc, r.city, r.consolidateId, pab.paymentId, pab.projectId, "
			+ "SUM(q.totalAmount)) FROM RequirementInfoDetails r  "
			+ "LEFT JOIN QuotationInfo q ON q.requirementInfo.requirementId = r.requirementId AND q.quotateStatus = 'QUOARV' "
			+ "LEFT JOIN ProjectAccountBook pab ON  pab.projectId= r.consolidateId AND pab.projectIncExpId = (SELECT MAX(p2.projectIncExpId) FROM ProjectAccountBook p2 WHERE p2.projectId = r.consolidateId AND p2.paymentId IS NOT NULL)"
			+ "where r.reqStatusCode=:reqStatusCode "
			+ "GROUP BY (r.consolidateId, r.schoolName, r.locality,  r.districtDesc, r.city, pab.paymentId, pab.projectId) "
			+ "ORDER BY pab.projectId DESC ")

	List<CompletedProjectDto> getTheCompletedProjects(@Param("reqStatusCode") String reqStatusCode);

	/**
	 * @param reqStatusCode
	 * @return
	 */
	@Query("SELECT r.consolidateId, r.assetName FROM RequirementInfoDetails r WHERE r.reqStatusCode = :reqStatusCode")
	List<Object[]> getAssetNamesByStatus(@Param("reqStatusCode") String reqStatusCode);
}
