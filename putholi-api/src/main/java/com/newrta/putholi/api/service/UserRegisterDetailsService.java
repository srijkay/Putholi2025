package com.newrta.putholi.api.service;

import java.util.List;

import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

public interface UserRegisterDetailsService {

	/**
	 * @param userRegisterDetails
	 * @return
	 */
	UserRegisterDetails saveUserRegisterDetails(UserRegisterDetails userRegisterDetails);

	/**
	 * @param username
	 * @return
	 */
	UserRegisterDetails getUserRegisterDetailsByUserName(String username);

	/**
	 * @param active
	 * @return
	 */
	int updateAccountStatus(String active, String username);

	/**
	 * @param emailid
	 * @return
	 */
	UserRegisterDetails getUserDetailsByEmailId(String emailid);

	/**
	 * @param username
	 * @param status
	 * @param active
	 */
	void updateApprovalDetails(String username, String status, String active);

	/**
	 * @param username
	 * @param status
	 * @param active
	 * @param role
	 * @param changeRequestRole
	 */
	void updateApprovalDetailsAndRole(String username, String status, String active, String role,
			String changeRequestRole);

	/**
	 * @param username
	 * @param status
	 * @param active
	 */
	void updateApprovalDetailsAndchangeRole(String username, String status, String active, String changeRequestRole);

	/**
	 * @param status
	 * @return
	 */
	int checkPendingReqStatus(String status);

	/**
	 * @param username
	 * @return
	 */
	ApiResultDTO deleteByUserNameIgnoreCase(String username);

	/**
	 * @param district
	 * @return
	 */
	List<UserRegisterDetails> findByDistrict(String district);

	/**
	 * 
	 * @param userName
	 * @param active
	 * @return
	 */

	boolean verifyUserNameAndActiveExists(String userName, String active);

	/**
	 * 
	 * @param emailId
	 * @param active
	 * @return
	 */
	boolean verifyEmailExists(String emailId, String active);

	/**
	 * @param orderId
	 * @return
	 */
	UserRegisterDetails findByOrderId(String orderId);

	/**
	 * @param username
	 * @param orderId
	 */
	void updateOrderId(String username, String orderId);

	/**
	 * @param role
	 * @return
	 */
	List<UserRegisterDetails> findByRole(String role);
}
