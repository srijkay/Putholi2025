package com.newrta.putholi.api.serviceimpl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ccavenue.security.AesCryptUtil;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.domain.PaymentInstruction;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.CheckoutResponseDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.PaymentCheckoutDTO;
import com.newrta.putholi.api.model.PaymentInstructionDTO;
import com.newrta.putholi.api.repository.PaymentInstructionRepositoty;
import com.newrta.putholi.api.service.DonorInfoService;
import com.newrta.putholi.api.service.PaymentService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonsUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class PaymentServiceImpl implements PaymentService {

	@Value("${mail.sent.from}")
	private String mailFrom;

	@Value("${caavenue.access-code}")
	private String accessCode;

	@Value("${ccaavenue.working-key}")
	private String workingKey;

	@Value("${ccaavenue.merchant-id}")
	private String merchantId;

	@Value("${ccaavenue.redirect-url}")
	private String redirectURL;

	@Value("${ccavenue.portal-redirect-url}")
	private String portalRedirectURL;

	@Value("${ccaavenue.cancel-payment-redirect-url}")
	private String cancelURL;

	@Value("${ccavenue.portal-cancel-payment-redirect-url}")
	private String portalCancelURL;

	private RestTemplate restTemplate;
	
	private JmsTemplate jmsTemplate;

	private PaymentInstructionRepositoty paymentRepo;

	private UserRegisterDetailsService userRegisterService;

	private DonorInfoService donorService;
	
	private ModelMapper modelMapper;

	/**
	 * @param modelMapper
	 * @param donorService
	 * @param userRegisterService
	 * @param paymentRepo
	 * @param restTemplate
	 * @param jmsTemplate
	 */
	@Autowired
	public PaymentServiceImpl(ModelMapper modelMapper, DonorInfoService donorService,
			UserRegisterDetailsService userRegisterService, PaymentInstructionRepositoty paymentRepo,
			RestTemplate restTemplate, JmsTemplate jmsTemplate) {
		this.modelMapper = modelMapper;
		this.donorService = donorService;
		this.userRegisterService = userRegisterService;
		this.paymentRepo = paymentRepo;
		this.restTemplate = restTemplate;
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public ResponseEntity<CheckoutResponseDTO> processCheckoutResponse(PaymentCheckoutDTO paymentCheckoutDTO) {
		log.info("-processCheckout-----------{}", paymentCheckoutDTO);

		// checking if orderId is null or not
		if (paymentCheckoutDTO.getOrderId() == null) {
			if (paymentCheckoutDTO.getConsolidateId() == null) {
				userRegisterService.updateOrderId(paymentCheckoutDTO.getUsername(), CommonsUtil.getNextSessionID());
				UserRegisterDetails userDetails = userRegisterService
						.getUserRegisterDetailsByUserName(paymentCheckoutDTO.getUsername());
				paymentCheckoutDTO.setOrderId(userDetails.getOrderId());

			} else {
				donorService.updateOrderId(CommonsUtil.getNextSessionID(), paymentCheckoutDTO.getUsername());
				DonorInfo donorInfo = donorService.getDonorInfoByEmailId(paymentCheckoutDTO.getUsername());
				paymentCheckoutDTO.setOrderId(donorInfo.getOrderId());
			}

		}

		String ccaRequest = "";
		// fetch the billing address from user registration table by passing the

		// raise an payment instruction
		PaymentInstruction paymentInstr = paymentContribution(null, paymentCheckoutDTO);

		String redirectUrl = paymentCheckoutDTO.getConsolidateId() != null ? portalRedirectURL : redirectURL;

		String cancelUrl = paymentCheckoutDTO.getConsolidateId() != null ? portalCancelURL : cancelURL;
		// construct the encRequest with all parameters
		ccaRequest = // "tid" + "=" + paymentInstr.getPaymentId() + "&"
				"merchant_id" + "=" + merchantId + "&" + "order_id" + "=" + paymentInstr.getPaymentId() + "&"
						+ "currency" + "=" + "INR" + "&" + "amount" + "=" + paymentCheckoutDTO.getAmount() + "&"
						+ "redirect_url" + "=" + redirectUrl + "&" + "cancel_url" + "=" + cancelUrl + "&" + "language"
						+ "=" + "EN";

		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		String encRequest = aesUtil.encrypt(ccaRequest);

		return ResponseEntity.status(HttpStatus.OK).body(CheckoutResponseDTO.build(encRequest, accessCode));
	}

	/**
	 *
	 */
	@Override
	public PaymentInstruction paymentContribution(String locale, PaymentCheckoutDTO paymentCheckoutDTO) {
		log.info("PaymentServiceImpl-paymentContribution {}", paymentCheckoutDTO);

		PaymentInstructionDTO paymentInstrDTO = PaymentInstructionDTO.builder()
				.amountPaid(paymentCheckoutDTO.getAmount()).instrType(paymentCheckoutDTO.getPaymentType())
				.status("PENDING").orderId(paymentCheckoutDTO.getOrderId()).userId(paymentCheckoutDTO.getUsername())
				.build();

		if (paymentCheckoutDTO.getConsolidateId() != null) {
			paymentInstrDTO.setConsolidateId(paymentCheckoutDTO.getConsolidateId());
		}
		log.info("paymentinstr-=-=-=-={}", paymentInstrDTO);
		PaymentInstruction paymentInstruction = modelMapper.map(paymentInstrDTO, PaymentInstruction.class);

		return paymentRepo.save(paymentInstruction);

	}

	/**
	 *
	 */
	@Override
	public String processSucessResponse(String encResp) {
		log.info("PaymentServiceImpl-processSucessResponse");

		Hashtable hs = getDecryptedValues(encResp);

		log.info("status_message ---- {} {}", hs.get("status_message"), hs);

		String paymentId = hs.get("order_id").toString();

		// updated the response in payment instruction table
		paymentRepo.updatePaymentByOrderId(paymentId, hs.get("order_status").toString(), hs.get("currency").toString(),
				hs.get("payment_mode").toString());

		PaymentInstruction paymentInstr = paymentRepo.findByPaymentId(paymentId);

		if (paymentInstr != null && "Success".equals(paymentInstr.getStatus())
				&& paymentInstr.getConsolidateId() != 0) {
			donorTrackingIdEmail(null, paymentInstr.getUserId(), null, paymentInstr.getPaymentId());
		}

		String paramters = "status_message=" + hs.get("status_message").toString() + "&" + "tracking_id="
				+ hs.get("tracking_id").toString() + "&" + "bank_ref_no=" + hs.get("bank_ref_no").toString() + "&"
				+ "amount=" + hs.get("amount").toString() + "&" + "name=" + hs.get("billing_email").toString() + "&"
				+ "order_id=" + paymentId + "&" + "payment_method=" + hs.get("payment_mode").toString() + "&"
				+ "order_status=" + hs.get("order_status") + "&" + "payment_date=" + hs.get("trans_date");

		return Base64.getEncoder().encodeToString(paramters.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * @param encResp
	 */
	private Hashtable getDecryptedValues(String encResp) {

		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(encResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		Hashtable hs = new Hashtable();
		String pair = null;
		String pname = null;
		String pvalue = null;
		while (tokenizer.hasMoreTokens()) {
			pair = tokenizer.nextToken();
			if (pair != null) {
				StringTokenizer strTok = new StringTokenizer(pair, "=");
				pname = "";
				pvalue = "";
				if (strTok.hasMoreTokens()) {
					pname = strTok.nextToken();
					if (strTok.hasMoreTokens())
						pvalue = strTok.nextToken();
					hs.put(pname, pvalue);
				}
			}
		}

		Enumeration enumeration = hs.keys();
		while (enumeration.hasMoreElements()) {
			pname = "" + enumeration.nextElement();
			pvalue = "" + hs.get(pname);
			log.info("{} - {}", pname = "" + pname, pvalue = "" + pvalue);
		}

		return hs;
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> donorTrackingIdEmail(String authorization, String emailId, String address,
			String paymentId) {
		log.info("-=-=-=emailId-=-=-={}", emailId);

		Map<String, Object> model = new HashMap<>();

		model.put("paymentId", paymentId);

		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, emailId, "Donor Tracking ID", "donorTrackId", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	@Override
	public PaymentInstruction findByPaymentId(String loggedUser, String paymentId) {
		log.info("PaymentServiceImpl-processSucessResponse");
		return paymentRepo.findByPaymentId(paymentId);
	}

}
