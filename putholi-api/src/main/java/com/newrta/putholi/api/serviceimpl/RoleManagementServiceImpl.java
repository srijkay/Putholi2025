package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.RoleDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchResultsDTO;
import com.newrta.putholi.api.model.RoleDetailsViewResultsDTO;
import com.newrta.putholi.api.repository.RoleManagementRepository;
import com.newrta.putholi.api.service.RoleManagementService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 */
@Service
@Slf4j
@Data
public class RoleManagementServiceImpl implements RoleManagementService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private RoleManagementRepository roleManagementRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	CommonQueueUtilService commonQueueUtilService;

	/**
	* 
	*/
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> createRoleDetails(RoleDetailsViewResultsDTO roleDetailDTO, String loggeduser,
			String locale) {
		log.info("::RoleManagementServiceImpl::createRoleDetails:: {}", roleDetailDTO.getRoleId());

		ApiResultDTO apiResultDTO;
		RoleDetails roleDetail = modelMapper.map(roleDetailDTO, RoleDetails.class);

		if (roleManagementRepository.existsByRoleCode(roleDetail.getRoleCode())) {

			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT, CommonsConstants.ROLE_MGMT,
								new ObjectMapper().writeValueAsString(roleDetail), "error.already.exists", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("createRoleDetails-JsonProcessingException {}", jpe.getCause());
			}

			List<Object> objArray = new ArrayList<>();
			objArray.add(roleDetail.getRoleCode());
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(
							LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
					.build();
		} else {

			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT, CommonsConstants.ROLE_MGMT,
								new ObjectMapper().writeValueAsString(roleDetail), "save.success", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("createRoleDetails-JsonProcessingException {}", jpe.getCause());
			}

			RoleDetails roleDetails = roleManagementRepository.save(roleDetail);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
			apiResultDTO.setId(roleDetails.getRoleId());
		}

		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> updateRoleDetails(RoleDetailsViewResultsDTO roleDetailDTO, String loggeduser,
			String locale) {
		log.info("::RoleManagementServiceImpl::updateRoleDetails:: {}", roleDetailDTO.getRoleId());

		ApiResultDTO apiResultDTO;

		RoleDetails roleDetail = modelMapper.map(roleDetailDTO, RoleDetails.class);

		if (roleManagementRepository.existsByRoleId(roleDetail.getRoleId())) {

			RoleDetails roleDetails = roleManagementRepository.save(roleDetail);

			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.ROLE_MGMT,
								new ObjectMapper().writeValueAsString(roleDetail), "update.success", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("updateRoleDetails-JsonProcessingException {}", jpe.getCause());
			}

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
			apiResultDTO.setId(roleDetails.getRoleId());
		} else {

			try {
				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE,
						CommonsConstants.ROLE_MGMT, new ObjectMapper().writeValueAsString(roleDetail),
						"error.invalid.request.notexist", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("updateRoleDetails-JsonProcessingException {}", jpe.getCause());
			}

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.invalid.request.notexist", null, locale, null))
					.build();
		}

		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);

	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<Page<RoleDetailsSearchResultsDTO>> searchRoleDetails(
			RoleDetailsSearchDTO roleDetailsSearchDTO, String loggeduser, String locale) {
		log.info("::RoleManagementServiceImpl::searchRoleDetails:: {}", roleDetailsSearchDTO.getRoleId());

		return new ResponseEntity<>(roleManagementRepository.searchRoleDetails(roleDetailsSearchDTO), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<RoleDetails> viewRoleDetails(Long roleId, String loggeduser, String locale) {
		log.info("::RoleManagementServiceImpl::viewRoleDetails:: {}", roleId);

		return new ResponseEntity<>(roleManagementRepository.findByRoleId(roleId), HttpStatus.OK);

	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> deleteRoledetails(Long roleId, String loggeduser, String locale) {
		log.info("::RoleManagementServiceImpl::deleteRoledetails::");

		commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.DELETE,
				CommonsConstants.ROLE_MGMT, null, "Deleted id: " + roleId, loggeduser));

		roleManagementRepository.deleteById(roleId);
		return new ResponseEntity<>(
				ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc(LocaleConfig.getResourceValue("delete.success", null, locale, null)).build(),
				HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<List<RoleDetails>> getActiveRoles(String loggeduser, String locale) {
		log.info("RoleManagementServiceImpl:: getActiveRoles");

		return new ResponseEntity<>(roleManagementRepository.findByStatus("Y"), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public RoleDetails getRoleDetailsbyRoleCode(String rolecode) {
		log.info("RoleManagementServiceImpl:: getRoleDetailsbyRoleCode");

		return roleManagementRepository.findByRoleCode(rolecode);
	}
}