package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RoleDetailsViewResultsDTO;
import com.newrta.putholi.api.model.RoleMenuDTO;
import com.newrta.putholi.api.model.RoleMenuResultDTO;
import com.newrta.putholi.api.service.RoleMenuService;
import com.newrta.putholi.api.service.RoleMenuServiceFacade;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Data
@Slf4j
@RequestMapping(value = "/v1/api/rolemenu")
public class RoleMenuResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	RoleMenuService roleMenuService;

	/**
	 * 
	 */
	@Autowired(required = true)
	RoleMenuServiceFacade roleMenuServiceFacade;

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param roleDetails
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyRolMenuedetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
			@RequestBody RoleMenuDTO roleDetails) {
		log.info(":RoleMenuResourcee:modifyRoleMenudetailse::");
		return roleMenuService.updateRoleMenuDetails(roleDetails, loggeduser, locale);
	}

	/**
	 * @param locale
	 * @param loggeduser
	 * @param authToken
	 * @param roleDetails
	 * @return
	 */
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<RoleMenuResultDTO>> getRolMenuedetails(@RequestHeader("Accept-Language") String locale,
			@RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
			@RequestBody RoleDetailsViewResultsDTO roleDetailsDTO) {
		log.info(":RoleMenuResourcee:getRolMenuedetails::");
		return roleMenuServiceFacade.getRoleMenuDetails(roleDetailsDTO, loggeduser, locale);
	}

}
