package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.DeoMasterCodeTypeDetails;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface DeoMasterCodeTypeDetailsCustomsRepo {

	/**
	 * 
	 * @param deoMasterCodeSearchDTO
	 * @return
	 */
	Page<DeoMasterCodeTypeDetails> searchDeoMasterCodeTypes(DeoMasterCodeSearchDTO deoMasterCodeSearchDTO);
}
