package com.newrta.putholi.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Data
@Table(name = "USER_REGISTRATION_DETAILS")
public class UserRegisterDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7316820205544707289L;

	/**
	 * 
	 */
	@Id
	@Column(name = "USERNAME", length = 25, nullable = false)
	private String userName;

	/**
	 * 
	 */
	@Column(name = "FIRST_NAME", length = 250, nullable = false)
	private String firstName;

	/**
	 * 
	 */
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;

	/**
	 * 
	 */
	@Column(name = "MIDDLE_NAME", length = 60, nullable = true)
	private String middleName;

	/**
	 * 
	 */
	@Column(name = "GENDER", length = 1, nullable = false)
	private String gender;

	/**
	 * 
	 */
	@Column(name = "MOBILE_NUMBER", length = 25, nullable = true)
	private String mobileNumber;

	/**
	 * 
	 */
	@Column(name = "EMAIL_ID", length = 50, nullable = false, unique = true)
	private String emailId;

	/**
	 * 
	 */
	@Column(name = "ADDRESS_LINE1", length = 255, nullable = false)
	private String addressLine1;

	/**
	 * 
	 */
	@Column(name = "ADDRESS_LINE2", length = 255, nullable = true)
	private String addressLine2;

	/**
	 * 
	 */
	@Column(name = "LOCALITY", length = 150, nullable = true)
	private String locality;

	/**
	 * 
	 */
	@Column(name = "CITY", length = 150, nullable = false)
	private String city;

	/**
	 * 
	 */
	@Column(name = "DISTRICT", length = 150, nullable = false)
	private String district;

	/**
	 * 
	 */
	@Column(name = "STATE", length = 3, nullable = false)
	private String state;

	/**
	 * 
	 */
	@Column(name = "COUNTRY", length = 3, nullable = false)
	private String country;

	/**
	 * 
	 */
	@Column(name = "PINCODE", length = 6, nullable = false)
	private String pincode;

	/**
	 * 
	 */
	@Column(name = "IDENTIFICATION_NO", length = 12, nullable = true)
	private String identificationNumber;
	/**
	 * 
	 */

	@Column(name = "SELECT_ROLE", length = 6, nullable = true)
	private String selectRole;
	/**
	 * 
	 */

	@Column(name = "REGISTRATION_FEE", nullable = true)
	private BigDecimal registrationFee;

	/**
	 * 
	 */
	@Column(name = "ORDER_ID", nullable = true, unique = true)
	private String orderId;

	/**
	 * 
	 */
	@Column(name = "REFERRED_BY", length = 25, nullable = true)
	private String referredBy;

	/**
	 * 
	 */
	@Column(name = "ROLE", length = 10, nullable = false)
	private String role;

	/**
	 * 
	 */
	@Column(name = "STATUS", length = 6, nullable = false)
	private String status;

	/**
	 * 
	 */
	@Column(name = "ACTIVE", length = 1, nullable = false)
	private String active;

	/**
	 * 
	 */
	@Transient
	private String pic;

	/**
	 * 
	 */
	@CreationTimestamp
	@Column(name = "CREATED_DATE", insertable = true, updatable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "UPDATED_BY", length = 25, nullable = true)
	private String updatedBy;

	/**
	 * 
	 */
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false, updatable = true)
	private Date updatedDate;

	/**
	 * 
	 */
	@Column(name = "IS_REVIEWED", length = 4, nullable = true)
	private String isReviewed;

	/**
	 * 
	 */
	@Column(name = "CHANGE_REQUEST_ROLE", length = 10, nullable = true)
	private String changeRequestRole;

}
