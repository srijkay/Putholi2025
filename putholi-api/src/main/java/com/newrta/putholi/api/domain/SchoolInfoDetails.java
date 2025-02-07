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
@AllArgsConstructor
@Entity
@Table(name = "vpuo_school_details")
public class SchoolInfoDetails {

	@Id
	@Column(name = "SCHOOL_INFO_ID")
	private Long schoolInfoId;

	@Column(name = "SCHOOL_REG_NO")
	private String schoolRegNo;

	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "SCHOOL_TYPE")
	private String schoolType;

	@Column(name = "PRI_NUM")
	private String primaryContact;

	@Column(name = "SCHOOL_STATUS")
	private String schoolStatus;

	@Column(name = "SCHOOL_STATUS_CODE")
	private String schoolStatusCode;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "LOCALITY")
	private String locality;

	@Column(name = "DISTRICT")
	private String district;

	@Column(name = "ACTIVE")
	private String active;

}
