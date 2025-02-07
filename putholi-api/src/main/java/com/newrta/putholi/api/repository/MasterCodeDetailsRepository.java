package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.MasterCodeDetailsCustomsRepo;
import com.newrta.putholi.api.domain.MasterCodeDetails;
import com.newrta.putholi.api.model.MasterCodeResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface MasterCodeDetailsRepository
	extends JpaRepository<MasterCodeDetails, Long>, MasterCodeDetailsCustomsRepo {

    /**
     * @param codeType
     * @param active
     * @return
     */
    @Query(value = "SELECT new com.newrta.putholi.api.model.MasterCodeResultDTO(m.code, m.description, m.descriptionOther) "
	    + "FROM MasterCodeDetails m WHERE m.codeType =:codeType AND m.active =:active ORDER BY m.description")
    List<MasterCodeResultDTO> findByCodeTypeAndActive(@Param("codeType") String codeType,
	    @Param("active") String active);

    /**
     * @param codeType
     * @param code
     * @return
     */
    @Query(value = "SELECT new com.newrta.putholi.api.model.MasterCodeResultDTO(m.code, m.description, m.descriptionOther) "
	    + "FROM MasterCodeDetails m WHERE m.codeType =:codeType AND m.code =:code")
    MasterCodeResultDTO findByCodeTypeAndCode(@Param("codeType") String codeType, @Param("code") String code);

    /**
     * @param code
     * @param codeType
     * @return
     */
    boolean existsByCodeAndCodeType(String code, String codeType);

    /**
     * @param codeList
     * @return
     */
    @Query(value = "SELECT new com.newrta.putholi.api.model.MasterCodeResultDTO(m.code, m.codeType, m.description, m.descriptionOther) "
	    + "FROM MasterCodeDetails m WHERE m.code in :codeList")
    List<MasterCodeResultDTO> getMasterCodeLists(@Param("codeList") List<String> codeList);
    
    /**
     * 
     * @param active
     * @return
     */

    @Query(value = "SELECT new com.newrta.putholi.api.model.MasterCodeResultDTO(m.code, m.description, m.descriptionOther) "
    	    + "FROM MasterCodeDetails m WHERE m.active =:active ORDER BY m.code")
        List<MasterCodeResultDTO> findByActive(@Param("active") String active);


}
