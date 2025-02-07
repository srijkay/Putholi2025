package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
/**
 * @author vijaya
 *
 */
@Data
@NoArgsConstructor
public class TrustAccountBookDTO {
	/**
	 *
	 */

	private Long trustAccId;
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
	private Long projectId;
	/**
	 *
	 */
	private String description;
	/**
	 *
	 */
	private String paymentId;
	/**
	 *
	 */
	private String paymentType;
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
	private String receiptId;
	/**
	 *
	 */
	private BigDecimal amount;
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
