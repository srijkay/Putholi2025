package com.newrta.putholi.api.resource;

import java.util.Date;
import java.util.List;

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

import com.newrta.putholi.api.domain.TrustMemberAccountBook;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.TrustMemberAccountBookDTO;
import com.newrta.putholi.api.service.TrustMemberAccountBookService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Data
@Slf4j
@RequestMapping(value = "/v1/api/trustmember")
public class TrustMemberAccountBookResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private TrustMemberAccountBookService trustMemberBookService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param trustMemberAccountBookDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveTrustMemberAccountBook(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody TrustMemberAccountBookDTO trustMemberAccountBookDTO) {
		log.info("TrustMemberAccountBookResource-saveTrustMemberAccountBook");

		return new ResponseEntity<>(
				trustMemberBookService.saveTrustMemberAccountBook(loggedUser, trustMemberAccountBookDTO),
				HttpStatus.OK);
	}

	/**
	 * @param endDate
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/year/{year}")
	public ResponseEntity<List<TrustMemberAccountBook>> getPaymentsByDateRange(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("year") Date endDate) {
		log.info("TrustMemberAccountBookResource-getPaymentsByDateRange{}", endDate);

		return new ResponseEntity<>(trustMemberBookService.findByCreatedDateBetween(endDate), HttpStatus.OK);
	}

}
