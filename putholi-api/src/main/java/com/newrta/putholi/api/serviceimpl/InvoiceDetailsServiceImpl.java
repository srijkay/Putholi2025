package com.newrta.putholi.api.serviceimpl;

import java.util.Arrays;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.InvoiceDetailsDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.repository.InvoiceDetailsRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.InvoiceDetailsService;
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
@Slf4j
@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

	public static final String TRUSTVOL = "Trust Volunteer";

	/**
	 * 
	 */
	@Autowired(required = true)
	private InvoiceDetailsRepository invoiceDetailsRepo;

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
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userDetailsService;

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
	@Override
	public ApiResultDTO saveInvoiceInfo(String loggedUser, InvoiceDetailsDTO invoiceDetailsDTO) {
		log.info("InvoiceDetailsServiceImpl-saveInvoiceInfo {}", invoiceDetailsDTO);

		ApiResultDTO apiResultDTO;
		try {
			InvoiceDetails invoiceDetails = modelMapper.map(invoiceDetailsDTO, InvoiceDetails.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.INVOICE).auditDesc("InvoiceDetailsServiceImpl-saveInvoiceInfo")
					.auditValue(new ObjectMapper().writeValueAsString(invoiceDetailsDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			invoiceDetails.setRequirementDetails(invoiceDetails.getRequirementDetails());

			int invoiceCount = invoiceNotInStatus(invoiceDetails.getRequirementDetails().getRequirementId(),
					Arrays.asList(CommonsConstants.ADMINV));

			String invoiceStatus = invoiceCount == 0 ? CommonsConstants.ADMINV : CommonsConstants.PARAPR;

			InvoiceDetails insertedInvoice = invoiceDetailsRepo.save(invoiceDetails);

			approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD", TRUSTVOL,
					CommonsConstants.ADMIN_COUNT, "Invoice Details");

			requirementService.updateApprovalDetails(invoiceDetailsDTO.getRequirementDTO().getRequirementId(),
					invoiceStatus);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(insertedInvoice.getInvoiceId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("InvoiceDetailsServiceImpl-saveInvoiceInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyInvoiceInfo(String loggedUser, InvoiceDetailsDTO invoiceDetailsDTO) {
		log.info("InvoiceDetailsServiceImpl-modifyInvoiceInfo");
		ApiResultDTO apiResultDTO;

		if (invoiceDetailsDTO.getInvoiceId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {
			if (invoiceDetailsRepo.existsById(invoiceDetailsDTO.getInvoiceId())) {
				try {
					InvoiceDetails invoiceDetails = modelMapper.map(invoiceDetailsDTO, InvoiceDetails.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.INVOICE)
							.auditDesc("InvoiceDetailsServiceImpl-modifyInvoiceInfo")
							.auditValue(new ObjectMapper().writeValueAsString(invoiceDetailsDTO)).createdBy(loggedUser)
							.build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					InvoiceDetails insertedInvoice = invoiceDetailsRepo.save(invoiceDetails);
					updateRequirementStatus(invoiceDetails);

					if (invoiceDetails.getInvoiceStatus().equals("ADMINV")) {
						approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD",
								TRUSTVOL, CommonsConstants.ADMIN_COUNT, "Invoice Details");
					} else if (invoiceDetails.getInvoiceStatus().equals("ADMREC")) {
						approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD",
								TRUSTVOL, CommonsConstants.ADMIN_COUNT, "Receipt Details");
					}
					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Saved Info Successfully").id(insertedInvoice.getInvoiceId()).build();

				} catch (JsonProcessingException jpe) {
					log.error("InvoiceDetailsServiceImpl-modifyInvoiceInfo-JsonProcessingException {} {}",
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
	public List<InvoiceDetails> findByRequirementId(String loggedUser, Long requirementId) {
		log.info("InvoiceDetailsServiceImpl-findByRequirementId");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.INVOICE).auditDesc("InvoiceDetailsServiceImpl-findByRequirementId")
				.auditValue(requirementId != null ? Long.toString(requirementId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return invoiceDetailsRepo.findByInvoiceDetails(requirementId);
	}

	/**
	 *
	 */
	@Override
	public InvoiceDetails findByInvoiceId(String loggedUser, Long invoiceId) {
		log.info("InvoiceDetailsServiceImpl-findByInvoiceId");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.INVOICE).auditDesc("InvoiceDetailsServiceImpl-findByInvoiceId")
				.auditValue(invoiceId != null ? Long.toString(invoiceId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return invoiceDetailsRepo.findByInvoiceId(invoiceId);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeInvoiceByInvoiceId(String loggedUser, Long invoiceId) {
		log.info("InvoiceDetailsServiceImpl-removeInvoiceByInvoiceId");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE)
				.auditModule(CommonsConstants.INVOICE).auditDesc("InvoiceDetailsServiceImpl-removeInvoiceByInvoiceId")
				.auditValue(invoiceId != null ? Long.toString(invoiceId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		if (invoiceId != null) {
			invoiceDetailsRepo.deleteById(invoiceId);
		}
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Delete Info Successfully")
				.build();

	}

	/**
	 *
	 */
	@Override
	public void updateApprovalDetails(Long invoiceId, String invoiceStatus) {
		log.info("InvoiceDetailsServiceImpl-updateApprovalDetails");
		invoiceDetailsRepo.updateApprovalDetails(invoiceId, invoiceStatus);

	}

	/**
	 *
	 */
	@Override
	public int checkPendingStatus(Long requirementId, String invoiceStatus) {
		log.info("InvoiceDetailsServiceImpl-checkPendingStatus");
		return invoiceDetailsRepo.checkPendingStatus(requirementId, invoiceStatus);
	}

	/**
	 *
	 */
	@Override
	public int invoiceNotInStatus(Long requirementId, List<String> invoiceStatus) {
		log.info("InvoiceDetailsServiceImpl-checkInvoiceStatus");
		return invoiceDetailsRepo.invoiceNotInStatus(requirementId, invoiceStatus);
	}

	/**
	 *
	 */
	@Override
	public void updateRequirementStatus(InvoiceDetails invoiceDetails) {
		invoiceDetails.setRequirementDetails(invoiceDetails.getRequirementDetails());

		String invoiceStatus;
		if (invoiceDetails.getInvoiceStatus().equals(CommonsConstants.ADMINV)) {
			int invoiceCount = invoiceNotInStatus(invoiceDetails.getRequirementDetails().getRequirementId(),
					Arrays.asList(CommonsConstants.ADMINV));
			invoiceStatus = invoiceCount == 0 ? CommonsConstants.ADMINV : CommonsConstants.PARAPR;
		} else {
			// if volunteer will add the new receipt after approving atleast one receipt
			// need to update requirement status
			int statusCount = checkPendingStatus(invoiceDetails.getRequirementDetails().getRequirementId(), "APRREC");
			invoiceStatus = statusCount == 0 ? "ADMREC" : "PARAPR";
		}
		// if volunteer deleted the rejected receipt need to update requirement status
		// based on invoice status
		if (invoiceDetails.getInvoiceStatus().equals(CommonsConstants.PAYINI)) {
			int statusCount = checkPendingStatus(invoiceDetails.getRequirementDetails().getRequirementId(), "APRREC");
			int rejCount = checkPendingStatus(invoiceDetails.getRequirementDetails().getRequirementId(), "RECREJ");
			if (statusCount == 0 && rejCount == 0) {
				invoiceStatus = "PAYINI";
			} else {
				invoiceStatus = rejCount != 0 ? "RECREJ" : "PARAPR";
			}

		}
		requirementService.updateApprovalDetails(invoiceDetails.getRequirementDetails().getRequirementId(),
				invoiceStatus);
	}

	/**
	 *
	 */
	@Override
	public List<InvoiceDetails> findByInvoiceStatus(String invoiceStatus) {
		log.info("InvoiceDetailsServiceImpl-findByInvoiceStatus");
		return invoiceDetailsRepo.findByInvoiceStatus(invoiceStatus);
	}

	/**
	 * @return 
	 *
	 */
	@Override
	public Long updateInvoicePayment(InvoiceDetailsDTO invoiceDTO) {
		log.info("InvoiceDetailsServiceImpl-findByInvoiceStatus");

		InvoiceDetails invoiceDetails = modelMapper.map(invoiceDTO, InvoiceDetails.class);

		InvoiceDetails details = findByInvoiceId(null, invoiceDetails.getInvoiceId());

		if (details != null && details.getInvoiceStatus().equals("PAYINI")) {
			invoiceDetailsRepo.updateInvoicePayment(invoiceDetails.getInvoiceId(), invoiceDetails.getInvoiceStatus(),
					invoiceDetails.getUtrDate(), invoiceDetails.getUtrNumber(), invoiceDetails.getRejectedReason());

			RequirementInfo req = requirementService.fetchRequirementInfo(null,
					details.getRequirementDetails().getRequirementId());
			ConsolidateRefInfo consolidateInfo = consolidateRefService.fetchConsolidateInfo(null,
					req.getConsolidateRef().getConsolidateId());

			SchoolInfo schoolInfo = schoolInfoService
					.fetchSchoolInfo(consolidateInfo.getSchoolInfo().getSchoolInfoId());

			UserRegisterDetails userDetails = userDetailsService
					.getUserRegisterDetailsByUserName(schoolInfo.getVolunteerName());

			invoiceApprovedEmail(null, null, null, userDetails.getEmailId(), "payment", null,
					invoiceDetails.getInvoiceId());

			updateReqStatus(invoiceDetails);
		} else if (details == null) {
			return invoiceDetails.getInvoiceId();
		}
		return null;
	}

	/**
	 * @param details
	 */
	private void updateReqStatus(InvoiceDetails details) {
		log.info("IncomingPaymentServiceImpl-updateRequirementStatus ");

		String invoiceStatus;

		InvoiceDetails invoiceDetails = findByInvoiceId(null, details.getInvoiceId());

		if (invoiceDetails != null) {
			List<InvoiceDetails> invoiceInfo = findByRequirementId(null,
					invoiceDetails.getRequirementDetails().getRequirementId());

			if (invoiceInfo != null) {
				// update the requirement Status

				log.info("IncomingPaymentServiceImpl-updateRequirementStatus-=-=-=-=-{} ", invoiceInfo.size());

				int rejectedCount = checkPendingStatus(invoiceDetails.getRequirementDetails().getRequirementId(),
						(CommonsConstants.PAYFAL));
				if (rejectedCount == 0) {
					int aprCount = checkPendingStatus(invoiceDetails.getRequirementDetails().getRequirementId(),
							"PAYCMP");
					invoiceStatus = aprCount == invoiceInfo.size() ? "PAYCMP" : "PARPAY";
				} else {
					invoiceStatus = rejectedCount == invoiceInfo.size() ? CommonsConstants.PAYFAL : "PARFAL";
				}
				requirementService.updateApprovalDetails(invoiceDetails.getRequirementDetails().getRequirementId(),
						invoiceStatus);
			}
		}

	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> invoiceApprovedEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String isReject, String address, Long invoiceId) {

		log.info("InvoiceDetailsServiceFacadeImpl-invoiceApprovedEmail {}", isReject);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
				emailId, "Invoice Approved", address == null ? "UNKNOWN" : address));

		Map<String, Object> model = new HashMap<>();

		model.put("rejected", isReject);
		model.put("invoiceId", invoiceId);

		String account = "Invoice Approval Information";

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, account, "invoiceApproved", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
