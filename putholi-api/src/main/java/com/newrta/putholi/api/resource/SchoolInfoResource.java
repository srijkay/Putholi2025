package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.SchoolInfoDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.model.SchoolDetailsDTO;
import com.newrta.putholi.api.model.SchoolInfoDTO;
import com.newrta.putholi.api.service.SchoolApprovalHistoryFacadeService;
import com.newrta.putholi.api.service.SchoolInfoService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Setter
@Getter
@RequestMapping(value = "/v1/api/schoolinfo")
public class SchoolInfoResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolInfoService schoolInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryFacadeService schoolhistoryfacadeService;

	/**
	 * @param schoolInfo
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveSchoolInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody SchoolInfoDTO schoolInfoDTO) {
		log.info("SchoolInfoResource-saveSchoolInfo {} {}", loggedUser, schoolInfoDTO);

		return new ResponseEntity<>(schoolInfoService.saveSchoolInfo(loggedUser, schoolInfoDTO), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<SchoolInfo> fetchSchoolInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("SchoolInfoResource-fetchSchoolInfo {} {}", loggedUser, id);

		return new ResponseEntity<>(schoolInfoService.fetchSchoolInfo(id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/descriptions/{id}")
	public ResponseEntity<SchoolInfo> fetchSchoolInfoDescriptions(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("SchoolInfoResource-fetchSchoolInfo {} {}", loggedUser, id);

		return new ResponseEntity<>(schoolInfoService.fetchSchoolInfoByUser(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param schoolInfo
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifySchoolInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody SchoolInfoDTO schoolInfoDTO) {
		log.info("SchoolInfoResource-modifySchoolInfo {} {}", loggedUser, schoolInfoDTO);

		return new ResponseEntity<>(schoolInfoService.modifySchoolInfo(loggedUser, schoolInfoDTO), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResultDTO> removeSchoolInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("SchoolInfoResource-removeSchoolInfo {} {}", loggedUser, id);

		return new ResponseEntity<>(schoolInfoService.removeSchoolInfo(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<SchoolInfoDetails> searchSchoolInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody SchoolDetailsDTO searchDTO) {
		log.info("SchoolInfoResource-searchSchoolInfo {} {}", loggedUser, searchDTO);

		return schoolInfoService.searchSchoolInfo(loggedUser, searchDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/createdBy/{createdBy}")
	public ResponseEntity<SchoolInfo> findBySchoolName(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable String schoolName) {
		log.info("SchoolInfoResource-findBySchoolName {} ", schoolName);

		return new ResponseEntity<>(schoolInfoService.findBySchoolName(loggedUser, schoolName), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param approvalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/approval")
	public ResponseEntity<ApiResultDTO> updateUserApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO schoolApprovalHistDtls) {
		log.info("SchoolInfoResource-updateUserApproval {}", schoolApprovalHistDtls);

		return new ResponseEntity<>(schoolhistoryfacadeService.updateApprovalHistory(schoolApprovalHistDtls),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param schoolApprovalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "DEO/approval")
	public ResponseEntity<ApiResultDTO> updateDEOApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO schoolApprovalHistDtls) {
		log.info("SchoolInfoResource-updateDEOApproval {}", schoolApprovalHistDtls);

		return new ResponseEntity<>(schoolhistoryfacadeService.updateDEOApprovalHistory(schoolApprovalHistDtls),
				HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "volunteer/approval")
	public ResponseEntity<ApiResultDTO> updateVolunteerApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO schoolApprovalHistDtls) {
		log.info("SchoolInfoResource-updateVolunteerApproval {}", schoolApprovalHistDtls);

		return new ResponseEntity<>(schoolhistoryfacadeService.updateVolunteerHistory(schoolApprovalHistDtls),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/approval/{schoolId}/{type}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchUserDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("schoolId") Long schoolId, @PathVariable("type") String type) {
		log.info("SchoolInfoResource-fetchUserDetailsForApproval {}", schoolId);

		return new ResponseEntity<>(schoolhistoryfacadeService.fetchDetailsForApproval(schoolId, type), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param emailid
	 * @param subject
	 * @param address
	 * @param id
	 * @param mailBody
	 * @return
	 */
	@CrossOrigin // need to push to separate controller as it will be exposed to public
	@GetMapping(value = "/sendMailtoDEO/{emailid}/{address}/{id}/{mailbody}/{consolidateid}/{mailCc}")
	public ResponseEntity<ApiResultDTO> sendMailtoDEO(@RequestHeader("Authorization") String authorization,
			@RequestHeader("loggedUser") String loggedUser, @PathVariable("emailid") String emailid,
			@PathVariable("address") String address, @PathVariable(value = "id", required = false) Long id,
			@PathVariable(value = "mailbody", required = false) String mailBody,
			@PathVariable(value = "consolidateid", required = true) Long consolidateid,
			@PathVariable(value = "mailCc", required = false) String[] mailCc)

	{

		log.info("::SchoolInfoResource::sendMailtoDEO::");

		return schoolhistoryfacadeService.sentEmailtoDEO(authorization, loggedUser, emailid, address, id, mailBody,
				consolidateid, mailCc);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param address
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/sendMailtoBeneficiary/{address}/{id}")
	public ResponseEntity<ApiResultDTO> sendMailtoBeneficiary(@RequestHeader("Authorization") String authorization,
			@RequestHeader("loggedUser") String loggedUser, @PathVariable("address") String address,
			@PathVariable("id") Long id) {

		log.info("::SchoolInfoResource::sendMailtoBeneficiary::");

		return schoolhistoryfacadeService.sendMailtoBeneficiary(authorization, loggedUser, address, id, false);
	}

	/**
	 * @param authorization
	 * @param schoolStatus
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/statuscount/{schoolstatus}/{active}")
	public ResponseEntity<Integer> checkPendingStatus(@RequestHeader("Authorization") String authorization,
			@PathVariable("schoolstatus") List<String> schoolStatus, @PathVariable("active") String active) {
		log.info("::SchoolInfoResource::CheckPendingStatus::");

		return new ResponseEntity<>(schoolInfoService.checkPendingStatus(schoolStatus, active), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param schoolApprovalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/deletion")
	public ResponseEntity<ApiResultDTO> deleteSchoolInfo(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO schoolApprovalHistDtls) {
		log.info("SchoolInfoResource-updateUserApproval {}", schoolApprovalHistDtls);

		return new ResponseEntity<>(schoolhistoryfacadeService.updateDeleteHistory(schoolApprovalHistDtls),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param schoolInfoId
	 * @param schoolStatus
	 */
	@CrossOrigin
	@GetMapping(value = "/updatestatus/{schoolInfoId}/{schoolStatus}/{volunteerName}")
	public void updateSchoolStatus(@RequestHeader String authorization, @PathVariable Long schoolInfoId,
			@PathVariable String schoolStatus, @PathVariable String volunteerName) {
		log.info("SchoolInfoResource-updateSchoolStatus {}");

		schoolInfoService.updateVolunteerDetails(schoolInfoId, volunteerName, schoolStatus);
	}

}
