package com.newrta.putholi.api.model;

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
public class RequirementSearchDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009619850535019274L;

	private Long requirementId;

	private Long schoolInfoId;

	private String schoolRegNo;

	private String schoolName;

	private String locality;

	private String district;

	private Long consolidateId;

	private String requirementType;

	private List<String> reqStatusCode;

	private List<String> reqStatus;

	private String volunteerName;

	private String createdBy;

	private String active;

	private List<String> consolidateStatus;
	
	private String assetType;

	private String assetName;

}
