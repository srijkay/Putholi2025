package com.newrta.putholi.api.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class CompletionofRequirementsDTO {

	private Long completionId;
	/**
	 * 
	 */
	private Date dateOfCompletion;

	/**
	 * 
	 */

	private String completionOfPayment;

	/**
	 * 
	 */

	private String remarks;

	/**
	 * 
	 */

	private String createdBy;

	/**
	 * 
	 */

	private Long requirementId;

	/**
	 * 
	 */
	private String beneficiaryName;

}
