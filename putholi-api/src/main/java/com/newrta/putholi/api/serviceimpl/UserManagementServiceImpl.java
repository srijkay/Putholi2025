package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.UserManagement;
import com.newrta.putholi.api.model.UserManagementDTO;
import com.newrta.putholi.api.repository.UserManagementRepository;
import com.newrta.putholi.api.service.UserManagementService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class UserManagementServiceImpl implements UserManagementService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserManagementRepository userManagementRepo;

	/**
	 *
	 */
	@Override
	public Page<UserManagement> searchUserManagement(UserManagementDTO searchDTO) {
		log.info("UserManagementServiceImpl-searchUserManagement");
		return userManagementRepo.searchUserManagement(searchDTO);
	}

	/**
	 *
	 */
	public int countofstatusCode(List<String> statusCode, List<String> role) {
    	log.info("UserManagementServiceImpl-countofstatusCode");
    	return userManagementRepo.countofstatusCode(statusCode, role);
    }
}
