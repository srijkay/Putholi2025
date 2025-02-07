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

import com.newrta.putholi.api.domain.ProjectAccountBook;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ProjectAccountBookDTO;
import com.newrta.putholi.api.service.ProjectAccountBookService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Slf4j
@RestController
@RequestMapping(value = "/v1/api/projectbook")
public class ProjectAccountBookResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ProjectAccountBookService projectBookService;

	/**
	 * @param loggedUser
	 * @param projectId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{projectid}")
	public List<ProjectAccountBook> getProjectAccount(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("projectid") Long projectId) {
		log.info("ProjectAccountBookResource-getProjectAccount");

		return projectBookService.findByProjectId(loggedUser, projectId);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param paymentInstrDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveProjectAccountBook(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ProjectAccountBookDTO projectAccountDTO) {
		log.info("ProjectAccountBookResource-saveProjectAccountBook");

		return new ResponseEntity<>(projectBookService.saveProjectAccountBook(loggedUser, projectAccountDTO),
				HttpStatus.OK);

	}

	/**
	 * @param endDate
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/year/{year}")
	public ResponseEntity<List<ProjectAccountBook>> getPaymentsByDateRange(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("year") Date endDate) {
		log.info("ProjectAccountBookResource-getPaymentsByDateRange{}", endDate);

		return new ResponseEntity<>(projectBookService.findByCreatedDateBetween(endDate), HttpStatus.OK);
	}

}
