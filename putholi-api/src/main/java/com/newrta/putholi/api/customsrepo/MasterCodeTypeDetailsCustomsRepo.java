package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.MasterCodeTypeDetails;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface MasterCodeTypeDetailsCustomsRepo {

    /**
     * @param masterCodeSearchDTO
     * @return
     */
    Page<MasterCodeTypeDetails> searchMasterCodeTypes(MasterCodeSearchDTO masterCodeSearchDTO);

}
