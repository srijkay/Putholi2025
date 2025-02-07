package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.newrta.putholi.api.domain.QuotationInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.QuotationInfoDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.QuotationInfoService;
import com.newrta.putholi.api.service.QuotationInfoServiceFacade;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@RestController
@Data
@RequestMapping(value = "/v1/api/quotation")
public class QuotationInfoResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationInfoService quotationInfoService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationInfoServiceFacade quotationServiceFacade;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService schoolApprovalHisService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param quotationInfoDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveQuotationInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody QuotationInfoDTO quotationInfoDTO) {
		log.info("QuotationInfoResource-saveQuotationInfo");

		return new ResponseEntity<>(quotationInfoService.saveQuotationInfo(loggedUser, quotationInfoDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param quotationInfoDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyQuotationInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody QuotationInfoDTO quotationInfoDTO) {
		log.info("QuotationInfoResource-saveQuotationInfo");

		return new ResponseEntity<>(quotationInfoService.modifyQuotationInfo(loggedUser, quotationInfoDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param quotateid
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{quotateid}")
	public ResponseEntity<QuotationInfo> findByQuotationId(@RequestHeader String loggedUser,
			@PathVariable Long quotateid) {
		log.info("QuotationInfoResource-findByQuotationId {}", quotateid);

		return new ResponseEntity<>(quotationInfoService.findByQuotationId(loggedUser, quotateid), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param quotateid
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/info/{requirementid}")
	public List<QuotationInfo> findQuotationInfo(@RequestHeader String loggedUser, @PathVariable long requirementid) {
		log.info("QuotationInfoResource-findQuotationInfo {}", requirementid);

		return quotationInfoService.findQuotationInfo(loggedUser, requirementid);
	}

	/**
	 * @param authorization
	 * @param quotateid
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{quotateid}")
	public ResponseEntity<ApiResultDTO> removeQuotationInfo(@RequestHeader String loggedUser,
			@PathVariable Long quotateid) {
		log.info("QuotationInfoResource-removeQuotationInfo {}", quotateid);

		return new ResponseEntity<>(quotationInfoService.removeQuotationInfo(loggedUser, quotateid), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param approvalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/approvalhistory")
	public ResponseEntity<ApiResultDTO> updateUserApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO schoolApprovalHistoty) {
		log.info("UserApprovalResource-updateUserApproval {}", schoolApprovalHistoty);

		return new ResponseEntity<>(quotationServiceFacade.updateApprovalHistory(schoolApprovalHistoty), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/approval/{quotationid}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchUserDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("quotationid") Long quotationId) {
		log.info("UserApprovalResource-fetchUserDetailsForApproval {}", quotationId);
		return new ResponseEntity<>(quotationServiceFacade.fetchDetailsByQuotationId(quotationId), HttpStatus.OK);

	}

	/**
	 * @param authorization
	 * @param requirementId
	 * @param type
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/approval/{requirementId}/{type}")
	public List<SchoolApprovalHistoryDetails> findByRequirementIdAndType(@RequestHeader String authorization,
			@PathVariable("requirementId") Long requirementId, @PathVariable("type") String type) {
		log.info("UserApprovalResource-fetchUserDetailsForApproval {}", requirementId);
		return (schoolApprovalHisService.findByRequirementIdAndType(requirementId, type));

	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param status
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/vendordetails/{status}")
	public List<QuotationInfo> getQuotationVendorInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("status") List<String> status) {
		log.info("QuotationInfoServiceImpl-getQuotationVendorInfo");
		return quotationInfoService.getQuotationVendorInfo(status);
	}

}
