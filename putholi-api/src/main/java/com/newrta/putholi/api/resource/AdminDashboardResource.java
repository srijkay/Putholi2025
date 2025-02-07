package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.model.AdminDashboardDTO;
import com.newrta.putholi.api.service.AdminDashboardService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/dashboard/")
public class AdminDashboardResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private AdminDashboardService dashboardService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param consolidateId
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/{id}")
	public AdminDashboardDTO fetchFundDetails(@RequestHeader String authorization, @RequestHeader String loggedUser,
			@PathVariable("id") Long consolidateId) {
		log.info("AdminDashboardResource-fetchFundDetails {}", consolidateId);

		return dashboardService.fetchFundDetails(loggedUser, consolidateId);
	}

}
