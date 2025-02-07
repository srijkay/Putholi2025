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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "TPUO_EXPENSES_DETAILS")
public class ExpensesDetails {
	@Id
	@GeneratedValue(generator = "TPUO_EXPENSES_DETAILS_EXPENSES_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_EXPENSES_DETAILS_EXPENSES_ID_SEQ", sequenceName = "TPUO_EXPENSES_DETAILS_EXPENSES_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "EXPENSES_ID", length = 25)
	private Long expensesId;

	@Column(name = "VENDOR_NAME", length = 50, nullable = false)
	private String vendorName;

	@Column(name = "UTR_NUMBER", length = 50, nullable = true)
	private String utrNumber;

	@Column(name = "BANK_NAME", length = 5, nullable = false)
	private String bankName;

	@Column(name = "ACCOUNT_NUMBER", length = 50, nullable = false)
	private String accountNum;

	@Column(name = "IFSC_CODE", length = 50, nullable = false)
	private String ifscCode;

	@Column(name = "TRANSACTION_DATE", nullable = false)
	private Date transactionDate;
	
	@Column(name = "UTR_DATE", nullable = true)
	private Date utrDate;

	@Column(name = "AMOUNT", scale = 2, precision = 12, nullable = false)
	private BigDecimal amount;

	@Column(name = "ADDRESS", length = 255, nullable = false)
	private String address;

	@Column(name = "MOBILE_NUMBER", length = 12, nullable = false)
	private long mobileNumber;

	@Column(name = "CATEGORY", length = 7, nullable = false)
	private String category;

	@Column(name = "DESCRIPTION", length = 255, nullable = true)
	private String description;

	@Column(name = "STATUS", length = 10, nullable = false)
	private String status;

	@Column(name = "OTHER", length = 255, nullable = true)
	private String other;
	
	@Column(name = "REJECTED_REASON", length = 255, nullable = true)
	private String rejectedReason;

	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
	private Date createdDate;

	@Column(name = "LAST_MODIFIED_BY", length = 25, nullable = true)
	private String lastModifiedBy;

	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE", nullable = true, insertable = false, updatable = true)
	private Date lastModifiedDate;
}
