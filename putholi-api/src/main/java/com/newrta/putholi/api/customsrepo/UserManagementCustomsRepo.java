package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.UserManagement;
import com.newrta.putholi.api.model.UserManagementDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserManagementCustomsRepo {

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<UserManagement> searchUserManagement(UserManagementDTO searchDTO);

}
