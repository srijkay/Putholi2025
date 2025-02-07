package com.newrta.putholi.api.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @author NEWRTA SOLUTIONS
 *
 */

/**
 * @author HP
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TPUO_FEATURE_MANAGEMENT")
public class FeatureManagement {

	@Id
	@GeneratedValue(generator = "TPUO_FEATUREMANAGEMENT_FEATURE_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_FEATUREMANAGEMENT_FEATURE_ID_SEQ", sequenceName = "TPUO_FEATUREMANAGEMENT_FEATURE_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "FEATURE_ID", length = 25)
	private Long featureId;

	@Column(name = "FEATURE_Name", length = 255, nullable = false)
	private String featureName;

	@Column(name = "MODULE", length = 3, nullable = false)
	private String module;

	@Column(name = "STATUS", length = 3, nullable = false)
	private String status;

	@Column(name = "FILTER_TYPE", length = 3, nullable = false)
	private String filterType;

	@Column(name = "USER_NAME", length = 25, nullable = true)
	private String userName;

	@Column(name = "ROLE", length = 3, nullable = true)
	private String role;

	@Column(name = "ORGANISATION", length = 3, nullable = true)
	private String organisation;

	@Column(name = "IS_APPROVAL_APPLICABLE", length = 3, nullable = false)
	private String isApprovalApplicable;

	@Column(name = "IS_AUTO_APPROVAL", length = 3, nullable = true)
	private String isAutoApproval;

	@Column(name = "LEVEL_OF_APPROVAL", length = 3, nullable = true)
	private String levelofApproval;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "featureManagement")
	private List<ApproverLevels> approverLevels;

	/**
	 * @param featureId
	 * @param featureName
	 * @param module
	 * @param filterType
	 */
	public FeatureManagement(Long featureId, String featureName, String module, String filterType, String status) {
		super();
		this.featureId = featureId;
		this.featureName = featureName;
		this.module = module;
		this.filterType = filterType;
		this.status = status;
	}
	
	
	
	
	

}
