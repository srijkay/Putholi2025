package com.newrta.putholi.api.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AddressInfo;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ContactInfo;
import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.SchoolInfoDetails;
import com.newrta.putholi.api.domain.UserRegisterDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.SchoolDetailsDTO;
import com.newrta.putholi.api.model.SchoolInfoDTO;
import com.newrta.putholi.api.repository.SchoolInfoRepository;
import com.newrta.putholi.api.service.MasterCodeDetailsService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;
import com.newrta.putholi.api.service.SchoolInfoService;
import com.newrta.putholi.api.service.UserRegisterDetailsService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class SchoolInfoServiceImpl implements SchoolInfoService {

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
	@Autowired(required = true)
	private SchoolInfoRepository schoolInfoRepository;

	/** 
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	private MasterCodeDetailsService masterCodeService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterDetailsService userRegisterDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 * @throws
	 * 
	 */
	@Override
	public ApiResultDTO saveSchoolInfo(String loggedUser, SchoolInfoDTO schoolInfoDTO) {
		log.info("SchoolInfoServiceImpl-saveSchoolInfo");

		ApiResultDTO apiResultDTO;
		try {

			if (schoolInfoDTO.getSchoolRegNo() != null
					&& existBySchoolRegNoIgnoreCase(schoolInfoDTO.getSchoolRegNo())) {
				return ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc("Registration Number Already Exists").build();
			} else {

				SchoolInfo schoolInfo = modelMapper.map(schoolInfoDTO, SchoolInfo.class);

				AddressInfo addressInfo = schoolInfo.getAddressInfo();
				addressInfo.setSchoolInfo(schoolInfo);

				ContactInfo contactInfo = schoolInfo.getContactsInfo();
				contactInfo.setSchoolInfo(schoolInfo);

				AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
						.auditModule(CommonsConstants.SCHOOLINFO).auditDesc("SchoolInfoServiceImpl-saveSchoolInfo")
						.auditValue(new ObjectMapper().writeValueAsString(schoolInfoDTO)).createdBy(loggedUser).build();
				commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

				schoolInfoDTO.setSchoolInfoId(schoolInfoDTO.getSchoolInfoId());
				SchoolInfo insertedSchoolInfo = schoolInfoRepository.save(schoolInfo);

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD", "Beneficiary",
						"admin_email_count", "School Details");

				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc("Saved Info Successfully").id(insertedSchoolInfo.getSchoolInfoId()).build();
			}
		} catch (JsonProcessingException jpe) {
			log.error("SchoolInfoServiceImpl-saveSchoolInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public SchoolInfo fetchSchoolInfoByUser(String loggedUser, Long schoolInfoId) {
		log.info("SchoolInfoServiceImpl-fetchSchoolInfoByUser");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.SCHOOLINFO).auditDesc("SchoolInfoServiceImpl-fetchSchoolInfoByUser")
				.auditValue(schoolInfoId != null ? Long.toString(schoolInfoId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		SchoolInfo school = schoolInfoRepository.findBySchoolInfoId(schoolInfoId);

		MasterCodeResultDTO educationDistrictCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("DIST",
				school.getEducationalDistrict());

		MasterCodeResultDTO schoolTypeCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("STY",
				school.getSchoolType());

		MasterCodeResultDTO districtCodeDTO = masterCodeService.findMasterCodesByCodeTypeAndCode("DIST",
				school.getAddressInfo().getDistrict());

		school.getAddressInfo().setDistrict(districtCodeDTO != null ? districtCodeDTO.getDescription() : "");
		school.setEducationalDistrict(
				educationDistrictCodeDTO != null ? educationDistrictCodeDTO.getDescription() : "");
		
		school.setSchoolType(schoolTypeCodeDTO.getDescription());

		return school;

	}

	/**
	 * 
	 */
	@Override
	public SchoolInfo fetchSchoolInfo(Long schoolInfoId) {
		log.info("SchoolInfoServiceImpl-fetchSchoolInfo");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.SCHOOLINFO).auditDesc("SchoolInfoServiceImpl-fetchSchoolInfo")
				.auditValue(schoolInfoId != null ? Long.toString(schoolInfoId) : "").createdBy(null).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return schoolInfoRepository.findBySchoolInfoId(schoolInfoId);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeSchoolInfo(String loggedUser, Long schoolInfoId) {
		log.info("SchoolInfoServiceImpl-removeSchoolInfo");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.DELETE)
				.auditModule(CommonsConstants.SCHOOLINFO).auditDesc("SchoolInfoServiceImpl-removeSchoolInfo")
				.auditValue(schoolInfoId != null ? Long.toString(schoolInfoId) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		schoolInfoRepository.deleteBySchoolInfoId(schoolInfoId);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Removed Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifySchoolInfo(String loggedUser, SchoolInfoDTO schoolInfoDTO) {
		log.info("SchoolInfoServiceImpl-modifySchoolInfo");

		ApiResultDTO apiResultDTO;

		if (schoolInfoDTO.getSchoolInfoId() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {

			if ((schoolInfoRepository.existsById(schoolInfoDTO.getSchoolInfoId())
					&& (schoolInfoRepository.existsBySchoolRegNoIgnoreCase(schoolInfoDTO.getSchoolRegNo())))) {

				try {

					SchoolInfo schoolInfo = modelMapper.map(schoolInfoDTO, SchoolInfo.class);

					ContactInfo contactInfo = schoolInfo.getContactsInfo();
					contactInfo.setSchoolInfo(schoolInfo);

					AddressInfo addressInfo = schoolInfo.getAddressInfo();
					addressInfo.setSchoolInfo(schoolInfo);

					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.SCHOOLINFO)
							.auditDesc("SchoolInfoServiceImpl-modifySchoolInfo")
							.auditValue(new ObjectMapper().writeValueAsString(schoolInfoDTO)).createdBy(loggedUser)
							.build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

					SchoolInfo insertedSchoolInfo = schoolInfoRepository.save(schoolInfo);

					approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMIN, "ADD",
							"Beneficiary", "admin_email_count", "School Details");

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully!").id(insertedSchoolInfo.getSchoolInfoId())
							.build();
				} catch (JsonProcessingException jpe) {
					log.error("SchoolInfoServiceImpl-modifySchoolInfo-JsonProcessingException {} {}", jpe.getCause(),
							jpe);
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
	public Page<SchoolInfoDetails> searchSchoolInfo(String loggedUser, SchoolDetailsDTO searchDTO) {
		log.info("SchoolInfoServiceImpl-searchSchoolInfo");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule(CommonsConstants.SCHOOLINFO).auditDesc("SchoolInfoServiceImpl-searchSchoolInfo")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("SchoolInfoServiceImpl-saveSchoolInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
		}
		return schoolInfoRepository.searchSchoolInfo(searchDTO);
	}

	/**
	 *
	 */
	@Override
	public SchoolInfo findBySchoolName(String loggedUser, String schoolName) {
		log.info("SchoolInfoServiceImpl-findBySchoolName");

		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.SCHOOLINFO).auditDesc("SchoolInfoServiceImpl-findBySchoolName")
				.auditValue(schoolName != null ? (schoolName) : "").createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return schoolInfoRepository.findBySchoolName(schoolName);
	}

	/**
	 *
	 */
	@Override
	public void updateApprovalDetails(Long schoolInfoId, String schoolStatus) {
		log.info("SchoolInfoServiceImpl-updateApprovalDetails{}", schoolInfoId);
		schoolInfoRepository.updateApprovalDetails(schoolInfoId, schoolStatus);
	}

	/**
	 *
	 */
	@Override
	public void updateVolunteerDetails(Long schoolInfoId, String volunteerName, String schoolStatus) {
		log.info("SchoolInfoServiceImpl-updateVolunteerDetails{}", schoolStatus);
		schoolInfoRepository.updateVolunteerDetails(schoolInfoId, volunteerName, schoolStatus);
		if (schoolStatus.equals("ASGVOL")) {

			UserRegisterDetails userRegisterDetails = userRegisterDetailsService
					.getUserRegisterDetailsByUserName(volunteerName);

			SchoolInfo school = fetchSchoolInfo(schoolInfoId);
			volunteerAssignedEmail(null, null, null, userRegisterDetails.getEmailId(), school.getSchoolName());
		}
	}

	/**
	 *
	 */
	@Override
	public int checkPendingStatus(List<String> schoolStatus, String active) {
		log.info("SchoolInfoServiceImpl-CheckPendingStatus{}");
		return schoolInfoRepository.checkPendingStatus(schoolStatus, active);
	}

	/**
	 *
	 */
	@Override
	public Boolean existBySchoolRegNoIgnoreCase(String schoolRegNo) {
		log.info("SchoolInfoServiceImpl-existBySchoolRegNoIgnoreCase {}", schoolRegNo);
		return schoolInfoRepository.existsBySchoolRegNoIgnoreCase(schoolRegNo);
	}

	/**
	 *
	 */
	@Override
	public void deleteSchoolDetails(String active, String schoolStatus, Long schoolInfoId) {
		log.info("SchoolInfoServiceImpl-existBySchoolRegNoIgnoreCase {}");
		schoolInfoRepository.deleteBySchoolDetails(active, schoolStatus, schoolInfoId);
	}

	/**
	 *
	 */
	@Override
	public ResponseEntity<ApiResultDTO> volunteerAssignedEmail(String authorization, String loggedUser, String address,
			String emailId, String schoolName) {
		log.info("SchoolInfoServiceImpl-volunteerAssignedEmail {}");

		UserRegisterDetails userRegisterDetails = userRegisterDetailsService.getUserDetailsByEmailId(emailId);

		Map<String, Object> model = new HashMap<>();

		model.put("schoolName", schoolName);

		jmsTemplate.convertAndSend("mailbox", new MailDTO(mailFrom, userRegisterDetails.getEmailId(),
				"Volunteer Assigned Successfully", "volunteerAssigned", model));

		return new ResponseEntity<>(ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Details sent to the registered Email Id").build(), HttpStatus.OK);
	}

}
