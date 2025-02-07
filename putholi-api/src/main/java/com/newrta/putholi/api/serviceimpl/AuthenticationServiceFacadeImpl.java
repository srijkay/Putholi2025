package com.newrta.putholi.api.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.constants.UtilParamPropertyConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.domain.UserAttachment;
import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.UserLoginHistory;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.UserAuthenticationDTO;
import com.newrta.putholi.api.model.UserDTO;
import com.newrta.putholi.api.model.UserRegisterDetailsDTO;
import com.newrta.putholi.api.repository.UserLoginRepository;
import com.newrta.putholi.api.service.AuthenticationServiceFacade;
import com.newrta.putholi.api.service.DonorInfoService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
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
public class AuthenticationServiceFacadeImpl implements AuthenticationServiceFacade {

	private static final int RANDOM_NUM_LENGTH = 7;
	private static final int CREDENTIALS_EXPIRATION = 60 * 24 * 60;
	private static final int CREDENTIALS_NON_EXPIRATION = 60 * 24 * 7300;
	private static final int RESET_EXPIRATION = 15;
	private static final int ACCOUNT_EXPIRATION = 60 * 24 * 365;
	private static final int ACCOUNT_NON_EXPIRATION = 60 * 24 * 7300;
	private static final int ACCOUNT_NON_EXPIRATION_EXPECT_BENEF = 60 * 24 * 365;

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
	@Autowired(required = true)
	UserLoginRepository userLoginRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	DonorInfoService donorInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * @param locale
	 * @param authorization
	 * @param userRegisterDetailsDTO
	 * @return
	 */
	@Override
	@Transactional
	public ApiResultDTO registeruser(HttpServletRequest request, String locale, String authorization,
			UserRegisterDetailsDTO userRegisterDetailsDTO, MultipartFile identityproof, boolean isManagedUser) {
		log.info("ApplicantAuthenticationServiceFacadeImpl-registeruser {}", userRegisterDetailsDTO.getEmailId());

		ApiResultDTO userAuthDTO;
		try {
			/* Verifying userName already exists in the system */
			if (userRegisterDetailsService.verifyUserNameAndActiveExists(userRegisterDetailsDTO.getUserName(), "Y")) {
				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR).apiStatusDesc(LocaleConfig
						.getResourceValue("user.name.notavailable", null, locale, CommonsConstants.AUTH_RESOURCE))
						.build();
			}

			/* Verifying email id already exists in the system */
			if (userRegisterDetailsService.verifyEmailExists(userRegisterDetailsDTO.getEmailId(), "Y")) {
				List<Object> objArray = new ArrayList<>();
				objArray.add(userRegisterDetailsDTO.getEmailId());

				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc(LocaleConfig.getResourceValue("account.exists.with.emailid ", objArray.toArray(),
								locale, CommonsConstants.AUTH_RESOURCE))
						.build();
			}

			/* insert the user register details */

			UserRegisterDetails user = userRegisterDetailsService
					.getUserRegisterDetailsByUserName(userRegisterDetailsDTO.getUserName());

			if (user != null && userRegisterDetailsService
					.verifyUserNameAndActiveExists(userRegisterDetailsDTO.getUserName(), "N")
					&& !user.getStatus().equals("REJ") && !user.getStatus().equals("PENPAY")) {
				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR).apiStatusDesc(LocaleConfig
						.getResourceValue("user.name.notavailable", null, locale, CommonsConstants.AUTH_RESOURCE))
						.build();
			}

			if (user != null && userRegisterDetailsService.verifyEmailExists(userRegisterDetailsDTO.getEmailId(), "N")
					&& !user.getStatus().equals("REJ") && !user.getStatus().equals("PENPAY")) {
				List<Object> objArray = new ArrayList<>();
				objArray.add(userRegisterDetailsDTO.getEmailId());

				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc(LocaleConfig.getResourceValue(" account.exists.with.emailid", objArray.toArray(),
								locale, CommonsConstants.AUTH_RESOURCE))
						.build();
			}

			UserRegisterDetails userRegisterDetailsResult = userRegisterDetailsService
					.saveUserRegisterDetails(modelMapper.map(userRegisterDetailsDTO, UserRegisterDetails.class));
			if (userRegisterDetailsResult != null) {
				/* insert the identification proof document */
				if (identityproof != null) {
					userAttachmentService.saveUserProfileAttachment(locale,
							new UserAttachment(userRegisterDetailsDTO.getUserName(), CommonsConstants.ID_PROOF,
									identityproof.getContentType(), identityproof.getBytes()));
				}

				/* generate the user login for registered user */
				UserLogin userLogin = new UserLogin();
				userLogin.setUserName(userRegisterDetailsDTO.getUserName());
				if (isManagedUser) {
					userLogin.setPassword(passwordEncoder.encode(userRegisterDetailsDTO.getSecretKey()));
					userLogin.setAccountEnabled(true);
					userLogin.setChangePasswordRequired(false);
				} else {
					userLogin.setPassword(passwordEncoder.encode(userRegisterDetailsDTO.getSecretKey()));
					userLogin.setAccountEnabled(false);
					userLogin.setChangePasswordRequired(false);
				}

				Calendar cal = Calendar.getInstance();
				userLogin.setCredentialsExpiryDate(CommonsUtil.calculateExpiryDate(RESET_EXPIRATION, cal));

				String accExpireFlag = UtilParamPropertyConstants.getUserAccExpiryFlag();
				if (null != accExpireFlag && (CommonsConstants.YES).equalsIgnoreCase(accExpireFlag)) {
					userLogin.setAccountExpiryDate(CommonsUtil.calculateExpiryDate(ACCOUNT_EXPIRATION, cal));
				} else {
					if (userRegisterDetailsResult.getRole().equals("BENIF")) {
						userLogin.setAccountExpiryDate(CommonsUtil.calculateExpiryDate(ACCOUNT_NON_EXPIRATION, cal));
					} else {
						userLogin.setAccountExpiryDate(
								CommonsUtil.calculateExpiryDate(ACCOUNT_NON_EXPIRATION_EXPECT_BENEF, cal));

					}
				}

				String credExpireFlag = UtilParamPropertyConstants.getUserPwdExpiryFlag();
				if (null != credExpireFlag && (CommonsConstants.YES).equalsIgnoreCase(credExpireFlag)) {
					userLogin.setCredentialsExpiryDate(
							CommonsUtil.calculateExpiryDate(CREDENTIALS_EXPIRATION, Calendar.getInstance()));
				} else {

					userLogin.setCredentialsExpiryDate(
							CommonsUtil.calculateExpiryDate(CREDENTIALS_NON_EXPIRATION, Calendar.getInstance()));

				}

				userLogin.setAccountNonLocked(true);
				userLogin.setRoles(userRegisterDetailsResult.getRole());
				userLogin.setEmailId(userRegisterDetailsResult.getEmailId());
				userLogin.setLocale(locale);

				UserLogin userResult = userDetailsService.generateUserCredentials(userLogin);

				/* send the user credentials through EMail Notification */
				if (userResult == null) {
					userAuthDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
							.apiStatusDesc(LocaleConfig.getResourceValue("user.registration.failure", null, locale,
									CommonsConstants.AUTH_RESOURCE))
							.build();
				} else {

					userAuthDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc(LocaleConfig.getResourceValue("user.registration.success", null, locale,
									CommonsConstants.AUTH_RESOURCE))
							.build();
					if (userRegisterDetailsDTO.getStatus().equals("PENADM")) {
						accountRegistrationEmail(request, locale, authorization, userRegisterDetailsDTO.getEmailId(),
								null);
					}
				}

			} else {
				List<Object> objArray = new ArrayList<>();
				objArray.add(userRegisterDetailsDTO.getEmailId());

				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc(LocaleConfig.getResourceValue(" account.exists.with.emailid", objArray.toArray(),
								locale, CommonsConstants.AUTH_RESOURCE))
						.build();
			}

		} catch (IOException e) {
			userAuthDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.processing", null, locale, null)).build();
		}
		return userAuthDTO;
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param userDTO
	 * @return
	 */
	@Override
	public ResponseEntity<UserAuthenticationDTO> authenticateUser(String locale, String authorization,
			UserDTO userDTO) {
		log.info("::AuthenticationServiceFacadeImpl::authenticateUser::");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getSecretKey()));

		boolean result = authentication.isAuthenticated();
		UserAuthenticationDTO userAuthDTO;

		if (result) {

			/* get user login details */
			UserLogin userLogin = userLoginRepo.findByUserNameIgnoreCase(userDTO.getUsername());
			UserLogin userLoginResult = userDetailsService.getUserDetailsByUserName(userLogin.getUserName());

			if (userLogin.getRoles() != null && userLogin.getRoles().equals("DONOR")) {

				DonorInfo donorInfo = donorInfoService.getUserRegisterDetailsByUserName(userDTO.getUsername());

				userAuthDTO = UserAuthenticationDTO.builder().username(userDTO.getUsername())
						.accessToken(jwtUtil.generateToken(userDTO.getUsername()))
						.changePasswordRequired(getPasswordChangeReq(userLoginResult)).status(CommonsConstants.SUCCESS)
						.statusDescription(LocaleConfig.getResourceValue("login.verify.success", null, locale,
								CommonsConstants.AUTH_RESOURCE))
						.firstName(donorInfo.getFirstName() + " " + donorInfo.getLastName())
						.emailId(donorInfo.getEmailId()).build();
			} else {
				UserRegisterDetails userDetails = userRegisterDetailsService
						.getUserRegisterDetailsByUserName(userDTO.getUsername());

				UserAttachment userProfileAttachment = userAttachmentService
						.getUserAttachment(userDetails.getUserName(), CommonsConstants.PROFILE_PIC);

				userAuthDTO = UserAuthenticationDTO.builder().username(userDetails.getUserName())
						.accessToken(jwtUtil.generateToken(userDetails.getUserName()))
						.changePasswordRequired(getPasswordChangeReq(userLoginResult)).status(CommonsConstants.SUCCESS)
						.statusDescription(LocaleConfig.getResourceValue("login.verify.success", null, locale,
								CommonsConstants.AUTH_RESOURCE))
						.firstName(userDetails.getFirstName()).lastName(userDetails.getLastName())
						.emailId(userDetails.getEmailId()).roles(userDetails.getRole())
						.profilePic(userProfileAttachment != null
								? Base64.getEncoder().encodeToString(userProfileAttachment.getFileData())
								: "")
						.userStatus(userDetails.getStatus()).mobileNumber(userDetails.getMobileNumber()).build();

				/* return the user success message */

				if (userProfileAttachment != null) {
					userAuthDTO.setProfilePic(Base64.getEncoder().encodeToString(userProfileAttachment.getFileData()));
				} else {
					userAuthDTO.setProfilePic("");
				}

			}

			/* insert login user history */
			userLoginHistoryService.saveUserLoginHistory(
					new UserLoginHistory(userDTO.getUserip(), userDTO.getUseragent(), userLogin.getUserName()));

		} else {
			UserRegisterDetails userDetails = userRegisterDetailsService
					.getUserRegisterDetailsByUserName(userDTO.getUsername());

			userAuthDTO = UserAuthenticationDTO.builder().userStatus(userDetails.getStatus()).build();
		}

		return new ResponseEntity<>(userAuthDTO, HttpStatus.OK);
	}

	/**
	 * @param userLoginResult
	 * @return
	 */
	private boolean getPasswordChangeReq(UserLogin userLoginResult) {
		log.info("::AuthenticationServiceFacadeImpl::getPasswordChangeReq::");
		return userLoginResult.isChangePasswordRequired();
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<UserRegisterDetails> applicantProfile(String locale, String authorization, String username) {
		log.info("::AuthenticationServiceFacadeImpl::applicantProfile::");

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserRegisterDetailsByUserName(username);

		if (userRegisterDetails != null) {
			UserAttachment userProfileAttachment = userAttachmentService.getUserAttachment(username,
					CommonsConstants.PROFILE_PIC);
			if (userProfileAttachment != null) {
				userRegisterDetails.setPic(Base64.getEncoder().encodeToString(userProfileAttachment.getFileData()));
			} else {
				userRegisterDetails.setPic("");
			}
		}
		return new ResponseEntity<>(userRegisterDetails, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> modifyRegisteruser(String locale, String authorization,
			UserRegisterDetailsDTO userRegisterDetailsDTO) {
		log.info("::AuthenticationServiceFacadeImpl::modifyRegisteruser::");
		ApiResultDTO apiResultDTO;
		boolean result = userRegisterDetailsService.verifyUserNameAndActiveExists(userRegisterDetailsDTO.getUserName(),
				"Y");

		if (result) {

			UserRegisterDetails userDetails = userRegisterDetailsService
					.getUserRegisterDetailsByUserName(userRegisterDetailsDTO.getUpdatedBy());

			if (userRegisterDetailsDTO.getChangeRequestRole() != null && userDetails.getRole().equals("ADMIN")) {
				userRegisterDetailsDTO.setStatus("CHRREV");
				userDetailsService.updateUserAccountEnabled(false, userRegisterDetailsDTO.getUserName());
			} else if (userRegisterDetailsDTO.getChangeRequestRole() != null && userDetails.getRole().equals("SUUSR")) {
				userRegisterDetailsDTO.setStatus("PENSUA");
				userDetailsService.updateUserAccountEnabled(false, userRegisterDetailsDTO.getUserName());

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.SUADM, "CHANGE",
						"Super Admin", CommonsConstants.SUADM_COUNT, "Change Role Request");
			}

			userRegisterDetailsService
					.saveUserRegisterDetails(modelMapper.map(userRegisterDetailsDTO, UserRegisterDetails.class));

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
		} else {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.invalid.request.notexist", null, locale, null))
					.build();
		}

		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	@Transactional
	public ResponseEntity<ApiResultDTO> forgetPassword(String locale, String authorization, String emailid,
			String address) {
		log.info("::AuthenticationServiceFacadeImpl::forgetPassword::");

		/* get account by email id */
		UserRegisterDetails applicantUserDtls = userRegisterDetailsService.getUserDetailsByEmailId(emailid);

		if (applicantUserDtls == null) {
			log.info("Email ID doesn't exists in the system");
		} else {

			if (applicantUserDtls.getActive().equals(CommonsConstants.YES)) {
				String pswdresult = CommonsUtil.randomAlphaNumeric(RANDOM_NUM_LENGTH);
				log.info("pswdresult-=-=-=-{}", pswdresult);

				userDetailsService.forgetPasswordChange(applicantUserDtls.getUserName(),
						passwordEncoder.encode(pswdresult));

				jmsTemplate.convertAndSend(CommonsConstants.AUDITBOX,
						new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.FORGET_PSWD, emailid,
								"Forget Password", address == null ? CommonsConstants.UNKNOWN : address));

				Map<String, Object> model = new HashMap<>();
				model.put("username", applicantUserDtls.getUserName());
				model.put("pswrd", pswdresult);
				jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
						new MailDTO(mailFrom, emailid, "Forget Password Reset", "emailsent", model));
			} else {
				log.info("Account {} associated to EMail ID {} is not active", applicantUserDtls.getUserName(),
						emailid);
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
		log.info("::AuthenticationServiceFacadeImpl::updateCredentials::");
		HttpStatus httpstatus;

		UserLogin userLoginResult = userDetailsService.getUserDetailsByUserName(userDTO.getUsername());

		boolean result = verifyPwdMatcher(userDTO, userLoginResult);

		UserRegisterDetails userRegisterInfo = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(userDTO.getUsername());

		UserRegisterDetails userRegisterDetail = userRegisterDetailsService
				.getUserDetailsByEmailId(userRegisterInfo.getEmailId());

		UserAuthenticationDTO userAuthDTO;
		if (result) {
			userDetailsService.updateUserCredentials(getApplicantUserLogin(userDTO, userLoginResult));
			userAuthDTO = UserAuthenticationDTO
					.builder().status(CommonsConstants.SUCCESS).statusDescription(LocaleConfig
							.getResourceValue("password.update.success", null, locale, CommonsConstants.AUTH_RESOURCE))
					.build();

			credentialsUpdatedEmail(null, locale, authorization, userRegisterDetail.getEmailId(), null);

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
	 * @param userDTO
	 * @param userLoginResult
	 * @return
	 */
	private boolean verifyPwdMatcher(UserDTO userDTO, UserLogin userLoginResult) {
		return passwordEncoder.matches(userDTO.getSecretKey(), userLoginResult.getPassword());
	}

	/**
	 * @param userDTO
	 * @param userLoginResult
	 * @return
	 */
	private UserLogin getApplicantUserLogin(UserDTO userDTO, UserLogin userLoginResult) {
		UserLogin userLogin = new UserLogin();
		userLogin.setUserName(userDTO.getUsername());
		userLogin.setPassword(passwordEncoder.encode(userDTO.getNewsecretkey()));
		userLogin.setAccountEnabled(true);
		userLogin.setCredentialsExpiryDate(
				CommonsUtil.calculateExpiryDate(CREDENTIALS_NON_EXPIRATION, Calendar.getInstance()));

		userLogin.setAccountExpiryDate(userLoginResult.getAccountExpiryDate());
		userLogin.setChangePasswordRequired(false);
		userLogin.setAccountNonLocked(true);
		userLogin.setRoles(userLoginResult.getRoles());
		return userLogin;
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> accountApproved(HttpServletRequest request, String locale, String authorization,
			String emailId, String address, String password, boolean isReject) {
		log.info("SchoolInfoServiceImpl-sendMailtoBeneficiary {}", emailId);

		jmsTemplate.convertAndSend(CommonsConstants.AUDITBOX, new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
				emailId, "Account Approved", address == null ? CommonsConstants.UNKNOWN : address));

		Map<String, Object> model = new HashMap<>();

		if (password != null) {
			UserRegisterDetails registerDetails = userRegisterDetailsService.getUserDetailsByEmailId(emailId);

			model.put("UserName", registerDetails.getUserName());
			model.put("rejected", isReject);

			String account = isReject ? "Account Rejected" : "Account Approved";

			jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
					new MailDTO(mailFrom, emailId, account, "AccountApproved", model));
		}

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id ").build(), HttpStatus.OK);
	}

	/**
	 * @param request
	 * @param locale
	 * @param loggedUser
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @return
	 */
	@Override
	public ApiResultDTO referVolunteer(HttpServletRequest request, String locale, String loggedUser,
			String authorization, String emailId, String address) {
		log.info("SchoolInfoServiceImpl-referVolunteer");

		jmsTemplate.convertAndSend(CommonsConstants.AUDITBOX, new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED, emailId,
				"Refer Volunteer", address == null ? CommonsConstants.UNKNOWN : address));

		Map<String, Object> model = new HashMap<>();

		if (emailId.contains(",")) {
			String[] convertedRankArray = emailId.contains(", ") ? StringUtils.split(emailId, ", ")
					: StringUtils.split(emailId, ",");
			for (String emailid : convertedRankArray) {
				if (userRegisterDetailsService.verifyEmailExists(emailid, "Y")
						&& userRegisterDetailsService.verifyEmailExists(emailid, "N")) {
					List<Object> objArray = new ArrayList<>();
					objArray.add(emailid);

					return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
							.apiStatusDesc(LocaleConfig.getResourceValue("account.exists.with.emailid",
									objArray.toArray(), locale, CommonsConstants.AUTH_RESOURCE))
							.build();
				} else {
					model.put("confirmurl", appUrl + "/?referred_name=" + loggedUser + "&email_id=" + emailid);

					jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
							new MailDTO(mailFrom, emailid, "Refer Volunteers", "account-verified", model));
				}
			}
		} else {
			if (userRegisterDetailsService.verifyEmailExists(emailId, "Y")
					&& userRegisterDetailsService.verifyEmailExists(emailId, "N")) {
				List<Object> objArray = new ArrayList<>();
				objArray.add(emailId);

				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc(LocaleConfig.getResourceValue("account.exists.with.emailid", objArray.toArray(),
								locale, CommonsConstants.AUTH_RESOURCE))
						.build();
			}
			model.put("confirmurl", appUrl + "/?referred_name=" + loggedUser + "&email_id=" + emailId);
			jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
					new MailDTO(mailFrom, emailId, "Refer Volunteer", "account-verified", model));
		}

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the refered Email Id").build();
	}

	/**
	 * 
	 */

	@Override
	public ResponseEntity<ApiResultDTO> accountRegistrationEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address) {

		jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.APPROVED, emailId,
				"Account Registered Successfully", address == null ? "UNKNOWN" : address));
		Map<String, Object> model = new HashMap<>();

		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, emailId, "Account Registered Successfully", "AccountRegistration", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 * 
	 */

	@Override
	public ResponseEntity<ApiResultDTO> credentialsUpdatedEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address) {

		jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.APPROVED, emailId,
				"Credentails Updated Successfully", address == null ? "UNKNOWN" : address));
		Map<String, Object> model = new HashMap<>();

		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, emailId, "Credentails Updated Successfully", "UpdateCredentials", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
