package com.newrta.putholi.api.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DonorInfoDTO;

import com.newrta.putholi.api.model.UserAuthenticationDTO;
import com.newrta.putholi.api.model.UserDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface DonorInfoServiceFacade {

	/**
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param donorInfoDTO
	 * @return
	 */
	ApiResultDTO saveDonor(HttpServletRequest request, String locale, String authorization, DonorInfoDTO donorInfoDTO);

	/**
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @param password
	 * @return
	 */
	ResponseEntity<ApiResultDTO> accountApproved(HttpServletRequest request, String locale, String authorization,
			String emailId, String address, String password);

	/**
	 * 
	 * @param locale
	 * @param authorization
	 * @param emailid
	 * @param address
	 * @return
	 */
	ResponseEntity<ApiResultDTO> forgetPassword(String locale, String authorization, String emailid, String address);

	/**
	 * 
	 * @param locale
	 * @param authorization
	 * @param donorLoginDTO
	 * @return
	 */
	ResponseEntity<UserAuthenticationDTO> updateCredentials(String locale, String authorization,
			UserDTO userDTO);

}
