package com.newrta.putholi.api.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.FeatureManagement;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.FeatureManagementDTO;
import com.newrta.putholi.api.model.FeatureManagementSearchDTO;
import com.newrta.putholi.api.repository.FeatureManagementRepository;

import com.newrta.putholi.api.service.FeatureManagementService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j

public class FeatureManagementServiceImpl implements FeatureManagementService {

	/**
	* 
	*/
	@Autowired(required = true)
	private FeatureManagementRepository featureManagementRepository;

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
	 * @throws
	 *
	 */

	@Override
	public ApiResultDTO saveFeatureManagement(String loggedUser, FeatureManagementDTO featureManagementDTO) {
		log.info("FeatureManagementServiceImpl-saveFeatureManagement {}", featureManagementDTO);

		ApiResultDTO apiResultDTO;
		try {

			FeatureManagement featureManagement = modelMapper.map(featureManagementDTO, FeatureManagement.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.FEATUREMGT)
					.auditDesc("FeatureManagementServiceImpl-saveFeatureManagement")
					.auditValue(new ObjectMapper().writeValueAsString(featureManagementDTO)).createdBy(loggedUser)
					.build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			featureManagement.setApproverLevels(featureManagement.getApproverLevels());
			FeatureManagement insertedFeatureManagement = featureManagementRepository.save(featureManagement);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(insertedFeatureManagement.getFeatureId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("FeatureManagementServiceImpl-saveFeatureManagement-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public FeatureManagement fetchFeatureManagement(String loggedUser, Long featureId) {
		log.info("FeatureManagementServiceImpl-fetchfeatureManagement");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.FEATUREMGT)
				.auditDesc("FeatureManagementServiceImpl-fetchfeatureManagement")
				.auditValue(featureId != null ? Long.toString(featureId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return featureManagementRepository.findByFeatureId(featureId);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyFeatureManagement(String loggedUser, FeatureManagementDTO featureManagementDTO) {

		log.info("FeatureManagementServiceImpl-modifyfeatureManagement");

		ApiResultDTO apiResultDTO;

		if (featureManagementDTO.getFeatureId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {

			if (featureManagementRepository.existsById(featureManagementDTO.getFeatureId())) {
				try {

					FeatureManagement featureManagement = modelMapper.map(featureManagementDTO,
							FeatureManagement.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.FEATUREMGT).auditDesc("FeatureManagementServiceImpl-modifyfeatureManagement")
							.auditValue(new ObjectMapper().writeValueAsString(featureManagementDTO))
							.createdBy(loggedUser).build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					featureManagement.setApproverLevels(featureManagement.getApproverLevels());
					FeatureManagement insertedFeatureManagement = featureManagementRepository.save(featureManagement);

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully").id(insertedFeatureManagement.getFeatureId())
							.build();

				} catch (JsonProcessingException jpe) {
					log.error("FeatureManagementServiceImpl-modifyfeatureManagement-JsonProcessingException {} {}",
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
	public Page<FeatureManagement> searchFeatureManagement(String loggedUser, FeatureManagementSearchDTO searchDTO) {
		log.info("FeatureManagementServiceImpl-searchFeatureManagement");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule("FEATUREMGT").auditDesc("FeatureManagementServiceImpl-searchFeatureManagement")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("FeatureManagementServiceImpl-searchFeatureManagement-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
		}
		return featureManagementRepository.searchFeatureManagement(searchDTO);
	}

}
