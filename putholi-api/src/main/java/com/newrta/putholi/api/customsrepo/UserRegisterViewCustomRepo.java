package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.UserRegisterInfoDetails;
import com.newrta.putholi.api.model.UserRegisterViewDetailsDTO;

/**
 * @author NEWRTA SOLTIONS
 *
 */
public interface UserRegisterViewCustomRepo {

	/**
	 * @param userInfoDTO
	 * @return
	 */
	Page<UserRegisterInfoDetails> searchUserInfo(UserRegisterViewDetailsDTO userInfoDTO);
}
