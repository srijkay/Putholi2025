package com.newrta.putholi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SDKPayloadDTO {

	/**
	 * 
	 */
	private String requestId;

	/**
	 * 
	 */
	private String service;

	/**
	 * 
	 */
	@JsonProperty("payload")
	private PayloadDTO payloadDTO;
}
