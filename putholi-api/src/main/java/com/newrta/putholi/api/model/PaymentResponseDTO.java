package com.newrta.putholi.api.model;

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
public class PaymentResponseDTO {

	/**
	 *
	 */
	@JsonProperty("status_message")
	private String statusMessage;

	/**
	 *
	 */
	@JsonProperty("tracking_id")
	private String trackingId;

	/**
	 *
	 */
	@JsonProperty("bank_ref_no")
	private String bankRefNo;

	/**
	 *
	 */
	@JsonProperty("billing_state")
	private String billingState;

	/**
	 *
	 */
	@JsonProperty("failure_message")
	private String failureMessage;

	/**
	 *
	 */
	@JsonProperty("billing_email")
	private String billingEmail;

	/**
	 *
	 */
	@JsonProperty("amount")
	private String amountPaid;

}
