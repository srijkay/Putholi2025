package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.RoleDetails;
import com.newrta.putholi.api.model.RoleDetailsViewResultsDTO;
import com.newrta.putholi.api.model.RoleMenuResultDTO;
import com.newrta.putholi.api.service.RoleMenuService;
import com.newrta.putholi.api.service.RoleMenuServiceFacade;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class RoleMenuServiceFacadeImpl implements RoleMenuServiceFacade {

	/**
	 * 
	 */
	@Autowired(required = true)
	RoleMenuService roleMenuService;

	/**
	* 
	*/
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Override
	public ResponseEntity<List<RoleMenuResultDTO>> getRoleMenuDetails(RoleDetailsViewResultsDTO roleDetailDTO,
			String loggeduser, String locale) {
		log.info("::RoleManagementServiceImpl::getRoleMenuDetails:: {}", roleDetailDTO.getRoleId());
		RoleDetails roleDetail = modelMapper.map(roleDetailDTO, RoleDetails.class);

		return new ResponseEntity<>(roleMenuService.findallRoleMenuMap(roleDetail.getRoleId()), HttpStatus.OK);

	}

}
