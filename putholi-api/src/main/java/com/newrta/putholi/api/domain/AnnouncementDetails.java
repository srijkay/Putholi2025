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
@Entity
@Data
@NoArgsConstructor
@Table(name = "TPUO_ANNOUNCEMENT")
public class AnnouncementDetails {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "TPUO_ANNOUNCEMENT_ANNOUNCEMENT_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_ANNOUNCEMENT_ANNOUNCEMENT_ID_SEQ", sequenceName = "TPUO_ANNOUNCEMENT_ANNOUNCEMENT_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "ANNOUNCEMENT_ID", length = 25)
	private Long announcementId;

	/**
	 * 
	 */
	@Column(name = "ANNOUNCEMENT_TITLE", length = 255, nullable = true)
	private String announcementTitle;

	/**
	 * 
	 */
	@Column(name = "ANNOUNCEMENT_ROLE", length = 10, nullable = false)
	private String announcementRole;

	/**
	 * 
	 */
	@Column(name = "EFFECTIVE_DATE", nullable = false)
	private Date effectiveDate;

	/**
	 * 
	 */
	@Column(name = "EXPIRY_DATE", nullable = false)
	private Date expiryDate;
	/**
	 * 
	 */
	@Column(name = "ANNOUNCEMENT_DESCRIPTION", length = 255, nullable = false)
	private String announcementDescription;

	/**
	 * 
	 */
	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	/**
	 * 
	 */
	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
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

	/**
	 * @param announcementId
	 * @param announcementRole
	 * @param effectiveDate
	 * @param expiryDate
	 * @param announcementDescription
	 */
	public AnnouncementDetails(Long announcementId, String announcementRole, Date effectiveDate, Date expiryDate,
			String announcementDescription) {
		super();
		this.announcementId = announcementId;
		this.announcementRole = announcementRole;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.announcementDescription = announcementDescription;
	}
	
	

}
