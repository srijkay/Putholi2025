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

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.model.UserApprovalDTO;
import com.newrta.putholi.api.service.UserApprovalServiceFacade;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/v1/api/userapproval")
@Slf4j
public class UserApprovalResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserApprovalServiceFacade userApprServiceFacade;

	/**
	 * @param authorization
	 * @param approvalHistDtls
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ApiResultDTO> updateUserApproval(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestBody ApprovalHistoryDetailsDTO approvalHistDtls) {
		log.info("UserApprovalResource-updateUserApproval {}", approvalHistDtls);

		return new ResponseEntity<>(userApprServiceFacade.updateUserApproval(approvalHistDtls), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param username
	 * @return
	 */
	@GetMapping(value = "/{username}/{type}")
	public ResponseEntity<UserApprovalDTO> fetchUserDetailsForApproval(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@PathVariable("username") String username, @PathVariable("type") String type) {
		log.info("UserApprovalResource-fetchUserDetailsForApproval {}", username);

		return new ResponseEntity<>(userApprServiceFacade.fetchUserDetailsForApproval(username, type), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param approvalHistDtls
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/deletion")
	public ResponseEntity<ApiResultDTO> updateRejectionUserApproval(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestBody ApprovalHistoryDetailsDTO approvalHistDtls) {
		log.info("UserApprovalResource-updateRejectionUserApproval {}", approvalHistDtls);

		return new ResponseEntity<>(userApprServiceFacade.updateRejectionUserApproval(approvalHistDtls), HttpStatus.OK);
	}
}
