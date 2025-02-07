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
import com.newrta.putholi.api.domain.MenuDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchResultsDTO;
import com.newrta.putholi.api.model.MenuDetailsViewResultsDTO;
import com.newrta.putholi.api.repository.MenuManagementRepository;
import com.newrta.putholi.api.service.MenuManagementService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class MenuManagementServiceImpl implements MenuManagementService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private MenuManagementRepository menuManagementRepository;

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
	public ResponseEntity<ApiResultDTO> insertMenuDetails(MenuDetailsViewResultsDTO menuDetailsDTO, String loggeduser,
			String locale) {
		log.info("::MenuManagementServiceImpl::MenuDetails:: {}", menuDetailsDTO.getMenuId());

		ApiResultDTO apiResultDTO;
		MenuDetails menuDetails = modelMapper.map(menuDetailsDTO, MenuDetails.class);
		if (menuManagementRepository.existsByMenuCode(menuDetails.getMenuCode())) {
			try {

				commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT,
						CommonsConstants.MENU_MGMT, new ObjectMapper().writeValueAsString(menuDetails),
						"Menu Code Already Exist", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("insertMenuDetails-JsonProcessingException {}", jpe.getCause());
			}
			List<Object> objArray = new ArrayList<>();
			objArray.add(menuDetails.getMenuCode());
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(
							LocaleConfig.getResourceValue("error.already.exists", objArray.toArray(), locale, null))
					.build();
		} else {
			menuDetails = menuManagementRepository.save(menuDetails);

			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.INSERT, CommonsConstants.MENU_MGMT,
								new ObjectMapper().writeValueAsString(menuDetails), "Menu Details Insert", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("insertMenuDetails-JsonProcessingException {}", jpe.getCause());
			}

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build();
			apiResultDTO.setId(menuDetails.getMenuId());
		}
		return new ResponseEntity<>(apiResultDTO, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ApiResultDTO> updateMenuDetails(MenuDetailsViewResultsDTO menuDetailsDTO, String loggeduser,
			String locale) {
		log.info("::MenuManagementServiceImpl::updateMenuDetails:: {}", menuDetailsDTO.getMenuId());

		ApiResultDTO apiResultDTO;
		MenuDetails menuDetails = modelMapper.map(menuDetailsDTO, MenuDetails.class);
		if (menuManagementRepository.existsByMenuId(menuDetails.getMenuId())) {
			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.MENU_MGMT,
								new ObjectMapper().writeValueAsString(menuDetails), "Menu Code Updated", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("updateMenuDetails-JsonProcessingException {}", jpe.getCause());
			}

			menuManagementRepository.save(menuDetails);

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("update.success", null, locale, null)).build();
		} else {
			try {
				commonQueueUtilService
						.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.MENU_MGMT,
								new ObjectMapper().writeValueAsString(menuDetails), "code.notexist", loggeduser));
			} catch (JsonProcessingException jpe) {
				log.error("updateMenuDetails-JsonProcessingException {}", jpe.getCause());
			}

			List<Object> objArray = new ArrayList<>();
			objArray.add(menuDetails.getMenuCode());
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
	public ResponseEntity<ApiResultDTO> deleteMenudetails(Long menuId, String loggeduser, String locale) {
		log.info("::MenuManagementServiceImpl::deleteMenuDetails:: {}", menuId);

		commonQueueUtilService.sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.DELETE,
				CommonsConstants.MENU_MGMT, null, "Deleted id: " + menuId, loggeduser));

		menuManagementRepository.deleteById(menuId);
		return new ResponseEntity<>(
				ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
						.apiStatusDesc(LocaleConfig.getResourceValue("delete.success", null, locale, null)).build(),
				HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<MenuDetails> viewMenuDetails(Long menuId, String loggeduser, String locale) {

		log.info("::MenuManagementServiceImpl::viewMenuDetails:: {}", menuId);

		return new ResponseEntity<>(menuManagementRepository.findByMenuId(menuId), HttpStatus.OK);

	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<Page<MenuDetailsSearchResultsDTO>> searchMenuDetails(
			MenuDetailsSearchDTO menuDetailsSearchDTO, String loggeduser, String locale) {
		log.info("::MenuManagementServiceImpl::searchMenuDetails:: {}", menuDetailsSearchDTO.getMenuId());

		return new ResponseEntity<>(menuManagementRepository.searchMenuDetails(menuDetailsSearchDTO), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@Override
	public ResponseEntity<List<MenuDetails>> getActiveMenu(String loggeduser, String locale) {
		log.info("MenuManagementServiceImpl:: getActiveMenu");

		return new ResponseEntity<>(menuManagementRepository.findByStatus("Y"), HttpStatus.OK);
	}

}
