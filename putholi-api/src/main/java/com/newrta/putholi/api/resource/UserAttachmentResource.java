package com.newrta.putholi.api.resource;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.UserAttachment;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.service.UserAttachmentService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/attachment")
public class UserAttachmentResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1184931457790566987L;

	/**
	 * 
	 */
	@Autowired(required = true)
	public UserAttachmentService userAttachmentService;

	/**
	 * @param authorization
	 * @param locale
	 * @param username
	 * @param profilepic
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/profilepic", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResultDTO> profileAttachment(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale, @RequestPart("username") String username,
			@RequestPart("profilepic") MultipartFile profilepic) {
		log.info("UserProfileAttachmentResource-profileAttachment");

		ApiResultDTO apiResultDTO;
		try {
			apiResultDTO = userAttachmentService.saveUserProfileAttachment(locale, new UserAttachment(username,
					CommonsConstants.PROFILE_PIC, profilepic.getContentType(), profilepic.getBytes()));
		} catch (IOException e) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.processing", null, locale, null)).build();
		}

		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param locale
	 * @param username
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/profilepic/{username}")
	public ResponseEntity<ApiResultDTO> removeProfileAttachment(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale, @PathVariable("username") String username) {
		log.info("UserProfileAttachmentResource-removeProfileAttachment");
		return new ResponseEntity<>(userAttachmentService.removeProfilePic(username), HttpStatus.OK);
	}

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param username
	 * @param uploadFor
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{username}/{uploadfor}")
	public UserAttachment getAttachments(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable("username") String username, @PathVariable("uploadfor") String uploadFor) {
		log.info("QuotationAttachmentResource-getAttachments");

		return userAttachmentService.getUserAttachment(username, uploadFor);
	}

}
