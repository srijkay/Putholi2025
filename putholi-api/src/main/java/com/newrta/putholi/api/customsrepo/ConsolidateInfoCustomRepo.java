package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.ConsolidateInfoDetails;
import com.newrta.putholi.api.model.ConsolidateSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ConsolidateInfoCustomRepo {

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<ConsolidateInfoDetails> searchConsolidateInfo(ConsolidateSearchDTO consolidateRefDTO);
}
