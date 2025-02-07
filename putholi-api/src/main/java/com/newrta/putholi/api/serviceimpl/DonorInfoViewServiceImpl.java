package com.newrta.putholi.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.DonorInfoDetails;
import com.newrta.putholi.api.model.DonorInfoViewDetailsDTO;
import com.newrta.putholi.api.repository.DonorInfoViewRepository;
import com.newrta.putholi.api.service.DonorInfoViewService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Slf4j
@Service
public class DonorInfoViewServiceImpl implements DonorInfoViewService {
	
	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private DonorInfoViewRepository donorInfoRepo;

	/**
	 *
	 */
	@Override
	public Page<DonorInfoDetails> searchDonorInfo(String loggedUser, DonorInfoViewDetailsDTO searchDTO) {
		log.info("DonorInfoViewServiceImpl-searchDonorInfo");
		
		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule("DONORVIEW").auditDesc("DonorInfoViewServiceImpl-searchDonorInfo")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("DonorInfoViewServiceImpl-searchDonorInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
		}
		
		return donorInfoRepo.searchDonorInfo(searchDTO);
	}

}
