package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ModuleDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ModuleDetailSearchResultsDTO;
import com.newrta.putholi.api.model.ModuleDetailsDTO;
import com.newrta.putholi.api.model.ModuleDetailsSearchDTO;
import com.newrta.putholi.api.repository.ModuleDetailsRepository;
import com.newrta.putholi.api.service.ModuleDetailsService;
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
public class ModuleDetailsServiceImpl implements ModuleDetailsService {

	/**
	 * 
	 */
	@Autowired(required = true)
	ModuleDetailsRepository moduleDetailsRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	CommonQueueUtilService commonQueueUtilService;

	/**
	* 
	*/
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Override
	public ResponseEntity<Page<ModuleDetailSearchResultsDTO>> searchModuleCodedetails(
			ModuleDetailsSearchDTO moduleDetailsSearchDTO, String loggeduser) {
		log.info("::ModuleDetailsServiceImpl::searchModuleCodedetails::");

		return new ResponseEntity<>(moduleDetailsRepository.searchModuleDetails(moduleDetailsSearchDTO), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ModuleDetails> viewModuleCodedetails(Long moduleId, String loggeduser, String locale) {
		log.info("::ModuleDetailsServiceImpl::viewModuleCodedetails:: {}", moduleId);

		return new ResponseEntity<>(moduleDetailsRepository.findByModuleId(moduleId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> saveModuleDetails(ModuleDetailsDTO moduleDetailsDTO, String locale,
			String loggeduser) {
		log.info("::ModuleDetailsServiceImpl::saveModuleDetails::");

		ApiResultDTO apiResultDTO;

		ModuleDetails moduleDetails = modelMapper.map(moduleDetailsDTO, ModuleDetails.class);

		/* Verify the code already exists in System */
		boolean coderesult = moduleDetailsRepository.existsByModuleCode(moduleDetails.getModuleCode());

		boolean orderresult = moduleDetailsRepository.existsByOrderNo(moduleDetails.getOrderNo());

		/* verify the order number exists only when id is null */
		if (orderresult && moduleDetails.getModuleId() == null) {

			try {
				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
						CommonsConstants.MODULE_MGMT, new ObjectMapper().writeValueAsString(moduleDetails),
						"orderno.already.exists", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("saveModuleDetails-JsonProcessingExceptions {} {}", jpe.getCause(), jpe);
			}

			List<Object> objArray = new ArrayList<>();
			objArray.add(moduleDetails.getOrderNo());
			apiResultDTO = ApiResultDTO
					.builder().apiStatusCode(CommonsConstants.ERROR).apiStatusDesc(LocaleConfig
							.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
					.build();
		}
		/* verify the code exists only when id is null */
		else if (coderesult && moduleDetails.getModuleId() == null) {

			try {
				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
						CommonsConstants.MODULE_MGMT, new ObjectMapper().writeValueAsString(moduleDetails),
						"modulecode.already.exists", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("saveModuleDetails-JsonProcessingException {}", jpe.getCause());
			}

			List<Object> objArray = new ArrayList<>();
			objArray.add(moduleDetails.getModuleCode());
			apiResultDTO = ApiResultDTO
					.builder().apiStatusCode(CommonsConstants.ERROR).apiStatusDesc(LocaleConfig
							.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
					.build();
		} else {

			moduleDetails = moduleDetailsRepository.save(moduleDetails);

			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT, CommonsConstants.MODULE_MGMT,
								new ObjectMapper().writeValueAsString(moduleDetails), "save.success", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("saveModuleDetails-JsonProcessingException {}", jpe.getCause());
			}

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
			apiResultDTO.setId(moduleDetails.getModuleId());
		}

		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * delete module details
	 */
	@Override
	public ResponseEntity<ApiResultDTO> deleteModuleCodedetails(Long moduleId, String loggeduser, String locale) {
		log.info("::ModuleDetailsServiceImpl::deleteModuleCodedetails::");

		commonQueueUtilService.sendAuditDetailsToQueue(
				new AuditDetails(CommonsConstants.DELETE, CommonsConstants.MODULE_MGMT, null, null, loggeduser));

		moduleDetailsRepository.deleteById(moduleId);
		return new ResponseEntity<>(
				ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc(LocaleConfig.getResourceValue("delete.success", null, locale, null)).build(),
				HttpStatus.OK);
	}

	/**
	 * update module code
	 */
	@Override
	public ResponseEntity<ApiResultDTO> updateModuleDetails(ModuleDetailsDTO moduleDetailsDTO, String loggeduser,
			String locale) {
		log.info("::ModuleDetailsServiceImpl::updateModuleDetails:: {}", moduleDetailsDTO.getModuleId());

		ApiResultDTO apiResultDTO;

		ModuleDetails moduleDetails = modelMapper.map(moduleDetailsDTO, ModuleDetails.class);

		ModuleDetails modDtls = moduleDetailsRepository.findByOrderNo(moduleDetails.getOrderNo());

		if (null != modDtls && !modDtls.getModuleId().equals(moduleDetails.getModuleId())) {

			try {
				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE,
						CommonsConstants.MODULE_MGMT, new ObjectMapper().writeValueAsString(moduleDetails),
						"error.already.exists", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("updateModuleDetails-JsonProcessingException {}", jpe.getCause());
			}

			List<Object> objArray = new ArrayList<>();
			objArray.add(moduleDetails.getOrderNo());
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(
							LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
					.build();
		} else {
			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.MODULE_MGMT,
								new ObjectMapper().writeValueAsString(moduleDetails), "update.success", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("updateModuleDetails-JsonProcessingException {}", jpe.getCause());
			}
			moduleDetailsRepository.save(moduleDetails);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
		}
		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<List<ModuleDetails>> getActiveModules(String loggeduser, String locale) {
		log.info("ModuleDetailsServiceImpl:: getActiveModules");

		return new ResponseEntity<>(moduleDetailsRepository.findByActive("Y"), HttpStatus.OK);

	}

}
