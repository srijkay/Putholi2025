package com.newrta.putholi.api.resource;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.UserAuthenticationDTO;
import com.newrta.putholi.api.model.UserDTO;
import com.newrta.putholi.api.model.UserRegisterDetailsDTO;
import com.newrta.putholi.api.service.AuthenticationServiceFacade;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.MyUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/v1/api/authenticate")
public class AuthenticationResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	AuthenticationServiceFacade authenticationServiceFacade;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MyUserDetailsService userDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * @param locale
	 * @param authorization
	 * @param userRegisterDetails
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/registeruser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> registeruser(HttpServletRequest request,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestPart("UserRegisterDetailsDTO") String userRegisterDetailsDTO,
			@RequestPart(value = "identifyproof", required = false) MultipartFile identityproof) {
		log.info("::AuthenticationResource::registeruser::");

		ApiResultDTO apiResultDTO;
		HttpStatus httpStatus;
		try {
			apiResultDTO = authenticationServiceFacade.registeruser(request, locale, authorization,
					new ObjectMapper().readValue(userRegisterDetailsDTO, UserRegisterDetailsDTO.class), identityproof,
					false);
			httpStatus = HttpStatus.OK;
		} catch (JsonMappingException jme) {
			log.error("registeruser-JsonMappingException {} {}", jme.getCause(), jme);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact Admin!").build();
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		} catch (JsonProcessingException jpe) {
			log.error("registeruser-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact Admin !!").build();
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<>(apiResultDTO, httpStatus);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param userRegisterDetails
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/registeruserinternal", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> registerUserInternal(HttpServletRequest request,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestPart("UserRegisterDetailsDTO") String userRegisterDetailsDTO,
			@RequestPart(value = "identifyproof", required = false) MultipartFile identityproof) {
		log.info("::AuthenticationResource::registerUserInternal::");

		ApiResultDTO apiResultDTO;
		HttpStatus httpStatus;
		try {
			apiResultDTO = authenticationServiceFacade.registeruser(request, locale, authorization,
					new ObjectMapper().readValue(userRegisterDetailsDTO, UserRegisterDetailsDTO.class), identityproof,
					true);
			httpStatus = HttpStatus.OK;
		} catch (JsonMappingException jme) {
			log.error("registeruser-JsonMappingException {} {}", jme.getCause(), jme);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact Admin!!!").build();
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		} catch (JsonProcessingException jpe) {
			log.error("registeruser-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact Admin").build();
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<>(apiResultDTO, httpStatus);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param userRegisterDetails
	 * @return
	 */
	@CrossOrigin
	@PutMapping(value = "/updateuser")
	public ResponseEntity<ApiResultDTO> modifyRegisteruser(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestBody UserRegisterDetailsDTO userRegisterDetailsDTO) {
		log.info("::AuthenticationResource::modifyRegisteruser::");
		return authenticationServiceFacade.modifyRegisteruser(locale, authorization, userRegisterDetailsDTO);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param userDTO
	 * @return
	 */
	@CrossOrigin // need to push to separate controller as it will be exposed to public
	@PostMapping(value = "/validateuser")
	public ResponseEntity<UserAuthenticationDTO> authenticateUser(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader("Authorization") String authorization, @RequestBody UserDTO userDTO) {
		log.info("::AuthenticationResource::authenticateUser::{}", locale);
		return authenticationServiceFacade.authenticateUser(locale, authorization, userDTO);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{username}")
	public ResponseEntity<UserRegisterDetails> applicantProfile(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader("Authorization") String authorization, @PathVariable("username") String username) {
		log.info("::AuthenticationResource::applicantProfile::{}", locale);
		return authenticationServiceFacade.applicantProfile(locale, authorization, username);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param emailid
	 * @param address
	 * @return
	 */
	@CrossOrigin // need to push to separate controller as it will be exposed to public
	@GetMapping(value = "/forgetpassword/{emailid}/{address}")
	public ResponseEntity<ApiResultDTO> forgetPassword(@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader("Authorization") String authorization, @PathVariable("emailid") String emailid,
			@PathVariable("address") String address) {
		log.info("::AuthenticationResource::forgetPassword::");
		return authenticationServiceFacade.forgetPassword(locale, authorization, emailid, address);
	}

	/**
	 * @param authexc
	 * @return
	 */
	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<UserAuthenticationDTO> handleAuthenticationException(AuthenticationException authexc,
			Locale lc) {
		log.error("::AuthenticationResource::handleAuthenticationException::{} {}", authexc.getMessage(), lc);
		UserAuthenticationDTO userAuthDTO = UserAuthenticationDTO.builder().status(CommonsConstants.ERROR)
				.statusDescription(authexc.getMessage()).build();
		return new ResponseEntity<>(userAuthDTO, HttpStatus.FORBIDDEN);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param userDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/updatecredentials")
	public ResponseEntity<UserAuthenticationDTO> updateCredentials(@RequestHeader("Accept-Language") String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization, @RequestBody UserDTO userDTO) {
		log.info("::AuthenticationResource::updateCredentials::");
		return authenticationServiceFacade.updateCredentials(locale, authorization, userDTO);
	}

	/**
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @param username
	 */
	@CrossOrigin // need to push to separate controller as it will be exposed to public
	@GetMapping(value = "/accountapproved/{emailid}/{address}/{username}")
	public ResponseEntity<ApiResultDTO> accountApproved(HttpServletRequest request,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@PathVariable("emailid") String emailId, @PathVariable("address") String address,
			@PathVariable("username") String username) {

		log.info("::AuthenticationResource::accountApproved::");

		UserLogin userlogin = userDetailsService.getUserDetailsByUserName(username);

		return authenticationServiceFacade.accountApproved(request, locale, authorization, emailId, address,
				userlogin.getPassword(), false);

	}

	/**
	 * @param status
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/count/{status}")
	public int checkPendingReqStatus(@PathVariable String status) {
		log.info("UserProfileAttachmentResource-checkPendingReqStatus");
		return userRegisterDetailsService.checkPendingReqStatus(status);
	}

	/**
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{username}")
	public ApiResultDTO deleteByUserNameIgnoreCase(String username) {
		log.info("UserProfileAttachmentResource-deleteByUserNameIgnoreCase");

		return userRegisterDetailsService.deleteByUserNameIgnoreCase(username);
	}

	@CrossOrigin
	@GetMapping(value = "/refervolunteer/{emailid}/{address}")
	public ResponseEntity<ApiResultDTO> referVolunteer(HttpServletRequest request,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale, @RequestHeader String loggedUser,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@PathVariable("emailid") String emailId, @PathVariable("address") String address) {
		log.info("UserProfileAttachmentResource-referVolunteer");

		return new ResponseEntity<>(authenticationServiceFacade.referVolunteer(request, locale, loggedUser,
				authorization, emailId, address), HttpStatus.OK);
	}
}
