package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.MasterCodeTypeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;
import com.newrta.putholi.api.repository.MasterCodeTypeDetailsRepository;
import com.newrta.putholi.api.service.MasterCodeTypeDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
@Service
public class MasterCodeTypeDetailsServiceImpl implements MasterCodeTypeDetailsService {

    /**
     * 
     */
    @Autowired(required = true)
    MasterCodeTypeDetailsRepository masterCodeTypeDetailsRepository;

    /**
     * 
     */
    @Override
    public ApiResultDTO createMasterCodeType(String locale, MasterCodeTypeDetails masterCodeTypeDetails) {
	log.info("MasterCodeTypeDetailsServiceImpl-createMasterCodeType");
	ApiResultDTO apiResultDTO;

	boolean result = masterCodeTypeDetailsRepository.existsByCodeType(masterCodeTypeDetails.getCodeType());

	if (result) {
	    List<Object> objArray = new ArrayList<>();
	    objArray.add(masterCodeTypeDetails.getCodeType());
	    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
		    .apiStatusDesc(
			    LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
		    .build();
	} else {
	    masterCodeTypeDetailsRepository.save(masterCodeTypeDetails);
	    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
		    .apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
	}
	return apiResultDTO;
    }

    /**
     * 
     */
    @Override
    public ApiResultDTO modifyMasterCodeType(String locale, MasterCodeTypeDetails masterCodeTypeDetails) {
	log.info("MasterCodeTypeDetailsServiceImpl-modifyMasterCodeType");
	ApiResultDTO apiResultDTO;

	if (masterCodeTypeDetails.getId() == null) {
	    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
		    .apiStatusDesc(LocaleConfig.getResourceValue("error.invalid.request.mandatory", null, locale, null))
		    .build();
	} else {
	    boolean result = masterCodeTypeDetailsRepository.existsById(masterCodeTypeDetails.getId());

	    if (result) {
		masterCodeTypeDetailsRepository.save(masterCodeTypeDetails);
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

    /**
     * 
     */
    @Override
    public Optional<MasterCodeTypeDetails> findMasterCodeTypeDetailsById(Long id) {
	log.info("MasterCodeTypeDetailsServiceImpl-findMasterCodeTypeDetailsById");
	return masterCodeTypeDetailsRepository.findById(id);
    }

    /**
     * 
     */
    @Override
    public List<MasterCodeResultDTO> findMasterCodeTypes(String active) {
	log.info("MasterCodeTypeDetailsServiceImpl-findMasterCodeTypeByCodeType");
	return masterCodeTypeDetailsRepository.findByActive(active);
    }

    /**
     * 
     */
    @Override
    public Page<MasterCodeTypeDetails> searchMasterCodeType(String locale, MasterCodeSearchDTO masterCodeSearchDTO) {
	log.info("MasterCodeTypeDetailsServiceImpl-searchMasterCodeType");
	return masterCodeTypeDetailsRepository.searchMasterCodeTypes(masterCodeSearchDTO);
    }

}
