package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.QuotationInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.QuotationInfoDTO;
import com.newrta.putholi.api.repository.QuotationInfoRepository;
import com.newrta.putholi.api.service.QuotationInfoService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Slf4j
@Service
public class QuotationInfoServiceImpl implements QuotationInfoService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationInfoRepository quotationInfoRepo;

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
	@Override
	public ApiResultDTO saveQuotationInfo(String loggedUser, QuotationInfoDTO quotationInfoDTO) {
		log.info("QuotationInfoServiceImpl-saveQuotationInfo {}", quotationInfoDTO);

		ApiResultDTO apiResultDTO;
		try {
			QuotationInfo quotationInfo = modelMapper.map(quotationInfoDTO, QuotationInfo.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.QUOTATION).auditDesc("QuotationInfoServiceImpl-saveQuotationInfo")
					.auditValue(new ObjectMapper().writeValueAsString(quotationInfoDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			quotationInfo.setRequirementInfo(quotationInfo.getRequirementInfo());
			QuotationInfo insertedQuotationInfo = quotationInfoRepo.save(quotationInfo);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").id(insertedQuotationInfo.getQuotationId()).build();

		} catch (JsonProcessingException jpe) {
			log.error("QuotationInfoServiceImpl-saveQuotationInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyQuotationInfo(String loggedUser, QuotationInfoDTO quotationInfoDTO) {
		log.info("QuotationInfoServiceImpl-modifyQuotationInfo");

		ApiResultDTO apiResultDTO;

		if (quotationInfoDTO.getQuotationId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {

			if (quotationInfoRepo.existsById(quotationInfoDTO.getQuotationId())) {
				try {
					QuotationInfo quotationInfo = modelMapper.map(quotationInfoDTO, QuotationInfo.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.QUOTATION)
							.auditDesc("QuotationInfoServiceImpl-modifyQuotationInfo")
							.auditValue(new ObjectMapper().writeValueAsString(quotationInfoDTO)).createdBy(loggedUser)
							.build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					quotationInfo.setRequirementInfo(quotationInfo.getRequirementInfo());
					QuotationInfo insertedQuotationInfo = quotationInfoRepo.save(quotationInfo);

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully").id(insertedQuotationInfo.getQuotationId())
							.build();

				} catch (JsonProcessingException jpe) {
					log.error("QuotationInfoServiceImpl-saveQuotationInfo-JsonProcessingException {} {}",
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
	public QuotationInfo findByQuotationId(String loggedUser, Long quotationId) {
		log.info("QuotationInfoServiceImpl-findByQuotationId");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.QUOTATION).auditDesc("QuotationInfoServiceImpl-findByQuotationId")
				.auditValue(quotationId != null ? Long.toString(quotationId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return quotationInfoRepo.findByQuotationId(quotationId);
	}

	/**
	 *
	 */
	@Override
	public List<QuotationInfo> findQuotationInfo(String loggedUser, Long requirementId) {
		log.info("QuotationInfoServiceImpl-findQuotationInfo");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.QUOTATION).auditDesc("QuotationInfoServiceImpl-findQuotationInfo")
				.auditValue(requirementId != null ? Long.toString(requirementId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return quotationInfoRepo.findByQuotatonInfo(requirementId);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeQuotationInfo(String loggedUser, Long quotationId) {
		log.info("QuotationInfoServiceImpl-removeQuotationInfo");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE)
				.auditModule(CommonsConstants.QUOTATION).auditDesc("QuotationInfoServiceImpl-removeQuotationInfo")
				.auditValue(quotationId != null ? Long.toString(quotationId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		quotationInfoRepo.deleteByQuotationId(quotationId);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Delete Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public void updateApprovalDetails(Long quotationId, String quotateStatus) {
		log.info("QuotationInfoServiceImpl-updateApprovalDetails");
		quotationInfoRepo.updateApprovalDetails(quotationId, quotateStatus);
	}

	/**
	 *
	 */
	@Override
	public List<QuotationInfo> getQuotationVendorInfo(List<String> quotateStatus) {
		log.info("QuotationInfoServiceImpl-getQuotationVendorInfo");

		return quotationInfoRepo.findDistinctCompanyNames(quotateStatus);
	}

}
