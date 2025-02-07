package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.ApproverLevels;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface ApproverLevelsRepository extends JpaRepository<ApproverLevels, Long> {

	/**
	 * @param loggedUser
	 * @param approverId
	 * @return
	 */
	ApproverLevels findByApproverId(Long approverId);

	/**
	 * @param featureId
	 * @return
	 */
	@Query("FROM ApproverLevels a where a.featureManagement.featureId = :featureId")
	List<ApproverLevels> findAllByApproverLevels(@Param("featureId") Long featureId);
}
