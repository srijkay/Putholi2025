package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class ProjectAccountBookDTO {

	/**
	 *
	 */
	private Long projectIncExpId;

	/**
	 *
	 */
	private Long projectId;

	/**
	 *
	 */
	private String feeType;

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

	private BigDecimal balanceAmount;

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
	private String receiptId;

	/**
	 *
	 */
	private String createdBy;

	/**
	 *
	 */
	private String lastModifiedBy;

	/**
	 * 
	 */
	private String orderId;

}
