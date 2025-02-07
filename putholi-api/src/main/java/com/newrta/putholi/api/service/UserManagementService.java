package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.UserManagement;
import com.newrta.putholi.api.model.UserManagementDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserManagementService {

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<UserManagement> searchUserManagement(UserManagementDTO searchDTO);

	/**
	 * @param statusCode
	 * @return
	 */
	int countofstatusCode(List<String> statusCode, List<String> role);

}
