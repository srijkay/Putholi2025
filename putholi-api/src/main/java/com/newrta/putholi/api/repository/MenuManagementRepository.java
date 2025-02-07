package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.MenuManagementCustomsRepo;
import com.newrta.putholi.api.domain.MenuDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface MenuManagementRepository extends JpaRepository<MenuDetails, Long>, MenuManagementCustomsRepo {

    /**
     * 
     * @param menuId
     * @return
     */

    /** method to get routing results by passing the key **/
    public MenuDetails findByMenuId(Long menuId);

    /**
     * @param menuId
     * @return
     */
    public boolean existsByMenuId(Long menuId);

    /**
     * @param menuCode
     * @return
     */
    public boolean existsByMenuCode(String menuCode);

    /**
     * @param status
     * @return
     */
    public List<MenuDetails> findByStatus(String status);
}
