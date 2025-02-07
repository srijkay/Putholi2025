package com.newrta.putholi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class DonorInfoDTO {

	/**
	* 
	*/
	private String donorId;
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
	private String emailId;
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
	private String city;
	/**
	 * 
	 */
	private String country;
	/**
	 * 
	 */
	private String phoneNumber;
	/**
	 * 
	 */
	private String designation;
	/**
	 * 
	 */
	private String organizationName;
	/**
	 * 
	 */
	private String organizationAddress;
	/**
	 * 
	 */
	private String organizationType;
	/**
	 * 
	 */
	private String entityType;
	/**
	 * 
	 */
	private String registrationNumber;
	/**
	 * 
	 */
	private String branches;
	/**
	 * 
	 */
	private String moneyTransfer;

	/**
	 * 
	 */
	private String active;
	/**
	 *
	 */
	private String orderId;
	/**
	 * 
	 */
	private String address;

	/**
	 * 
	 */
	private String panNumber;

	/**
	 * 
	 */
	private String registerDonor;
	/**
	 * 
	 */
	private String createdBy;
	/**
	 * 
	 */
	private String lastModifiedBy;
}
