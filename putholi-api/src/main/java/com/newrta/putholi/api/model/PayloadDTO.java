package com.newrta.putholi.api.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayloadDTO {

	/**
	 * 
	 */
	private String clientId;

	/**
	 * 
	 */
	private BigDecimal amount;

	/**
	 * 
	 */
	private String merchantId;

	/**
	 * 
	 */
	private String clientAuthToken;

	/**
	 * 
	 */
	private String clientAuthTokenExpiry;

	/**
	 * 
	 */
	private String environment;

	/**
	 * 
	 */
	private String lastName;

	/**
	 * 
	 */
	private String action;

	/**
	 * 
	 */
	private String customerId;

	/**
	 * 
	 */
	private String returnUrl;

	/**
	 * 
	 */
	private String currency;

	/**
	 * 
	 */
	private String firstName;

	/**
	 * 
	 */
	private String customerPhone;

	/**
	 * 
	 */
	private String customerEmail;

	/**
	 * 
	 */
	private String orderId;

	/**
	 * 
	 */
	private String description;

}
