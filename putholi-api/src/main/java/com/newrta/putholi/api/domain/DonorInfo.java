package com.newrta.putholi.api.domain;

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
@Data
@NoArgsConstructor
@Entity
@Table(name = "TPUO_DONORINFO")
public class DonorInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TPUO_DONORINFO_DONOR_ID_SEQ")
	@SequenceGenerator(name = "TPUO_DONORINFO_DONOR_ID_SEQ", sequenceName = "TPUO_DONORINFO_DONOR_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "DONOR_ID", length = 25)
	private Long donorId;

	@Column(name = "FIRST_NAME", length = 250, nullable = true)
	private String firstName;

	@Column(name = "LAST_NAME", length = 50, nullable = true)
	private String lastName;

	@Column(name = "EMAIL_ID", length = 50, nullable = true, unique = true)
	private String emailId;

	@Column(name = "PASSWORD", length = 60)
	private String secretKey;

	@Column(name = "CITY", length = 255, nullable = true)
	private String city;

	@Column(name = "ADDRESS", length = 250, nullable = true)
	private String address;
	private String userType;

	@Column(name = "COUNTRY", length = 3, nullable = true)
	private String country;

	@Column(name = "PHONE_NUMBER", length = 25, nullable = true)
	private String phoneNumber;

	@Column(name = "DESIGNATION", length = 25, nullable = true)
	private String designation;

	@Column(name = "ORGANIZATION_NAME", length = 25, nullable = true)
	private String organizationName;

	@Column(name = "ORGANIZATION_ADDRESS", length = 250, nullable = true)
	private String organizationAddress;

	@Column(name = "ORGANIZATION_TYPE", length = 3, nullable = true)
	private String organizationType;

	@Column(name = "ENTITY_TYPE", length = 3, nullable = true)
	private String entityType;

	@Column(name = "REGN_NO", length = 25, nullable = true)
	private String registrationNumber;

	@Column(name = "BRANCHES", length = 3, nullable = true)
	private String branches;

	@Column(name = "MONEY_TRANSFER", length = 3, nullable = true)
	private String moneyTransfer;

	@Column(name = "ACTIVE", length = 1, nullable = true)
	private String active;

	@Column(name = "ORDER_ID", nullable = true, unique = true)
	private String orderId;
	
	@Column(name = "PAN_NUMBER", length = 50, nullable = true)
	private String panNumber;

	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
	private Date createdDate;

	@Column(name = "LAST_MODIFIED_BY", length = 25, nullable = true)
	private String lastModifiedBy;

	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE", nullable = true, insertable = false, updatable = true)
	private Date lastModifiedDate;

}
