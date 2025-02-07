package com.newrta.putholi.api.service;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.FeatureManagement;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.FeatureManagementDTO;
import com.newrta.putholi.api.model.FeatureManagementSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface FeatureManagementService {

	/**
	 * 
	 * @param loggedUser
	 * @param featureManagementDTO
	 * @return
	 */

	ApiResultDTO saveFeatureManagement(String loggedUser, FeatureManagementDTO featureManagementDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param featureId
	 * @return
	 */

	FeatureManagement fetchFeatureManagement(String loggedUser, Long featureId);

	/**
	 * 
	 * @param loggedUser
	 * @param featureManagementDTO
	 * @return
	 */

	ApiResultDTO modifyFeatureManagement(String loggedUser, FeatureManagementDTO featureManagementDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */

	Page<FeatureManagement> searchFeatureManagement(String loggedUser, FeatureManagementSearchDTO searchDTO);

}
