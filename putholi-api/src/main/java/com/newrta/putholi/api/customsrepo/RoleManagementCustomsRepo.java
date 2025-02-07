package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.model.RoleDetailsSearchDTO;
import com.newrta.putholi.api.model.RoleDetailsSearchResultsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RoleManagementCustomsRepo {

    /**
     * @param roleDetailsSearchDTO
     * @return
     */
    Page<RoleDetailsSearchResultsDTO> searchRoleDetails(RoleDetailsSearchDTO roleDetailsSearchDTO);

}
