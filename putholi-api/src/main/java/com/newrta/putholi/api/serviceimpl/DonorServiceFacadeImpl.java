
package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.constants.UtilParamPropertyConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DonorInfoDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.UserAuthenticationDTO;
import com.newrta.putholi.api.model.UserDTO;
import com.newrta.putholi.api.service.DonorInfoService;
import com.newrta.putholi.api.service.DonorInfoServiceFacade;
import com.newrta.putholi.api.service.UserAttachmentService;
import com.newrta.putholi.api.service.UserLoginHistoryService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.service.VerificationTokenService;
import com.newrta.putholi.api.util.CommonsUtil;
import com.newrta.putholi.api.util.JwtUtil;
import com.newrta.putholi.api.util.MyUserDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class DonorServiceFacadeImpl implements DonorInfoServiceFacade {

	private static final int RANDOM_NUM_LENGTH = 7;
	private static final int CREDENTIALS_EXPIRATION = 60 * 24 * 60;
	private static final int CREDENTIALS_NON_EXPIRATION = 60 * 24 * 5475;
	private static final int RESET_EXPIRATION = 15;
	private static final int ACCOUNT_EXPIRATION = 60 * 24 * 365;
	private static final int ACCOUNT_NON_EXPIRATION = 60 * 24 * 5475;

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Value("${app.url}")
	private String appUrl;

	/**
	 * 
	 */
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Autowired
	ApplicationEventPublisher eventPublisher;

	/**
	 * 
	 */
	@Autowired(required = true)
	ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	PasswordEncoder passwordEncoder;

	/**
	 * 
	 */
	@Autowired(required = true)
	AuthenticationManager authenticationManager;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	DonorInfoService donorInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	MyUserDetailsService userDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	JwtUtil jwtUtil;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserLoginHistoryService userLoginHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	VerificationTokenService verificationTokenService;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserAttachmentService userAttachmentService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveDonor(HttpServletRequest request, String locale, String authorization,
			DonorInfoDTO donorInfoDTO) {
		log.info("DonorServiceFacadeImpl-saveDonor");

		ApiResultDTO userAuthDTO;

		/* Verifying email id already exists in the system */
		if (donorInfoService.verifyEmailExists(donorInfoDTO.getEmailId())) {
			List<Object> objArray = new ArrayList<>();
			objArray.add(donorInfoDTO.getEmailId());

			return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("account.exists.with.emailid", objArray.toArray(),
							locale, CommonsConstants.AUTH_RESOURCE))
					.build();
		}

		/* insert the user register details */

		DonorInfo donorInfoResult = donorInfoService.saveDonorInfo(modelMapper.map(donorInfoDTO, DonorInfo.class));

		/* generate the user login for registered user */
		UserLogin donorLogin = new UserLogin();
		donorLogin.setUserName(donorInfoResult.getEmailId());
		donorLogin.setPassword(passwordEncoder.encode(donorInfoDTO.getSecretKey()));
		donorLogin.setAccountEnabled(true);
		donorLogin.setRoles("DONOR");

		Calendar cal = Calendar.getInstance();
		donorLogin.setCredentialsExpiryDate(CommonsUtil.calculateExpiryDate(RESET_EXPIRATION, cal));

		String accExpireFlag = UtilParamPropertyConstants.getUserAccExpiryFlag();
		if (null != accExpireFlag && (CommonsConstants.YES).equalsIgnoreCase(accExpireFlag)) {
			donorLogin.setAccountExpiryDate(CommonsUtil.calculateExpiryDate(ACCOUNT_EXPIRATION, cal));
		} else {
			donorLogin.setAccountExpiryDate(CommonsUtil.calculateExpiryDate(ACCOUNT_NON_EXPIRATION, cal));
		}

		String credExpireFlag = UtilParamPropertyConstants.getUserPwdExpiryFlag();
		if (null != credExpireFlag && (CommonsConstants.YES).equalsIgnoreCase(credExpireFlag)) {
			donorLogin.setCredentialsExpiryDate(
					CommonsUtil.calculateExpiryDate(CREDENTIALS_EXPIRATION, Calendar.getInstance()));
		} else {
			donorLogin.setCredentialsExpiryDate(
					CommonsUtil.calculateExpiryDate(CREDENTIALS_NON_EXPIRATION, Calendar.getInstance()));
		}

		donorLogin.setAccountNonLocked(true);
		donorLogin.setEmailId(donorInfoResult.getEmailId());
		donorLogin.setLocale(locale);

		UserLogin donorInfoResults = userDetailsService.generateDonorCredentials(donorLogin);

		/* send the user credentials through EMail Notification */
		if (donorInfoResults == null) {
			userAuthDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR).apiStatusDesc(LocaleConfig
					.getResourceValue("user.registration.failure", null, locale, CommonsConstants.AUTH_RESOURCE))
					.build();
		} else {
			userAuthDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc(LocaleConfig
					.getResourceValue("user.registration.success", null, locale, CommonsConstants.AUTH_RESOURCE))
					.build();
		}

		return userAuthDTO;
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> accountApproved(HttpServletRequest request, String locale, String authorization,
			String emailId, String address, String password) {
		log.info("DonorServiceFacadeImpl-sendMailtoDonor {}", emailId);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.APPROVED, emailId,
				"Account Approved", address == null ? "UNKNOWN" : address));

		Map<String, Object> model = new HashMap<>();

		DonorInfo donorDetails = donorInfoService.getDonorInfoByEmailId(emailId);

		log.info("----------------------- {}", donorDetails);

		model.put("UserName", donorDetails.getEmailId());
		model.put("password", password);
		model.put("show", true);

		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, emailId, "Account Approved", "AccountApproved", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 * 
	 */

	@Override
	public ResponseEntity<ApiResultDTO> forgetPassword(String locale, String authorization, String emailid,
			String address) {
		log.info("::DonorServiceFacadeImpl::forgetPassword::");

		/* get account by email id */
		DonorInfo donorUserDtls = donorInfoService.getDonorInfoByEmailId(emailid);
		
		log.info("donor info --=-=-=-= {}", donorUserDtls);

		if (donorUserDtls == null) {
			log.info("Email ID doesn't exists in the system");
		} else {

			if (donorUserDtls.getActive().equals(CommonsConstants.YES)) {
				String pswdresult = CommonsUtil.randomAlphaNumeric(RANDOM_NUM_LENGTH);
				log.info("pswdresult-=-=-=-{}", pswdresult);

				userDetailsService.forgetPasswordChange(donorUserDtls.getEmailId(), passwordEncoder.encode(pswdresult));

				jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.FORGET_PSWD, emailid,
						"Forget Password", address == null ? "UNKNOWN" : address));

				Map<String, Object> model = new HashMap<>();
				model.put("username", donorUserDtls.getEmailId());
				model.put("pswrd", pswdresult);
				jmsTemplate.convertAndSend("mailbox",
						new MailDTO(mailFrom, emailid, "Forget Password Reset", "emailsent", model));
			} else {
				log.info("Account {} associated to EMail ID {} is not active", donorUserDtls.getEmailId(), emailid);
			}
		}

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered EMail ID").build(), HttpStatus.OK);
	}

	/**
	 * 
	 */

	@Override
	public ResponseEntity<UserAuthenticationDTO> updateCredentials(String locale, String authorization,
			UserDTO userDTO) {
		log.info("::DonorServiceFacadeImpl::updateCredentials::");
		HttpStatus httpstatus;

		UserLogin donorLoginResult = userDetailsService.getDonorDetailsByUsername(userDTO.getUsername());
		boolean result = verifyPwdMatcher(userDTO, donorLoginResult);

		UserAuthenticationDTO userAuthDTO;
		if (result) {
			userDetailsService.updateUserCredentials(getApplicantUserLogin(userDTO, donorLoginResult));
			userAuthDTO = UserAuthenticationDTO
					.builder().status(CommonsConstants.SUCCESS).statusDescription(LocaleConfig
							.getResourceValue("password.update.success", null, locale, CommonsConstants.AUTH_RESOURCE))
					.build();
			httpstatus = HttpStatus.OK;
		} else {
			userAuthDTO = UserAuthenticationDTO.builder().status(CommonsConstants.ERROR).statusDescription(LocaleConfig
					.getResourceValue("password.update.old.incorrect", null, locale, CommonsConstants.AUTH_RESOURCE))
					.build();
			httpstatus = HttpStatus.FORBIDDEN;
		}

		return new ResponseEntity<>(userAuthDTO, httpstatus);
	}

	/**
	 * 
	 * @param donorLoginDTO
	 * @param donorLoginResult
	 * @return
	 */

	private UserLogin getApplicantUserLogin(UserDTO userDTO, UserLogin donorLoginResult) {
		UserLogin userLogin = new UserLogin();
		userLogin.setUserName(userDTO.getUsername());
		userLogin.setPassword(passwordEncoder.encode(userDTO.getNewsecretkey()));
		userLogin.setAccountEnabled(true);
		userLogin.setCredentialsExpiryDate(
				CommonsUtil.calculateExpiryDate(CREDENTIALS_NON_EXPIRATION, Calendar.getInstance()));

		userLogin.setAccountExpiryDate(donorLoginResult.getAccountExpiryDate());
		userLogin.setAccountNonLocked(true);
		userLogin.setRoles("DONOR");
		return userLogin;
	}

	/**
	 * 
	 * @param donorLoginDTO
	 * @param donorLoginResult
	 * @return
	 */

	private boolean verifyPwdMatcher(UserDTO userDTO, UserLogin donorLoginResult) {
		return passwordEncoder.matches(userDTO.getSecretKey(), donorLoginResult.getPassword());
	}
}
