package com.newrta.putholi.api.service;

import java.util.List;

import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface DonorInfoService {

	/**
	 * 
	 * @param loggedUser
	 * @param donorInfoDTO
	 * @return
	 */

	DonorInfo saveDonorInfo(DonorInfo donorInfo);

	/**
	 * @param emailId
	 * @return
	 */
	DonorInfo getDonorInfoByEmailId(String emailId);

	/**
	 * 
	 * @param username
	 * @return
	 */

	DonorInfo getUserRegisterDetailsByUserName(String username);

	/**
	 * @param donorId
	 * @param orderId
	 */
	void updateOrderId(String orderId, String emailId);

	/**
	 * @param orderId
	 * @return
	 */
	DonorInfo findByOrderId(String orderId);

	/**
	 * @return
	 */
	List<DonorInfo> getDonorDetails();

	/**
	 * @param emailId
	 * @param active
	 * @return
	 */
	boolean verifyEmailExistsAndActive(String emailId, String active);

	/**
	 * @param emailId
	 * @param active
	 * @return
	 */
	ApiResultDTO verifyEmailExists(String locale, String emailId, String active);

}