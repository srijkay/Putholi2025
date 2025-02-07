package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ConsolidateInfoDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ConsolidateRefDTO;
import com.newrta.putholi.api.model.ConsolidateSearchDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.repository.ConsolidateRefRepository;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class ConsolidateRefServiceImpl implements ConsolidateRefService {

	
	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefRepository consolidateRefRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementInfoRepository requirementRepo;

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
	public ApiResultDTO saveConsolidateInfo(String loggedUser, ConsolidateRefDTO consolidateRefDTO) {
		log.info("ConsolidateRefServiceImpl-saveConsolidateInfo {} {}", loggedUser, consolidateRefDTO);

		ApiResultDTO apiResultDTO;
		try {
			ConsolidateRefInfo consolidateRefInfo = modelMapper.map(consolidateRefDTO, ConsolidateRefInfo.class);

			List<RequirementInfo> requirementInfo = consolidateRefInfo.getRequirementInfo();
			if (requirementInfo != null) {
				for (RequirementInfo requirementsInfo : requirementInfo) {
					requirementsInfo.setConsolidateRef(consolidateRefInfo);
				}
			}

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.CONSOLIREF).auditDesc("ConsolidateRefServiceImpl-saveConsolidateInfo")
					.auditValue(new ObjectMapper().writeValueAsString(consolidateRefDTO)).createdBy(loggedUser).build();

			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			consolidateRefDTO.setConsolidateId(consolidateRefDTO.getConsolidateId());
			ConsolidateRefInfo insertedConsolidateRefInfos = consolidateRefRepository.save(consolidateRefInfo);

			schoolInfoService.updateApprovalDetails(consolidateRefInfo.getSchoolInfo().getSchoolInfoId(), "APR");

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(insertedConsolidateRefInfos.getConsolidateId())
					.build();

		} catch (JsonProcessingException jpe) {
			log.error("ConsolidateRefServiceImpl-saveConsolidateInfo-JsonProcessingException {} {}", jpe.getCause(),
					jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyConsolidateInfo(String loggedUser, ConsolidateRefDTO consolidateRefDTO) {
		log.info("ConsolidateRefServiceImpl-modifyConsolidateInfo");

		ApiResultDTO apiResultDTO;

		if (consolidateRefDTO.getConsolidateId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {
			if (consolidateRefRepository.existsById(consolidateRefDTO.getConsolidateId())) {

				try {

					ConsolidateRefInfo consolidateRefInfo = modelMapper.map(consolidateRefDTO,
							ConsolidateRefInfo.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.CONSOLIREF)
							.auditDesc("ConsolidateRefServiceImpl-modifyConsolidateInfo")
							.auditValue(new ObjectMapper().writeValueAsString(consolidateRefDTO)).createdBy(loggedUser)
							.build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					int rej = checkRequirementPendingStatus(consolidateRefInfo.getConsolidateId(), "REJ");
					int deorej = checkRequirementPendingStatus(consolidateRefInfo.getConsolidateId(), "DEOREJ");

					if (deorej != 0) {
						consolidateRefInfo.setStatus("DEOREJ");
					} else if (rej != 0) {
						consolidateRefInfo.setStatus("REJ");
					} else {
						consolidateRefInfo.setStatus(consolidateRefInfo.getStatus());
					}

					ConsolidateRefInfo consolidateRef = consolidateRefRepository.save(consolidateRefInfo);
					sentEmailtoAdmin(consolidateRefInfo);

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully").id(consolidateRef.getConsolidateId()).build();

				} catch (JsonProcessingException jpe) {
					log.error("ConsolidateRefServiceImpl-modifyConsolidateInfo-JsonProcessingException {} {}",
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

	private void sentEmailtoAdmin(ConsolidateRefInfo consolidateRef) {

		if (consolidateRef.getStatus().equals("ADMREQ")) {
			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD", "Beneficiary",
					"admin_email_count", "Requirement Details");

		}
	}

	/**
	 *
	 */
	@Override
	public Page<ConsolidateInfoDetails> searchConsolidateInfo(String loggedUser, ConsolidateSearchDTO searchDTO) {
		log.info("ConsolidateRefServiceImpl-searchConsolidateInfo");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule(CommonsConstants.CONSOLIREF)
					.auditDesc("ConsolidateRefServiceImpl-searchConsolidateInfo")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("ConsolidateRefServiceImpl-searchConsolidateInfo-JsonProcessingException {} {}", jpe.getCause(),
					jpe);
		}
		return consolidateRefRepository.searchConsolidateInfo(searchDTO);
	}

	/**
	 *
	 */
	@Override
	public ConsolidateRefInfo fetchConsolidateInfo(String loggedUser, Long consolidateId) {
		log.info("ConsolidateRefServiceImpl-fetchRequirementInfo");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND).auditModule("CONSOLIREF")
				.auditDesc("ConsolidateRefServiceImpl-fetchRequirementInfo")
				.auditValue(consolidateId != null ? Long.toString(consolidateId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return consolidateRefRepository.findByConsolidateId(consolidateId);
	}

	/**
	 *
	 */
	@Override
	public void updateConsolidateStatus(Long consolidateId, String status) {
		log.info("ConsolidateRefServiceImpl-updateConsolidateStatus", status);
		consolidateRefRepository.updateApprovalDetails(consolidateId, status);


		
	}

	/**
	 *
	 */
	@Override
	public int checkPendingStatus(List<String> status) {
		log.info("ConsolidateRefServiceImpl-CheckPendingStatus");
		return consolidateRefRepository.checkPendingStatus(status);
	}

	/**
	 *
	 */
	@Override
	public int pendingStatusCount(String status, String volunteerName) {
		log.info("ConsolidateRefServiceImpl-pendingStatusCount");
		return consolidateRefRepository.pendingStatusCount(status, volunteerName);
	}

	/**
	 *
	 */
	@Override
	public SchoolApprovalHistoryDTO fetchDetailsForApproval(Long consolidateId) {
		log.info("SchoolApprovalHistoryFacadeImpl-fetchDetailsForApproval");

		List<SchoolApprovalHistoryDetails> approvalHistory = approvalHistoryService.findByConsolidateId(consolidateId);

		return SchoolApprovalHistoryDTO.builder().schoolApprovalHistoryDetails(approvalHistory).build();
	}

	/**
	 *
	 */
	@Override
	public void deleteConsolidateInfo(String loggedUser, String active, String status, Long consolidateId) {
		log.info("SchoolApprovalHistoryFacadeImpl-deleteConsolidateInfo");
		consolidateRefRepository.deleteConsolidateDetails(active, status, consolidateId);
	}

	/**
	 *
	 */
	@Override
	public List<ConsolidateRefInfo> findConsolidateDetailsBySchoolId(Long schoolInfoId, String status) {
		log.info("SchoolApprovalHistoryFacadeImpl-findConsolidateDetailsBySchoolId");
		return consolidateRefRepository.findConsolidateDetailsBySchoolId(schoolInfoId, status);
	}

	@Override
	public int checkRequirementPendingStatus(Long consolidateId, String reqStatus) {
		log.info("RequirementServiceImpl-checkPendingStatus{}", consolidateId);
		return requirementRepo.checkPendingStatus(consolidateId, reqStatus, "Y");
	}

}
