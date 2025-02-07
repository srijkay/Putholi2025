package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.RoleDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchResultsDTO;
import com.newrta.putholi.api.model.RoleDetailsViewResultsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RoleManagementService {

    /**
     * @param roleDetail
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> createRoleDetails(RoleDetailsViewResultsDTO roleDetailDTO, String loggeduser, String locale);

    /**
     * @param roleDetail
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> updateRoleDetails(RoleDetailsViewResultsDTO roleDetailDTO, String loggeduser, String locale);

    /**
     * @param roleDetailsSearchDTO
     * @param loggeduser
     * @return
     */
    ResponseEntity<Page<RoleDetailsSearchResultsDTO>> searchRoleDetails(RoleDetailsSearchDTO roleDetailsSearchDTO,
	    String loggeduser, String locale);

    /**
     * @param roleId
     * @param loggeduser
     * @return
     */
    ResponseEntity<RoleDetails> viewRoleDetails(Long roleId, String loggeduser, String locale);

    /**
     * @param roleId
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> deleteRoledetails(Long roleId, String loggeduser, String locale);

    /**
     * @param loggeduser
     * @return
     */
    ResponseEntity<List<RoleDetails>> getActiveRoles(String loggeduser, String locale);

    /**
     * @param rolecode
     * @return
     */
    RoleDetails getRoleDetailsbyRoleCode(String rolecode);

}
