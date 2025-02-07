package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentInstructionDTO {
	/**
	 * 
	 */
	private String paymentId;
	/**
	 * 
	 */

	private BigDecimal amountPaid;
	/**
	 * 
	 */

	private String paymentCurrency;

	/**
	 * 
	 */
	private String paymentMethod;
	/**
	 * 
	 */

	private String instrType;
	/**
	 * 
	 */

	private String status;

	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */

	private long consolidateId;

	/**
	 * 
	 */
	private Date paymentDate;

	/**
	 * 
	 */
	private String orderId;

	/**
	 * 
	 */
	private String webUrl;

}
