package com.newrta.putholi.api.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
public class DeoMasterCodeDetailsDTO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -4866396349374962734L;
	/**
	 * 
	 */

	private Long id;

	/**
	* 
	*/

	private String deoName;
	/**
	 * 
	 */

	private String district;

	/**
	 * 
	 */

	private String deoEmail;

	/**
	 * 
	 */

	private String city;
	/**
	 * 
	 */

	private String town;

	/**
	 * 
	 */

	private String phoneNumber;

	/**
	 * 
	 */

	private String deoOfficeAddress;

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

}
