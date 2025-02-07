package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.newrta.putholi.api.domain.ApproverLevels;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApproverLevelsDTO;
import com.newrta.putholi.api.service.ApproverLevelDetailsService;

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
@RequestMapping(value = "/v1/api/approverinfo")
public class ApproverLevelDetailsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	ApproverLevelDetailsService approverDetailsService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param approverLevelsDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResultDTO> saveApproverDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ApproverLevelsDTO approverLevelsDTO) {
		log.info("ApproverLevelDetailsResource-saveApproverDetails {}", approverLevelsDTO);

		return new ResponseEntity<>(approverDetailsService.saveApproverDetails(loggedUser, approverLevelsDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param approverLevelsDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyApproverDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ApproverLevelsDTO approverLevelsDTO) {
		log.info("ApproverLevelDetailsResource-saveApproverDetails {}", approverLevelsDTO);

		return new ResponseEntity<>(approverDetailsService.modifyApproverDetails(loggedUser, approverLevelsDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<ApproverLevels> fetchApproverDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("ApproverLevelDetailsResource-fetchSchoolInfo {} {}", loggedUser, id);

		return new ResponseEntity<>(approverDetailsService.fetchApproverDetails(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResultDTO> removeApproverDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("ApproverLevelDetailsResource-removeSchoolInfo {} {}", loggedUser, id);

		return new ResponseEntity<>(approverDetailsService.removeApproverDetails(loggedUser, id), HttpStatus.OK);
	}

	
	
	@CrossOrigin
	@GetMapping(value = "/all/{id}")
	public List<ApproverLevels> findByAllApproverLevels(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("ApproverLevelDetailsServiceImpl-findByAllApproverLevels {}", id);
		
		return approverDetailsService.findByAllApproverLevels(loggedUser, id);
	}
}
