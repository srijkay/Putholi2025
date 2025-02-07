package com.newrta.putholi.api.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.RequirementApprovalHistoryFacadeService;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class RequirementApprovalHistoryFacadeImpl implements RequirementApprovalHistoryFacadeService {

	/**
	 * 
	 */
	private static final String REQ_DETAILS = "Requirement Details";

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
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateRefService;
	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = false)
	private RequirementInfoRepository requirementRepo;

	/**
	 * 
	 */
	@Override
	public ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO approvalHistDtlsDTO) {
		log.info("RequirementApprovalHistoryFacadeImpl-updateApprovalHistory");

		SchoolApprovalHistoryDetails approvalHistDtls = modelMapper.map(approvalHistDtlsDTO,
				SchoolApprovalHistoryDetails.class);

		String reqStatus = "ADMREQ";

		if (approvalHistDtls.getStatus() != null && approvalHistDtls.getStatus().equals("REJ")) {
			reqStatus = "REJ";
			if (approvalHistDtls.getRole().equals("REVIEW") || approvalHistDtls.getRole().equals("APPRV")) {
				reqStatus = "ADMREQ";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "true",
						approvalHistDtls.getRole().equals(CommonsConstants.REVIEW) ? "Reviewer" : "Approver",
						"admin_email_count", REQ_DETAILS);

			}

		} else if (approvalHistDtls.getStatus() != null && approvalHistDtls.getStatus().equals("APR")) {

			if (approvalHistDtls.getRole().equals("ADMIN")) {
				reqStatus = "REVREQ";

			} else if (approvalHistDtls.getRole().equals("REVIEW")) {
				reqStatus = "APRREQ";

			} else if (approvalHistDtls.getRole().equals("APPRV")) {
				reqStatus = "APR";

			} else if (approvalHistDtls.getRole().equals("BENIF")) {
				reqStatus = "DEL";
				requirementService.removeRequirementInfo(null, approvalHistDtls.getRequirementId());
			}
		}

		requirementService.updateApprovalDetails(approvalHistDtls.getRequirementId(), reqStatus);

		RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
				approvalHistDtls.getRequirementId());

		ConsolidateRefInfo consolidate = consolidateRefService.fetchConsolidateInfo(null,
				requirement.getConsolidateRef().getConsolidateId());

		// check the status
		if (!approvalHistDtls.getRole().equals("BENIF")) {
			requirementService.checkingStatus(consolidate.getConsolidateId());
		}
		approvalHistoryService.saveSchoolApprovalHistoryDetails(approvalHistDtls);

		// sent email notifications
		sendEmail(requirement.getConsolidateRef().getConsolidateId(), approvalHistDtls);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Approval Details Updated Successfully!").build();
	}

	/**
	 * @param id
	 * @param requirement
	 */
	private void sendEmail(Long id, SchoolApprovalHistoryDetails approvalHistDtls) {

		ConsolidateRefInfo consolidate = consolidateRefService.fetchConsolidateInfo(null, id);

		RequirementInfo requirementInfo = requirementService.fetchRequirementInfo(null,
				approvalHistDtls.getRequirementId());

		if (consolidate.getStatus().equals("REVREQ")) {
			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW, CommonsConstants.FALSE,
					"Admin", CommonsConstants.REVIEWER_COUNT, REQ_DETAILS);

		} else if (consolidate.getStatus().equals("APRREQ")) {
			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, CommonsConstants.FALSE,
					"Reviewer", CommonsConstants.APPROVER_COUNT, REQ_DETAILS);

		} else if (consolidate.getStatus().equals("APR")) {
			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.FALSE,
					"Approver", CommonsConstants.ADMIN_COUNT, REQ_DETAILS);
		}

		// sent email notification to beneficiary
		UserRegisterDetails userRegisterDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(requirementInfo.getCreatedBy());

		if (approvalHistDtls.getRole().equals(CommonsConstants.APPRV) && approvalHistDtls.getStatus().equals("APR")) {
			requirementApprovalEmail(null, null, null, userRegisterDetails.getEmailId(),
					requirementInfo.getRequirementId(), "APR", "false");
		}

		if (approvalHistDtls.getRole().equals(CommonsConstants.ADMIN) && approvalHistDtls.getStatus().equals("REJ")) {
			requirementApprovalEmail(null, null, null, userRegisterDetails.getEmailId(),
					requirementInfo.getRequirementId(), "REJ", "true");
		}
	}

	/**
	 * @param quotationId
	 * @return
	 */
	@Override
	public SchoolApprovalHistoryDTO fetchDetailsForApproval(Long requirementId, String type) {
		log.info("SchoolApprovalHistoryFacadeImpl-fetchDetailsByQuotationIdl");

		RequirementInfo requirementInfo = requirementService.fetchRequirementInfoDescription(requirementId);
		List<SchoolApprovalHistoryDetails> apprHistoryDetails = approvalHistoryService
				.findByRequirementId(requirementId, type);

		return SchoolApprovalHistoryDTO.builder().requirementInfo(requirementInfo)
				.schoolApprovalHistoryDetails(apprHistoryDetails).build();
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> requirementApprovalEmail(HttpServletRequest request, String authorization,
			String address, String emailId, Long requirementId, String reqStatus, String isReject) {

		log.info("RequirementApprovalHistoryFacadeImpl-RequirementApprovalEmail {}", reqStatus);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
				emailId, "Requirements Approved", address == null ? "UNKNOWN" : address));

		List<RequirementInfo> requirementInfo = requirementRepo.findByReqDescription(reqStatus, requirementId);

		Map<String, Object> model = new HashMap<>();

		model.put("rejected", isReject);
		model.put("requirementInfo", requirementInfo);

		String account;
		if ("true".equals(isReject)) {
			account = "Requirements Rejected";
		} else {
			account = "false".equals(isReject) ? "Requirements Approved" : "Completion of Requirements";
		}

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, account, "requirementApproval", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> requirementAssignedAdminEmail(HttpServletRequest request, String authorization,
			String address, String emailId, String type) {

		log.info("RequirementApprovalHistoryFacadeImpl-RequirementApprovalEmail {}");

		Map<String, Object> model = new HashMap<>();

		model.put("type", type);

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX, new MailDTO(mailFrom, emailId,
				type.equals("REQ") ? "Requirements Approved Successfully" : "DEO Response Approved Successfully",
				"requirementsAssignedEmail", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
