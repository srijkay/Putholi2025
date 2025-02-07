package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.newrta.putholi.api.domain.ModuleDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ModuleDetailSearchResultsDTO;
import com.newrta.putholi.api.model.ModuleDetailsDTO;
import com.newrta.putholi.api.model.ModuleDetailsSearchDTO;
import com.newrta.putholi.api.service.ModuleDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Data
@Slf4j
@RequestMapping(value = "/v1/api/module")
public class ModuleDetailsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	ModuleDetailsService moduleDetailsService;

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param moduleDetails
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> saveModuleCodeDetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
			@RequestBody ModuleDetailsDTO moduleDetailsDTO) {
		log.info("::ModuleDetailsResource::saveModuleCodeDetails::");
		return moduleDetailsService.saveModuleDetails(moduleDetailsDTO, locale, loggeduser);
	}

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param moduleDetailsSearchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public ResponseEntity<Page<ModuleDetailSearchResultsDTO>> searchModuleCodedetails(
			@RequestHeader("Accept-Language") String locale, @RequestHeader("loggeduser") String loggeduser,
			@RequestHeader("Authorization") String authToken,
			@RequestBody ModuleDetailsSearchDTO moduleDetailsSearchDTO) {
		log.info("::ModuleDetailsResource::searchModuleCodedetails:: {}", moduleDetailsSearchDTO);
		return moduleDetailsService.searchModuleCodedetails(moduleDetailsSearchDTO, loggeduser);
	}

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param sysId
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{moduleId}")
	public ResponseEntity<ModuleDetails> viewModuleCodedetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
			@PathVariable("moduleId") Long moduleId) {
		log.info("::ModuleDetailsResource::viewModuleCodedetails:: {}", moduleId);
		return moduleDetailsService.viewModuleCodedetails(moduleId, loggeduser, locale);
	}

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param sysId
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{moduleId}")
	public ResponseEntity<ApiResultDTO> deleteModuleCodedetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
			@PathVariable("moduleId") Long moduleId) {
		log.info("::ModuleDetailsResource::deleteModuleCodedetails:: {}", moduleId);
		return moduleDetailsService.deleteModuleCodedetails(moduleId, loggeduser, locale);
	}

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param moduleDetails
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyModuledetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
			@RequestBody ModuleDetailsDTO moduleDetailsDTO) {
		log.info("::ModuleDetailsResource::modifyModuledetails::");
		return moduleDetailsService.updateModuleDetails(moduleDetailsDTO, loggeduser, locale);
	}

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/active")
	public ResponseEntity<List<ModuleDetails>> fetchActiveModuleDetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken) {
		log.info("::ModuleDetailsResource::fetchActiveModuleDetails::");
		return moduleDetailsService.getActiveModules(loggeduser, locale);
	}

}
