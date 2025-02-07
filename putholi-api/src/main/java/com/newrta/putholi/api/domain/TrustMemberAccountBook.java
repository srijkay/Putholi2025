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
@Table(name = "TPUO_TRUST_MEMBER_ACCOUNT_BOOK")
public class TrustMemberAccountBook {

	@Id
	@GeneratedValue(generator = "TPUO_TRUST_MEMBER_ACCOUNT_BOOK_TRUST_BOOK_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_TRUST_MEMBER_ACCOUNT_BOOK_TRUST_BOOK_ID_SEQ", sequenceName = "TPUO_TRUST_MEMBER_ACCOUNT_BOOK_TRUST_BOOK_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "TRUST_BOOK_ID", length = 25)
	private Long trustBookId;

	@Column(name = "FEE_TYPE", length = 3, nullable = false)
	private String feeType;

	@CreationTimestamp
	@Column(name = "PAYMENT_DATE", nullable = false, insertable = true, updatable = false)
	private Date paymentDate;

	@Column(name = "DESCRIPTION ", length = 500, nullable = false)
	private String description;

	@Column(name = "AMOUNT", scale = 2, precision = 12, nullable = false)
	private BigDecimal amount;

	@Column(name = "BALANCE_AMOUNT", scale = 2, precision = 25, nullable = true)
	private BigDecimal balanceAmount;

	@Column(name = "PAYMENT_ID ", length = 25, nullable = true)
	private String paymentId;

	@Column(name = "REMARKS", length = 500, nullable = true)
	private String remarks;

	@Column(name = "DONOR_ID", length = 50, nullable = true)
	private String donorId;

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
