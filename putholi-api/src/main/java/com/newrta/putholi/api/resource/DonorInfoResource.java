package com.newrta.putholi.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DonorInfoDTO;

import com.newrta.putholi.api.model.UserAuthenticationDTO;
import com.newrta.putholi.api.model.UserDTO;
import com.newrta.putholi.api.service.DonorInfoService;
import com.newrta.putholi.api.service.DonorInfoServiceFacade;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/v1/api/donorauthenticate")
public class DonorInfoResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	DonorInfoServiceFacade donorInfoServiceFacade;

	/**
	 * 
	 */
	@Autowired(required = true)
	DonorInfoService donorInfoService;

	/**
	 * 
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param donorInfoDTO
	 * @return
	 */
	@CrossOrigin // need to push to separate controller as it will be exposed to public
	@PostMapping(value = "/savedonor")
	public ResponseEntity<ApiResultDTO> saveDonor(HttpServletRequest request,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestBody DonorInfoDTO donorInfoDTO) {
		log.info("DonorInfoResource-saveDonor {} {}");

		return new ResponseEntity<>(donorInfoServiceFacade.saveDonor(request, locale, authorization, donorInfoDTO),
				HttpStatus.OK);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{emailid}")
	public ResponseEntity<DonorInfo> getDonorInfoByEmailId(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@PathVariable("emailid") String emailId) {
		log.info("DonorInfoResource-getDonorInfoByEmailId");

		return new ResponseEntity<>(donorInfoService.getDonorInfoByEmailId(emailId), HttpStatus.OK);
	}

	/**
	 * 
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
		log.info("::DonorInfoResource::forgetPassword::");
		return donorInfoServiceFacade.forgetPassword(locale, authorization, emailid, address);
	}

	/**
	 * 
	 * @param locale
	 * @param authorization
	 * @param userDTO
	 * @return
	 */

	@CrossOrigin
	@PostMapping(value = "/updatecredentials")
	public ResponseEntity<UserAuthenticationDTO> updateCredentials(@RequestHeader("Accept-Language") String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization, @RequestBody UserDTO userDTO) {
		log.info("::DonorInfoResource::updateCredentials::");
		return donorInfoServiceFacade.updateCredentials(locale, authorization, userDTO);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @return
	 */
	@CrossOrigin 
	@GetMapping
	public ResponseEntity<List<DonorInfo>> getDonorDetails(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader("Authorization") String authorization) {
		log.info("::DonorInfoResource::getDonorDetails::");

		return new ResponseEntity<>(donorInfoService.getDonorDetails(), HttpStatus.OK);
	}
}
