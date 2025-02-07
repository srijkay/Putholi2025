package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.DeoMasterCodeTypeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.repository.DeoMasterCodeTypeDetailsRepository;
import com.newrta.putholi.api.service.DeoMasterCodeTypeService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
@Service
public class DeoMasterCodeTypeDetailsServiceImpl implements DeoMasterCodeTypeService {
	
	 /**
     * 
     */
    @Autowired(required = true)
    DeoMasterCodeTypeDetailsRepository deoMasterCodeTypeDetailsRepository;

	@Override
	public ApiResultDTO createDeoMasterCodeType(String locale, DeoMasterCodeTypeDetails deoMasterCodeTypeDetails) {
		log.info("DeoMasterCodeTypeDetailsServiceImpl-createDeoMasterCodeType");
		ApiResultDTO apiResultDTO;

		boolean result = deoMasterCodeTypeDetailsRepository.existsByDistrict(deoMasterCodeTypeDetails.getDistrict());

		if (result) {
		    List<Object> objArray = new ArrayList<>();
		    objArray.add(deoMasterCodeTypeDetails.getDistrict());
		    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
			    .apiStatusDesc(
				    LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
			    .build();
		} else {
			deoMasterCodeTypeDetailsRepository.save(deoMasterCodeTypeDetails);
		    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
			    .apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
		}
		return apiResultDTO;
	}

	@Override
	public ApiResultDTO modifyDeoMasterCodeType(String locale, DeoMasterCodeTypeDetails deoMasterCodeTypeDetails) {
		log.info("DeoMasterCodeTypeDetailsServiceImpl-modifyDeoMasterCodeType");
		ApiResultDTO apiResultDTO;

		if (deoMasterCodeTypeDetails.getId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.invalid.request.mandatory", null, locale, null))
					.build();
		} else {
			boolean result = deoMasterCodeTypeDetailsRepository.existsById(deoMasterCodeTypeDetails.getId());

			if (result) {
				deoMasterCodeTypeDetailsRepository.save(deoMasterCodeTypeDetails);
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
			} else {
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc(
								LocaleConfig.getResourceValue("error.invalid.request.notexist", null, locale, null))
						.build();
			}
		}

		return apiResultDTO;
	}


}
