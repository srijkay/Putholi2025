package com.newrta.putholi.api.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ApplicationConfig;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApplicationConfigDTO;
import com.newrta.putholi.api.model.GenericSearchDTO;
import com.newrta.putholi.api.repository.ApplicationConfigRepository;
import com.newrta.putholi.api.service.ApplicationConfigService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class ApplicationConfigServiceImpl implements ApplicationConfigService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ApplicationConfigRepository configRepository;
	/**
	* 
	*/
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveConfigInfo(String loggedUser, ApplicationConfigDTO applicationConfigDTO) {
		log.info("ApplicationConfigServiceImpl-saveConfigInfo");

		ApiResultDTO apiResultDTO;

		try {
			ApplicationConfig applicationConfig = modelMapper.map(applicationConfigDTO, ApplicationConfig.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.CONFIG).auditDesc("ApplicationConfigServiceImpl-saveConfigInfo")
					.auditValue(new ObjectMapper().writeValueAsString(applicationConfigDTO)).createdBy(loggedUser)
					.build();

			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
			ApplicationConfig applicationConfiguration = configRepository.save(applicationConfig);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(applicationConfiguration.getConfigId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("ApplicationConfigServiceImpl-saveConfigInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyConfigInfo(String loggedUser, ApplicationConfigDTO applicationConfigDTO) {

		log.info("ApplicationConfigServiceImpl-modifyConfigInfo");

		ApiResultDTO apiResultDTO;

		if (applicationConfigDTO.getConfigId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {
			if (configRepository.existsById(applicationConfigDTO.getConfigId())) {

				try {

					ApplicationConfig applicationConfig = modelMapper.map(applicationConfigDTO,
							ApplicationConfig.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.CONFIG).auditDesc("ApplicationConfigServiceImpl-modifyConfig")
							.auditValue(new ObjectMapper().writeValueAsString(applicationConfigDTO))
							.createdBy(loggedUser).build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					configRepository.save(applicationConfig);

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully!").build();

				} catch (JsonProcessingException jpe) {
					log.error("ApplicationConfigServiceImpl-modifyConfigInfo-JsonProcessingException {} {}",
							jpe.getCause(), jpe);
					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
							.apiStatusDesc("Error While Processing, Contact System Administrator").build();
				}
			} else {
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc("ID Doesn't exists in the System").build();
			}
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApplicationConfig fetchApplicationConfig(String loggedUser, String configFor) {
		log.info("ApplicationConfigServiceImpl-fetchApplicationConfig");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND).auditModule(CommonsConstants.CONFIG)
				.auditDesc("ApplicationConfigServiceImpl-fetchConfig")
				.auditValue(configFor != null ? configFor : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return configRepository.findByConfigFor(configFor);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeApplicationConfig(String loggedUser, Long configId) {
		log.info("ApplicationConfigServiceImpl-removeApplicationConfig");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE).auditModule(CommonsConstants.CONFIG)
				.auditDesc("ApplicationConfigServiceImpl-removeConfig")
				.auditValue(configId != null ? Long.toString(configId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		if (configId != null) {
			configRepository.deleteById(configId);
		}
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusCode("Removed Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public Page<ApplicationConfig> searchApplicationConfig(String loggedUser, GenericSearchDTO searchDTO) {
		log.info("ApplicationConfigServiceImpl-searchApplicationConfig");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH).auditModule(CommonsConstants.CONFIG)
					.auditDesc("ApplicationConfigServiceImpl-searchApplicationConfig")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		} catch (JsonProcessingException jpe) {
			log.error("ApplicationConfigServiceImpl-searchApplicationConfig-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
		}

		return configRepository.searchApplicationConfig(searchDTO);
	}

}
