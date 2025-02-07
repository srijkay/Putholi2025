package com.newrta.putholi.api.service;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.UserRegisterInfoDetails;
import com.newrta.putholi.api.model.UserRegisterViewDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserInfoViewService {
	
	/**
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	Page<UserRegisterInfoDetails> searchUserInfo(String loggedUser, UserRegisterViewDetailsDTO searchDTO);
}
