package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.MasterCodeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;
import com.newrta.putholi.api.repository.MasterCodeDetailsRepository;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class MasterCodeDetailsServiceImpl implements MasterCodeDetailsService {

    /**
     * 
     */
    @Autowired(required = true)
    private MasterCodeDetailsRepository masterCodeDetailsRepository;

    /**
     * 
     */
    @Autowired(required = true)
    CommonQueueUtilService commonQueueUtilService;

    /**
     * 
     */
    @Override
    public ApiResultDTO createMasterCode(String locale, MasterCodeDetails masterCodeDetails) {
	log.info("MasterCodeDetailsServiceImpl-createMasterCode");

	boolean result = masterCodeDetailsRepository.existsByCodeAndCodeType(masterCodeDetails.getCode(),
		masterCodeDetails.getCodeType());
	ApiResultDTO apiResultDTO;
	if (result) {
	    try {
		commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
			CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(masterCodeDetails),
			"code.already.exists", masterCodeDetails.getCreatedBy()));
	    } catch (JsonProcessingException jpe) {
		log.error("createMasterCode-JsonProcessingException {}", jpe.getCause());
	    }
	    List<Object> objArray = new ArrayList<>();
	    objArray.add(masterCodeDetails.getCode());
	    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
		    .apiStatusDesc(
			    LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
		    .build();
	} else {

	    try {
		commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
			CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(masterCodeDetails),
			"save.success", masterCodeDetails.getCreatedBy()));
	    } catch (JsonProcessingException jpe) {
		log.error("createMasterCode-JsonProcessingException {}", jpe.getCause());
	    }

	    masterCodeDetailsRepository.save(masterCodeDetails);
	    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
		    .apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
	}

	return apiResultDTO;
    }

    /**
     * 
     */
    @Override
    public MasterCodeResultDTO findMasterCodesByCodeTypeAndCode(String codeType, String code) {
	log.info("MasterCodeDetailsServiceImpl-findMasterCodesByCodeTypeAndCode");
	return masterCodeDetailsRepository.findByCodeTypeAndCode(codeType, code);
    }

    /**
     * 
     */
    @Override
    public Optional<MasterCodeDetails> findMasterCodeDetailsById(Long id) {
	log.info("MasterCodeDetailsServiceImpl-findMasterCodeDetailsById");
	return masterCodeDetailsRepository.findById(id);
    }

    /**
     * 
     */
    @Override
    public List<MasterCodeResultDTO> findActiveMasterCodesByCodeType(String codeType, String active) {
	log.info("MasterCodeDetailsServiceImpl-findActiveMasterCodesByCodeType");
	return masterCodeDetailsRepository.findByCodeTypeAndActive(codeType, active);
    }

    /**
     * 
     */
    @Override
    public Page<MasterCodeDetails> searchMasterCodes(String locale, MasterCodeSearchDTO masterCodeSearchDTO) {
	log.info("MasterCodeDetailsServiceImpl-searchMasterCodes");
	return masterCodeDetailsRepository.searchMasterCodes(locale, masterCodeSearchDTO);
    }

    /**
     * 
     */
    @Override
    public ApiResultDTO modifyMasterCode(String locale, MasterCodeDetails masterCodeDetails) {
	log.info("MasterCodeDetailsServiceImpl-modifyMasterCode");

	ApiResultDTO apiResultDTO;
	if (masterCodeDetails.getId() == null) {
	    apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
		    .apiStatusDesc(LocaleConfig.getResourceValue("error.invalid.request.mandatory", null, locale, null))
		    .build();
	} else {
	    if (masterCodeDetailsRepository.existsById(masterCodeDetails.getId())) {

		try {
		    commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE,
			    CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(masterCodeDetails),
			    "update.success", masterCodeDetails.getUpdatedBy()));
		} catch (JsonProcessingException jpe) {
		    log.error("modifyMasterCode-JsonProcessingException {}", jpe.getCause());
		}

		masterCodeDetailsRepository.save(masterCodeDetails);

		apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
			.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
	    } else {
		try {
		    commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE,
			    CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(masterCodeDetails),
			    "id.notexist", masterCodeDetails.getUpdatedBy()));
		} catch (JsonProcessingException jpe) {
		    log.error("modifyMasterCode-JsonProcessingException {}", jpe.getCause());
		}
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
    public List<MasterCodeResultDTO> getMasterCodeLists(List<String> codeList) {
	log.info("MasterCodeDetailsServiceImpl-getMasterCodeLists");
	return masterCodeDetailsRepository.getMasterCodeLists(codeList);
    }

	@Override
	public List<MasterCodeResultDTO> findActiveMasterCodeType(String active) {
		log.info("MasterCodeDetailsServiceImpl-findActiveMasterCodeType");
		return masterCodeDetailsRepository.findByActive(active);
	}
	

}
