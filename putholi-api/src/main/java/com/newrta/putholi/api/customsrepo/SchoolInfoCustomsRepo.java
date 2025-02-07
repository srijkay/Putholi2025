package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.SchoolInfoDetails;
import com.newrta.putholi.api.model.SchoolDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface SchoolInfoCustomsRepo {
    
    /**
     * @param searchDTO
     * @return
     */
    Page<SchoolInfoDetails> searchSchoolInfo(SchoolDetailsDTO searchDTO);

}
