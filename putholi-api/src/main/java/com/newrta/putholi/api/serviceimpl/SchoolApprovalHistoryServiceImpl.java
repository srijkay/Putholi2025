package com.newrta.putholi.api.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ApplicationConfig;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.repository.SchoolApprovalHistoryRepository;
import com.newrta.putholi.api.service.ApplicationConfigService;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
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
public class SchoolApprovalHistoryServiceImpl implements SchoolApprovalHistoryService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryRepository schoolApvrHstryRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeDetailsService masterCodeService;

	/**
	* 
	*/
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ApplicationConfigService applicationService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveSchoolApprovalHistoryDetails(SchoolApprovalHistoryDetails schoolApprovalHistDtls) {
		log.info("SchoolApprovalHistoryServiceImpl-saveApprovalHistoryDetails");

		schoolApvrHstryRepo.save(schoolApprovalHistDtls);
		return ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Approval Details Saved Successfully!")
				.build();
	}

	/**
	 *
	 */
	@Override
	public List<SchoolApprovalHistoryDetails> fetchSchoolApprovalHistoryDetails(Long schoolInfoId, String type) {
		log.info("SchoolApprovalHistoryServiceImpl-fetchSchoolApprovalHistoryDetails");

		List<SchoolApprovalHistoryDetails> details = schoolApvrHstryRepo.findBySchoolInfoIdAndType(schoolInfoId, type);

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : details) {
			MasterCodeResultDTO roleCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("ROLE",
					schoolApprovalHistoryDetails.getRole());
			schoolApprovalHistoryDetails.setRole(roleCodeDTO.getDescription());
		}

		return details;
	}

	/**
	 *
	 */
	@Override
	public List<SchoolApprovalHistoryDetails> findByQuotationId(Long quotationId) {
		log.info("SchoolApprovalHistoryServiceImpl-findByQuotationId");

		List<SchoolApprovalHistoryDetails> details = schoolApvrHstryRepo.findByQuotationId(quotationId);

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : details) {
			MasterCodeResultDTO roleCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("ROLE",
					schoolApprovalHistoryDetails.getRole());
			schoolApprovalHistoryDetails.setRole(roleCodeDTO.getDescription());
		}

		return details;

	}

	/**
	 *
	 */
	@Override
	public List<SchoolApprovalHistoryDetails> findByRequirementId(Long requirementId, String type) {
		log.info("SchoolApprovalHistoryServiceImpl-findByRequirementId");

		List<SchoolApprovalHistoryDetails> details = schoolApvrHstryRepo.findByRequirementIdAndType(requirementId,
				type);

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : details) {
			MasterCodeResultDTO roleCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("ROLE",
					schoolApprovalHistoryDetails.getRole());
			schoolApprovalHistoryDetails.setRole(roleCodeDTO.getDescription());
		}

		return details;
	}

	/**
	 *
	 */
	@Override
	public List<SchoolApprovalHistoryDetails> findByInvoiceIdAndType(Long invoiceId, String type) {
		log.info("SchoolApprovalHistoryServiceImpl-findByInvoiceIdAndType");

		List<SchoolApprovalHistoryDetails> details = schoolApvrHstryRepo.findByInvoiceIdAndType(invoiceId, type);

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : details) {
			MasterCodeResultDTO roleCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("ROLE",
					schoolApprovalHistoryDetails.getRole());
			schoolApprovalHistoryDetails.setRole(roleCodeDTO.getDescription());
		}

		return details;
	}

	/**
	 *
	 */
	@Override
	public List<SchoolApprovalHistoryDetails> findByConsolidateId(Long consolidateId) {
		log.info("SchoolApprovalHistoryServiceImpl-findByConsolidateId");

		List<SchoolApprovalHistoryDetails> details = schoolApvrHstryRepo.findByConsolidateId(consolidateId);

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : details) {
			MasterCodeResultDTO roleCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("ROLE",
					schoolApprovalHistoryDetails.getRole());
			schoolApprovalHistoryDetails.setRole(roleCodeDTO.getDescription());
		}
		return details;
	}

	/**
	 * 
	 */

	@Override
	public List<SchoolApprovalHistoryDetails> findByRequirementIdAndType(Long requirementId, String type) {
		log.info("SchoolApprovalHistoryServiceImpl-findByRequirementIdAndType");
		return schoolApvrHstryRepo.findByRequirementIdAndType(requirementId, type);
	}

	/**
	 *
	 */
	@Override
	public List<SchoolApprovalHistoryDetails> findByExpensesId(Long expensesId, String type) {
		log.info("SchoolApprovalHistoryServiceImpl-findByExpensesId");

		List<SchoolApprovalHistoryDetails> details = schoolApvrHstryRepo.findByExpensesIdAndType(expensesId, type);

		for (SchoolApprovalHistoryDetails schoolApprovalHistoryDetails : details) {
			MasterCodeResultDTO roleCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("ROLE",
					schoolApprovalHistoryDetails.getRole());
			schoolApprovalHistoryDetails.setRole(roleCodeDTO.getDescription());
		}

		return details;
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> sentEmailForApprovals(HttpServletRequest request, String address, String role,
			String isReject, String roleName, String configValue, String description) {
		log.info("SchoolInfoServiceImpl-sentEmailForApprovals {}", role);

		List<UserRegisterDetails> userDetails = userRegisterService.findByRole(role);

		ApplicationConfig config = applicationService.fetchApplicationConfig(null, configValue);
		
		
		
		if (config != null) {
			int value = Integer.parseInt(config.getConfigValue());
			for (int i = 0; i < userDetails.size(); i++) {
				UserRegisterDetails userRegisterDetails = userDetails.get(i);

				if (value >= i + 1) {

					jmsTemplate.convertAndSend(CommonsConstants.AUDITBOX,
							new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
									userRegisterDetails.getEmailId(), "Approvals (or) Rejections Information",
									address == null ? CommonsConstants.UNKNOWN : address));

					Map<String, Object> model = new HashMap<>();
					model.put("roleName", roleName);
					model.put("isRejected", isReject);
					model.put("description", description);
					
					jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
							new MailDTO(mailFrom, userRegisterDetails.getEmailId(),
									"Approvals (or) Rejections Information", "expensesApproved", model));
				}

			}
		}
		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the Users Email Id").build(), HttpStatus.OK);
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> sendMailtoTrust(HttpServletRequest request, String locale,
			String attachmentFilePath, String address, String emailId, String type, String name) {
		log.info("SchoolInfoServiceImpl-sendMailtoTrust {}", attachmentFilePath);

		jmsTemplate.convertAndSend(CommonsConstants.AUDITBOX, new AuditDetails(CommonsConstants.UPDATE,
				CommonsConstants.APPROVED, emailId, type, address == null ? CommonsConstants.UNKNOWN : address));

		Map<String, Object> model = new HashMap<>();

		model.put("type", type);

		MailDTO mailDTO = MailDTO.builder().contentType("text/html").mailFrom(mailFrom).mailTo(emailId)
				.mailSubject(type).docType("sentMailtoTrust").model(model).build();

		mailDTO.setAttachmentFilePath(attachmentFilePath);
		mailDTO.setFileName(name);

		jmsTemplate.convertAndSend("mailbox", mailDTO);

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the provided Email Id").build(), HttpStatus.OK);
	}

}
