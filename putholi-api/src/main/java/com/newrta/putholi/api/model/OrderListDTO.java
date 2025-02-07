package com.newrta.putholi.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Newrta Solutions
 *
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderListDTO {

	/**
	 * 
	 */
	@JsonProperty("reference_no")
	private String referenceNo;

	/**
	 * 
	 */
	@JsonProperty("amount")
	private BigDecimal amount;
	
	

}
