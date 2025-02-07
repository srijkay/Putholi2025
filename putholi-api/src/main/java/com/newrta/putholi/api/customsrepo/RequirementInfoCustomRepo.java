package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.RequirementInfoDetails;
import com.newrta.putholi.api.model.RequirementSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RequirementInfoCustomRepo {

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<RequirementInfoDetails> searchRequirementInfo(RequirementSearchDTO requirementDTO);

}
