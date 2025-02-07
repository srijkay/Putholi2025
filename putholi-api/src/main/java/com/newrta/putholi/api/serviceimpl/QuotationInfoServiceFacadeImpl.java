package com.newrta.putholi.api.serviceimpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.QuotationInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.QuotationInfoService;
import com.newrta.putholi.api.service.QuotationInfoServiceFacade;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class QuotationInfoServiceFacadeImpl implements QuotationInfoServiceFacade {

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationInfoService quotationInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementInfoRepository requirementInfoRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolInfoService;
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
	private static final String QUOTATION_DETAILS = "Quotation Details";

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO approvalHistDtlsDTO) {
		log.info("QuotationInfoServiceFacadeImpl-updateApprovalHistory-==========={}", approvalHistDtlsDTO);

		SchoolApprovalHistoryDetails approvalHistDtls = modelMapper.map(approvalHistDtlsDTO,
				SchoolApprovalHistoryDetails.class);

		SchoolInfo schoolInfo = schoolInfoService.fetchSchoolInfo(approvalHistDtlsDTO.getSchoolInfoId());

		String status = "ADMQUO";
		if (approvalHistDtls.getStatus().equals("REJ")) {
			status = "REJQUO";

			UserRegisterDetails userRegisterDetails = userRegisterDetailsService
					.getUserRegisterDetailsByUserName(schoolInfo.getVolunteerName());

			if (approvalHistDtls.getRole().equals(CommonsConstants.ADMIN)) {
				quotationApprovedEmail(null, null, null, userRegisterDetails.getEmailId(), true, null);
			}
			if (approvalHistDtls.getRole().equals("REVIEW")
					|| approvalHistDtls.getRole().equals(CommonsConstants.APPRV)) {
				status = "ADMQUO";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "true",
						approvalHistDtls.getRole().equals(CommonsConstants.REVIEW) ? "Reviewer" : "Approver",
						"admin_email_count", QUOTATION_DETAILS);

			}
		} else if (approvalHistDtls.getStatus().equals("APR")) {

			if (approvalHistDtls.getRole().equals("ADMIN")) {
				status = "REVQUO";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW,
						CommonsConstants.FALSE, "Admin", "reviewer_email_count", QUOTATION_DETAILS);

			} else if (approvalHistDtls.getRole().equals("REVIEW")) {
				status = "APRQUO";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, CommonsConstants.FALSE,
						"Reviewer", "approver_email_count", QUOTATION_DETAILS);

			} else if (approvalHistDtls.getRole().equals("APPRV")) {
				status = "QUOARV";

				UserRegisterDetails userRegisterDetails = userRegisterDetailsService
						.getUserRegisterDetailsByUserName(schoolInfo.getVolunteerName());

				quotationApprovedEmail(null, null, null, userRegisterDetails.getEmailId(), false, null);
			}

		}
		// update approval history for quotation Table
		quotationInfoService.updateApprovalDetails(approvalHistDtls.getQuotationId(), status);

		// saved the approval history
		approvalHistoryService.saveSchoolApprovalHistoryDetails(approvalHistDtls);

		// get the quotation info based on quotation id
		QuotationInfo quotationinfo = quotationInfoService.findByQuotationId(null, approvalHistDtls.getQuotationId());

		// update the quotation status based on quotation total amount
		updateQuotationStatus(approvalHistDtls, status, quotationinfo, approvalHistDtlsDTO);

		// get the requirement info based on requirement id
		RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
				quotationinfo.getRequirementInfo().getRequirementId());

		// update the school and consolidate status once all requirement's quotations
		// are approved
		updateSchoolStatus(requirement, approvalHistDtls, status);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Approval Details Updated Successfully!").build();

	}

	/**
	 * @param approvalHistDtls
	 * @param status
	 * @param quotationinfo
	 * @param approvalHistDtlsDTO
	 */
	private void updateQuotationStatus(SchoolApprovalHistoryDetails approvalHistDtls, String status,
			QuotationInfo quotationinfo, SchoolApprovalHistoryDetailsDTO approvalHistDtlsDTO) {
		if (approvalHistDtls.getRole().equals("APPRV") && approvalHistDtls.getStatus().equals("APR")) {
			requirementInfoRepository.updateApprovalDetails(quotationinfo.getRequirementInfo().getRequirementId(),
					approvalHistDtlsDTO.getQuotationAmount() == 0 ? CommonsConstants.ORDINI : status);
		} else {
			requirementInfoRepository.updateApprovalDetails(quotationinfo.getRequirementInfo().getRequirementId(), status);
		}

//		if (status.equals("ADMQUO") && approvalHistDtls.getRole().equals(CommonsConstants.ADMIN)) {
//			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD", "Trust Volunteer",
//					"admin_email_count", QUOTATION_DETAILS);
//		}
	}

	/**
	 * @param reqStatus
	 * @param requirement
	 * @param approvalHistDtls
	 * @param status
	 */
	private void updateSchoolStatus(RequirementInfo requirement, SchoolApprovalHistoryDetails approvalHistDtls,
			String status) {

		// check the status in requirement info
		int reqStatus = requirementService.requirementNotInStatus(requirement.getConsolidateRef().getConsolidateId(),
				Arrays.asList("QUOARV", CommonsConstants.ORDINI));
		if (reqStatus == 0) {

			List<RequirementInfo> requirementList = requirementInfoRepository
					.findAllByRequirementInfoList(requirement.getConsolidateRef().getConsolidateId(), "Y");

			int count = requirementService.checkPendingStatus(requirement.getConsolidateRef().getConsolidateId(),
					CommonsConstants.ORDINI);

			// update the consolidate and school statuses
			schoolInfoService.updateApprovalDetails(approvalHistDtls.getSchoolInfoId(),
					count == requirementList.size() ? "ORDINI" : status);
			consolidateService.updateConsolidateStatus(requirement.getConsolidateRef().getConsolidateId(),
					count == requirementList.size() ? "ORDINI" : status);
		}

	}

	/**
	 *
	 */
	@Override
	public SchoolApprovalHistoryDTO fetchDetailsByQuotationId(Long quotationId) {
		log.info("SchoolApprovalHistoryFacadeImpl-fetchDetailsByQuotationId");

		QuotationInfo quotationinfo = quotationInfoService.findByQuotationId(null, quotationId);
		List<SchoolApprovalHistoryDetails> apprHistoryDetails = approvalHistoryService.findByQuotationId(quotationId);

		return SchoolApprovalHistoryDTO.builder().quotationInfo(quotationinfo)
				.schoolApprovalHistoryDetails(apprHistoryDetails).build();
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> quotationApprovedEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, boolean isReject, String address) {

		log.info("QuotationInfoServiceFacadeImpl-quotationApprovedEmail {}", isReject);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
				emailId, "Quotation Approved", address == null ? "UNKNOWN" : address));

		Map<String, Object> model = new HashMap<>();

		model.put("rejected", isReject);

		String account = isReject ? "Quotation Rejected" : "Quotation Approved";

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, account, "quotationApproved", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
