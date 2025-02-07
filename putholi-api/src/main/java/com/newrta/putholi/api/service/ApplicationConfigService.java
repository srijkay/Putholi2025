package com.newrta.putholi.api.service;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.ApplicationConfig;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApplicationConfigDTO;
import com.newrta.putholi.api.model.GenericSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ApplicationConfigService {

	/**
	 * @param applicationConfig
	 * @return
	 */
	ApiResultDTO saveConfigInfo(String loggedUser, ApplicationConfigDTO applicationConfigDTO);

	/**
	 * @param applicationConfig
	 * @return
	 */
	ApiResultDTO modifyConfigInfo(String loggedUser, ApplicationConfigDTO applicationConfigDTO);

	/**
	 * @param ConfigId
	 * @return
	 */
	ApplicationConfig fetchApplicationConfig(String loggedUser, String configFor);

	/**
	 * @param configId
	 * @return
	 */
	ApiResultDTO removeApplicationConfig(String loggedUser, Long configId);
	
	
	/**
	 * @param searchDTO
	 * @return
	 */
	Page<ApplicationConfig> searchApplicationConfig(String loggedUser, GenericSearchDTO searchDTO);

}
