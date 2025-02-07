package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.InvoicePaymentDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface InvoicePaymentDetailsRepository extends JpaRepository<InvoicePaymentDetails, Long>{

	/**
	 * @param invoiceId
	 * @return
	 */
	InvoicePaymentDetails findByInvoiceId(Long invoiceId);
}
