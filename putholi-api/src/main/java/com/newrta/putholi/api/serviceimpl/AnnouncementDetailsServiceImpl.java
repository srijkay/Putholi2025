package com.newrta.putholi.api.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AnnouncementDetails;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.model.AnnouncementDetailsDTO;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.repository.AnnouncementDetailsRepository;
import com.newrta.putholi.api.service.AnnouncementDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class AnnouncementDetailsServiceImpl implements AnnouncementDetailsService {

	/**
	 * 
	 */
	@Autowired(required = true)
	AnnouncementDetailsRepository announcementRepo;
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
	public ApiResultDTO saveAnnouncementInfo(String loggedUser, AnnouncementDetailsDTO announcementDetailDTO) {
		log.info("AnnouncementDetailsServiceImpl-saveAnnouncementInfo {}", announcementDetailDTO);
		ApiResultDTO apiResultDTO;

		try {
			AnnouncementDetails announcementDetails = modelMapper.map(announcementDetailDTO, AnnouncementDetails.class);

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.ANNOUNCE).auditDesc("AnnouncementServiceImpl-saveAnnouncement")
					.auditValue(new ObjectMapper().writeValueAsString(announcementDetailDTO)).createdBy(loggedUser)
					.build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			AnnouncementDetails insertedAnnouncementDetails = announcementRepo.save(announcementDetails);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Save Info Successfully").id(insertedAnnouncementDetails.getAnnouncementId())
					.build();

		} catch (JsonProcessingException jpe) {
			log.error("AnnouncementDetailsServiceImpl-saveAnnouncementInfo-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyAnnouncementInfo(String loggedUser, AnnouncementDetailsDTO announcementDetailDTO) {
		log.info("AnnouncementDetailsServiceImpl-modifyAnnouncementInfo {}", announcementDetailDTO);

		ApiResultDTO apiResultDTO;

		if (announcementDetailDTO.getAnnouncementId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {
			if (announcementRepo.existsById(announcementDetailDTO.getAnnouncementId())) {

				try {
					AnnouncementDetails announcementDetails = modelMapper.map(announcementDetailDTO,
							AnnouncementDetails.class);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.ANNOUNCE).auditDesc("AnnouncementServiceImpl-modifyAnnouncementInfo")
							.auditValue(new ObjectMapper().writeValueAsString(announcementDetailDTO))
							.createdBy(loggedUser).build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					announcementRepo.save(announcementDetails);
					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully!").build();

				} catch (JsonProcessingException jpe) {
					log.error("AnnouncementDetailsServiceImpl-modifyAnnouncementInfo-JsonProcessingException {} {}",
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
	public AnnouncementDetails fectchAnnouncementInfo(String loggedUser, Long announcementId) {
		log.info("AnnouncementDetailsServiceImpl-fectchAnnouncementInfo {}", announcementId);

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND).auditModule(CommonsConstants.ANNOUNCE)
				.auditDesc("AnnouncementServiceImpl-fectchAnnouncement")
				.auditValue(announcementId != null ? Long.toString(announcementId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return announcementRepo.findByAnnouncementId(announcementId);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeAnnouncementInfo(String loggedUser, Long announcementId) {
		log.info("AnnouncementDetailsServiceImpl-removeAnnouncementInfo {}", announcementId);

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE).auditModule(CommonsConstants.ANNOUNCE)
				.auditDesc("AnnouncementServiceImpl-removeAnnouncementInfo")
				.auditValue(announcementId != null ? Long.toString(announcementId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		if (announcementId != null) {
			announcementRepo.deleteById(announcementId);
		}
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Removed Info Successfully!").build();
	}

	/**
	 *
	 */
	@Override
	public Page<AnnouncementDetails> searchAnnouncementInfo(String loggedUser, AnnouncementDetailsDTO searchDTO) {
		log.info("AnnouncementDetailsServiceImpl-searchAnnouncementInfo {}");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule(CommonsConstants.ANNOUNCE).auditDesc("AnnouncementServiceImpl-searchAnnouncement")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("AnnouncementDetailsServiceImpl-searchAnnouncementInfo-JsonProcessingException {} {}",
					jpe.getCause(), jpe);
		}

		return announcementRepo.searchAnnouncemnetInfo(searchDTO);
	}

}
