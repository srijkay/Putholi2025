package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.newrta.putholi.api.domain.UserManagement;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.UserManagementDTO;
import com.newrta.putholi.api.service.UserManagementService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/usermgmt")
public class UserManagementResource {

	/**
	* 
	*/
	@Autowired(required = true)
	private UserManagementService userManagementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * @param locale
	 * @param authorization
	 * @param searchDTO
	 * @return
	 */
	@PostMapping(value = "/search")
	public Page<UserManagement> searchUserManagement(@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestBody UserManagementDTO searchDTO) {
		log.info("UserManagementResource-searchUserManagement {}", searchDTO);
		return userManagementService.searchUserManagement(searchDTO);
	}

	/**
	 * @param authorization
	 * @param schoolStatus
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{statuscode}/{role}")
	public ResponseEntity<Integer> checkPendingStatus(@RequestHeader("Authorization") String authorization,
			@PathVariable("statuscode") List<String> statuscode, @PathVariable List<String> role) {
		log.info("UserManagementResource::CheckPendingStatus::");

		return new ResponseEntity<>(userManagementService.countofstatusCode(statuscode, role), HttpStatus.OK);
	}

	/**
	 * @param district
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{district}")
	public List<UserRegisterDetails> findByDistrict(@RequestHeader("Authorization") String authorization,
			@PathVariable String district) {
		log.info("UserManagementResource::findByDistrict::");

		return userRegisterDetailsService.findByDistrict(district);
	}

}
