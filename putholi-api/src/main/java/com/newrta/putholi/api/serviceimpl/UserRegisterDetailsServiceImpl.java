package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.repository.UserRegisterDetailsRepository;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonsUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class UserRegisterDetailsServiceImpl implements UserRegisterDetailsService {

	/**
	 *
	 */
	@Autowired(required = true)
	private UserRegisterDetailsRepository userRegisterDetailsRepository;

	/**
	 *
	 */
	@Override
	public boolean verifyUserNameAndActiveExists(String userName, String active) {
		log.info("UserRegisterDetailsServiceImpl-verifyUserNameExists");
		return userRegisterDetailsRepository.existsByUserNameIgnoreCaseAndActive(userName, active);
	}

	/**
	 *
	 */
	@Override
	public UserRegisterDetails saveUserRegisterDetails(UserRegisterDetails userRegisterDetails) {
		log.info("UserRegisterDetailsServiceImpl-saveUserRegisterDetails");
		UserRegisterDetails user = getUserRegisterDetailsByUserName(userRegisterDetails.getUserName());

		if (user == null) {
			if (verifyEmailExists(userRegisterDetails.getEmailId(), "N")) {
				return null;
			} else {
				userRegisterDetails.setOrderId(CommonsUtil.getNextSessionID());
				return userRegisterDetailsRepository.save(userRegisterDetails);
			}
		} else {
			if (verifyEmailExists(userRegisterDetails.getEmailId(), "N")
					&& !user.getEmailId().equals(userRegisterDetails.getEmailId()) && !user.getStatus().equals("REJ")) {
				return null;
			} else {
				userRegisterDetails.setOrderId(user.getOrderId());
				return userRegisterDetailsRepository.save(userRegisterDetails);
			}
		}

	}

	/**
	 *
	 */
	@Override
	public UserRegisterDetails getUserRegisterDetailsByUserName(String username) {
		log.info("UserRegisterDetailsServiceImpl-getUserRegisterDetailsByUserName");
		return userRegisterDetailsRepository.findByUserNameIgnoreCase(username);
	}

	/**
	 *
	 */
	@Override
	public int updateAccountStatus(String active, String username) {
		log.info("UserRegisterDetailsServiceImpl-updateAccountStatus");
		return userRegisterDetailsRepository.updateAccountStatus(active, username);
	}

	/**
	 *
	 */
	@Override
	public boolean verifyEmailExists(String emailId, String active) {
		log.info("UserRegisterDetailsServiceImpl-verifyEmailExists");
		return userRegisterDetailsRepository.existsByEmailIdIgnoreCaseAndActive(emailId, active);
	}

	/**
	 *
	 */
	@Override
	public UserRegisterDetails getUserDetailsByEmailId(String emailid) {
		log.info("UserRegisterDetailsServiceImpl-getUserDetailsByEmailId");
		return userRegisterDetailsRepository.findByEmailIdIgnoreCase(emailid);
	}

	/**
	 *
	 */
	@Override
	public void updateApprovalDetails(String username, String status, String active) {
		log.info("UserRegisterDetailsServiceImpl-updateApprovalDetails");
		userRegisterDetailsRepository.updateApprovalDetails(username, status, active);
	}

	/**
	 *
	 */
	@Override
	public void updateApprovalDetailsAndRole(String username, String status, String active, String role,
			String changeRequestRole) {
		log.info("UserRegisterDetailsServiceImpl-updateApprovalDetailsAndRole");
		userRegisterDetailsRepository.updateApprovalDetailsAndRole(username, status, active, role, changeRequestRole);
	}

	/**
	 *
	 */
	/**
	 *
	 */
	@Override
	public void updateApprovalDetailsAndchangeRole(String username, String status, String active,
			String changeRequestRole) {
		log.info("UserRegisterDetailsServiceImpl-updateApprovalDetailsAndchangeRole");
		userRegisterDetailsRepository.updateApprovalDetailsAndChangeRole(username, status, active, changeRequestRole);
	}

	/**
	 *
	 */
	@Override
	public int checkPendingReqStatus(String status) {
		log.info("UserRegisterDetailsServiceImpl-checkPendingReqStatus");
		return userRegisterDetailsRepository.checkPendingReqStatus(status);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO deleteByUserNameIgnoreCase(String username) {
		log.info("UserRegisterDetailsServiceImpl-deleteByUserNameIgnoreCase");

		userRegisterDetailsRepository.deleteByUserNameIgnoreCase(username);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Removed Info Successfully")
				.build();
	}

	/**
	 * @param district
	 * @return
	 */
	public List<UserRegisterDetails> findByDistrict(String district) {
		log.info("UserRegisterDetailsServiceImpl-fingByDistrict");

		return userRegisterDetailsRepository.findByDistrictIgnoreCase("TRUSTV", "Y", district);
	}

	@Override
	public UserRegisterDetails findByOrderId(String orderId) {
		log.info("UserRegisterDetailsServiceImpl-findByOrderId");
		return userRegisterDetailsRepository.findByOrderId(orderId);
	}

	/**
	 *
	 */
	@Override
	public void updateOrderId(String username, String orderId) {
		log.info("UserRegisterDetailsServiceImpl-updateOrderId");
		userRegisterDetailsRepository.updateOrderId(username, orderId);
	}

	/**
	 *
	 */
	@Override
	public List<UserRegisterDetails> findByRole(String role) {
		log.info("UserRegisterDetailsServiceImpl-findByRole");
		return userRegisterDetailsRepository.findByRoleAndActive(role, "Y");
	}

}
