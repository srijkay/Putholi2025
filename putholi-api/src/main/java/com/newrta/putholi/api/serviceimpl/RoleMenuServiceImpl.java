package com.newrta.putholi.api.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.RoleMenuPrivilege;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MenuDetailsSelectDTO;
import com.newrta.putholi.api.model.RoleMenuDTO;
import com.newrta.putholi.api.model.RoleMenuMapProj;
import com.newrta.putholi.api.model.RoleMenuResultDTO;
import com.newrta.putholi.api.repository.RoleMenuManagementRepository;
import com.newrta.putholi.api.service.RoleMenuService;
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
public class RoleMenuServiceImpl implements RoleMenuService {

    /**
     * 
     */
    @Autowired(required = true)
    private RoleMenuManagementRepository roleMenuManagementRepository;

    /**
     * 
     */
    @Autowired(required = true)
    CommonQueueUtilService commonQueueUtilService;

    /**
     * Method to find role menu mapping for view
     */
    @Override
    public List<RoleMenuResultDTO> findallRoleMenuMap(Long roleId) {
	log.info("::RoleMenuServiceImpl::findallRoleMenuMap:: {}", roleId);

	List<RoleMenuResultDTO> res = new ArrayList<>();
	List<RoleMenuMapProj> list = roleMenuManagementRepository.findallRoleMenuMap(roleId, "Y");

	LinkedHashMap<String, String> map = new LinkedHashMap<>();
	for (RoleMenuMapProj projDTO : list) {
	    map.put(projDTO.getModuleCode(), projDTO.getModuleDesc() + "-" + projDTO.getOrderNo());
	}

	for (Map.Entry<String, String> entry : map.entrySet()) {
	    RoleMenuResultDTO roleresDTO = new RoleMenuResultDTO();
	    List<MenuDetailsSelectDTO> menuDtlList = new ArrayList<>();
	    roleresDTO.setModuleCode(entry.getKey());
	    String str = entry.getValue();
	    String [] strarr = str.split("-");
	    roleresDTO.setModuleDesc(strarr[0]);
	    roleresDTO.setOrderNo(Integer.parseInt(strarr[1]));

	    for (RoleMenuMapProj projDTO : list) {
		if (projDTO.getModuleCode().equals(entry.getKey())) {
		    MenuDetailsSelectDTO menudtlDTO = new MenuDetailsSelectDTO();
		    menudtlDTO.setMenuCode(projDTO.getMenuCode());
		    menudtlDTO.setMenuName(projDTO.getMenuName());
		    menudtlDTO.setSelected(projDTO.isSelected());
		    menudtlDTO.setMenuId(projDTO.getMenuId());
		    menuDtlList.add(menudtlDTO);
		}
	    }
	    roleresDTO.setMenuDetails(menuDtlList);
	    res.add(roleresDTO);
	}
	return res;
    }

    /**
     *
     */
    @Override
    @Transactional
    public ResponseEntity<ApiResultDTO> updateRoleMenuDetails(RoleMenuDTO roleMenuDetails, String loggeduser,
	    String locale) {
	log.info("::RoleMenuServiceImpl::updateRoleMenuDetails:: {}", roleMenuDetails.getRoleId());

	try {
	    commonQueueUtilService
		    .sendAuditDetailsToQueue(new AuditDetails(CommonsConstants.UPDATE, CommonsConstants.ROLEMENU_MGMT,
			    new ObjectMapper().writeValueAsString(roleMenuDetails), null, loggeduser));
	} catch (JsonProcessingException jpe) {
	    log.error("updateRoleMenuDetails-JsonProcessingException {}", jpe.getCause());
	}
	roleMenuManagementRepository.deleteRoleMenuWithMenuIds(roleMenuDetails.getRoleId());
	Long roleid = roleMenuDetails.getRoleId();
	String createdBy = roleMenuDetails.getCreatedBy();
	List<RoleMenuPrivilege> menuList = new ArrayList<>();
	for (Long menuid : roleMenuDetails.getMenuId()) {
	    RoleMenuPrivilege menupriv = new RoleMenuPrivilege();
	    menupriv.setRoleId(roleid);
	    menupriv.setMenuId(menuid);
	    menupriv.setCreatedBy(createdBy);
	    menuList.add(menupriv);
	}
	roleMenuManagementRepository.saveAll(menuList);
	return new ResponseEntity<>(
		ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
			.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null)).build(),
		HttpStatus.OK);
    }

}