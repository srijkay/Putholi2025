package com.newrta.putholi.api.model;

import java.util.Date;
import java.util.List;

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
public class ConsolidateSearchDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201988406059757768L;

	private Long consolidateId;

	private long noOfRequirements;

	private Long schoolInfoId;

	private String schoolRegNo;

	private String schoolType;

	private String primaryContact;

	private String locality;

	private String schoolName;

	private Date createdDate;

	private String status;

	private List<String> consolidateStatusCode;

	private String volunteerName;

	private String createdBy;

	private String schoolStatus;

	private String schoolStatusCode;

	private String active;
}
