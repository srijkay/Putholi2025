package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.CompletionofRequirementsDTO;
import com.newrta.putholi.api.service.CompletionofRequirementsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@RestController
@Slf4j

@RequestMapping(value = "/v1/api/completionofrequirements")
public class CompletionofRequirementsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	CompletionofRequirementsService completionservice;

	/**
	 * @param authorization
	 * @param announcementDetails
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveCompletionofRequirementsInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody CompletionofRequirementsDTO completionofrequirementsDTO) {
		log.info("CompletionofRequirementsResource-saveCompletionofRequirementsInfo {}");

		return new ResponseEntity<>(
				completionservice.saveCompletionofRequirementsInfo(loggedUser, completionofrequirementsDTO),
				HttpStatus.OK);
	}
}
