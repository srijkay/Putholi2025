package com.newrta.putholi.api.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.PaymentInstruction;
import com.newrta.putholi.api.model.CheckoutResponseDTO;
import com.newrta.putholi.api.model.PaymentCheckoutDTO;
import com.newrta.putholi.api.service.PaymentService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/payment")
public class PaymentResource {

	/**
	 *
	 */
	@Value("${ccaavenue.response-url}")
	private String responseURL;

	/**
	 *
	 */
	@Value("${ccaavenue.portal-response-url}")
	private String portalResponseURL;

	/**
	 *
	 */
	@Value("${ccaavenue.cancel-url}")
	private String cancelURL;

	/**
	 *
	 */
	@Value("${ccaavenue.portal-cancel-url}")
	private String portalCancelURL;

	/**
	 * 
	 */
	private PaymentService paymentService;

	/**
	 * @param paymentService
	 */
	@Autowired
	public PaymentResource(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	/**
	 * @param authToken
	 * @param paymentCheckoutDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/checkout-redirect")
	public ResponseEntity<CheckoutResponseDTO> processCheckoutResponse(@RequestHeader("Authorization") String authToken,
			@RequestBody PaymentCheckoutDTO paymentCheckoutDTO) {
		log.info("PaymentResource-processCheckoutResponse");

		return paymentService.processCheckoutResponse(paymentCheckoutDTO);
	}

	/**
	 * @param encResp
	 */
	@CrossOrigin
	@PostMapping(value = "/handleccavenueresponse")
	public ResponseEntity<Void> processSucessResponse(@RequestParam("encResp") String encResp) {
		log.info("PaymentResource-handleccavenueresponse {}", encResp);

		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(responseURL + paymentService.processSucessResponse(encResp))).build();
	}

	/**
	 * @param encResp
	 */
	@CrossOrigin
	@PostMapping(value = "/response")
	public ResponseEntity<Void> processResponse(@RequestParam("encResp") String encResp) {
		log.info("PaymentResource-processResponse {}", encResp);

		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(portalResponseURL + paymentService.processSucessResponse(encResp))).build();
	}

	/**
	 * @param paymentId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{paymentid}")
	public PaymentInstruction findByPaymentId(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable("paymentid") String paymentId) {
		log.info("PaymentResource-findByOrderId {}", paymentId);

		return paymentService.findByPaymentId(loggedUser, paymentId);
	}

	/**
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/handlecancelpayment")
	public ResponseEntity<Void> processCancelPayment() {
		log.info("PaymentResource-processCancelPayment {}");

		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(cancelURL)).build();
	}

	/**
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/cancel")
	public ResponseEntity<Void> processCancel() {
		log.info("PaymentResource-processCancel {}");

		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(portalCancelURL)).build();
	}

}
