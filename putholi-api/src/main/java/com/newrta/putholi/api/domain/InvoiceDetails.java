package com.newrta.putholi.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "TPUO_INVOICE_INFO")
public class InvoiceDetails {

	@Id
	@GeneratedValue(generator = "TPUO_INVOICE_INFO_INVOICE_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_INVOICE_INFO_INVOICE_ID_SEQ", sequenceName = "TPUO_INVOICE_INFO_INVOICE_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "INVOICE_ID", length = 25)
	private Long invoiceId;

	@Column(name = "COMPANY_NAME", length = 50, nullable = false)
	private String companyName;

	@Column(name = "ADDRESS", length = 255, nullable = false)
	private String address;

	@Column(name = "STREET", length = 50, nullable = false)
	private String street;

	@Column(name = "CITY", length = 50, nullable = false)
	private String city;

	@Column(name = "PHONE_NUMBER", length = 13, nullable = false)
	private long phoneNumber;

	@Column(name = "PINCODE", length = 6, nullable = false)
	private long pincode;

	@Column(name = "COMMENTS", length = 255, nullable = false)
	private String comments;

	@Column(name = "ACCOUNT_TYPE", length = 2, nullable = false)
	private String accountType;

	@Column(name = "INVOICE_PREPARED_BY", length = 25, nullable = false)
	private String invoicePreparedBy;

	@Column(name = "WORK_STATUS", length = 2, nullable = false)
	private String workStatus;

	@Column(name = "DISCOUNT_DETAILS", length = 100, nullable = false)
	private String discountDetails;

	@Column(name = "ITEM_DESCRIPTION", length = 255, nullable = false)
	private String itemDescription;

	@Column(name = "INVOICE_DATE", nullable = false)
	private Date invoiceDate;

	@Column(name = "QUANTITY", length = 12, nullable = false)
	private long quantity;

	@Column(name = "QUOTED_UNIT_PRICE", scale = 2, precision = 12, nullable = false)
	private BigDecimal quotedUnitPrice;

	@Column(name = "QUOTED_TAX", scale = 2, precision = 12, nullable = false)
	private BigDecimal quotedTax;

	@Column(name = "QUOTED_SHIPPING_COST", scale = 2, precision = 12, nullable = false)
	private BigDecimal quotedShippingCost;

	@Column(name = "QUOTED_AMOUNT", scale = 2, precision = 12, nullable = false)
	private BigDecimal quotedAmount;
	
	@Column(name = "INVOICE_UNIT_PRICE", scale = 2, precision = 12, nullable = true)
	private BigDecimal invoiceUnitPrice;

	@Column(name = "INVOICE_TAX", scale = 2, precision = 12, nullable = false)
	private BigDecimal invoiceTax;

	@Column(name = "INVOICE_SHIPPING_COST", scale = 2, precision = 12, nullable = false)
	private BigDecimal invoiceShippingCost;

	@Column(name = "INVOICE_AMOUNT", scale = 2, precision = 12, nullable = false)
	private BigDecimal invoiceAmount;

	@Column(name = "BANK_NAME", length = 5, nullable = false)
	private String bankName;

	@Column(name = "VENDOR_CODE", length = 50, nullable = false)
	private String vendorCode;

	@Column(name = "IFSC_CODE", length = 15, nullable = false)
	private String ifscCode;

	@Column(name = "PAYMENT_MODE", length = 4, nullable = false)
	private String paymentMode;

	@Column(name = "ACCOUNT_NUMBER", length = 50, nullable = false)
	private String accountNumber;

	@Column(name = "INVOICE_STATUS", length = 6, nullable = false)
	private String invoiceStatus;
	
	@Column(name = "UTR_NUMBER", length = 50, nullable = true)
	private String utrNumber;

	@Column(name = "UTR_DATE", nullable = true)
	private Date utrDate;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RequirementInfo.class)
	@JsonBackReference
	@JoinColumn(name = "REQUIREMENT_ID", referencedColumnName = "REQUIREMENT_ID", nullable = false)
	private RequirementInfo requirementDetails;

}
