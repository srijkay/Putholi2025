package com.newrta.putholi.api.serviceimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.constants.UtilParamPropertyConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.PaymentInstruction;
import com.newrta.putholi.api.domain.TrustMemberAccountBook;
import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.TrustMemberAccountBookDTO;
import com.newrta.putholi.api.repository.TrustMemberAccountBookRepository;
import com.newrta.putholi.api.service.PaymentService;
import com.newrta.putholi.api.service.TrustMemberAccountBookService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;
import com.newrta.putholi.api.util.CommonsUtil;
import com.newrta.putholi.api.util.MyUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class TrustMemberAccountBookServiceImpl implements TrustMemberAccountBookService {

	private static final int CREDENTIALS_EXPIRATION = 60 * 24 * 60;
	private static final int CREDENTIALS_NON_EXPIRATION = 60 * 24 * 5475;
	private static final int ACCOUNT_EXPIRATION = 60 * 24 * 365;
	private static final int ACCOUNT_NON_EXPIRATION = 60 * 24 * 365;

	/**
	 * 
	 */
	@Autowired(required = true)
	private TrustMemberAccountBookRepository memberBookRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	MyUserDetailsService userDetailsService;

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	* 
	*/
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserRegisterDetailsService userRegistrationService;

	/**
	 *
	 */
	@Autowired(required = true)
	private PaymentService paymentService;

	/**
	 *
	 */
	@Override
	public TrustMemberAccountBook findTopByOrderByTrustBookIdDesc() {
		log.info("TrustMemberAccountBookServiceImpl-findTopByOrderByTrustBookIdDesc");
		return memberBookRepo.findTopByOrderByTrustBookIdDesc();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveTrustMemberAccountBook(String loggedUser, TrustMemberAccountBookDTO memberAccountBookDTO) {
		log.info("TrustAccountBookServiceImpl-saveAccountBook {}", memberAccountBookDTO.getAmount());
		ApiResultDTO apiResultDTO;

		try {
			TrustMemberAccountBook trustMemberAccountBook = modelMapper.map(memberAccountBookDTO,
					TrustMemberAccountBook.class);
			UserRegisterDetails userRegisterDetails = new UserRegisterDetails();
			PaymentInstruction paymentInstr = paymentService.findByPaymentId(null, memberAccountBookDTO.getOrderId());
			if (paymentInstr != null) {
				userRegisterDetails = userRegistrationService.findByOrderId(paymentInstr.getOrderId());

				trustMemberAccountBook.setCreatedBy(userRegisterDetails.getUserName());
				trustMemberAccountBook.setDonorId(userRegisterDetails.getUserName());
			} else {
				trustMemberAccountBook.setDonorId(memberAccountBookDTO.getCreatedBy());
			}

			TrustMemberAccountBook accuntBook = findTopByOrderByTrustBookIdDesc();

			if (accuntBook != null && accuntBook.getBalanceAmount() != null) {
				if (trustMemberAccountBook.getFeeType() != null && trustMemberAccountBook.getFeeType().equals("INC")) {
					trustMemberAccountBook
							.setBalanceAmount(accuntBook.getBalanceAmount().add(trustMemberAccountBook.getAmount()));

					if (trustMemberAccountBook.getRemarks().equals("RENPAY")) {
						updateUserDetails(userRegisterDetails);
					} else {
						userRegistrationService.updateApprovalDetails(userRegisterDetails.getUserName(), "PENADM", "N");

						// Calling email method after payment is success

						memberAccountRegistrationEmail(null, null, null, userRegisterDetails.getEmailId(), null);

					}

				} else {
					trustMemberAccountBook.setBalanceAmount(
							accuntBook.getBalanceAmount().subtract(trustMemberAccountBook.getAmount()));
				}
			} else {
				trustMemberAccountBook.setBalanceAmount(trustMemberAccountBook.getAmount());
			}

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule("TRUSTBOOK").auditDesc("TrustAccountBookServiceImpl-saveAccountBook")
					.auditValue(new ObjectMapper().writeValueAsString(memberAccountBookDTO)).createdBy(loggedUser)
					.build();

			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			memberBookRepo.save(trustMemberAccountBook);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Saved Info Successfully")
					.build();
		} catch (JsonProcessingException jpe) {
			log.error("TrustAccountBookServiceImpl-saveAccountBook-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 * 
	 */

	@Override
	public ResponseEntity<ApiResultDTO> memberAccountRegistrationEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address) {

		UserRegisterDetails userRegisterDetails = userRegistrationService.getUserDetailsByEmailId(emailId);

		jmsTemplate.convertAndSend("auditbox",
				new AuditDetails("UPDATE", CommonsConstants.APPROVED, userRegisterDetails.getEmailId(),
						"Account Registered Successfully", address == null ? "UNKNOWN" : address));
		Map<String, Object> model = new HashMap<>();

		jmsTemplate.convertAndSend("mailbox", new MailDTO(mailFrom, userRegisterDetails.getEmailId(),
				"Account Registered Successfully", "AccountRegistration", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public List<TrustMemberAccountBook> findByCreatedDateBetween(Date endDate) {
		log.info("TrustAccountBookServiceImpl-findByCreatedDateBetween{}", endDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate); // Set the calendar to the provided end date

		// Set to the first day of the year
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		Date beginDate = calendar.getTime();
		log.info("beginning date {}", beginDate);

		return memberBookRepo.findByCreatedDateBetween(beginDate, endDate);
	}

	/**
	 * @param userRegisterDetails
	 */
	private void updateUserDetails(UserRegisterDetails userRegisterDetails) {

		userRegistrationService.updateApprovalDetails(userRegisterDetails.getUserName(), "APR", "Y");

		UserLogin userLoginDetails = userDetailsService.getUserDetailsByUserName(userRegisterDetails.getUserName());

		UserLogin userLogin = new UserLogin();

		Calendar cal = Calendar.getInstance();
		String accExpireFlag = UtilParamPropertyConstants.getUserAccExpiryFlag();

		userLogin.setUserName(userRegisterDetails.getUserName());
		userLogin.setAccountEnabled(true);
		userLogin.setChangePasswordRequired(false);

		if (null != accExpireFlag && (CommonsConstants.YES).equalsIgnoreCase(accExpireFlag)) {
			userLogin.setAccountExpiryDate(CommonsUtil.calculateExpiryDate(ACCOUNT_EXPIRATION, cal));
		} else {
			userLogin.setAccountExpiryDate(CommonsUtil.calculateExpiryDate(ACCOUNT_NON_EXPIRATION, cal));
		}

		String credExpireFlag = UtilParamPropertyConstants.getUserPwdExpiryFlag();
		if (null != credExpireFlag && (CommonsConstants.YES).equalsIgnoreCase(credExpireFlag)) {
			userLogin.setCredentialsExpiryDate(
					CommonsUtil.calculateExpiryDate(CREDENTIALS_EXPIRATION, Calendar.getInstance()));
		} else {
			userLogin.setCredentialsExpiryDate(
					CommonsUtil.calculateExpiryDate(CREDENTIALS_NON_EXPIRATION, Calendar.getInstance()));
		}

		userLogin.setPassword(userLoginDetails.getPassword());
		userLogin.setAccountNonLocked(true);
		userLogin.setRoles(userRegisterDetails.getRole());
		userLogin.setEmailId(userRegisterDetails.getEmailId());

		userDetailsService.generateUserCredentials(userLogin);
	}
}
