package com.newrta.putholi.api.serviceimpl;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ApprovalHistoryDetails;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.UserAttachment;
import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.UserApprovalDTO;
import com.newrta.putholi.api.service.ApprovalHistoryDetailsService;
import com.newrta.putholi.api.service.AuthenticationServiceFacade;
import com.newrta.putholi.api.service.UserApprovalServiceFacade;
import com.newrta.putholi.api.service.UserAttachmentService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.MyUserDetailsService;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class UserApprovalServiceFacadeImpl implements UserApprovalServiceFacade {

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
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ApprovalHistoryDetailsService approvalHistDtlsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MyUserDetailsService userDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserAttachmentService userAttachmentService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private AuthenticationServiceFacade authenticateServiceFacade;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeDetailsService masterCodeDetailsService;
	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;
	
	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;
	
	/**
	 * 
	 */
	private static final String REGDETAILS = "Registered Details";
	
	/**
	 * 
	 */
	private static final String USER_DETAILS = "User Details";

	/**
	 *
	 */
	@Override
	@Transactional
	public ApiResultDTO updateUserApproval(ApprovalHistoryDetailsDTO approvalHistDtlsDTO) {
		log.info("UserApprovalServiceFacadeImpl-updateUserApproval");

		ApprovalHistoryDetails approvalHistDtls = modelMapper.map(approvalHistDtlsDTO, ApprovalHistoryDetails.class);

		String status;
		String active;
		UserRegisterDetails userDetails = userRegisterDetailsService
				.getUserRegisterDetailsByUserName(approvalHistDtls.getUsername());
		UserLogin userLogin = userDetailsService.getUserDetailsByUserName(approvalHistDtls.getUsername());

		if (approvalHistDtls.getStatus() != null && approvalHistDtls.getStatus().equals("REJ")) {
			active = "N";
			status = "REJ";
			if (approvalHistDtls.getRole().equals(CommonsConstants.ADMIN)) {

				if (approvalHistDtlsDTO.getType().equals("Beneficiary")) {
					userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), status, active);
					authenticateServiceFacade.accountApproved(null, null, null, userDetails.getEmailId(), null,
							userLogin.getPassword(), true);
				} else {
					userDetailsService.updateUserAccountEnabled(true, approvalHistDtls.getUsername());
					userRegisterDetailsService.updateApprovalDetailsAndRole(approvalHistDtls.getUsername(), "APR", "Y",
							approvalHistDtls.getChangeRequestRole(), null);
				}

			} else if (approvalHistDtls.getRole().equals(CommonsConstants.SUUSR)) {
				userDetailsService.updateUserAccountEnabled(true, approvalHistDtls.getUsername());
				userRegisterDetailsService.updateApprovalDetailsAndRole(approvalHistDtls.getUsername(), "APR", "Y",
						approvalHistDtls.getChangeRequestRole(), null);
			}

			if (approvalHistDtls.getRole().equals(CommonsConstants.REVIEW)
					|| approvalHistDtls.getRole().equals(CommonsConstants.APPRV)) {
				status = approvalHistDtls.getChangeRequestRole() == null ? "PENADM" : "CHRADM";
				userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), status, active);
				
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "true",
						approvalHistDtls.getRole().equals(CommonsConstants.REVIEW) ? "Reviewer" : "Approver",
						"admin_email_count", REGDETAILS);
			}
			if (approvalHistDtls.getRole().equals(CommonsConstants.SUADM)) {
				status = "PENSUS";
				userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), status, active);
			}

		} else if (approvalHistDtls.getStatus().equals("APR")) {

			if (approvalHistDtls.getRole().equals(CommonsConstants.ADMIN)) {
				status = approvalHistDtls.getChangeRequestRole() == null ? "PENREV" : "CHRREV";
				userRegisterDetailsService.updateApprovalDetailsAndchangeRole(approvalHistDtls.getUsername(), status,
						"N", approvalHistDtls.getChangeRequestRole());
				
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW,
						CommonsConstants.FALSE, "Admin", CommonsConstants.REVIEWER_COUNT, REGDETAILS);

			} else if (approvalHistDtls.getRole().equals(CommonsConstants.SUUSR)) {
				userRegisterDetailsService.updateApprovalDetailsAndchangeRole(approvalHistDtls.getUsername(), "PENSUA",
						"N", approvalHistDtls.getChangeRequestRole());

			} else if (approvalHistDtls.getRole().equals(CommonsConstants.REVIEW)) {
				status = approvalHistDtls.getChangeRequestRole() == null ? "PENAPR" : "CHRAPR";
				userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), status, "N");
				
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV, CommonsConstants.FALSE,
						CommonsConstants.REVIEWER, CommonsConstants.APPROVER_COUNT, REGDETAILS);

			} else if (approvalHistDtls.getRole().equals(CommonsConstants.APPRV)
					|| approvalHistDtls.getRole().equals(CommonsConstants.SUADM)) {

				if (approvalHistDtls.getChangeRequestRole() == null) {
					userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), "APR", "Y");
					authenticateServiceFacade.accountApproved(null, null, null, userDetails.getEmailId(), null,
							userLogin.getPassword(), false);
				} else {
					userRegisterDetailsService.updateApprovalDetailsAndRole(approvalHistDtls.getUsername(), "APR", "Y",
							approvalHistDtls.getChangeRequestRole(), null);
					ChangeRole(null, null, null, userDetails.getEmailId(), null,
							approvalHistDtls.getChangeRequestRole());
				}

				// enable the login
				userDetailsService.updateUserAccountEnabled(true, approvalHistDtls.getUsername());
			}
		}

		// update register details

		// updated approval history
		approvalHistDtlsService.saveApprovalHistoryDetails(approvalHistDtls);

		return ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Approval Details Updated Successfully!")
				.build();
	}

	/**
	 *
	 */
	@Override
	public UserApprovalDTO fetchUserDetailsForApproval(String username, String type) {
		log.info("UserApprovalServiceFacadeImpl-fetchUserDetailsForApproval");

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserRegisterDetailsByUserName(username);

		List<ApprovalHistoryDetails> approvalHistDtlsList = approvalHistDtlsService
				.fetchApprovalHistoryDetails(username, type);

		UserAttachment userProfileAttachment = userAttachmentService.getUserAttachment(username,
				CommonsConstants.ID_PROOF);

		String pic;
		if (userProfileAttachment != null) {
			pic = Base64.getEncoder().encodeToString(userProfileAttachment.getFileData());
		} else {
			pic = "";
		}

		return UserApprovalDTO.builder().userRegisterDetails(userRegisterDetails)
				.approvalHistDtlsDTOs(approvalHistDtlsList).pic(pic).build();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateRejectionUserApproval(ApprovalHistoryDetailsDTO approvalHistDtlsDTO) {
		log.info("UserApprovalServiceFacadeImpl-updateRejectionUserApproval");

		ApprovalHistoryDetails approvalHistDtls = modelMapper.map(approvalHistDtlsDTO, ApprovalHistoryDetails.class);

		String status = "APR";
		String active = "Y";

		if (approvalHistDtls.getStatus().equals("CAN")) {
			status = "APR";
			active = "Y";
			if (approvalHistDtls.getRole().equals(CommonsConstants.ADMIN)
					|| approvalHistDtls.getRole().equals(CommonsConstants.SUUSR)) {
				userDetailsService.updateUserAccountEnabled(true, approvalHistDtls.getUsername());
				
			}

			if (approvalHistDtls.getRole().equals(CommonsConstants.REVIEW)
					|| approvalHistDtls.getRole().equals(CommonsConstants.APPRV)) {
				status = "DELADM";
				active = "N";
			} else if (approvalHistDtls.getRole().equals(CommonsConstants.SUADM)) {
				status = "DELSUA";
				active = "N";
			
			}
			userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), status, active);

		} else if (approvalHistDtls.getStatus().equals("DEL")) {

			if (approvalHistDtlsDTO.getRole().equals(CommonsConstants.ADMIN)) {
				status = "DELREV";
				active = "N";
				
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.REVIEW,
						"DELETE", "Admin", CommonsConstants.REVIEWER_COUNT, USER_DETAILS);
				
			} else if (approvalHistDtlsDTO.getRole().equals(CommonsConstants.REVIEW)) {
				status = "DELAPR";
				active = "N";
				
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.APPRV,
						"DELETE", CommonsConstants.REVIEWER, CommonsConstants.APPROVER_COUNT, USER_DETAILS);
				
			} else if (approvalHistDtls.getRole().equals(CommonsConstants.SUUSR)) {
				status = "DELUSR";
				active = "N";
				
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.SUADM,
						CommonsConstants.DELETE, "Super user", CommonsConstants.SUADM_COUNT, USER_DETAILS);
				
			} else if (approvalHistDtls.getRole().equals(CommonsConstants.APPRV)
					|| approvalHistDtls.getRole().equals(CommonsConstants.SUADM)) {
				status = "DEL";
				active = "N";

				userAttachmentService.removeProfilePic(approvalHistDtls.getUsername());

				UserRegisterDetails userRegisterDetails = userRegisterDetailsService
						.getUserRegisterDetailsByUserName(approvalHistDtls.getUsername());

				userAccountDeletionEmail(null, null, null, userRegisterDetails.getEmailId(), null);

			}
			userDetailsService.updateUserAccountEnabled(false, approvalHistDtls.getUsername());
			// update register details
			userRegisterDetailsService.updateApprovalDetails(approvalHistDtls.getUsername(), status, active);

		}

		// updated approval history
		approvalHistDtlsService.saveApprovalHistoryDetails(approvalHistDtls);

		return ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Rejected Info Successfully!").build();

	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> ChangeRole(HttpServletRequest request, String locale, String authorization,
			String emailId, String address, String role) {
		log.info("SchoolInfoServiceImpl-sendMailtoBeneficiary {}", emailId);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails("UPDATE", CommonsConstants.APPROVED, emailId,
				"Account Approved", address == null ? "UNKNOWN" : address));

		Map<String, Object> model = new HashMap<>();
		UserRegisterDetails registerDetails = userRegisterDetailsService.getUserDetailsByEmailId(emailId);

		MasterCodeResultDTO masterdto = masterCodeDetailsService.findMasterCodesByCodeTypeAndCode("ROLE", role);
		model.put("UserName", registerDetails.getUserName());
		model.put("role", masterdto.getDescription());

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, "Change Role Acknowledgement", "change-role", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}
	
	/**
	 * 
	 */

	@Override
	public ResponseEntity<ApiResultDTO> userAccountDeletionEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address) {

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserDetailsByEmailId(emailId);

		jmsTemplate.convertAndSend("auditbox",
				new AuditDetails("UPDATE", CommonsConstants.APPROVED, userRegisterDetails.getEmailId(),
						"Account Deleted Successfully", address == null ? "UNKNOWN" : address));
		Map<String, Object> model = new HashMap<>();

		jmsTemplate.convertAndSend("mailbox", new MailDTO(mailFrom, userRegisterDetails.getEmailId(),
				"Account Deleted Successfully", "deleteUserAccount", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
