package com.newrta.putholi.api.domain;

import java.io.Serializable;
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
@Entity
@Data
@Table(name = "VPUO_USER_MGMT")
@AllArgsConstructor
@NoArgsConstructor
public class UserManagement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7378307452613342794L;

	/**
	 * 
	 */
	@Id
	@Column(name = "USERNAME")
	private String userName;

	/**
	 * 
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;

	/**
	 * 
	 */
	@Column(name = "EMAIL_ID")
	private String emailId;

	/**
	 * 
	 */
	@Column(name = "mobile_number")
	private String mobileNumber;

	/**
	 * 
	 */
	@Column(name = "ROLE")
	private String role;

	/**
	 * 
	 */
	@Column(name = "ROLE_CODE")
	private String roleCode;

	/**
	 * 
	 */
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "STATUS")
	private String status;

	/**
	 * 
	 */
	@Column(name = "STATUS_CODE")
	private String statusCode;

	/**
	 * 
	 */
	@Column(name = "ACCOUNT_ENABLED")
	private boolean accountEnabled;

	/**
	 * 
	 */
	@Column(name = "DISTRICT")
	private String district;
	
	/**
	 * 
	 */
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
	@Column(name = "REFERRED_BY")
	private String referredBy;
}

