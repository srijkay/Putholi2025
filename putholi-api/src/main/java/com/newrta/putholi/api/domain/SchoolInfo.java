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

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "TPUO_SCHOOLINFO")
public class SchoolInfo {

	@Id
	@GeneratedValue(generator = "TPUO_SCHOOLINFO_SCHOOL_INFO_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_SCHOOLINFO_SCHOOL_INFO_ID_SEQ", sequenceName = "TPUO_SCHOOLINFO_SCHOOL_INFO_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "SCHOOL_INFO_ID", length = 25)
	private Long schoolInfoId;

	@Column(name = "SCHOOL_NAME", length = 255, nullable = false)
	private String schoolName;

	@Column(name = "SCHOOL_TYPE", length = 3, nullable = false)
	private String schoolType;

	@Column(name = "SCHOOL_REG_NO", length = 25, nullable = false, unique = true)
	private String schoolRegNo;

	@Column(name = "NO_OF_STUDENTS", length = 5, nullable = false)
	private int noOfStudents;

	@Column(name = "NO_OF_TEACHERS", length = 3, nullable = false)
	private int noOfTeachers;

	@Column(name = "EDUCATIONAL_DISTRICT", length = 20, nullable = false)
	private String educationalDistrict;

	@Column(name = "SCHOOL_STATUS", length = 6, nullable = false)
	private String schoolStatus;

	@Column(name = "VOLUNTEER_NAME", length = 255, nullable = true)
	private String volunteerName;
	
	@Column(name = "SCHOOL_DESCRIPTION", length = 255, nullable = false)
	private String schoolDescription;
	
	@Column(name = "SCHOOL_URL", length= 255, nullable = false)
	private String schoolUrl;
	
	@Column(name = "COMMENTS", length = 255, nullable = true)
	private String comments;
	
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

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "schoolInfo")
	private AddressInfo addressInfo;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "schoolInfo")
	private ContactInfo contactsInfo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolInfo")
	private List<ConsolidateRefInfo> consolidateRefInfo;
	
	



}
