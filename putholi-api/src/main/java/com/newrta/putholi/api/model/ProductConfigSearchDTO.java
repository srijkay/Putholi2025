package com.newrta.putholi.api.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfigSearchDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052034406730965996L;

	/**
	 * 
	 */
	private Long companyId;

	/**
	 * 
	 */
	private String companyName;

	/**
	 * 
	 */
	private String companyCode;

	/**
	 * 
	 */
	private String companySlogan;

	/**
	 * 
	 */
	private String companyAddress;

	/**
	 * 
	 */
	private String city;

	/**
	 * 
	 */
	private String province;

	/**
	 * 
	 */
	private long postalCode;

	/**
	 * 
	 */
	private String companyPhone;

	/**
	 * 
	 */
	private String companyPhone2;

	/**
	 * 
	 */
	private String companyEmail;

	/**
	 * 
	 */
	private String fax;

	/**
	 * 
	 */
	private String webSite;

	/**
	 * 
	 */
	private String companyRegNo;

	/**
	 * 
	 */
	private String taxIdentificationNo;

	/**
	 * 
	 */
	private Date timezone;

	/**
	 * 
	 */
	private String primaryContactName;

	/**
	 * 
	 */
	private String primaryContactNumber;

	/**
	 * 
	 */
	private String emailId;


}
