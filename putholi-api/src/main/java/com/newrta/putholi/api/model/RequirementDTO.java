package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class RequirementDTO {

	private Long requirementId;

	private String requirementType;

	private String assetType;

	private String assetName;

	private long quantity;

	private String comments;

	private long estimateAmount;

	private String createdBy;

	private String reqStatus;
	
	private String timePeriod;

	private String lastModifiedBy;

	private String active;
	
	private ConsolidateRefDTO consolidateRef;
		
}
