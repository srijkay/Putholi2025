package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class TrustMemberAccountBookDTO {

	/**
	 *
	 */
	private Long trustBookId;

	/**
	 *
	 */
	private String feeType;

	/**
	 *
	 */
	private Date paymentDate;

	/**
	 *
	 */
	private String description;

	/**
	 *
	 */
	private BigDecimal amount;

	/**
	 *
	 */
	private String paymentId;

	/**
	 *
	 */
	private String remarks;

	/**
	 *
	 */
	private String donorId;

	/**
	 *
	 */
	private BigDecimal bankCharge;

	/**
	 *
	 */
	private String bankRefNo;
	/**
	 *
	 */
	private String trackingId;

	/**
	 *
	 */
	private String createdBy;
	
	/**
	 * 
	 */
	private String orderId;

}
