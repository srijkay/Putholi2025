package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.FeatureManagement;
import com.newrta.putholi.api.model.FeatureManagementSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

public interface FeatureManagementCustomsRepo {
	
	/**
	 * 
	 * @param searchDTO
	 * @return
	 */
	
	Page<FeatureManagement> searchFeatureManagement(FeatureManagementSearchDTO searchDTO);

}
