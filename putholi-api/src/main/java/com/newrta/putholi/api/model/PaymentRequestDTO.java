package com.newrta.putholi.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PaymentRequestDTO {

	/**
	 * 
	 */
	@JsonProperty("order_id")
	private String orderId;

	/**
	 * 
	 */
	@JsonProperty("amount")
	private BigDecimal amount;

	/**
	 * 
	 */
	@JsonProperty("customer_id")
	private String customerId;

	/**
	 * 
	 */
	@JsonProperty("customer_email")
	private String customerEmail;

	/**
	 * 
	 */
	@JsonProperty("customer_phone")
	private String customerPhone;

	/**
	 * 
	 */
	@JsonProperty("payment_page_client_id")
	private String paymentPageClientId;

	/**
	 * 
	 */
	@JsonProperty("action")
	private String action;

	/**
	 * 
	 */
	@JsonProperty("return_url")
	private String returnUrl;

	/**
	 * 
	 */
	@JsonProperty("description")
	private String description;

	/**
	 * 
	 */
	@JsonProperty("first_name")
	private String firstName;

	/**
	 * 
	 */
	@JsonProperty("last_name")
	private String lastName;
	
	
	private long consolidateId;

}
