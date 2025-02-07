package com.newrta.putholi.api.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdminDashboardDTO {

	/**
	 * 
	 */
	private BigDecimal totalAmount;

	/**
	 * 
	 */
	private BigDecimal collectedAmount;

	/**
	 * 
	 */
	private BigDecimal trustFund;
}
