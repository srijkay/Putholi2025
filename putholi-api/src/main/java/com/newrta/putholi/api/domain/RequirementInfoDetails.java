package com.newrta.putholi.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "vpuo_requirement_details")
public class RequirementInfoDetails {

	@Id
	@Column(name = "REQUIREMENT_ID")
	private Long requirementId;

	@Column(name = "SCHOOL_INFO_ID")
	private Long schoolInfoId;

	@Column(name = "SCHOOL_REG_NO")
	private String schoolRegNo;

	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "LOCALITY")
	private String locality;

	@Column(name = "DISTRICT")
	private String district;

	@Column(name = "CONSOLIDATE_ID")
	private Long consolidateId;

	@Column(name = "REQUIREMENT_TYPE")
	private String requirementType;

	@Column(name = "REQ_STATUS_CODE")
	private String reqStatusCode;

	@Column(name = "REQ_STATUS")
	private String reqStatus;

	@Column(name = "VOLUNTEER_NAME")
	private String volunteerName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CONSOLIDATE_STATUS")
	private String consolidateStatus;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "ASSET_TYPE")
	private String assetType;

	@Column(name = "ASSET_NAME")
	private String assetName;

}
