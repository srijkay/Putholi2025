package com.newrta.putholi.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.PaymentInstruction;

/**
 * @author NEWRTA SOLUTION
 *
 */
@Repository
@Transactional
public interface PaymentInstructionRepositoty extends JpaRepository<PaymentInstruction, String> {

	/**
	 * @param paymentId
	 * @return
	 */
	PaymentInstruction findByPaymentId(String paymentId);

	/**
	 * @param orderId
	 * @return
	 */
	PaymentInstruction findByOrderId(String orderId);

	/**
	 * @param orderId
	 * @param status
	 * @return
	 */
	@Modifying
	@Query(value = "UPDATE PaymentInstruction p SET p.status =:status, p.paymentCurrency=:paymentCurrency, p.paymentMethod=:paymentMethod  WHERE p.paymentId =:paymentId")
	void updatePaymentByOrderId(@Param("paymentId") String paymentId, @Param("status") String status,
								@Param("paymentCurrency") String paymentCurrency, @Param("paymentMethod") String paymentMethod );

	

}
