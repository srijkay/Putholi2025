package com.newrta.putholi.api.service;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.PaymentInstruction;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.CheckoutResponseDTO;
import com.newrta.putholi.api.model.PaymentCheckoutDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface PaymentService {

	/**
	 * @param paymentCheckoutDTO
	 * @return
	 */
	public ResponseEntity<CheckoutResponseDTO> processCheckoutResponse(PaymentCheckoutDTO paymentCheckoutDTO);

	/**
	 * @param locale
	 * @param paymentCheckoutDTO
	 * @return
	 */
	PaymentInstruction paymentContribution(String locale, PaymentCheckoutDTO paymentCheckoutDTO);

	/**
	 * @param encResp
	 * @return
	 */
	String processSucessResponse(String encResp);

	/**
	 *
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @param paymentId
	 * @return
	 */
	ResponseEntity<ApiResultDTO> donorTrackingIdEmail(String authorization, String emailId, String address,
													  String paymentId);


	/**
	 * @param loggedUser
	 * @param paymentId
	 * @return
	 */
	PaymentInstruction findByPaymentId(String loggedUser, String paymentId);
}
