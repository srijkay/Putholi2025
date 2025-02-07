package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.ConsolidateInfoDetails;
import com.newrta.putholi.api.domain.ConsolidateRefInfo;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ConsolidateRefDTO;
import com.newrta.putholi.api.model.ConsolidateSearchDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.service.ConsolidateRefService;

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
@RequestMapping(value = "/v1/api/consolidate")
public class ConsolidateRefResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ConsolidateRefService consolidateRefService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param consolidateRefInfo
	 * @return
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<ApiResultDTO> saveConsolidateInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ConsolidateRefDTO consolidateRefDTO) {
		log.info("ConsolidateRefResource-saveConsolidateInfo {} {}", loggedUser, consolidateRefDTO);

		return new ResponseEntity<>(consolidateRefService.saveConsolidateInfo(loggedUser, consolidateRefDTO),
				HttpStatus.OK);

	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param consolidateRefDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyConsolidateInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ConsolidateRefDTO consolidateRefDTO) {
		log.info("ConsolidateRefResource-modifyConsolidateInfo {} {}", loggedUser, consolidateRefDTO);

		return new ResponseEntity<>(consolidateRefService.modifyConsolidateInfo(loggedUser, consolidateRefDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/consolidate/{id}")
	public ResponseEntity<ConsolidateRefInfo> fetchConsolidateInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("ConsolidateRefResource-fetchConsolidateInfo {}", id);

		return new ResponseEntity<>(consolidateRefService.fetchConsolidateInfo(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param consolidateDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<ConsolidateInfoDetails> searchConsolidateInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ConsolidateSearchDTO searchDTO) {
		log.info("ConsolidateRefResource-searchConsolidateInfo {} {}", loggedUser, searchDTO);

		return consolidateRefService.searchConsolidateInfo(loggedUser, searchDTO);
	}

	/**
	 * @param authorization
	 * @param status
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/statusCount/{status}")
	public ResponseEntity<Integer> checkPendingStatus(@RequestHeader String authorization,
			@PathVariable("status") List<String> status) {
		log.info("ConsolidateRefResource-CheckPendingStatus {}");

		return new ResponseEntity<>(consolidateRefService.checkPendingStatus(status), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param status
	 * @param volunteerName
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/assigned/statusCount/{status}/{volunteername}")
	public ResponseEntity<Integer> checkPendingStatus(@RequestHeader String authorization,
			@PathVariable("status") String status, @PathVariable("volunteername") String volunteerName) {
		log.info("ConsolidateRefResource-CheckPendingStatus {}");

		return new ResponseEntity<>(consolidateRefService.pendingStatusCount(status, volunteerName), HttpStatus.OK);
	}
	
	
	/**
	 * @param authorization
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/approval/{consolidateId}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchUserDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("consolidateId") Long consolidateId) {
		log.info("SchoolInfoResource-fetchUserDetailsForApproval {}");

		return new ResponseEntity<>(consolidateRefService.fetchDetailsForApproval(consolidateId), HttpStatus.OK);
	}

}
