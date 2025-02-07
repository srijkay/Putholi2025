package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.service.QuotationAttachmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.QuotationAttachments;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Data
@Slf4j
@RequestMapping(value = "/v1/api/attachments")
public class QuotationAttachmentResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private QuotationAttachmentService quotationAttachmentService;

	/**
	 * @param authorization
	 * @param locale
	 * @param attachmentDTO
	 * @param quotation
	 * @return
	 */
	@CrossOrigin
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> applicantAttachments(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestPart("QuotationAttachmentsDTO") String attachmentDTO, @RequestPart("fileType") String fileType,
			@RequestPart("quotation") MultipartFile quotation) {
		log.info("QuotationAttachmentResource-applicantAttachments");

		ApiResultDTO apiResultDTO;

		try {
			QuotationAttachments attDTO = new ObjectMapper().readValue(attachmentDTO, QuotationAttachments.class);

			apiResultDTO = quotationAttachmentService.saveQuotationAttachment(locale, attDTO.getRequirementId(),
					attDTO.getQuotationId(), attDTO.getInvoiceId(), attDTO.getSchoolInfoId(), attDTO.getExpensesId(),
					fileType, quotation);

		} catch (JsonProcessingException jpe) {
			log.error("applicantAttachments-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact Admin!").build();
		}
		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param locale
	 * @param attachmentDTO
	 * @param quotation
	 * @return
	 */
	@CrossOrigin
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> updateAttachmentByAttachmentId(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestPart("QuotationAttachmentsDTO") String attachmentDTO, @RequestPart("fileType") String fileType,
			@RequestPart("quotation") MultipartFile quotation) {

		log.info("QuotationAttachmentResource-updateAttachmentByAttachmentId");

		ApiResultDTO apiResultDTO;
		try {
			QuotationAttachments attDTO = new ObjectMapper().readValue(attachmentDTO, QuotationAttachments.class);

			apiResultDTO = quotationAttachmentService.updateAttachmentByAttachmentId(locale, attDTO.getAttachmentId(),
					attDTO.getRequirementId(), attDTO.getQuotationId(), attDTO.getInvoiceId(), attDTO.getSchoolInfoId(),
					attDTO.getExpensesId(), fileType, quotation);

		} catch (JsonProcessingException jpe) {
			log.error("applicantAttachments-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact Admin!").build();
		}
		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{requirementid}/{uploadFor}")
	public List<QuotationAttachments> findQuotationAttachment(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long requirementid, @PathVariable String uploadFor) {
		log.info("QuotationAttachmentResource-findQuotationAttachment {}", requirementid);

		return quotationAttachmentService.getQuotationAttachment(requirementid, uploadFor);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{attachmentid}")
	public ResponseEntity<ApiResultDTO> removeQuotationAttachment(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long attachmentid) {
		log.info("QuotationAttachmentResource-removeQuotationAttachment {}", attachmentid);

		return new ResponseEntity<>(quotationAttachmentService.removeQuotationAttachment(attachmentid), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/attachment/{attachmentid}")
	public QuotationAttachments findByAttachmentId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long attachmentid) {
		log.info("QuotationAttachmentResource-findByAllApproverLevels {}", attachmentid);

		return quotationAttachmentService.findByAttachmentId(attachmentid);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/quotate/{quotationid}")
	public QuotationAttachments getAttachmentByquotationId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long quotationid) {
		log.info("QuotationAttachmentResource-getAttachmentByquotationId {}", quotationid);

		return quotationAttachmentService.getAttachmentByquotationId(quotationid);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param id
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/invoice/{invoiceid}/{uploadfor}")
	public List<QuotationAttachments> getAttachmentByInvoiceId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long invoiceid, @PathVariable String uploadfor) {
		log.info("QuotationAttachmentResource-findByAllApproverLevels {}", invoiceid);

		return quotationAttachmentService.getAttachmentByInvoiceId(invoiceid, uploadfor);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param quotationId
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/quotationid/{quotationId}")
	public ResponseEntity<ApiResultDTO> removeAttachmentByQuotateId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable long quotationId) {
		log.info("QuotationAttachmentResource-removeAttachmentByQuotateId {}", quotationId);

		return new ResponseEntity<>(quotationAttachmentService.removeAttachmentByQuotateId(quotationId), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param schoolId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/school/{schoolid}/{uploadfor}")
	public List<QuotationAttachments> findBySchoolId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable long schoolid, @PathVariable String uploadfor) {
		log.info("QuotationAttachmentResource-findBySchoolId {}", schoolid);

		return quotationAttachmentService.findBySchoolInfoId(schoolid, uploadfor);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param invoiceid
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/invoiceid/{invoiceid}")
	public ResponseEntity<ApiResultDTO> removeAttachmentByInvoiceId(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable long invoiceid) {
		log.info("QuotationAttachmentResource-removeAttachmentByQuotateId {}", invoiceid);

		return new ResponseEntity<>(quotationAttachmentService.removeAttachmentByInvoiceId(invoiceid), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param requirementId
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/delAttach/{requirementId}/{uploadFor}")
	public ResponseEntity<ApiResultDTO> deleteByRequirementIdAndUploadFor(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long requirementId, @PathVariable String uploadFor) {
		log.info("QuotationAttachmentResource-deleteByRequirementIdAndUploadFor {}", requirementId);

		return new ResponseEntity<>(
				quotationAttachmentService.deleteByRequirementIdAndUploadFor(requirementId, uploadFor), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/fectchby/{uploadfor}")
	public List<QuotationAttachments> getAttachments(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("uploadfor") String uploadFor) {
		log.info("QuotationAttachmentResource-getAttachments");

		return quotationAttachmentService.getAttachments(loggedUser, uploadFor);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/fectchbysixmonths/{uploadfor}")
	public List<QuotationAttachments> findByLastSixMonthsUploadFor(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable("uploadfor") String uploadFor) {
		log.info("QuotationAttachmentResource-findByLastSixMonthsUploadFor");

		return quotationAttachmentService.findByLastSixMonthsUploadFor(uploadFor);
	}

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param expensesId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/expensesid/{id}")
	public QuotationAttachments findByExpensesId(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable("id") long expensesId) {
		log.info("QuotationAttachmentResource-findByExpensesId {}");

		return quotationAttachmentService.findByExpensesId(expensesId);
	}
}
