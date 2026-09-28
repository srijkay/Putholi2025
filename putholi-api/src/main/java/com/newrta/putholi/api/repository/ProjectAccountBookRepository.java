package com.newrta.putholi.api.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.ProjectAccountBook;
import com.newrta.putholi.api.model.TrackingDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface ProjectAccountBookRepository extends JpaRepository<ProjectAccountBook, Long> {

	/**
	 * @param projectId
	 * @return
	 */
	List<ProjectAccountBook> findByProjectId(long projectId);

	/**
	 * @return
	 */
	ProjectAccountBook findTopByOrderByProjectIncExpIdDesc();

	/**
	 * @param projectId
	 * @return
	 */
	@Query(value = "SELECT SUM(amount) FROM ProjectAccountBook where projectId=:projectId and feeType = 'INC'")
	BigDecimal findBalanceAmountByProjectId(Long projectId);

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ProjectAccountBook> findByCreatedDateBetween(Date startDate, Date endDate);

	/**
	 * @param createdBy
	 * @return
	 */
	@Query("SELECT new com.newrta.putholi.api.model.TrackingDetailsDTO(p.projectIncExpId, p.projectId, "
			+ "p.amount, p.paymentId, p.createdBy, p.createdDate, c.schoolInfo.schoolName, "
			+ "(SELECT m.description  FROM MasterCodeDetails m WHERE m.code = c.schoolInfo.addressInfo.district  AND m.codeType = 'DIST') AS district, "
			+ "c.schoolInfo.addressInfo.city) FROM ProjectAccountBook p "
			+ "JOIN ConsolidateRefInfo c ON c.consolidateId = p.projectId JOIN c.requirementInfo r "
			+ "WHERE LOWER(p.createdBy) = LOWER(:createdBy) ORDER BY p.createdDate DESC")
	List<TrackingDetailsDTO> findByCreatedByIgnoreCaseOrderByCreatedDateDesc(String createdBy);
}
