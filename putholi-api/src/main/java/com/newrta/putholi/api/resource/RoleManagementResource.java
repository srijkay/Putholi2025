package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.newrta.putholi.api.domain.RoleDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchResultsDTO;
import com.newrta.putholi.api.model.RoleDetailsViewResultsDTO;
import com.newrta.putholi.api.service.RoleManagementService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/role")
public class RoleManagementResource {

    /**
     * 
     */
    @Autowired(required = true)
    RoleManagementService roleManagementService;

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param roleDetailsSearchDTO
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/search")
    public ResponseEntity<Page<RoleDetailsSearchResultsDTO>> searchRoleDetails(
	    @RequestHeader("Accept-Language") String locale, @RequestHeader("loggeduser") String loggeduser,
	    @RequestHeader("Authorization") String authToken, @RequestBody RoleDetailsSearchDTO roleDetailsSearchDTO) {
	log.info("::RoleManagementResource::searchRoledetails::");
	return roleManagementService.searchRoleDetails(roleDetailsSearchDTO, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param roleId
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/{roleId}")
    public ResponseEntity<RoleDetails> viewRoledetails(@RequestHeader("Accept-Language") String locale,
	    @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
	    @RequestBody @PathVariable Long roleId) {
	log.info("::RoleManagementResource::viewMaildetails:: {}", roleId);
	return roleManagementService.viewRoleDetails(roleId, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param roleDetails
     * @return
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ApiResultDTO> saveRoledetails(@RequestHeader("Accept-Language") String locale,
	    @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
	    @RequestBody RoleDetailsViewResultsDTO roleDetailsDTO) {
	log.info("::RoleManagementResource::RoleManagementResource::");
	return roleManagementService.createRoleDetails(roleDetailsDTO, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param roleDetails
     * @return
     */
    @CrossOrigin
    @PutMapping
    public ResponseEntity<ApiResultDTO> modifyRoledetails(@RequestHeader("Accept-Language") String locale,
	    @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
	    @RequestBody RoleDetailsViewResultsDTO roleDetailsDTO) {
	log.info("::RoleManagementResource::RoleManagementResource::");
	return roleManagementService.updateRoleDetails(roleDetailsDTO, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/active")
    public ResponseEntity<List<RoleDetails>> fetchActiveRoleDetails(@RequestHeader("Accept-Language") String locale,
	    @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken) {
	log.info("::RoleManagementResource::fetchActiveRoleDetails::");
	return roleManagementService.getActiveRoles(loggeduser, locale);
    }
}
