package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.MasterCodeDetails;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface MasterCodeDetailsCustomsRepo {
    
    /**
     * @param locale
     * @param masterCodeSearchDTO
     * @return
     */
    Page<MasterCodeDetails> searchMasterCodes(String locale, MasterCodeSearchDTO masterCodeSearchDTO);

}
