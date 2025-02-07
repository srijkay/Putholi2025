package com.newrta.putholi.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.DeoMasterCodeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DeoMasterCodeResultDTO;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;

public interface DeoMasterCodeDetailsService {

	/**
	 * 
	 * @param locale
	 * @param deoMasterCodeDetails
	 * @return
	 */

	ApiResultDTO createDeoMasterCode(String locale, DeoMasterCodeDetails deoMasterCodeDetails);

	/**
	 * 
	 * @param locale
	 * @param deoMasterCodeDetails
	 * @return
	 */

	ApiResultDTO modifyDeoMasterCode(String locale, DeoMasterCodeDetails deoMasterCodeDetails);
	
	/**
	 * 
	 * @param locale
	 * @param deoMasterCodeSearchDTO
	 * @return
	 */

	

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<DeoMasterCodeDetails> findDeoMasterCodeDetailsById(Long id);
	
	/**
	 * 
	 * @param yes
	 * @return
	 */

	List<DeoMasterCodeResultDTO> findMasterCode(String active);

	/**
	 * @param loggedUser 
	 * 
	 */
	Page<DeoMasterCodeDetails> searchDeoMasterCodes(String loggedUser, DeoMasterCodeSearchDTO deoMasterCodeSearchDTO);
	

}
