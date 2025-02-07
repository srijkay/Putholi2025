package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.model.ModuleDetailSearchResultsDTO;
import com.newrta.putholi.api.model.ModuleDetailsSearchDTO;


/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ModuleDetailsCustomsRepo {

    
    /**
     * @param moduleDetailsSearchDTO
     * @return
     */
    Page<ModuleDetailSearchResultsDTO> searchModuleDetails(ModuleDetailsSearchDTO moduleDetailsSearchDTO);

}
