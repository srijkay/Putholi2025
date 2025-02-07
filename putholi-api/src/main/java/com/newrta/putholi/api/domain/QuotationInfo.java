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
@Table(name = "TPUO_QUOTATION_INFO")
public class QuotationInfo {

	@Id
	@GeneratedValue(generator = "TPUO_QUOTATION_INFO_QUOTATION_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_QUOTATION_INFO_QUOTATION_ID_SEQ", sequenceName = "TPUO_QUOTATION_INFO_QUOTATION_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "QUOTATION_ID", length = 25)
	private Long quotationId;

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

	@Column(name = "QUOTATION_PREPARED_BY", length = 255, nullable = false)
	private String quotationPreparedBy;

	@Column(name = "COMMENTS", length = 255, nullable = false)
	private String comments;

	@Column(name = "WARRANTY", length = 5, nullable = false)
	private String warranty;

	@Column(name = "QUOTATION_DATE", nullable = false)
	private Date quotationDate;

	@Column(name = "QUOTATION_VALID_DATE", nullable = false)
	private Date quotationValidDate;

	@Column(name = "DISCOUNT_DETAILS", length = 100, nullable = false)
	private String discountDetails;

	@Column(name = "ITEM_DESCRIPTION", length = 255, nullable = false)
	private String itemDescription;

	@Column(name = "QUANTITY", length = 12, nullable = false)
	private long quantity;

	@Column(name = "UNIT_PRICE", scale = 2, precision = 12, nullable = false)
	private BigDecimal unitPrice;

	@Column(name = "TAX", scale = 2, precision = 12, nullable = false)
	private BigDecimal tax;

	@Column(name = "SHIPPING_COST", scale = 2, precision = 12, nullable = false)
	private BigDecimal shippingCost;

	@Column(name = "TOTAL_AMOUNT", scale = 2, precision = 12, nullable = false)
	private BigDecimal totalAmount;

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
	
	
	@Column(name = "QUOTATE_STATUS", length = 6, nullable = false)
	private String quotateStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RequirementInfo.class)
	@JsonBackReference
	@JoinColumn(name = "REQUIREMENT_ID", referencedColumnName = "REQUIREMENT_ID", nullable = false)
	private RequirementInfo requirementInfo;

}
