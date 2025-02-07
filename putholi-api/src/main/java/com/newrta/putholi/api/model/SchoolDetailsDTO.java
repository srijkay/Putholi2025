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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SchoolDetailsDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6620152695683515086L;

	private Long schoolInfoId;

	private String schoolRegNo;

	private String schoolName;

	private String schoolType;

	private String schoolStatus;

	private List<String> schoolStatusCode;

	private String primaryContact;

	private String volunteerName;

	private String createdBy;
	
	private Date createdDate;

	private String locality;

	private String district;

	private String active;

	private Date fromDate;

	private Date todate;
}
