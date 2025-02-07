package com.newrta.putholi.api.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
public class DeoMasterCodeTypeDTO implements Serializable {

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

	private String city;

	/**
	 * 
	 */

	private String phoneNumber;

	/**
	 * 
	 */

	private String deoEmail;

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
