package com.newrta.putholi.api.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.FeatureManagement;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.FeatureManagementDTO;
import com.newrta.putholi.api.model.FeatureManagementSearchDTO;
import com.newrta.putholi.api.service.FeatureManagementService;
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
@RequestMapping(value = "/v1/api/featuremanagement")
public class FeatureManagementResource {
	
	    /**
	     * 
	     */
	    @Autowired(required = true)
	    private FeatureManagementService featureManagementService;

	    /**
	     * 
	     * @param authorization
	     * @param loggedUser
	     * @param featureManagementDTO
	     * @return
	     */
	    @CrossOrigin
	    @PostMapping
	    public ResponseEntity<ApiResultDTO> saveFeatureManagement(@RequestHeader String authorization,
		    @RequestHeader String loggedUser, @RequestBody FeatureManagementDTO featureManagementDTO) {
		log.info("FeatureManagementResource-saveFeatureManagement {} {}", loggedUser, featureManagementDTO);

		return new ResponseEntity<>(featureManagementService.saveFeatureManagement(loggedUser, featureManagementDTO), HttpStatus.OK);
	    }

	    /**
	     * 
	     * @param authorization
	     * @param loggedUser
	     * @param id
	     * @return
	     */
	    @CrossOrigin
	    @GetMapping(value = "/{id}")
	    public ResponseEntity<FeatureManagement> fetchFeatureManagement(@RequestHeader String authorization,
		    @RequestHeader String loggedUser, @PathVariable Long id) {
		log.info("FeatureManagementResource-fetchFeatureManagement {} {}", loggedUser, id);

		return new ResponseEntity<>(featureManagementService.fetchFeatureManagement (loggedUser, id), HttpStatus.OK);
	    }

	    /**
	     * 
	     * @param authorization
	     * @param loggedUser
	     * @param featureManagementDTO
	     * @return
	     */
	    
	    @CrossOrigin
	    @PutMapping
	    public ResponseEntity<ApiResultDTO> modifyFeatureManagement (@RequestHeader String authorization,
		    @RequestHeader String loggedUser, @RequestBody FeatureManagementDTO featureManagementDTO) {
		log.info("FeatureManagementResource-modifyFeatureManagement {} {}", loggedUser, featureManagementDTO);

		return new ResponseEntity<>(featureManagementService.modifyFeatureManagement(loggedUser, featureManagementDTO), HttpStatus.OK);
	    }

	   

	   /**
	    * 
	    * @param authorization
	    * @param loggedUser
	    * @param searchDTO
	    * @return
	    */
	    @CrossOrigin
	    @PostMapping(value = "/search")
	    public Page<FeatureManagement> searchFeatureManagement(@RequestHeader String authorization,
		    @RequestHeader String loggedUser, @RequestBody FeatureManagementSearchDTO searchDTO) {
		log.info("FeatureManagementResource-searchFeatureManagement( {} {}", loggedUser, searchDTO);

		return featureManagementService.searchFeatureManagement(loggedUser, searchDTO);
	    }

	


}
