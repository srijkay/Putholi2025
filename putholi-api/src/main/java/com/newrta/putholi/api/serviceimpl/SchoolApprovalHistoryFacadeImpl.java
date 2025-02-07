package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.Base64;
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
import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.domain.QuotationAttachments;
import com.newrta.putholi.api.domain.QuotationInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.ExpensesDetailsService;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
import com.newrta.putholi.api.service.QuotationAttachmentService;
import com.newrta.putholi.api.service.QuotationInfoService;
import com.newrta.putholi.api.service.RequirementApprovalHistoryFacadeService;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryFacadeService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class SchoolApprovalHistoryFacadeImpl implements SchoolApprovalHistoryFacadeService {
	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationInfoService quotationService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateRefService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementInfoRepository requirementRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ExpensesDetailsService expensesService;

	/**
	* 
	*/
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeDetailsService masterCodeDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationAttachmentService quotationAttachmentService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementApprovalHistoryFacadeService requirementFacadeService;

	/**
	 * 
	 */
	private static final String EXPENSES = "Expenses";

	/**
	 * 
	 */
	private static final String SCHOOLDETAILS = "School Details";

	/**
	 * 
	 */
	private static final String DEORESPONSE = "Deo Response";

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 *
	 */

	@Override
	public ApiResultDTO updateApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO) {
		log.info("SchoolApprovalHistoryFacadeImpl-updateApprovalHistory");

		String apprvlStatus = CommonsConstants.PENADM;

		SchoolInfo schoolInfo = schoolInfoService.fetchSchoolInfo(schoolHistoryDetailsDTO.getSchoolInfoId());

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(schoolInfo.getCreatedBy());

		if (schoolHistoryDetailsDTO.getStatus() != null && schoolHistoryDetailsDTO.getStatus().equals("REJ")) {
			apprvlStatus = "REJ";

			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.ADMIN)) {
				schoolRegistrationEmail(null, null, null, null, userRegisterDetails.getEmailId(), true);
			}

			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.REVIEW)) {
				apprvlStatus = "PENADM";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.TRUE,
						CommonsConstants.REVIEWER, CommonsConstants.ADMIN_COUNT, SCHOOLDETAILS);

			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
				apprvlStatus = "PENADM";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.TRUE,
						CommonsConstants.APPROVER, CommonsConstants.ADMIN_COUNT, SCHOOLDETAILS);
			}

		} else if (schoolHistoryDetailsDTO.getStatus() != null && schoolHistoryDetailsDTO.getStatus().equals("APR")) {
			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.ADMIN)) {
				apprvlStatus = "PENREV";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW,
						CommonsConstants.FALSE, "Admin", CommonsConstants.REVIEWER_COUNT, SCHOOLDETAILS);

			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.REVIEW)) {
				apprvlStatus = "PENAPR";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, CommonsConstants.FALSE,
						CommonsConstants.REVIEWER, CommonsConstants.APPROVER_COUNT, SCHOOLDETAILS);

			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
				apprvlStatus = "APR";

				schoolRegistrationEmail(null, null, null, null, userRegisterDetails.getEmailId(), false);

			}
		}

		SchoolApprovalHistoryDetails schoolHistoryDetails = modelMapper.map(schoolHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		schoolInfoService.updateApprovalDetails(schoolHistoryDetails.getSchoolInfoId(), apprvlStatus);
		approvalHistoryService.saveSchoolApprovalHistoryDetails(schoolHistoryDetails);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("School Approval Details Updated Successfully!").build();

	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateDEOApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO) {
		log.info("SchoolApprovalHistoryFacadeImpl-updateDEOApprovalHistory");

		String apprvlStatus = CommonsConstants.ADMDEO;

		SchoolInfo schoolInfo = schoolInfoService.fetchSchoolInfo(schoolHistoryDetailsDTO.getSchoolInfoId());

		if (schoolHistoryDetailsDTO.getStatus() != null && schoolHistoryDetailsDTO.getStatus().equals("REJ")) {
			apprvlStatus = "DEOREJ";

			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.ADMIN)) {
				sendMailtoBeneficiary(null, null, null, schoolInfo.getSchoolInfoId(), true);

			}
			if (schoolHistoryDetailsDTO.getRole().equals("REVIEW")) {
				apprvlStatus = CommonsConstants.ADMDEO;
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.TRUE,
						"Reviewer", CommonsConstants.ADMIN_COUNT, DEORESPONSE);

			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
				apprvlStatus = CommonsConstants.ADMDEO;
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.TRUE,
						CommonsConstants.APPROVER, CommonsConstants.ADMIN_COUNT, DEORESPONSE);

			}
		} else if (schoolHistoryDetailsDTO.getStatus() != null && schoolHistoryDetailsDTO.getStatus().equals("APR")) {
			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.ADMIN)) {
				apprvlStatus = "REVDEO";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW,
						CommonsConstants.FALSE, "Admin", CommonsConstants.REVIEWER_COUNT, DEORESPONSE);
			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.REVIEW)) {
				apprvlStatus = "APRDEO";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, CommonsConstants.FALSE,
						"Reviewer", CommonsConstants.APPROVER_COUNT, DEORESPONSE);
			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
				apprvlStatus = "DEOAPR";

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.FALSE,
						"Approver", CommonsConstants.ADMIN_COUNT, DEORESPONSE);

				sendMailtoBeneficiary(null, null, null, schoolInfo.getSchoolInfoId(), false);

			}
		}

		SchoolApprovalHistoryDetails schoolHistoryDetails = modelMapper.map(schoolHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		schoolInfoService.updateApprovalDetails(schoolHistoryDetails.getSchoolInfoId(),
				apprvlStatus.equals("DEOREJ") ? "APR" : apprvlStatus);

		// updated the consolidate status
		consolidateRefService.updateConsolidateStatus(schoolHistoryDetailsDTO.getConsolidateId(), apprvlStatus);
		// updated the requirements status

		requirementService.updateRequirementDetails(schoolHistoryDetailsDTO.getConsolidateId(), apprvlStatus);

		approvalHistoryService.saveSchoolApprovalHistoryDetails(schoolHistoryDetails);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Approval DEO Details Updated Successfully!").build();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateVolunteerHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO) {
		log.info("SchoolApprovalHistoryFacadeImpl-updateVolunteerHistory");

		SchoolApprovalHistoryDetails schoolHistoryDetails = modelMapper.map(schoolHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		String sklStatus = "VOLREJ";
		if (schoolHistoryDetails.getStatus() != null && schoolHistoryDetails.getStatus().equals("REJ")) {
			sklStatus = "VOLREJ";

			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, CommonsConstants.TRUE,
					"Volunteer", "admin_email_count", "Assigned School");

		} else if (schoolHistoryDetails.getStatus() != null && schoolHistoryDetails.getStatus().equals("APR")) {
			sklStatus = "VOLACP";

		}

		List<RequirementInfo> requirement = requirementService.findRequirementsByConsolidateId(null,
				schoolHistoryDetails.getConsolidateId());

		for (RequirementInfo requirementInfo : requirement) {
			List<QuotationInfo> quotation = quotationService.findQuotationInfo(null,
					requirementInfo.getRequirementId());
			if (quotation.isEmpty()) {
				requirementService.updateRequirementDetails(schoolHistoryDetails.getConsolidateId(), sklStatus);
			} else {
				if (requirementInfo.getReqStatus().equals("QUOARV") || requirementInfo.getReqStatus().equals("REDALL")
						|| requirementInfo.getReqStatus().equals("GNRORD")
						|| requirementInfo.getReqStatus().equals("ORDINI")) {

					sklStatus = requirementInfo.getReqStatus();
				}
			}
		}

		schoolInfoService.updateApprovalDetails(schoolHistoryDetails.getSchoolInfoId(), sklStatus);
		consolidateRefService.updateConsolidateStatus(schoolHistoryDetails.getConsolidateId(), sklStatus);

		// save details in school approval history table
		approvalHistoryService.saveSchoolApprovalHistoryDetails(schoolHistoryDetails);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Approval Details Updated Successfully!").build();
	}

	/**
	 *
	 */
	@Override
	public SchoolApprovalHistoryDTO fetchDetailsForApproval(Long schoolId, String type) {
		log.info("SchoolApprovalHistoryFacadeImpl-fetchDetailsForApproval");

		SchoolInfo schoolInfo = schoolInfoService.fetchSchoolInfoByUser(null, schoolId);

		List<SchoolApprovalHistoryDetails> approvalHistory = approvalHistoryService
				.fetchSchoolApprovalHistoryDetails(schoolId, type);

		return SchoolApprovalHistoryDTO.builder().schoolInfo(schoolInfo).schoolApprovalHistoryDetails(approvalHistory)
				.build();
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> sentEmailtoDEO(String authorization, String loggedUser, String emailid,
			String address, Long schoolInfoId, String mailBody, Long consolidateId, String[] mailCc) {
		log.info("SchoolInfoServiceImpl-sentEmailtoDEO ");

		SchoolInfo schoolinfo = schoolInfoService.fetchSchoolInfo(schoolInfoId);

		List<QuotationAttachments> attachments = quotationAttachmentService.findBySchoolInfoId(schoolInfoId, "SI");

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(schoolinfo.getCreatedBy());

		List<RequirementInfo> requirementInfo = requirementRepo.findAllByRequirementInfo(consolidateId);

		String subject = schoolinfo.getSchoolName().concat(", ").concat(schoolinfo.getAddressInfo().getLocality());

		jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.APPROVED, emailid, subject,
				address == null ? "UNKNOWN" : address));

		MasterCodeResultDTO masterdto = masterCodeDetailsService.findMasterCodesByCodeTypeAndCode("STY",
				schoolinfo.getSchoolType());

		Map<String, Object> model = new HashMap<>();
		model.put("registrationNumber", schoolinfo.getSchoolRegNo());
		model.put("schoolName", schoolinfo.getSchoolName());
		model.put("schoolType", masterdto.getDescription());
		model.put("contactNumber", schoolinfo.getContactsInfo().getPrimaryNumber());
		model.put("locality", schoolinfo.getAddressInfo().getLocality());
		model.put("pincode", schoolinfo.getAddressInfo().getPincode());
		model.put("consolidateId", consolidateId);
		model.put("firstName", userRegisterDetails.getFirstName() + userRegisterDetails.getLastName());
		model.put("email", userRegisterDetails.getEmailId());
		model.put("phoneNumber", userRegisterDetails.getMobileNumber());
		model.put("requirementInfo", requirementInfo);
		model.put("mailBody", mailBody.matches("undefined") ? " " : mailBody);
		model.put("username", loggedUser);

		MailDTO mailDTO = MailDTO.builder().contentType("text/html").mailFrom(mailFrom).mailTo(emailid)
				.mailSubject(subject).docType("sendMailtoDEO").mailCc(mailCc).model(model).build();

		List<Object> imageName = new ArrayList<>();
		List<Object> imageData = new ArrayList<>();

		Map<String, List<Object>> imageAttachemnts = new HashMap<>();
		for (QuotationAttachments quotationAttachments : attachments) {
			String decoded = (Base64.getEncoder().encodeToString(quotationAttachments.getFileData()));
			imageName.add(quotationAttachments.getFileName());
			imageData.add(decoded);
		}
		imageAttachemnts.put("imageData", imageData);
		imageAttachemnts.put("imageNames", imageName);

		mailDTO.setAttachments(imageAttachemnts);

		jmsTemplate.convertAndSend("mailbox", mailDTO);

		schoolInfoService.updateApprovalDetails(schoolinfo.getSchoolInfoId(), "ADMDEO");
		consolidateRefService.updateConsolidateStatus(consolidateId, "ADMDEO");

		SchoolApprovalHistoryDetails details = new SchoolApprovalHistoryDetails();
		details.setActionBy(emailid);
		details.setRole("DEO");
		details.setType("DEO Mail Sent");
		details.setSchoolInfoId(schoolInfoId);
		details.setStatus("DEOMAL");

		approvalHistoryService.saveSchoolApprovalHistoryDetails(details);

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered EMail ID").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> sendMailtoBeneficiary(String authorization, String loggedUser, String address,
			Long schoolInfoId, boolean isReject) {
		log.info("SchoolInfoServiceImpl-sendMailtoBeneficiary {}");

		SchoolInfo schoolinfo = schoolInfoService.fetchSchoolInfo(schoolInfoId);

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(schoolinfo.getCreatedBy());

		jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.APPROVED,
				userRegisterDetails.getEmailId(), "Admin Deo response", address == null ? "UNKNOWN" : address));
		Map<String, Object> model = new HashMap<>();

		model.put("UserName", userRegisterDetails.getUserName());
		model.put("rejected", isReject);

		String account = isReject ? "DEO Rejected" : "DEO Approved";

		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, userRegisterDetails.getEmailId(), account, "sendMailtoBeneficiary", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the  Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateDeleteHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO) {
		log.info("SchoolApprovalHistoryFacadeImpl-updateDeleteHistory");

		SchoolApprovalHistoryDetails schoolHistoryDetails = modelMapper.map(schoolHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		SchoolInfo schoolInfo = schoolInfoService.fetchSchoolInfo(schoolHistoryDetailsDTO.getSchoolInfoId());

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(schoolInfo.getCreatedBy());

		String active = "Y";
		String status = "DSCADM";
		if (schoolHistoryDetails.getStatus() != null && schoolHistoryDetails.getStatus().equals("REJ")) {
			status = "APR";
			if (schoolHistoryDetails.getRole().equals("REVIEW") || schoolHistoryDetails.getRole().equals("APPRV")) {
				status = "DSCADM";
			}
		} else if (schoolHistoryDetails.getStatus() != null && schoolHistoryDetails.getStatus().equals("APR")) {
			if (schoolHistoryDetails.getRole().equals(CommonsConstants.ADMIN)) {
				status = "DSCREV";
			} else if (schoolHistoryDetails.getRole().equals(CommonsConstants.REVIEW)) {
				status = "DSCAPR";
			} else if (schoolHistoryDetails.getRole().equals("APPRV")
					|| schoolHistoryDetails.getRole().equals("BENIF")) {
				active = "N";
				status = "DEL";

				deleteSchoolEmail(null, null, null, userRegisterDetails.getEmailId());
			}
		}

		schoolInfoService.deleteSchoolDetails(active, status, schoolHistoryDetails.getSchoolInfoId());

		consolidateRefService.deleteConsolidateInfo(null, active, status, schoolHistoryDetails.getSchoolInfoId());

		List<ConsolidateRefInfo> consolidateInfo = consolidateRefService
				.findConsolidateDetailsBySchoolId(schoolHistoryDetails.getSchoolInfoId(), status);

		for (ConsolidateRefInfo consolidateRefInfo : consolidateInfo) {
			requirementService.deleteRequirementDetails(null, active, status, consolidateRefInfo.getConsolidateId());
		}
		approvalHistoryService.saveSchoolApprovalHistoryDetails(schoolHistoryDetails);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("School Details Deleted Successfully!").build();

	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateExpensesApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO) {
		log.info("SchoolInfoServiceImpl-updateExpensesApprovalHistory {}", schoolHistoryDetailsDTO);

		String apprvlStatus = "EXPREV";

		if (schoolHistoryDetailsDTO.getStatus() != null && schoolHistoryDetailsDTO.getStatus().equals("REJ")) {
			apprvlStatus = "REJ";

			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.REVIEW)) {
				apprvlStatus = "REJEXR";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "true",
						CommonsConstants.REVIEWER, "admin_email_count", EXPENSES);
			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
				apprvlStatus = "REJEXA";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW, "true", "Approver",
						"reviewer_email_count", EXPENSES);
			}

		} else if (schoolHistoryDetailsDTO.getStatus() != null && schoolHistoryDetailsDTO.getStatus().equals("APR")) {
			if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.REVIEW)) {
				apprvlStatus = "EXPAPR";
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, "false",
						CommonsConstants.REVIEWER, "approver_email_count", EXPENSES);

			} else if (schoolHistoryDetailsDTO.getRole().equals(CommonsConstants.APPRV)) {
				apprvlStatus = "APR";

			}
		}

		SchoolApprovalHistoryDetails schoolHistoryDetails = modelMapper.map(schoolHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		expensesService.updateStatusByExpensesId(schoolHistoryDetailsDTO.getExpensesId(), apprvlStatus);
		approvalHistoryService.saveSchoolApprovalHistoryDetails(schoolHistoryDetails);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Expenses Approval Details Updated Successfully!").build();
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> deleteSchoolEmail(String authorization, String loggedUser, String address,
			String emailId) {
		log.info("SchoolInfoServiceImpl-deleteSchoolEmail {}");

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserDetailsByEmailId(emailId);

		Map<String, Object> model = new HashMap<>();

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX, new MailDTO(mailFrom, userRegisterDetails.getEmailId(),
				"School Deleted Successfully", "deleteSchoolMail", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> schoolRegistrationEmail(HttpServletRequest request, String locale,
			String authorization, String address, String emailId, boolean isReject) {

		log.info("SchoolInfoServiceImpl-schoolRegistrationEmail {}", isReject);

		jmsTemplate.convertAndSend(CommonsConstants.AUDITBOX,
				new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED, emailId, "School Approved",
						address == null ? CommonsConstants.UNKNOWN : address));

		Map<String, Object> model = new HashMap<>();

		model.put("rejected", isReject);

		String account = isReject ? "School Rejected" : "School Approved";

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, account, "schoolApproved", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public SchoolApprovalHistoryDTO fetchDetailsForExpensesApproval(Long expensesId, String type) {
		log.info("InvoiceDetailsServiceFacadeImpl-fetchDetailsForApproval");

		ExpensesDetails details = expensesService.findByExpensesId(null, expensesId);
		List<SchoolApprovalHistoryDetails> apprHistoryDetails = approvalHistoryService.findByExpensesId(expensesId,
				type);

		return SchoolApprovalHistoryDTO.builder().expensesDetails(details)
				.schoolApprovalHistoryDetails(apprHistoryDetails).build();
	}

}
