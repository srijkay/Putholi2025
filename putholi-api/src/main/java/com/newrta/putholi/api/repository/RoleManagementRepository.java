package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.RoleManagementCustomsRepo;
import com.newrta.putholi.api.domain.RoleDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface RoleManagementRepository extends JpaRepository<RoleDetails, Long>, RoleManagementCustomsRepo {

    /**
     * 
     * @param roleId
     * @return
     */
    /** method to get routing results by passing the key **/
    public RoleDetails findByRoleId(Long roleId);

    /**
     * @param roleId
     * @return
     */
    public boolean existsByRoleId(Long roleId);

    /**
     * @param roleCode
     * @return
     */
    public boolean existsByRoleCode(String roleCode);

    /**
     * @param status
     * @return
     */
    public List<RoleDetails> findByStatus(String status);

    /**
     * @param rolecode
     * @return
     */
    public RoleDetails findByRoleCode(String rolecode);

}
