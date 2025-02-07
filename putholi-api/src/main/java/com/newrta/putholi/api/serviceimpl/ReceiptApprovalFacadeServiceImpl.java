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
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.InvoiceDetailsService;
import com.newrta.putholi.api.service.ReceiptApprovalfacadeService;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class ReceiptApprovalFacadeServiceImpl implements ReceiptApprovalfacadeService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private InvoiceDetailsService invoiceDetailService;

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
	@Override
	public SchoolApprovalHistoryDTO fetchSchoolDetailsForApproval(Long invoiceId, String type) {
		log.info("ReceiptApprovalFacadeServiceImpl-fetchSchoolDetailsForApproval");

		InvoiceDetails invoiceDetails = invoiceDetailService.findByInvoiceId(null, invoiceId);
		List<SchoolApprovalHistoryDetails> apprHistoryDetails = approvalHistoryService.findByInvoiceIdAndType(invoiceId,
				type);

		return SchoolApprovalHistoryDTO.builder().invoiceDetails(invoiceDetails)
				.schoolApprovalHistoryDetails(apprHistoryDetails).build();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateReceiptApprovalHistory(SchoolApprovalHistoryDetailsDTO schoolHistoryDetailsDTO) {
		log.info("ReceiptApprovalFacadeServiceImpl-updateReceiptApprovalHistory");

		SchoolApprovalHistoryDetails schoolHistoryDetails = modelMapper.map(schoolHistoryDetailsDTO,
				SchoolApprovalHistoryDetails.class);

		String status = "ADMREC";
		if (schoolHistoryDetails.getStatus() != null && schoolHistoryDetails.getStatus().equals("REJ")) {
			status = CommonsConstants.RECREJ;

			InvoiceDetails invoice = invoiceDetailService.findByInvoiceId(null, schoolHistoryDetails.getInvoiceId());

			RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
					invoice.getRequirementDetails().getRequirementId());

			UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserRegisterDetailsByUserName(
					requirement.getConsolidateRef().getSchoolInfo().getVolunteerName());

			receiptApprovedEmail(null, null, null, null, userRegisterDetails.getEmailId(), true);

		} else if (schoolHistoryDetails.getStatus() != null && schoolHistoryDetails.getStatus().equals("APR")) {
			status = "APRREC";

			InvoiceDetails invoice = invoiceDetailService.findByInvoiceId(null, schoolHistoryDetails.getInvoiceId());

			RequirementInfo requirement = requirementService.fetchRequirementInfo(null,
					invoice.getRequirementDetails().getRequirementId());

			UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserRegisterDetailsByUserName(
					requirement.getConsolidateRef().getSchoolInfo().getVolunteerName());

			receiptApprovedEmail(null, null, null, null, userRegisterDetails.getEmailId(), false);

		}
		// updated the invoice status
		invoiceDetailService.updateApprovalDetails(schoolHistoryDetails.getInvoiceId(), status);

		// get the invoice details based on id
		InvoiceDetails invoice = invoiceDetailService.findByInvoiceId(null, schoolHistoryDetails.getInvoiceId());

		// get the requirements details based on requirement id
		RequirementInfo requirementInfo = requirementService.fetchRequirementInfo(null,
				invoice.getRequirementDetails().getRequirementId());

		// get the invoice details based on requirement id
		List<InvoiceDetails> invoiceInfo = invoiceDetailService.findByRequirementId(null,
				requirementInfo.getRequirementId());

		int rej = invoiceDetailService.checkPendingStatus(invoice.getRequirementDetails().getRequirementId(),
				CommonsConstants.RECREJ);

		String invoiceStatus;
		if (rej == 0) {
			int count = invoiceDetailService.checkPendingStatus(requirementInfo.getRequirementId(), status);
			invoiceStatus = count == invoiceInfo.size() ? "APRREC" : "PARAPR";
		} else {
			int count = invoiceDetailService.checkPendingStatus(requirementInfo.getRequirementId(),
					CommonsConstants.RECREJ);
			invoiceStatus = count == invoiceInfo.size() ? CommonsConstants.RECREJ : "PARREJ";
		}

		requirementService.updateApprovalDetails(invoice.getRequirementDetails().getRequirementId(), invoiceStatus);

		approvalHistoryService.saveSchoolApprovalHistoryDetails(schoolHistoryDetails);

		return ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Approval Details Updated Successfully!")
				.build();
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> receiptApprovedEmail(HttpServletRequest request, String locale,
			String authorization, String address, String emailId, boolean isReject) {

		log.info("ReceiptApprovalFacadeServiceImpl-receiptApprovedEmail {}", isReject);

		jmsTemplate.convertAndSend("auditbox", new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.APPROVED,
				emailId, "Receipt Approved", address == null ? "UNKNOWN" : address));

		Map<String, Object> model = new HashMap<>();

		model.put("rejected", isReject);

		String account = isReject ? "Receipt Rejected" : "Receipt Approved";

		jmsTemplate.convertAndSend(CommonsConstants.MAIL_BOX,
				new MailDTO(mailFrom, emailId, account, "receiptApproved", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
