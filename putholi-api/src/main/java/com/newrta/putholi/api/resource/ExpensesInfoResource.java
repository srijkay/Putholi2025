package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ExpensesDetailsDTO;
import com.newrta.putholi.api.model.ExpensesViewDetailsSearchDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.ExpensesDetailsService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryFacadeService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@RestController
@Data
@Slf4j
@RequestMapping(value = "/v1/api/expenses")
public class ExpensesInfoResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ExpensesDetailsService expensesService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryFacadeService schoolhistoryfacadeService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param expensesDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveExpensesDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ExpensesDetailsDTO expensesDTO) {
		log.info("ExpensesInfoResource-saveExpensesDetails");
		return new ResponseEntity<>(expensesService.saveExpensesDetails(loggedUser, expensesDTO), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param expensesDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyExpensesDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ExpensesDetailsDTO expensesDTO) {
		log.info("ExpensesInfoResource-modifyExpensesDetails");
		return new ResponseEntity<>(expensesService.modifyExpensesDetails(loggedUser, expensesDTO), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param expensesId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{expensesId}")
	public ResponseEntity<ExpensesDetails> findByExpensesId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("expensesId") Long expensesId) {
		log.info("ExpensesInfoResource-findByExpensesId");

		return new ResponseEntity<>(expensesService.findByExpensesId(loggedUser, expensesId), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<ExpensesDetails> serachExpensesDetails(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ExpensesViewDetailsSearchDTO searchDTO) {
		log.info("ExpensesInfoResource-serachExpensesDetails {} {}", loggedUser, searchDTO);

		return expensesService.searchExpensesDetails(loggedUser, searchDTO);
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
		log.info("ExpensesInfoResource-updateUserApproval {}", schoolApprovalHistDtls);

		return new ResponseEntity<>(schoolhistoryfacadeService.updateExpensesApprovalHistory(schoolApprovalHistDtls),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param expensesId
	 * @param type
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/approval/{expensesId}/{type}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchUserDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("expensesId") Long expensesId, @PathVariable("type") String type) {
		log.info("ExpensesInfoResource-fetchUserDetailsForApproval {}", expensesId);

		return new ResponseEntity<>(schoolhistoryfacadeService.fetchDetailsForExpensesApproval(expensesId, type),
				HttpStatus.OK);
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
		log.info("SchoolInfoResource-CheckPendingStatus {}");

		return new ResponseEntity<>(expensesService.checkPendingStatus(status), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param locale
	 * @param quotation
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/paymentdetails", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> applicantAttachments(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,

			@RequestPart("quotation") MultipartFile file) {
		log.info("ExpensesInfoResource-applicantAttachments");

		return new ResponseEntity<>(expensesService.updateExpensesPayment(file), HttpStatus.OK);
	}
}
