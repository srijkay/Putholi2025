package com.newrta.putholi.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.TrustMemberAccountBook;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface TrustMemberAccountBookRepository extends JpaRepository<TrustMemberAccountBook, Long> {

	/**
	 * @return
	 */
	TrustMemberAccountBook findTopByOrderByTrustBookIdDesc();

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TrustMemberAccountBook> findByCreatedDateBetween(Date startDate, Date endDate);
}
