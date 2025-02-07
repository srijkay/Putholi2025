package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.newrta.putholi.api.domain.AnnouncementDetails;
import com.newrta.putholi.api.model.AnnouncementDetailsDTO;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.service.AnnouncementDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@RestController
@Slf4j

@RequestMapping(value = "/v1/api/announcement")
public class AnnouncementDetailsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	AnnouncementDetailsService announcementService;

	/**
	 * @param authorization
	 * @param announcementDetails
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveAnnouncementInfo(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@RequestBody AnnouncementDetailsDTO announcementDetailsDTO) {
		log.info("AnnouncementDetailsResource-saveAnnouncementInfo {}");

		return new ResponseEntity<>(announcementService.saveAnnouncementInfo(loggedUser, announcementDetailsDTO), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param announcementDetails
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyAnnouncementInfo(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@RequestBody AnnouncementDetailsDTO announcementDetailsDTO) {
		log.info("AnnouncementDetailsResource-modifyAnnouncementInfo {}");

		return new ResponseEntity<>(announcementService.modifyAnnouncementInfo(loggedUser, announcementDetailsDTO), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnnouncementDetails> fectchAnnouncementInfo(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable Long id) {
		log.info("AnnouncementDetailsResource-fectchAnnouncementInfo {}");

		return new ResponseEntity<>(announcementService.fectchAnnouncementInfo(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResultDTO> removeAnnouncementInfo(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable Long id) {
		log.info("AnnouncementDetailsResource-removeAnnouncementInfo {}");

		return new ResponseEntity<>(announcementService.removeAnnouncementInfo(loggedUser,id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<AnnouncementDetails> searchAnnouncementInfo(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@RequestBody AnnouncementDetailsDTO searchDTO) {
		log.info("AnnouncementDetailsResource-searchAnnouncementInfo {}");

		return announcementService.searchAnnouncementInfo(loggedUser, searchDTO);
	}

}
