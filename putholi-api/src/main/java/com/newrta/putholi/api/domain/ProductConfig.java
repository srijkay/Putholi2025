package com.newrta.putholi.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Table(name = "TSYS_PRODUCT_CONFIG")
@Data
@NoArgsConstructor
public class ProductConfig {

	/**
	 * 
	 */
	@Id
	@Column(name = "COMPANY_NAME", length = 255, nullable = false)
	private String companyName;

	/**
	 * 
	 */
	@Column(name = "COMPANY_CODE", length = 25, nullable = false)
	private String companyCode;

	/**
	 * 
	 */
	@Column(name = "COMPANY_SLOGAN", length = 100, nullable = false)
	private String companySlogan;

	/**
	 * 
	 */
	@Column(name = "COMPANY_ADDRESS", length = 255, nullable = false)
	private String companyAddress;

	/**
	 * 
	 */
	@Column(name = "CITY", length = 100, nullable = false)
	private String city;

	/**
	 * 
	 */
	@Column(name = "PROVINCE", length = 100, nullable = false)
	private String province;

	/**
	 * 
	 */
	@Column(name = "POSTAL_CODE", length = 6, nullable = false)
	private long postalCode;

	/**
	 * 
	 */
	@Column(name = "COMPANY_PHONE", length = 15, nullable = false)
	private String companyPhone;

	/**
	 * 
	 */
	@Column(name = "COMPANY_PHONE_2", length = 15, nullable = false)
	private String companyPhone2;

	/**
	 * 
	 */
	@Column(name = "COMPANY_EMAIL", length = 255, nullable = false)
	private String companyEmail;

	/**
	 * 
	 */
	@Column(name = "FAX", nullable = false, length = 255)
	private String fax;

	/**
	 * 
	 */
	@Column(name = "website", nullable = false, length = 255)
	private String webSite;

	/**
	 * 
	 */
	@Column(name = "CPMPANY_REGNO", nullable = false, length = 205)
	private String companyRegNo;

	/**
	 * 
	 */
	@Column(name = "TAX_IDENTIFICATION_NO", nullable = false, length = 20)
	private String taxIdentificationNo;

	/**
	 * 
	 */
	@Column(name = "TIMEZONE", nullable = false)
	private Date timezone;

	/**
	 * 
	 */
	@Column(name = "PRIMARY_CONTACT_NAME", nullable = false, length = 255)
	private String primaryContactName;

	/**
	 * 
	 */
	@Column(name = "PRIMARY_CONTACT_NO", length = 15, nullable = false)
	private String primaryContactNumber;

	/**
	 * 
	 */
	@Column(name = "EMAIL_ID", length = 255, nullable = false)
	private String emailId;

	/**
	 * 
	 */
	@Transient
	private String companyLogo;

	/**
	 * 
	 */
	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	/**
	 * 
	 */
	@CreationTimestamp
	@Column(name = "CREATED_DATE", insertable = true, updatable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "LAST_MODIFIED_BY", length = 25, nullable = true)
	private String lastModifiedBy;

	/**
	 * 
	 */
	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE", nullable = true, insertable = false, updatable = true)
	private Date lastModifiedDate;

}
