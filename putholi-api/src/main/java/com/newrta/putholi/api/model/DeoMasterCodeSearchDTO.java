package com.newrta.putholi.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class DeoMasterCodeSearchDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866396349374962734L;
	
	/**
	 * 
	 */

	private String deoName;

	/**
	 * 
	 */

	private String district;
	
	/**
	 * 
	 */
	
	private String town;


	/**
	 * 
	 */

	private String active;

}
