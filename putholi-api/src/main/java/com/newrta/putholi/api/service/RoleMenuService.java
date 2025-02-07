package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RoleMenuDTO;
import com.newrta.putholi.api.model.RoleMenuResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RoleMenuService {

    /**
     * @param roleMenuDetails
     * @param loggeduser
     * @param locale
     * @return
     */
    ResponseEntity<ApiResultDTO> updateRoleMenuDetails(RoleMenuDTO roleMenuDetails, String loggeduser, String locale);

    /**
     * @param roleId
     * @return
     */
    List<RoleMenuResultDTO> findallRoleMenuMap(Long roleId);

}
