package com.newrta.putholi.api.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@AllArgsConstructor
@Builder
public class ApiResultDTO {

	/**
	 * 
	 */
	private String apiStatusCode;

	/**
	 * 
	 */
	private String apiStatusDesc;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String refNo;

	/**
	 * 
	 */
	List<String> errorDetails;

	/**
	 * 
	 */
	private int failureCount;

	/**
	 * 
	 */
	private int successCount;

}
