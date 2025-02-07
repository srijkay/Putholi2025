package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.TrustAccountBook;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface TrustAccountBookRepository extends JpaRepository<TrustAccountBook, Long> {

	/**
	 * @param projectId
	 * @return
	 */
	TrustAccountBook findByProjectId(long projectId);

	/**
	 * @return
	 */
	TrustAccountBook findTopByOrderByTrustAccIdDesc();
}
