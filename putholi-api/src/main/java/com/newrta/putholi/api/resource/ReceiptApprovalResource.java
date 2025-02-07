package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDTO;
import com.newrta.putholi.api.model.SchoolApprovalHistoryDetailsDTO;
import com.newrta.putholi.api.service.ReceiptApprovalfacadeService;

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
@RequestMapping(value = "/v1/api/receipt")
public class ReceiptApprovalResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	ReceiptApprovalfacadeService receiptApprovalFacadeService;

	/**
	 * @param authorization
	 * @param approvalHistDtls
	 * @return
	 */
	@PostMapping(value = "/approval")
	public ResponseEntity<ApiResultDTO> updateUserApproval(@RequestHeader String authorization,
			@RequestBody SchoolApprovalHistoryDetailsDTO schoolApprovalHistDtls) {
		log.info("ReceiptApprovalResource-updateUserApproval {}", schoolApprovalHistDtls);

		return new ResponseEntity<>(receiptApprovalFacadeService.updateReceiptApprovalHistory(schoolApprovalHistDtls),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param username
	 * @return
	 */
	@GetMapping(value = "/approval/{schoolid}/{type}")
	public ResponseEntity<SchoolApprovalHistoryDTO> fetchUserDetailsForApproval(@RequestHeader String authorization,
			@PathVariable("schoolid") Long schoolId, @PathVariable String type) {
		log.info("ReceiptApprovalResource-fetchUserDetailsForApproval {}", schoolId);

		return new ResponseEntity<>(receiptApprovalFacadeService.fetchSchoolDetailsForApproval(schoolId, type),
				HttpStatus.OK);
	}

}
