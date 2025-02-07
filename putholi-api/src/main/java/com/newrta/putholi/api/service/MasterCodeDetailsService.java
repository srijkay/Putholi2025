package com.newrta.putholi.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.MasterCodeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MasterCodeResultDTO;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface MasterCodeDetailsService {

    /**
     * @param masterCodeDetails
     * @return
     */
    ApiResultDTO createMasterCode(String locale, MasterCodeDetails masterCodeDetails);

    /**
     * @param id
     * @return
     */
    Optional<MasterCodeDetails> findMasterCodeDetailsById(Long id);

    /**
     * @param codeType
     * @param active
     * @return
     */
    List<MasterCodeResultDTO> findActiveMasterCodesByCodeType(String codeType, String active);

    /**
     * @param codeType
     * @param code
     * @return
     */
    MasterCodeResultDTO findMasterCodesByCodeTypeAndCode(String codeType, String code);

    /**
     * @param locale
     * @param masterCodeSearchDTO
     * @return
     */
    Page<MasterCodeDetails> searchMasterCodes(String locale, MasterCodeSearchDTO masterCodeSearchDTO);

    /**
     * @param masterCodeDetailsDTO
     * @return
     */
    ApiResultDTO modifyMasterCode(String locale, MasterCodeDetails masterCodeDetailsDTO);

    /**
     * @param codeList
     * @return
     */
    List<MasterCodeResultDTO> getMasterCodeLists(List<String> codeList);
    
    /**
     * 
     * @param active
     * @return
     */

	List<MasterCodeResultDTO> findActiveMasterCodeType(String active);

}
