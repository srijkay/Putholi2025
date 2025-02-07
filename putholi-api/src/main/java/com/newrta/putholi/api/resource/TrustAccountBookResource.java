package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.TrustAccountBook;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.TrustAccountBookDTO;
import com.newrta.putholi.api.service.TrustAccountBookService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/trustaccountbook")
public class TrustAccountBookResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private TrustAccountBookService trustAccountBookService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param trustAccountBookDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveTrustAccountBook(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody TrustAccountBookDTO trustAccountBookDTO) {
		log.info("TrustAccountBookResource-saveTrustAccountBook");

		return new ResponseEntity<>(trustAccountBookService.saveAccountBook(loggedUser, trustAccountBookDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param projectId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{projectid}")
	public TrustAccountBook findByTrustBook(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable("projectid") Long projectId) {
		log.info("TrustAccountBookResource-findByTrustBook");

		return trustAccountBookService.getAccountBook(loggedUser, projectId);

	}
}
