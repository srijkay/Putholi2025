package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.domain.DonorInfo;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Transactional
@Repository
public interface DonorInfoRepository extends JpaRepository<DonorInfo, Long> {

	/**
	 * 
	 * @param emailId
	 * @return
	 */
	boolean existsByEmailIdIgnoreCase(String emailId);

	/**
	 * @param emailid
	 * @return
	 */
	DonorInfo findByEmailIdIgnoreCase(String emailId);

	/**
	 * @param donorId
	 * @param orderId
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE DonorInfo u SET u.orderId =:orderId WHERE u.emailId =:emailId")
	void updateOrderId(@Param("orderId") String orderId, @Param("emailId") String emailId);
	
	
	/**
	 * @param orderId
	 * @return
	 */
	DonorInfo findByOrderId(String orderId);

}
