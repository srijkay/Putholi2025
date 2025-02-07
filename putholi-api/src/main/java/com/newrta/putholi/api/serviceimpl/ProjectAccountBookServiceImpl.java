package com.newrta.putholi.api.serviceimpl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.newrta.putholi.api.domain.*;
import com.newrta.putholi.api.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ProjectAccountBookDTO;
import com.newrta.putholi.api.repository.ProjectAccountBookRepository;
import com.newrta.putholi.api.repository.RequirementInfoRepository;
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
public class ProjectAccountBookServiceImpl implements ProjectAccountBookService {

	private static final String REDALL = "REDALL";
	/**
	 * 
	 */
	@Autowired(required = true)
	private ProjectAccountBookRepository projectBookRepo;

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
	private SchoolInfoService schoolService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateRefService;

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
	UserRegisterDetailsService userRegisterDetailsService;

	/**
	 *
	 */
	@Autowired(required = true)
	private PaymentService paymentService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private DonorInfoService donorService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveProjectAccountBook(String loggedUser, ProjectAccountBookDTO projectBookDTO) {
		log.info("ProjectAccountBookServiceImpl-saveProjectAccountBook {}", projectBookDTO);

		ApiResultDTO apiResultDTO;
		try {

			ProjectAccountBook projectAccountBook = modelMapper.map(projectBookDTO, ProjectAccountBook.class);

			ProjectAccountBook projectBook = findTopByOrderByIdDesc();

			if (projectBook != null && projectBook.getBalanceAmount() != null) {
				if (projectAccountBook.getFeeType() != null && projectAccountBook.getFeeType().equals("INC")) {
					projectAccountBook
							.setBalanceAmount(projectBook.getBalanceAmount().add(projectAccountBook.getAmount()));
				} else {
					projectAccountBook
							.setBalanceAmount(projectBook.getBalanceAmount().subtract(projectAccountBook.getAmount()));
				}
			} else {
				projectAccountBook.setBalanceAmount(projectAccountBook.getAmount());
			}

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule("PRJTBOOK").auditDesc("ProjectAccountBookServiceImpl-saveProjectAccountBook")
					.auditValue(new ObjectMapper().writeValueAsString(projectAccountBook)).createdBy(loggedUser)
					.build();

			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			if ("Fully Collected".equals(projectAccountBook.getRemarks())) {
				List<RequirementInfo> requirementInfo = requirementInfoRepository
						.findAllRequirementByStatus(projectAccountBook.getProjectId(), "QUOARV", "Y");
				for (RequirementInfo requirementInfo2 : requirementInfo) {
					requirementService.updateApprovalDetails(requirementInfo2.getRequirementId(), REDALL);

				}

				ConsolidateRefInfo details = consolidateRefService.fetchConsolidateInfo(null,
						projectAccountBook.getProjectId());
				consolidateRefService.updateConsolidateStatus(details.getConsolidateId(), REDALL);

				schoolService.updateApprovalDetails(details.getSchoolInfo().getSchoolInfoId(), REDALL);

				// to add admin email for all amount contribution collected once
				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "Quote", null,
						CommonsConstants.ADMIN_COUNT, null);

			}

			PaymentInstruction paymentInstr = paymentService.findByPaymentId(null, projectBookDTO.getOrderId());
			log.info("payment{}-------", paymentInstr);

			if (paymentInstr != null) {
				if (projectBookDTO.getProjectId() == null) {
					UserRegisterDetails userRegisterDetails = userRegisterDetailsService
							.findByOrderId(paymentInstr.getOrderId());

					projectAccountBook.setCreatedBy(userRegisterDetails.getUserName());
					projectAccountBook.setDonorId(userRegisterDetails.getUserName());
				} else {
					DonorInfo donor = donorService.findByOrderId(paymentInstr.getOrderId());
					projectAccountBook.setCreatedBy(donor.getEmailId());
					projectAccountBook.setDonorId(donor.getEmailId());
				}
			} else {
				projectAccountBook.setDonorId(projectBookDTO.getCreatedBy());
			}

			projectBookRepo.save(projectAccountBook);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("SUCCESS").apiStatusDesc("Saved Info Successfully")
					.build();

		} catch (JsonProcessingException jpe) {
			log.error("CProjectAccountBookServiceImpl-saveProjectAccountBook-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public List<ProjectAccountBook> findByProjectId(String loggedUser, Long projectId) {
		log.info("ProjectAccountBookServiceImpl-findByProjectId");

		return projectBookRepo.findByProjectId(projectId);
	}

	/**
	 *
	 */
	@Override
	public ProjectAccountBook findTopByOrderByIdDesc() {
		log.info("ProjectAccountBookServiceImpl-findTopByOrderByIdDesc");

		return projectBookRepo.findTopByOrderByProjectIncExpIdDesc();
	}

	/**
	 *
	 */
	@Override
	public BigDecimal findBalanceAmountByProjectId(Long projectId) {
		log.info("ProjectAccountBookServiceImpl-findBalanceAmountByProjectId");
		return projectBookRepo.findBalanceAmountByProjectId(projectId);

	}

	/**
	 *
	 */
	@Override
	public List<ProjectAccountBook> findByCreatedDateBetween(Date endDate) {
		log.info("TrustAccountBookServiceImpl-findByCreatedDateBetween{}", endDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate); // Set the calendar to the provided end date

		// Set to the first day of the year
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		Date beginDate = calendar.getTime();
		log.info("beginning date {}", beginDate);

		return projectBookRepo.findByCreatedDateBetween(beginDate, endDate);
	}

}
