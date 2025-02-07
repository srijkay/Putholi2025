package com.newrta.putholi.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@NoArgsConstructor
@Data
public class PaymentCheckoutDTO {

	@JsonProperty("tid")
	private String transactionId;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("username")
	private String username;

	@JsonProperty("payment_type")
	private String paymentType;

	@JsonProperty("consolidate_id")
	private Long consolidateId;

	@JsonProperty("payment_option")
	private String paymentOption;
}
