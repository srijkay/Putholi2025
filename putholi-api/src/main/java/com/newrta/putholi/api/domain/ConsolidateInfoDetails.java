package com.newrta.putholi.api.domain;

import java.util.Date;

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
@Table(name = "vpuo_consolidate_details")
@AllArgsConstructor
public class ConsolidateInfoDetails {

	@Id
	@Column(name = "CONSOLIDATE_ID")
	private Long consolidateId;

	@Column(name = "NO_OF_REQUIREMENTS")
	private long noOfRequirements;

	@Column(name = "SCHOOL_INFO_ID")
	private Long schoolInfoId;

	@Column(name = "SCHOOL_REG_NO")
	private String schoolRegNo;

	@Column(name = "SCHOOL_TYPE")
	private String schoolType;

	@Column(name = "PRI_NUM")
	private String primaryContact;

	@Column(name = "LOCALITY")
	private String locality;

	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CONSOLIDATE_STATUS_CODE")
	private String consolidateStatusCode;

	@Column(name = "VOLUNTEER_NAME")
	private String volunteerName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "SCHOOL_STATUS")
	private String schoolStatus;

	@Column(name = "SCHOOL_STATUS_CODE")
	private String schoolStatusCode;
	
	@Column(name = "ACTIVE")
	private String active;

}
