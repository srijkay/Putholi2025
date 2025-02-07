package com.newrta.putholi.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.MasterCodeTypeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface MasterCodeTypeDetailsService {

    /**
     * @param locale
     * @param masterCodeTypeDetails
     * @return
     */
    ApiResultDTO createMasterCodeType(String locale, MasterCodeTypeDetails masterCodeTypeDetails);

    /**
     * @param locale
     * @param masterCodeTypeDetails
     * @return
     */
    ApiResultDTO modifyMasterCodeType(String locale, MasterCodeTypeDetails masterCodeTypeDetails);

    /**
     * @param id
     * @return
     */
    Optional<MasterCodeTypeDetails> findMasterCodeTypeDetailsById(Long id);

    /**
     * @param active
     * @return
     */
    List<MasterCodeResultDTO> findMasterCodeTypes(String active);

    /**
     * @param locale
     * @param masterCodeSearchDTO
     * @return
     */
    Page<MasterCodeTypeDetails> searchMasterCodeType(String locale, MasterCodeSearchDTO masterCodeSearchDTO);

}
