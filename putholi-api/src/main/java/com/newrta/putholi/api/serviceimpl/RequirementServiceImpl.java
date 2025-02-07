package com.newrta.putholi.api.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.RequirementInfoDetails;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.RequirementDTO;
import com.newrta.putholi.api.model.RequirementSearchDTO;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.repository.RequirementViewRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
import com.newrta.putholi.api.service.MasterCodeTypeDetailsService;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data

public class RequirementServiceImpl implements RequirementService {

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Value("${app.url}")
	private String appUrl;

	/**
	 * 
	 */
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementInfoRepository requirementInfoRepository;

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
	@Autowired(required = true)
	private ConsolidateRefService consolidateRefService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementViewRepository viewRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeDetailsService masterCodeService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeTypeDetailsService masterCodeTypeServive;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 *
	 */

	@Override
	public ApiResultDTO saveRequirementInfo(String loggedUser, RequirementDTO requirementDTO) {
		log.info("requirementServiceImpl-saveRequirementInfo {} {}", loggedUser,
				requirementDTO.getConsolidateRef().getConsolidateId());

		ApiResultDTO apiResultDTO;
		try {
			if (requirementInfoRepository.existsByAssetNameAndCreatedByIgnoreCase(requirementDTO.getAssetName(),
					requirementDTO.getCreatedBy(), requirementDTO.getConsolidateRef().getConsolidateId(), "Y")) {
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc("Asset Name Already Exists").build();
			} else {
				RequirementInfo requirementInfo = modelMapper.map(requirementDTO, RequirementInfo.class);

				AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
						.auditModule(CommonsConstants.REQINFO).auditDesc("RequirementServiceImpl-saveRequirementInfo")
						.auditValue(new ObjectMapper().writeValueAsString(requirementDTO)).createdBy(loggedUser)
						.build();
				commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

				requirementInfo.setConsolidateRef(requirementInfo.getConsolidateRef());

				RequirementInfo insertedrequirementInfo = requirementInfoRepository.save(requirementInfo);

				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc("Saved Info Successfully").id(insertedrequirementInfo.getRequirementId())
						.build();
			}
		} catch (JsonProcessingException jpe) {
			log.error("RequirementServiceImpl-saveRequirementInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyRequirementInfo(String loggedUser, RequirementDTO requirementDTO) {
		log.info("RequirementServiceImpl-modifyRequirementInfo");

		ApiResultDTO apiResultDTO;

		if (requirementDTO.getRequirementId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {

			if (requirementInfoRepository.existsById(requirementDTO.getRequirementId())) {
				RequirementInfo requirement = fetchRequirementInfo(null, requirementDTO.getRequirementId());
				try {

					if (!requirement.getAssetName().equals(requirementDTO.getAssetName())
							&& requirementInfoRepository.existsByAssetNameAndCreatedByIgnoreCase(
									requirementDTO.getAssetName(), requirementDTO.getCreatedBy(),
									requirementDTO.getConsolidateRef().getConsolidateId(), "Y")) {

						apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
								.apiStatusDesc("Asset Name Already Exists").build();
					} else {

						RequirementInfo requirementInfo = modelMapper.map(requirementDTO, RequirementInfo.class);

						AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
								.auditModule(CommonsConstants.REQINFO)
								.auditDesc("RequirementServiceImpl-modifyRequirementInfo")
								.auditValue(new ObjectMapper().writeValueAsString(requirementInfo))
								.createdBy(loggedUser).build();
						commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

						RequirementInfo insertedrequirementInfo = requirementInfoRepository.save(requirementInfo);

						updateRequirementInfoStatus(requirementInfo);

						apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
								.apiStatusDesc("Updated Info Successfully")
								.id(insertedrequirementInfo.getRequirementId()).build();
					}

				} catch (JsonProcessingException jpe) {
					log.error("RequirementServiceImpl-modifyRequirementInfo-JsonProcessingException {} {}",
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
	public RequirementInfo fetchRequirementInfo(String loggedUser, Long requirementId) {
		log.info("RequirementServiceImpl-fetchRequirementInfo");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.REQINFO).auditDesc("ConsolidateRefServiceImpl-fetchRequirementInfo")
				.auditValue(requirementId != null ? Long.toString(requirementId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return requirementInfoRepository.findByRequirementId(requirementId);
	}

	/**
	 *
	 */
	@Override
	public List<RequirementInfo> findRequirementsByConsolidateId(String loggedUser, Long consolidateId) {
		log.info("RequirementServiceImpl-findRequirementsByConsolidateId {}", consolidateId);

		return requirementInfoRepository.findAllByRequirementInfoList(consolidateId, "Y");
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeRequirementInfo(String loggedUser, Long requirementId) {
		log.info("RequirementServiceImpl-removeRequirementInfo {}", requirementId);

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE)
				.auditModule(CommonsConstants.REQINFO).auditDesc("RequirementServiceImpl-removeRequirementInfo")
				.auditValue(requirementId != null ? Long.toString(requirementId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		requirementInfoRepository.updateRequirementDetails(requirementId, "DEL", "N");

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Removed Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public Page<RequirementInfoDetails> searchRequirement(String loggedUser, RequirementSearchDTO searchDTO) {
		log.info("RequirementServiceImpl-searchRequirement");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule(CommonsConstants.REQINFO).auditDesc("RequirementServiceImpl-searchRequirement")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("RequirementServiceImpl-searchRequirement-JsonProcessingException {} {}", jpe.getCause(), jpe);
		}
		return requirementInfoRepository.searchRequirementInfo(searchDTO);
	}

	/**
	 *
	 */
	@Override
	public List<RequirementInfo> findAllRequirementByStatus(String loggedUser, Long consolidateId, String status) {
		log.info("RequirementServiceImpl-findAllByRequirementInfoList {}", consolidateId);

		List<RequirementInfo> requirementDetails = requirementInfoRepository.findAllRequirementByStatus(consolidateId,
				status, "Y");

		for (RequirementInfo requirementInfoDetails : requirementDetails) {
			// convert the asset Name code to description
			if (!requirementInfoDetails.getAssetType().equals("OTH")) {
				MasterCodeResultDTO assetNameCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode(
						requirementInfoDetails.getAssetType(), requirementInfoDetails.getAssetName());
				requirementInfoDetails.setAssetName(assetNameCodeDTO.getDescription());
			}

			// convert the asset type code to description
			List<MasterCodeResultDTO> assetTypeCodeDTO = masterCodeTypeServive.findMasterCodeTypes("Y");

			for (MasterCodeResultDTO masterCodeResultDTO : assetTypeCodeDTO) {
				if (masterCodeResultDTO.getCode().equals(requirementInfoDetails.getAssetType())) {
					requirementInfoDetails.setAssetType(masterCodeResultDTO.getDescription());
				}
			}

			// convert the requirement status code
			MasterCodeResultDTO statusCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("STS",
					requirementInfoDetails.getReqStatus());
			requirementInfoDetails.setReqStatusDescription(statusCodeDTO.getDescription());

		}
		return requirementDetails;
	}

	/**
	 *
	 */
	@Override
	public List<RequirementInfo> findAllByRequirementInfoList(String loggedUser, Long consolidateId) {
		log.info("RequirementServiceImpl-findAllByRequirementInfoList {}", consolidateId);

		List<RequirementInfo> requirementDetails = requirementInfoRepository.findAllByRequirementInfoList(consolidateId,
				"Y");

		for (RequirementInfo requirementInfoDetails : requirementDetails) {
			// convert the asset Name code to description
			if (!requirementInfoDetails.getAssetType().equals("OTH")) {
				MasterCodeResultDTO assetNameCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode(
						requirementInfoDetails.getAssetType(), requirementInfoDetails.getAssetName());
				requirementInfoDetails.setAssetName(assetNameCodeDTO.getDescription());
			}

			// convert the asset type code to description
			List<MasterCodeResultDTO> assetTypeCodeDTO = masterCodeTypeServive.findMasterCodeTypes("Y");

			for (MasterCodeResultDTO masterCodeResultDTO : assetTypeCodeDTO) {
				if (masterCodeResultDTO.getCode().equals(requirementInfoDetails.getAssetType())) {
					requirementInfoDetails.setAssetType(masterCodeResultDTO.getDescription());
				}
			}

			// convert the requirement status code
			MasterCodeResultDTO statusCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("STS",
					requirementInfoDetails.getReqStatus());
			requirementInfoDetails.setReqStatusDescription(statusCodeDTO.getDescription());

		}
		return requirementDetails;
	}

	/**
	 *
	 */
	@Override
	public void updateApprovalDetails(Long requirementId, String reqStatus) {
		log.info("RequirementServiceImpl-updateApprovalDetails{}", requirementId);
		requirementInfoRepository.updateApprovalDetails(requirementId, reqStatus);

		if (reqStatus.equals("ADMQUO")) {
			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD", "Trust Volunteer",
					"admin_email_count", "Quotation Details");
		}
	}

	/**
	 *
	 */
	@Override
	public void updateRequirementDetails(Long requirementId, String reqStatus, String active) {
		log.info("RequirementServiceImpl-updateRequirementDetails{}", requirementId);
		requirementInfoRepository.updateRequirementDetails(requirementId, reqStatus, active);

	}

	/**
	 *
	 */
	@Override
	public int checkPendingStatus(Long consolidateId, String reqStatus) {
		log.info("RequirementServiceImpl-checkPendingStatus{}", consolidateId);
		return requirementInfoRepository.checkPendingStatus(consolidateId, reqStatus, "Y");
	}

	/**
	 * @param requirementInfo
	 */
	public void updateRequirementInfoStatus(RequirementInfo requirementInfo) {

		if (requirementInfo.getReqStatus().equals(CommonsConstants.ORDINI)) {
			// fetch the consolidate details based on consolidate id
			ConsolidateRefInfo details = consolidateRefService.fetchConsolidateInfo(null,
					requirementInfo.getConsolidateRef().getConsolidateId());

			// send the email after generate the work order
			UserRegisterDetails userRegisterDetails = userRegisterDetailsService
					.getUserRegisterDetailsByUserName(details.getSchoolInfo().getVolunteerName());

			workOrderInitiatedEmail(null, null, null, userRegisterDetails.getEmailId(),
					requirementInfo.getRequirementId());

			// get the count of requirements based on status
			int reqStatus = checkPendingStatus(details.getConsolidateId(), CommonsConstants.GNRORD);
			if (reqStatus == 0) {
				// updated consolidate status
				consolidateRefService.updateConsolidateStatus(details.getConsolidateId(), CommonsConstants.ORDINI);
				// updated school status
				schoolInfoService.updateApprovalDetails(details.getSchoolInfo().getSchoolInfoId(),
						CommonsConstants.ORDINI);

			}

		}
		if (requirementInfo.getReqStatus().equals(CommonsConstants.DEOREJ)) {
			int rej = checkPendingStatus(requirementInfo.getConsolidateRef().getConsolidateId(),
					CommonsConstants.DEOREJ);
			if (rej != 0) {
				consolidateRefService.updateConsolidateStatus(requirementInfo.getConsolidateRef().getConsolidateId(),
						CommonsConstants.DEOREJ);
			} else {
				consolidateRefService.updateConsolidateStatus(requirementInfo.getConsolidateRef().getConsolidateId(),
						CommonsConstants.ADMREQ);
			}
		}

	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO checkingStatus(Long consolidateId) {
		log.info("RequirementServiceImpl-checkingStatus{}", consolidateId);

		String status;
		int rej = checkPendingStatus(consolidateId, "REJ");
		int deorej = checkPendingStatus(consolidateId, "DEOREJ");
		if (rej == 0 && deorej == 0) {
			int penadm = checkPendingStatus(consolidateId, "ADMREQ");
			if (penadm == 0) {
				int penrev = checkPendingStatus(consolidateId, "REVREQ");
				if (penrev == 0) {
					int penapr = checkPendingStatus(consolidateId, "APRREQ");
					int apr = checkPendingStatus(consolidateId, "APR");
					if (penapr == 0 && apr != 0) {
						status = "APR";

					} else {
						status = "APRREQ";
					}
				} else {
					status = "REVREQ";
				}
			} else {
				status = "ADMREQ";
			}
		} else {
			status = rej != 0 ? "REJ" : "DEOREJ";
		}

		consolidateRefService.updateConsolidateStatus(consolidateId, status);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("updated Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public int requirementNotInStatus(Long consolidateId, List<String> reqStatus) {
		log.info("RequirementServiceImpl-requirementNotInStatus{}", consolidateId);

		return requirementInfoRepository.requirementNotInStatus(consolidateId, reqStatus, "Y");
	}

	/**
	 *
	 */
	@Override
	public int checkPenStatus(List<String> reqStatus) {
		log.info("RequirementServiceImpl-CheckPenStatus");
		return requirementInfoRepository.checkPendingReqStatus(reqStatus, "Y");
	}

	/**
	 * @param reqStatus
	 * @param volunteerName
	 * @return
	 */
	@Override
	public int pendingStatusCount(List<String> reqStatus, String volunteerName, String consolidateStatus) {
		log.info("RequirementServiceImpl-CheckPenStatus");
		return requirementInfoRepository.pendingStatusCount(reqStatus, volunteerName, consolidateStatus, "Y");
	}

	/**
	 *
	 */
	@Override
	public void updateRequirementDetails(Long consolidateId, String reqStatus) {
		log.info("RequirementServiceImpl-updateRequirementDetails");
		requirementInfoRepository.updateRequirementDetails(consolidateId, reqStatus);
	}

	/**
	 *
	 */
	@Override
	public void deleteRequirementDetails(String loggedUser, String active, String reqStatus, Long consolidateId) {
		log.info("RequirementServiceImpl-deleteRequirementDetails");
		requirementInfoRepository.deleteRequirementDetails(active, reqStatus, consolidateId);
	}

	/**
	 *
	 */
	@Override
	public int checkInvoiceAndRequirementStatus(String loggedUser, List<String> reqStatus, List<String> invoiceStatus) {
		log.info("RequirementServiceImpl-checkInvoiceAndRequirementStatus");

		return requirementInfoRepository.checkInvoiceAndRequirementStatus(reqStatus, invoiceStatus);

	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> workOrderInitiatedEmail(String authorization, String loggedUser, String address,
			String emailId, Long assetname) {
		log.info("RequirementServiceImpl-workOrderInitiatedEmail {}");

		Map<String, Object> model = new HashMap<>();

		model.put("assetname", assetname);
		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, emailId, "Work Order Initiated Successfully", "workOrderInitiated", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public List<RequirementInfoDetails> getAllRequirements(String loggedUser) {
		log.info("RequirementServiceImpl-getAllRequirements {}");
		return viewRepo.findAll();
	}

	/**
	 *
	 */
	@Override
	public RequirementInfo fetchRequirementInfoDescription(Long requirementId) {
		log.info("RequirementServiceImpl-fetchRequirementInfoDescription");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.REQINFO)
				.auditDesc("ConsolidateRefServiceImpl-fetchRequirementInfoDescription")
				.auditValue(requirementId != null ? Long.toString(requirementId) : "").build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		RequirementInfo requirementInfo = requirementInfoRepository.findByRequirementId(requirementId);

		if (!requirementInfo.getAssetType().equals("OTH")) {
			MasterCodeResultDTO assetNameCodeDTO = masterCodeService
					.findMasterCodesByCodeTypeAndCode(requirementInfo.getAssetType(), requirementInfo.getAssetName());
			requirementInfo.setAssetName(assetNameCodeDTO.getDescription());
		}

		List<MasterCodeResultDTO> assetTypeCodeDTO = masterCodeTypeServive.findMasterCodeTypes("Y");

		for (MasterCodeResultDTO masterCodeResultDTO : assetTypeCodeDTO) {
			if (masterCodeResultDTO.getCode().equals(requirementInfo.getAssetType())) {
				requirementInfo.setAssetType(masterCodeResultDTO.getDescription());
			}
		}

		return requirementInfo;
	}
}
