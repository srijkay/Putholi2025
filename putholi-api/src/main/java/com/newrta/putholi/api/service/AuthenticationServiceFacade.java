package com.newrta.putholi.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.UserAuthenticationDTO;
import com.newrta.putholi.api.model.UserDTO;
import com.newrta.putholi.api.model.UserRegisterDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface AuthenticationServiceFacade {

	/**
	 * @param locale
	 * @param authorization
	 * @param userRegisterDetailsDTO
	 * @return
	 */
	ApiResultDTO registeruser(HttpServletRequest request, String locale, String authorization,
			UserRegisterDetailsDTO userRegisterDetailsDTO, MultipartFile identityproof, boolean isManagedUser);

	/**
	 * @param locale
	 * @param authorization
	 * @param userDTO
	 * @return
	 */
	ResponseEntity<UserAuthenticationDTO> authenticateUser(String locale, String authorization, UserDTO userDTO);

	/**
	 * @param locale
	 * @param authorization
	 * @param username
	 * @return
	 */
	ResponseEntity<UserRegisterDetails> applicantProfile(String locale, String authorization, String username);

	/**
	 * @param locale
	 * @param authorization
	 * @param userRegisterDetailsDTO
	 * @return
	 */
	ResponseEntity<ApiResultDTO> modifyRegisteruser(String locale, String authorization,
			UserRegisterDetailsDTO userRegisterDetailsDTO);

	/**
	 * @param locale
	 * @param authorization
	 * @param emailid
	 * @param address
	 * @return
	 */
	ResponseEntity<ApiResultDTO> forgetPassword(String locale, String authorization, String emailid, String address);

	/**
	 * @param locale
	 * @param authorization
	 * @param userDTO
	 * @return
	 */
	ResponseEntity<UserAuthenticationDTO> updateCredentials(String locale, String authorization, UserDTO userDTO);

	/**
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @param username
	 * @return
	 */
	ResponseEntity<ApiResultDTO> accountApproved(HttpServletRequest request, String locale, String authorization,
			String emailId, String address, String pswd, boolean isReject);

	/**
	 * 
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @return
	 */

	ResponseEntity<ApiResultDTO> accountRegistrationEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address);

	/**
	 * @param request
	 * @param locale
	 * @param loggedUser
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @return
	 */
	ApiResultDTO referVolunteer(HttpServletRequest request, String locale, String loggedUser,
			String authorization, String emailId, String address);

	/**
	 * 
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @return
	 */
	ResponseEntity<ApiResultDTO> credentialsUpdatedEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address);
}
