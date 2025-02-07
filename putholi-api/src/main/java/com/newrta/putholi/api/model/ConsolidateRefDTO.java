package com.newrta.putholi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidateRefDTO {
	private Long consolidateId;

	private long noOfRequirements;

	private long totalAmount;

	private String status;

	private String createdBy;

	private String lastModifiedBy;

	private String active;

	private SchoolDetailsDTO schoolDetailsDTO;
}
