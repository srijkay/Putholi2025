package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.UserRegisterInfoDetails;
import com.newrta.putholi.api.model.UserRegisterViewDetailsDTO;
import com.newrta.putholi.api.service.UserInfoViewService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
@RestController
@RequestMapping(value = "/v1/api/userreports")
public class UserInfoReportsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserInfoViewService userViewService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<UserRegisterInfoDetails> userInfoReports(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody UserRegisterViewDetailsDTO searchDTO) {
		log.info("UserInfoReportsResource-userInfoReports {} {}", loggedUser, searchDTO);

		return userViewService.searchUserInfo(loggedUser, searchDTO);
	}
}
