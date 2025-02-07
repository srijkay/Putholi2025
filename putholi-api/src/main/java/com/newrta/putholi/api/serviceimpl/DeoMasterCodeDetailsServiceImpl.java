package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.DeoMasterCodeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DeoMasterCodeResultDTO;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;
import com.newrta.putholi.api.repository.DeoMasterCodeDetailsRepository;
import com.newrta.putholi.api.service.DeoMasterCodeDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class DeoMasterCodeDetailsServiceImpl implements DeoMasterCodeDetailsService {

	/**
	* 
	*/
	@Autowired(required = true)
	private DeoMasterCodeDetailsRepository deoMasterCodeDetailsRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */

	@Override
	public Page<DeoMasterCodeDetails> searchDeoMasterCodes(String loggedUser,
			DeoMasterCodeSearchDTO deoMasterCodeSearchDTO) {

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditDesc("DeoMasterCodeDetailsServiceImpl-searchMasterCodes")
					.auditValue(new ObjectMapper().writeValueAsString(deoMasterCodeSearchDTO)).createdBy(loggedUser)
					.build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("DeoMasterCodeDetailsServiceImpl-JsonProcessingException {} {}", jpe.getCause(), jpe);
		}
		return deoMasterCodeDetailsRepository.searchDeoMasterCodes(deoMasterCodeSearchDTO);
	}

	@Override
	public ApiResultDTO createDeoMasterCode(String locale, DeoMasterCodeDetails deoMasterCodeDetails) {
		log.info("DeoMasterCodeDetailsServiceImpl-createDeoMasterCode");

		boolean result = deoMasterCodeDetailsRepository.existsByCityAndDistrict(deoMasterCodeDetails.getCity(),
				deoMasterCodeDetails.getDistrict());
		ApiResultDTO apiResultDTO;
		if (result) {
			try {
				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
						CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(deoMasterCodeDetails),
						"code.already.exists", deoMasterCodeDetails.getCreatedBy()));
			} catch (JsonProcessingException jpe) {
				log.error("createMasterCode-JsonProcessingException {}", jpe.getCause());
			}
			List<Object> objArray = new ArrayList<>();
			objArray.add(deoMasterCodeDetails.getCity());
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(
							LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
					.build();
		} else {

			try {
				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
						CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(deoMasterCodeDetails),
						"save.success", deoMasterCodeDetails.getCreatedBy()));
			} catch (JsonProcessingException jpe) {
				log.error("createMasterCode-JsonProcessingException {}", jpe.getCause());
			}

			deoMasterCodeDetailsRepository.save(deoMasterCodeDetails);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
		}

		return apiResultDTO;
	}

	@Override
	public ApiResultDTO modifyDeoMasterCode(String locale, DeoMasterCodeDetails deoMasterCodeDetails) {
		log.info("DeoMasterCodeDetailsServiceImpl-modifyDeoMasterCode");

		ApiResultDTO apiResultDTO;
		if (deoMasterCodeDetails.getId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.invalid.request.mandatory", null, locale, null))
					.build();
		} else {
			if (deoMasterCodeDetailsRepository.existsById(deoMasterCodeDetails.getId())) {

				try {
					commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE,
							CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(deoMasterCodeDetails),
							"update.success", deoMasterCodeDetails.getUpdatedBy()));
				} catch (JsonProcessingException jpe) {
					log.error("modifyDeoMasterCode-JsonProcessingException {}", jpe.getCause());
				}

				deoMasterCodeDetailsRepository.save(deoMasterCodeDetails);

				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
			} else {
				try {
					commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE,
							CommonsConstants.MASTER_MGMT, new ObjectMapper().writeValueAsString(deoMasterCodeDetails),
							"id.notexist", deoMasterCodeDetails.getUpdatedBy()));
				} catch (JsonProcessingException jpe) {
					log.error("modifyMasterCode-JsonProcessingException {}", jpe.getCause());
				}
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc(
								LocaleConfig.getResourceValue("error.invalid.request.notexist", null, locale, null))
						.build();
			}
		}

		return apiResultDTO;
	}

	@Override
	public Optional<DeoMasterCodeDetails> findDeoMasterCodeDetailsById(Long id) {
		log.info("DeoMasterCodeDetailsServiceImpl-findDeoMasterCodeDetailsById");
		return deoMasterCodeDetailsRepository.findById(id);
	}

	@Override
	public List<DeoMasterCodeResultDTO> findMasterCode(String active) {
		log.info("DeoMasterCodeDetailsServiceImpl-findMasterCode");
		return deoMasterCodeDetailsRepository.findByActive(active);
	}

}
