package com.newrta.putholi.api.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

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
@Table(name = "TPUO_REQUIREMENTS_INFO")
public class RequirementInfo {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "TPUO_REQUIREMENTS_INFO_REQUIREMENT_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_REQUIREMENTS_INFO_REQUIREMENT_ID_SEQ", sequenceName = "TPUO_REQUIREMENTS_INFO_REQUIREMENT_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "REQUIREMENT_ID", length = 25)
	private Long requirementId;

	@Column(name = "REQUIREMENT_TYPE", length = 25, nullable = false)
	private String requirementType;

	@Column(name = "ASSET_TYPE", length = 5, nullable = false)
	private String assetType;

	@Column(name = "ASSET_NAME", length = 25, nullable = false)
	private String assetName;

	@Column(name = "QUANTITY", length = 5, nullable = false)
	private long quantity;

	@Column(name = "COMMENTS", length = 255, nullable = false)
	private String comments;

	@Column(name = "REQ_STATUS", length = 6, nullable = false)
	private String reqStatus;

	@Column(name = "TIME_PERIOD", length = 6, nullable = true)
	private String timePeriod;

	@Column(name = "ACTIVE", length = 2, nullable = false)
	private String active;

	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	@Transient
	private String reqStatusDescription;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
	private Date createdDate;

	@Column(name = "LAST_MODIFIED_BY", length = 25, nullable = true)
	private String lastModifiedBy;

	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE", nullable = true, insertable = false, updatable = true)
	private Date lastModifiedDate;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ConsolidateRefInfo.class)
	@JsonBackReference
	@JoinColumn(name = "CONSOLIDATE_ID", referencedColumnName = "CONSOLIDATE_ID", nullable = false)
	private ConsolidateRefInfo consolidateRef;

	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "requirementInfo", fetch = FetchType.LAZY)
	private List<QuotationInfo> quotationInfo;

	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "requirementDetails", fetch = FetchType.LAZY)
	private List<InvoiceDetails> invoiceDetails;

	/**
	 * @param requirementId
	 * @param requirementType
	 * @param assetType
	 * @param assetName
	 * @param reqStatus
	 * @param consolidateRef
	 */
	public RequirementInfo(Long requirementId, String requirementType, String assetType, String assetName,
			String reqStatus, ConsolidateRefInfo consolidateRef) {
		super();
		this.requirementId = requirementId;
		this.requirementType = requirementType;
		this.assetType = assetType;
		this.assetName = assetName;
		this.reqStatus = reqStatus;
		this.consolidateRef = consolidateRef;
	}

	/**
	 * @param requirementType
	 * @param assetType
	 * @param assetName
	 * @param quantity
	 * @param reqStatus
	 */
	public RequirementInfo(String requirementType, String assetType, String assetName, long quantity,
			String reqStatus) {
		super();
		this.requirementType = requirementType;
		this.assetType = assetType;
		this.assetName = assetName;
		this.quantity = quantity;
		this.reqStatus = reqStatus;
	}

	/**
	 * @param requirementType
	 * @param assetType
	 * @param assetName
	 * @param quantity
	 * @param reqStatus
	 */
	public RequirementInfo(String requirementType, String assetType) {
		super();
		this.requirementType = requirementType;
		this.assetType = assetType;
	}

}
