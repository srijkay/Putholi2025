package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.DeoMasterCodeTypeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;

public interface DeoMasterCodeTypeService {

	/**
	 * 
	 * @param locale
	 * @param deoMasterCodeTypeDetails
	 * @return
	 */

	ApiResultDTO createDeoMasterCodeType(String locale, DeoMasterCodeTypeDetails deoMasterCodeTypeDetails);

	/**
	 * 
	 * @param locale
	 * @param deoMasterCodeTypeDetails
	 * @return
	 */

	ApiResultDTO modifyDeoMasterCodeType(String locale, DeoMasterCodeTypeDetails deoMasterCodeTypeDetails);

}
