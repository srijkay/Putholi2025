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

import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.InvoiceDetailsDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.InvoiceDetailsService;
import com.newrta.putholi.api.service.InvoiceDetailsServiceFacade;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Slf4j
@RestController
@Data
@RequestMapping(value = "/v1/api/invoice")
public class InvoiceDetailsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private InvoiceDetailsService invoiceService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private InvoiceDetailsServiceFacade invoiceServiceFacade;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param quotationInfoDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveInvoiceInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody InvoiceDetailsDTO invoiceDetailsDTO) {
		log.info("InvoiceDetailsService-saveInvoiceInfo");

		return new ResponseEntity<>(invoiceService.saveInvoiceInfo(loggedUser, invoiceDetailsDTO), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param invoiceDetailsDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyInvoiceInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody InvoiceDetailsDTO invoiceDetailsDTO) {
		log.info("InvoiceDetailsService-modifyInvoiceInfo");

		return new ResponseEntity<>(invoiceService.modifyInvoiceInfo(loggedUser, invoiceDetailsDTO), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param quotateid
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{invoiceid}")
	public ResponseEntity<InvoiceDetails> findByInvoiceId(@RequestHeader String loggedUser,
			@PathVariable Long invoiceid) {
		log.info("InvoiceDetailsService-findByInvoiceId {}", invoiceid);

		return new ResponseEntity<>(invoiceService.findByInvoiceId(loggedUser, invoiceid), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param quotateid
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/info/{requirementid}")
	public List<InvoiceDetails> findByRequirementId(@RequestHeader String loggedUser,
			@PathVariable Long requirementid) {
		log.info("InvoiceDetailsService-findByRequirementId {}", requirementid);

		return invoiceService.findByRequirementId(loggedUser, requirementid);
	}

	/**
	 * @param authorization
	 * @param quotateid
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{invoiceid}")
	public ResponseEntity<ApiResultDTO> removeInvoiceByInvoiceId(@RequestHeader String loggedUser,
			@PathVariable Long invoiceid) {
		log.info("InvoiceDetailsService-removeInvoiceByInvoiceId {}", invoiceid);

		return new ResponseEntity<>(invoiceService.removeInvoiceByInvoiceId(loggedUser, invoiceid), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param approvalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/approvalhistory")
	public ResponseEntity<ApiResultDTO> updateUserApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO historyDTOs) {
		log.info("InvoiceDetailsService-updateUserApproval {}");
		return new ResponseEntity<>(invoiceServiceFacade.updateApprovalHistory(historyDTOs), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/approval/{invoiceid}/{type}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchUserDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("invoiceid") Long invoiceId, @PathVariable String type) {
		log.info("InvoiceDetailsService-fetchUserDetailsForApproval {}", invoiceId);
		return new ResponseEntity<>(invoiceServiceFacade.fetchDetailsForApproval(invoiceId, type), HttpStatus.OK);

	}

}
