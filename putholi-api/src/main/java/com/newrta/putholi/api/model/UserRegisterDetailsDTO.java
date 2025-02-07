package com.newrta.putholi.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
public class UserRegisterDetailsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8696306525173841470L;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private String secretKey;

	/**
	 * 
	 */
	@JsonIgnoreProperties
	private String confirmPassword;

	/**
	 * 
	 */
	private String firstName;

	/**
	 * 
	 */
	private String lastName;

	/**
	 * 
	 */
	private String middleName;

	/**
	 * 
	 */
	private String gender;

	/**
	 * 
	 */
	private String mobileNumber;

	/**
	 * 
	 */
	private String emailId;

	/**
	 * 
	 */
	private String addressLine1;

	/**
	 * 
	 */
	private String addressLine2;

	/**
	 * 
	 */
	private String locality;

	/**
	 * 
	 */
	private String city;

	/**
	 * 
	 */
	private String district;

	/**
	 * 
	 */
	private String state;

	/**
	 * 
	 */
	private String country;

	/**
	 * 
	 */
	private String pincode;

	/**
	 * 
	 */
	private String identificationNumber;

	/**
	 * 
	 */

	private BigDecimal registrationFee;

	/**
	 * 
	 */
	private String orderId;

	/**
	 * 
	 */
	private String selectRole;
	/**
	 * 
	 */
	private String role;

	/**
	 * 
	 */
	private String status;

	/**
	 * 
	 */

	private String referredBy;

	/**
	 * 
	 */
	private String active;

	/**
	 * 
	 */
	private String createdBy;

	/**
	 * 
	 */
	private String updatedBy;

	/**
	 * 
	 */
	private String isReviewed;

	/**
	 * 
	 */
	private String changeRequestRole;

}
