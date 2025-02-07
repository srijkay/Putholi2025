package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ApproverLevels;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApproverLevelsDTO;
import com.newrta.putholi.api.repository.ApproverLevelsRepository;
import com.newrta.putholi.api.service.ApproverLevelDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Slf4j
@Service
public class ApproverLevelDetailsServiceImpl implements ApproverLevelDetailsService {

	/**
	 * 
	 */
	@Autowired(required = true)
	ApproverLevelsRepository approverRepository;

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

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveApproverDetails(String loggedUser, ApproverLevelsDTO approverLevelsDTO) {
		log.info("ApproverLevelDetailsServiceImpl-saveApproverDetails {}", approverLevelsDTO);

		ApiResultDTO apiResultDTO;
		try {

			ApproverLevels approverLevels = modelMapper.map(approverLevelsDTO, ApproverLevels.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.APPRLEVEL)
					.auditDesc("FeatureManagementServiceImpl-savefeatureManagement")
					.auditValue(new ObjectMapper().writeValueAsString(approverLevelsDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			approverLevels.setFeatureManagement(approverLevels.getFeatureManagement());
			ApproverLevels insertedApproverLevels = approverRepository.save(approverLevels);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(insertedApproverLevels.getApproverId()).build();
		}

		catch (JsonProcessingException jpe) {
			log.error("ApproverLevelDetailsServiceImpl-saveApproverDetails-JsonProcessingException {} {}",
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
	public ApiResultDTO modifyApproverDetails(String loggedUser, ApproverLevelsDTO approverLevelsDTO) {
		log.info("ApproverLevelDetailsServiceImpl-modifyApproverDetails {}", approverLevelsDTO);

		ApiResultDTO apiResultDTO;

		if (approverLevelsDTO.getApproverId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {

			if (approverRepository.existsById(approverLevelsDTO.getApproverId())) {
				try {

					ApproverLevels approverLevels = modelMapper.map(approverLevelsDTO, ApproverLevels.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.APPRLEVEL)
							.auditDesc("FeatureManagementServiceImpl-modifyApproverDetails")
							.auditValue(new ObjectMapper().writeValueAsString(approverLevelsDTO)).createdBy(loggedUser)
							.build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					ApproverLevels insertedApproverLevels = approverRepository.save(approverLevels);

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully").id(insertedApproverLevels.getApproverId())
							.build();
				}

				catch (JsonProcessingException jpe) {
					log.error("ApproverLevelDetailsServiceImpl-modifyApproverDetails-JsonProcessingException {} {}",
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

	@Override
	public ApproverLevels fetchApproverDetails(String loggedUser, Long approverId) {
		log.info("ApproverLevelDetailsServiceImpl-fetchApproverDetails {}", approverId);

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.APPRLEVEL).auditDesc("fetchApproverDetails-fetchApproverDetails")
				.auditValue(approverId != null ? Long.toString(approverId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return approverRepository.findByApproverId(approverId);
	}

	@Override
	public ApiResultDTO removeApproverDetails(String loggedUser, Long approverId) {
		log.info("ApproverLevelDetailsServiceImpl-removeApproverDetails {}", approverId);

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE)
				.auditModule(CommonsConstants.APPRLEVEL).auditDesc("fetchApproverDetails-removeApproverDetails")
				.auditValue(approverId != null ? Long.toString(approverId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		if (approverId != null) {
			approverRepository.deleteById(approverId);
		}
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Delete Info Successfully")
				.build();

	}

	@Override
	public List<ApproverLevels> findByAllApproverLevels(String loggedUser, Long featureId) {
		log.info("ApproverLevelDetailsServiceImpl-findByAllApproverLevels {}", featureId);

		return approverRepository.findAllByApproverLevels(featureId);
	}

}
