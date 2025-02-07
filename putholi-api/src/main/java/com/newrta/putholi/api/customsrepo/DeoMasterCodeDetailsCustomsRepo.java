package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.DeoMasterCodeDetails;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface DeoMasterCodeDetailsCustomsRepo {

	Page<DeoMasterCodeDetails> searchDeoMasterCodes(DeoMasterCodeSearchDTO deoMasterCodeSearchDTO);

}
