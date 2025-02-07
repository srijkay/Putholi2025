package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class ApplicationConfigDTO {

	/**
	 * 
	 */
	private Long configId;

	/**
	 * 
	 */
	private String configFor;

	/**
	 * 
	 */
	private String configValue;

	/**
	 * 
	 */
	private String module;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private String createdBy;

	/**
	 * 
	 */
	private String lastModifiedBy;

}
