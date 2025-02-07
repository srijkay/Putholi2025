package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.ApplicationConfig;
import com.newrta.putholi.api.model.GenericSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ApplicationConfigCustomsRepo {

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<ApplicationConfig> searchApplicationConfig(GenericSearchDTO searchDTO);
}
