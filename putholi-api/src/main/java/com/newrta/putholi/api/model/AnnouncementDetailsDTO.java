package com.newrta.putholi.api.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AnnouncementDetailsDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -958255245583148962L;

	/**
	 * 
	 */
	private Long announcementId;
	
	/**
	 * 
	 */
	private String announcementTitle;

	/**
	 * 
	 */
	private String announcementRole;

	/**
	 * 
	 */
	private String announcementDescription;

	/**
	 * 
	 */
	private Date effectiveDate;

	/**
	 * 
	 */
	private Date expiryDate;

	/**
	 * 
	 */
	private String createdBy;

	/**
	 * 
	 */
	private String lastModifiedBy;

}
