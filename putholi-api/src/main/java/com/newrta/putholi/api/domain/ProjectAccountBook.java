package com.newrta.putholi.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "TPUO_PROJECT_ACCOUNT_BOOK")
public class ProjectAccountBook {

	@Id
	@GeneratedValue(generator = "TPUO_PROJECT_ACCOUNT_BOOK_PROJECT_INC_EXP_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_PROJECT_ACCOUNT_BOOK_PROJECT_INC_EXP_ID_SEQ", sequenceName = "TPUO_PROJECT_ACCOUNT_BOOK_PROJECT_INC_EXP_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "PROJECT_INC_EXP_ID", length = 25)
	private Long projectIncExpId;

	@Column(name = " PROJECT_ID", length = 25, nullable = false)
	private Long projectId;

	@Column(name = "FEE_TYPE", length = 3, nullable = false)
	private String feeType;

	@Column(name = "Description", length = 500, nullable = false)
	private String description;

	@Column(name = "AMOUNT", scale = 2, precision = 12, nullable = false)
	private BigDecimal amount;

	@Column(name = "PAYMENT_ID", nullable = true)
	private String paymentId;

	@Column(name = "REMARKS", length = 255, nullable = true)
	private String remarks;

	@Column(name = "DONOR_ID", length = 50, nullable = true)
	private String donorId;

	@Column(name = "RECEIPT_ID", length = 25, nullable = true)
	private String receiptId;

	@Column(name = "BALANCE_AMOUNT", scale = 2, precision = 25, nullable = true)
	private BigDecimal balanceAmount;

	@Column(name = "BANK_CHARGE", scale = 2, precision = 12, nullable = true)
	private BigDecimal bankCharge;

	@Column(name = "BANK_REF_NO", length = 25, nullable = true)
	private String bankRefNo;

	@Column(name = "TRACKING_ID", length = 25, nullable = true)
	private String trackingId;

	@Column(name = "CREATED_BY", length = 50, nullable = false)
	private String createdBy;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
	private Date createdDate;

}
