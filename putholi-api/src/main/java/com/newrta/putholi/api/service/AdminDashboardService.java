package com.newrta.putholi.api.service;

import com.newrta.putholi.api.model.AdminDashboardDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface AdminDashboardService {
	

	/**
	 * @param loggedUser
	 * @param consolidateId
	 * @return
	 */
	AdminDashboardDTO fetchFundDetails(String loggedUser, Long consolidateId);
}
