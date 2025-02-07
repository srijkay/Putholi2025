package com.newrta.putholi.api.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.ProjectAccountBook;

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

}
