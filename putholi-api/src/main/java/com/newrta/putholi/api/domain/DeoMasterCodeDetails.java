package com.newrta.putholi.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Table(name = "tsys_deo_master_code_details")
@Data
@NoArgsConstructor
public class DeoMasterCodeDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866396349374962734L;
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "deo_master_code_details_seq")
	@SequenceGenerator(name = "deo_master_code_details_seq", sequenceName = "deo_master_code_details_seq", allocationSize = 1)
	@Column(name = "id", length = 25)
	private Long id;

	/**
	* 
	*/

	@Column(name = "DEO_NAME", length = 25, nullable = false)
	private String deoName;
	/**
	 * 
	 */

	@Column(name = "DISTRICT", length = 20, nullable = false)
	private String district;

	/**
	 * 
	 */

	@Column(name = "DEO_EMAIL", length = 255, nullable = false)
	private String deoEmail;

	/**
	 * 
	 */

	@Column(name = "CITY", length = 255, nullable = false)
	private String city;

	/**
	 * 
	 */

	@Column(name = "PHONE_NUMBER", length = 25, nullable = false)
	private String phoneNumber;

	/**
	 * 
	 */

	@Column(name = "DEO_OFFICE_ADDRESS", length = 255, nullable = false)
	private String deoOfficeAddress;

	/**
	 * 
	 */

	@Column(name = "TOWN", length = 255, nullable = false)
	private String town;

	/**
	 * 
	 */

	@Column(name = "Active", length = 3, nullable = false)
	private String active;

	/**
	* 
	*/
	@Column(name = "created_by", length = 20, nullable = false)
	private String createdBy;
	/**
	 * 
	 */
	@CreationTimestamp
	@Column(name = "created_date", insertable = true, updatable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "updated_by", length = 20, nullable = true)
	private String updatedBy;

	/**
	 * 
	 */
	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false, updatable = true)
	private Date updatedDate;

	/**
	 * 
	 * @param id
	 * @param deoName
	 * @param district
	 * @param deoEmail
	 * @param city
	 * @param phoneNumber
	 * @param deoOfficeAddress
	 * @param active
	 */
	public DeoMasterCodeDetails(Long id, String deoName, String district, String town, String active) {
		super();
		this.id = id;
		this.deoName = deoName;
		this.district = district;
		this.town = town;
		this.active = active;
	}

}
