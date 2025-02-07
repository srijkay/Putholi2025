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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "TPUO_CONSOLIDATE_REF")
public class ConsolidateRefInfo {

	@Id
	@GeneratedValue(generator = "TPUO_CONSOLIDATE_REF_CONSOLIDATE_ID_REQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_CONSOLIDATE_REF_CONSOLIDATE_ID_REQ", sequenceName = "TPUO_CONSOLIDATE_REF_CONSOLIDATE_ID_REQ", initialValue = 1, allocationSize = 1)
	@Column(name = "CONSOLIDATE_ID", length = 25)
	private Long consolidateId;

	@Column(name = "NO_OF_REQUIREMENTS", length = 20, nullable = false)
	private long noOfRequirements;

	@Column(name = "STATUS", length = 6, nullable = false)
	private String status;

	@Column(name = "ACTIVE", length = 2, nullable = false)
	private String active;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "consolidateRef", fetch = FetchType.LAZY)
	private List<RequirementInfo> requirementInfo;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SchoolInfo.class)
	@JsonBackReference
	@JoinColumn(name = "SCHOOL_INFO_ID", referencedColumnName = "SCHOOL_INFO_ID")
	private SchoolInfo schoolInfo;

	/**
	 * @param consolidateId
	 * @param noOfRequirements
	 * @param totalAmount
	 * @param submittedDate
	 * @param status
	 */

	public ConsolidateRefInfo(Long consolidateId, long noOfRequirements, String status, Date createdDate) {
		super();
		this.consolidateId = consolidateId;
		this.noOfRequirements = noOfRequirements;
		this.status = status;
		this.createdDate = createdDate;
	}

}
