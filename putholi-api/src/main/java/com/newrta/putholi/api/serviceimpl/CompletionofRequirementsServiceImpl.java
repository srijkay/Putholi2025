package com.newrta.putholi.api.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.CompletionofRequirements;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.CompletionofRequirementsDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.repository.CompletionofRequirementsRepository;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.service.CompletionofRequirementsService;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.RequirementApprovalHistoryFacadeService;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class CompletionofRequirementsServiceImpl implements CompletionofRequirementsService {

	/**
	 * 
	 */
	@Autowired(required = true)
	CompletionofRequirementsRepository completionRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementApprovalHistoryFacadeService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService schoolHstryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementInfoRepository requirementInfoRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	ConsolidateRefService consolidateService;

	/**
	 * 
	 */
	@Autowired(required = true)
	SchoolInfoService schoolService;

	/**
	* 
	*/
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 *
	 */

	@Override
	public ApiResultDTO saveCompletionofRequirementsInfo(String loggedUser,
			CompletionofRequirementsDTO completionofrequirementsDTO) {
		log.info("CompletionofRequirementsServiceImpl-saveCompletionofRequirementsInfo {}",
				completionofrequirementsDTO);
		ApiResultDTO apiResultDTO;

		try {

			RequirementInfo requirementInfo = requirementService.fetchRequirementInfo(null,
					completionofrequirementsDTO.getRequirementId());

			CompletionofRequirements completionofRequirements = modelMapper.map(completionofrequirementsDTO,
					CompletionofRequirements.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.ANNOUNCE)
					.auditDesc("CompletionofRequirementsServiceImpl-saveCompletionofRequirementsInfo")
					.auditValue(new ObjectMapper().writeValueAsString(completionofrequirementsDTO))
					.createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			if (completionofRequirements.getCompletionOfPayment().equals("PC")) {
				completionRepo.save(completionofRequirements);
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc("Save Info Successfully").build();
			} else {
				if (requirementInfo.getReqStatus() != null && requirementInfo.getReqStatus().equals("APRREC")
						|| requirementInfo.getReqStatus().equals("ORDINI")) {

					// save the completion requirements
					completionRepo.save(completionofRequirements);

					// update the requirement status
					requirementService.updateApprovalDetails(completionofRequirements.getRequirementId(),
							CommonsConstants.CMPLTD);

					// get the beneficiary mail by using beneficiary name
					UserRegisterDetails userRegisterDetails = userRegisterDetailsService
							.getUserRegisterDetailsByUserName(completionofrequirementsDTO.getBeneficiaryName());

					// send email to beneficiary
					approvalHistoryService.requirementApprovalEmail(null, null, null, userRegisterDetails.getEmailId(),
							requirementInfo.getRequirementId(), "CMPLTD", "COMPLETED");

					// get the requirements info based on requirement id
					RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
							completionofRequirements.getRequirementId());

					// get all requirement based on consolidate id and which are in active
					List<RequirementInfo> requirementDetails = requirementInfoRepository
							.findAllByRequirementInfoList(requirement.getConsolidateRef().getConsolidateId(), "Y");

					int count = requirementService.checkPendingStatus(
							requirement.getConsolidateRef().getConsolidateId(), CommonsConstants.CMPLTD);

					if (count == requirementDetails.size()) {
						ConsolidateRefInfo consolidate = consolidateService.fetchConsolidateInfo(null,
								requirement.getConsolidateRef().getConsolidateId());

						// update the consolidate status
						consolidateService.updateConsolidateStatus(consolidate.getConsolidateId(),
								CommonsConstants.CMPLTD);

						// get school info by id
						SchoolInfo school = schoolService
								.fetchSchoolInfo(consolidate.getSchoolInfo().getSchoolInfoId());

						// update the school status
						schoolService.updateVolunteerDetails(consolidate.getSchoolInfo().getSchoolInfoId(),
								school.getVolunteerName(), "OPEREQ");

						// send email to all user
						sendEmailtoAllUsers(school);

					}
					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Save Info Successfully").build();

				} else {
					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
							.apiStatusDesc("Receipts are not approved yet !!!").build();
				}
			}
		} catch (JsonProcessingException jpe) {
			log.error(
					"CompletionofRequirementsServiceImpl-saveCompletionofRequirementsInfo-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 * @param consolidate
	 */
	private void sendEmailtoAllUsers(SchoolInfo school) {

		// send email to Admin
		schoolHstryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "Completion", "",
				"admin_email_count", school.getSchoolName());

		// send email to reviewer
		schoolHstryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW, "Completion", "",
				"reviewer_email_count", school.getSchoolName());

		// send email to approver
		schoolHstryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, "Completion", "",
				"approver_email_count", school.getSchoolName());

		// get the volunteer mail by using volunteer name
		UserRegisterDetails userRegisterDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(school.getVolunteerName());

		// send email to volunteer
		requirementCompletionEmail(null, null, null, userRegisterDetails.getEmailId(), school.getSchoolName());

		// send email to DEO
		List<SchoolApprovalHistoryDetails> apprvlService = schoolHstryService
				.fetchSchoolApprovalHistoryDetails(school.getSchoolInfoId(), "DEO Mail Sent");

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : apprvlService) {

			if (schoolApprovalHistoryDetails != null)
				requirementCompletionEmail(null, null, null, schoolApprovalHistoryDetails.getActionBy(),
						school.getSchoolName());
		}

	}

	/**
	 * @param request
	 * @param authorization
	 * @param address
	 * @param emailId
	 * @param SchoolName
	 */
	private void requirementCompletionEmail(HttpServletRequest request, String locale, String address, String emailId,
			String schoolName) {

		log.info("RequirementApprovalHistoryFacadeImpl-RequirementApprovalEmail {}", emailId);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
				emailId, "Completion Of Requirements", address == null ? "UNKNOWN" : address));

		Map<String, Object> model = new HashMap<>();
		model.put("SchoolName", schoolName);

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, "Completion Of Requirements", "completionReq", model));

	}
}
