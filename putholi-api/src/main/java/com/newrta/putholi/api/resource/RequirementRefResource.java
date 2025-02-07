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

import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.RequirementInfoDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RequirementDTO;
import com.newrta.putholi.api.model.RequirementSearchDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.RequirementApprovalHistoryFacadeService;
import com.newrta.putholi.api.service.RequirementService;

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
@RequestMapping(value = "/v1/api/requirement")

public class RequirementRefResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementService requirementService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private RequirementApprovalHistoryFacadeService requirementapprovalhistoryfacadeService;

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param requirementDTO
	 * @return
	 */

	@PostMapping
	@CrossOrigin
	public ResponseEntity<ApiResultDTO> saveRequirementInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody RequirementDTO requirementDTO) {
		log.info("RequirementRefResource-saveRequirementInfo {} {}", loggedUser, requirementDTO);

		return new ResponseEntity<>(requirementService.saveRequirementInfo(loggedUser, requirementDTO), HttpStatus.OK);

	}

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param requirementDTO
	 * @return
	 */

	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyRequirementInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody RequirementDTO requirementDTO) {
		log.info("RequirementRefResource-modifyRequirementInfo {} {}", loggedUser, requirementDTO);

		return new ResponseEntity<>(requirementService.modifyRequirementInfo(loggedUser, requirementDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/req/{id}")
	public ResponseEntity<RequirementInfo> fetchRequirementInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("RequirementRefResource-fetchRequirementInfo {}", id);

		return new ResponseEntity<>(requirementService.fetchRequirementInfo(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResultDTO> removeRequirementInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("RequirementRefResource-removeRequirementInfo {}", id);

		return new ResponseEntity<>(requirementService.removeRequirementInfo(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<RequirementInfoDetails> searchRequirementInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody RequirementSearchDTO searchDTO) {
		log.info("RequirementRefResource-searchRequirementInfo {} {}", loggedUser, searchDTO);

		return requirementService.searchRequirement(loggedUser, searchDTO);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @param status
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/all/{id}/{status}")
	public List<RequirementInfo> findAllRequirementByStatus(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id, @PathVariable(required = false) String status) {
		log.info("RequirementRefResource-findAllByRequirementInfoList {}", id);

		return requirementService.findAllRequirementByStatus(loggedUser, id, status);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/all/{id}")
	public List<RequirementInfo> findAllByRequirementInfoList(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("RequirementRefResource-findAllByRequirementInfoList {}", id);

		return requirementService.findAllByRequirementInfoList(loggedUser, id);
	}

	/**
	 * 
	 * @param authorization
	 * @param requirementApprovalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/reqapproval")
	public ResponseEntity<ApiResultDTO> updateUserApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO approvalHistotyDTO) {
		log.info("RequirementRefResource-updateUserApproval {}", approvalHistotyDTO);

		return new ResponseEntity<>(requirementapprovalhistoryfacadeService.updateApprovalHistory(approvalHistotyDTO),
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param authorization
	 * @param requirementId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/reqapproval/{requirementId}/{type}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("requirementId") Long requirementId, @PathVariable String type) {
		log.info("RequirementRefResource-fetchUserDetailsForApproval {}", requirementId);

		return new ResponseEntity<>(
				requirementapprovalhistoryfacadeService.fetchDetailsForApproval(requirementId, type), HttpStatus.OK);
	}

	/**
	 * 
	 * @param authorization
	 * @param reqStatus
	 * @return
	 */

	@CrossOrigin
	@GetMapping(value = "/statusCount/{reqStatus}")
	public ResponseEntity<Integer> checkPenStatus(@RequestHeader String authorization,
			@PathVariable("reqStatus") List<String> reqStatus) {
		log.info("RequirementRefResource-checkPenStatus {}");

		return new ResponseEntity<>(requirementService.checkPenStatus(reqStatus), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param reqStatus
	 * @param volunteername
	 * @param consolidateStatus
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/pendingstatusCount/{reqStatus}/{volunteername}/{consolidateStatus}")
	public ResponseEntity<Integer> pendingStatusCount(@RequestHeader String authorization,
			@PathVariable("reqStatus") List<String> reqStatus, @PathVariable String volunteername,
			@PathVariable String consolidateStatus) {
		log.info("RequirementRefResource-checkPenStatus {}");

		return new ResponseEntity<>(requirementService.pendingStatusCount(reqStatus, volunteername, consolidateStatus),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param consolidateId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/checkingStatus/{consolidateId}")
	public ResponseEntity<ApiResultDTO> updateConsolidateStatus(@RequestHeader String authorization,
			@PathVariable Long consolidateId) {
		log.info("RequirementRefResource-updateConsolidateStatus {}");

		return new ResponseEntity<>(requirementService.checkingStatus(consolidateId), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param reqStatus
	 * @param invoiceStatus
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/invoiceStatus/{reqStatus}/{invoiceStatus}")
	public ResponseEntity<Integer> checkInvoiceAndRequirementStatus(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable List<String> reqStatus,
			@PathVariable List<String> invoiceStatus) {
		log.info("RequirementRefResource-checkInvoiceAndRequirementStatus {}");

		return new ResponseEntity<>(
				requirementService.checkInvoiceAndRequirementStatus(loggedUser, reqStatus, invoiceStatus),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/allrequirements")
	public List<RequirementInfoDetails> getAllRequirements(@RequestHeader String authorization,
			@RequestHeader String loggedUser) {
		log.info("RequirementRefResource-getAllRequirements {}");
		return requirementService.getAllRequirements(loggedUser);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/req/description/{id}")
	public ResponseEntity<RequirementInfo> fetchRequirementInfoDescription(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("RequirementRefResource-fetchRequirementInfoDescription {}", id);

		return new ResponseEntity<>(requirementService.fetchRequirementInfoDescription(id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @param reqStatus
	 */
	@CrossOrigin
	@GetMapping(value = "/updatestatus/{id}/{reqStatus}")
	public void updateRequirementStatus(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable Long id, @PathVariable String reqStatus) {
		log.info("RequirementRefResource-fetchRequirementInfoDescription {}", id);

		requirementService.updateApprovalDetails(id, reqStatus);
	}
}
