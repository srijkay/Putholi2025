package com.newrta.putholi.api.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserManagementDTO extends GenericSearchDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7378307452613342794L;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private String firstName;

	/**
	 * 
	 */
	private String emailId;

	/**
	 * 
	 */
	private String mobileNumber;

	/**
	 * 
	 */
	private List<String> userRole;

	/**
	 * 
	 */
	private List<String> roleCode;

	/**
	 * 
	 */
	private Date createdDate;

	/**
	 * 
	 */
	private String status;

	/**
	 * 
	 */
	private List<String> statusCode;

	/**
	 * 
	 */
	private boolean accountEnabled;

	/**
	 * 
	 */
	private String district;

	/**
	 * 
	 */
	private String updatedBy;

	/**
	 * 
	 */
	private String referredBy;

	/**
	 * 
	 */
	private Date fromDate;

	/**
	 * 
	 */
	private Date todate;

}
