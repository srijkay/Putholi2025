package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.MasterCodeTypeDetailsCustomsRepo;
import com.newrta.putholi.api.domain.MasterCodeTypeDetails;
import com.newrta.putholi.api.model.MasterCodeResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface MasterCodeTypeDetailsRepository
	extends JpaRepository<MasterCodeTypeDetails, Long>, MasterCodeTypeDetailsCustomsRepo {

    /**
     * @param active
     * @return
     */
    @Query(value = "SELECT new com.newrta.putholi.api.model.MasterCodeResultDTO(m.codeType, m.description, m.descriptionOther) "
	    + "FROM MasterCodeTypeDetails m WHERE m.active =:active ORDER BY m.codeType")
    List<MasterCodeResultDTO> findByActive(@Param("active") String active);

    /**
     * @param codeType
     * @return
     */
    boolean existsByCodeType(String codeType);

}
