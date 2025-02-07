package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.model.RoleDetailsViewResultsDTO;
import com.newrta.putholi.api.model.RoleMenuResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RoleMenuServiceFacade {

	/**
	 * @param roleDetail
	 * @param loggeduser
	 * @param locale
	 * @return
	 */
	ResponseEntity<List<RoleMenuResultDTO>> getRoleMenuDetails(RoleDetailsViewResultsDTO roleDetailDTO,
			String loggeduser, String locale);

}
