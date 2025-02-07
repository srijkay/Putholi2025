package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.domain.PaymentInstruction;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.TrustAccountBook;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.TrustAccountBookDTO;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
import com.newrta.putholi.api.repository.TrustAccountBookRepository;
import com.newrta.putholi.api.service.ConsolidateRefService;
import com.newrta.putholi.api.service.DonorInfoService;
import com.newrta.putholi.api.service.PaymentService;
import com.newrta.putholi.api.service.RequirementService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.TrustAccountBookService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Service
@Slf4j
public class TrustAccountBookServiceImpl implements TrustAccountBookService {

	/**
	 * 
	 */
	@Value("${app.url}")
	private String appUrl;

	/**
	 * 
	 */
	@Autowired(required = true)
	private TrustAccountBookRepository trustBookRepo;

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
	private ConsolidateRefService consolidateRef;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private DonorInfoService donorService;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserRegisterDetailsService userRegisterDetailsService;

	@Autowired(required = true)
	private PaymentService paymentService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveAccountBook(String loggedUser, TrustAccountBookDTO trustAccountBookDTO) {
		log.info("TrustAccountBookServiceImpl-saveAccountBook {}", trustAccountBookDTO);
		ApiResultDTO apiResultDTO;

		try {
			TrustAccountBook trustAccountBook = modelMapper.map(trustAccountBookDTO, TrustAccountBook.class);

			TrustAccountBook accuntBook = findTopByOrderByIdDesc();

			PaymentInstruction paymentInstr = paymentService.findByPaymentId(null, trustAccountBookDTO.getOrderId());
			if (paymentInstr != null) {
				if (trustAccountBookDTO.getProjectId() == null) {
					UserRegisterDetails userRegisterDetails = userRegisterDetailsService
							.findByOrderId(paymentInstr.getOrderId());

					trustAccountBook.setCreatedBy(userRegisterDetails.getUserName());
					trustAccountBook.setDonorId(userRegisterDetails.getUserName());
				} else {
					DonorInfo donor = donorService.findByOrderId(paymentInstr.getOrderId());
					trustAccountBook.setCreatedBy(donor.getEmailId());
					trustAccountBook.setDonorId(donor.getEmailId());
				}
			} else {
				trustAccountBook.setDonorId(trustAccountBookDTO.getCreatedBy());
			}

			if (accuntBook != null && accuntBook.getBalanceAmount() != null) {

				if (trustAccountBook.getFeeType() != null && trustAccountBook.getFeeType().equals("INC")) {
					trustAccountBook.setBalanceAmount(accuntBook.getBalanceAmount().add(trustAccountBook.getAmount()));
				} else {
					trustAccountBook
							.setBalanceAmount(accuntBook.getBalanceAmount().subtract(trustAccountBook.getAmount()));
					// fetch the consolidate details based on consolidate id
					ConsolidateRefInfo consolidateDetails = consolidateRef.fetchConsolidateInfo(null,
							trustAccountBook.getProjectId());

					// updated Requirement status
					List<RequirementInfo> requirementInfo = requirementInfoRepository
							.findAllRequirementByStatus(trustAccountBook.getProjectId(), "REDALL", "Y");
					for (RequirementInfo requirementDetails : requirementInfo) {
						requirementService.updateApprovalDetails(requirementDetails.getRequirementId(),
								CommonsConstants.GNRORD);
					}
					// updated consolidate status
					consolidateRef.updateConsolidateStatus(trustAccountBook.getProjectId(), CommonsConstants.GNRORD);

					// updated school status
					schoolInfoService.updateApprovalDetails(consolidateDetails.getSchoolInfo().getSchoolInfoId(),
							CommonsConstants.GNRORD);

				}
			} else {
				trustAccountBook.setBalanceAmount(trustAccountBook.getAmount());
			}

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule("TRUSTBOOK").auditDesc("TrustAccountBookServiceImpl-saveAccountBook")
					.auditValue(new ObjectMapper().writeValueAsString(trustAccountBookDTO)).createdBy(loggedUser)
					.build();

			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			trustBookRepo.save(trustAccountBook);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Saved Info Successfully")
					.build();
		} catch (JsonProcessingException jpe) {
			log.error("TrustAccountBookServiceImpl-saveAccountBook-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public TrustAccountBook getAccountBook(String loggedUser, Long projectId) {
		log.info("TrustAccountBookServiceImpl-getAccountBook");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND).auditModule("TRUSTBOOK")
				.auditDesc("TrustAccountBookServiceImpl-getAccountBook")
				.auditValue(projectId != 0 ? Long.toString(projectId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return trustBookRepo.findByProjectId(projectId);

	}

	/**
	 *
	 */
	@Override
	public TrustAccountBook findTopByOrderByIdDesc() {
		log.info("TrustAccountBookServiceImpl-findTopByOrderByIdDesc");

		return trustBookRepo.findTopByOrderByTrustAccIdDesc();
	}
}
