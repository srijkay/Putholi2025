package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.ModuleDetailsCustomsRepo;
import com.newrta.putholi.api.domain.ModuleDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface ModuleDetailsRepository extends JpaRepository<ModuleDetails, Long>, ModuleDetailsCustomsRepo {

    /**
     * @param moduleCode
     * @return
     */
    ModuleDetails findByModuleCode(String moduleCode);

    /**
     * @param moduleCode
     * @param id
     * @return
     */
    ModuleDetails findByModuleCodeOrModuleId(String moduleCode, Long id);

    /**
     * @param moduleId
     * @return
     */
    public ModuleDetails findByModuleId(Long moduleId);

    /**
     * @param moduleCode
     * @return
     */
    public boolean existsByModuleCode(String moduleCode);

    /**
     * @param orderNo
     * @return
     */
    public boolean existsByOrderNo(int orderNo);

    /**
     * @param orderNo
     * @return
     */
    ModuleDetails findByOrderNo(int orderNo);

    /**
     * @param active
     * @return
     */
    List<ModuleDetails> findByActive(String active);

}
