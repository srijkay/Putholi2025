package com.newrta.putholi.api.customsrepo;

import java.util.List;

import com.newrta.putholi.api.model.RoleMenuMapProj;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RoleMenuCustomsRepo {

    /**
     * @param roleId
     * @param status
     * @return
     */
    List<RoleMenuMapProj> findallRoleMenuMap(Long roleId, String status);
}
