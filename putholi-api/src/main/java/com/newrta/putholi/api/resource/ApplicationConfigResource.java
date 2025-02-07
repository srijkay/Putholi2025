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

import com.newrta.putholi.api.domain.ApplicationConfig;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ApplicationConfigDTO;
import com.newrta.putholi.api.model.GenericSearchDTO;
import com.newrta.putholi.api.service.ApplicationConfigService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Setter
@Getter
@RequestMapping(value = "/v1/api/config")
public class ApplicationConfigResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ApplicationConfigService applicationConfigService;

	/**
	 * @param authorization
	 * @param applicationConfig
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveConfigInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ApplicationConfigDTO applicationConfigDTO) {
		log.info("ApplicationConfigResource-saveConfigInfo {}", applicationConfigDTO);

		return new ResponseEntity<>(applicationConfigService.saveConfigInfo(loggedUser, applicationConfigDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param applicationConfig
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyConfigInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody ApplicationConfigDTO applicationConfigDTO) {
		log.info("ApplicationConfigResource-ApiResultDTO {}", applicationConfigDTO);

		return new ResponseEntity<>(applicationConfigService.modifyConfigInfo(loggedUser, applicationConfigDTO),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{configFor}")
	public ResponseEntity<ApplicationConfig> fetchConfigInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable String configFor) {
		log.info("ApplicationConfigResource-fetchConfigInfo {}", configFor);

		return new ResponseEntity<>(applicationConfigService.fetchApplicationConfig(loggedUser, configFor),
				HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResultDTO> removeConfigInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("ApplicationConfigResource-removeConfigInfo {}", id);

		return new ResponseEntity<>(applicationConfigService.removeApplicationConfig(loggedUser, id), HttpStatus.OK);
	}

	/**
	 * @param authorization
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<ApplicationConfig> searchApplicationConfig(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody GenericSearchDTO searchDTO) {
		log.info("ApplicationConfigResource-searchApplicationConfig {}");

		return applicationConfigService.searchApplicationConfig(loggedUser, searchDTO);
	}

}
