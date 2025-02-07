package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.ModuleDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ModuleDetailSearchResultsDTO;
import com.newrta.putholi.api.model.ModuleDetailsDTO;
import com.newrta.putholi.api.model.ModuleDetailsSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ModuleDetailsService {

    /**
     * @param locale
     * @param authorization
     * @param moduleDetailsDTO
     * @return
     */
    ResponseEntity<ApiResultDTO> saveModuleDetails(ModuleDetailsDTO moduleDetailsDTO, String locale, String loggeduser);

    /**
     * @param moduleDetailsSearchDTO
     * @param loggeduser
     * @return
     */
    ResponseEntity<Page<ModuleDetailSearchResultsDTO>> searchModuleCodedetails(
	    ModuleDetailsSearchDTO moduleDetailsSearchDTO, String loggeduser);

    /**
     * @param sysId
     * @param loggeduser
     * @return
     */
    ResponseEntity<ModuleDetails> viewModuleCodedetails(Long moduleId, String loggeduser, String locale);

    /**
     * @param sysId
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> deleteModuleCodedetails(Long moduleId, String loggeduser, String locale);

    /**
     * @param moduleDetails
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> updateModuleDetails(ModuleDetailsDTO moduleDetailsDTO, String loggeduser, String locale);

    /**
     * @param loggeduser
     * @param locale
     * @return
     */
    ResponseEntity<List<ModuleDetails>> getActiveModules(String loggeduser, String locale);

}
