package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.model.MenuDetailsSearchDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchResultsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface MenuManagementCustomsRepo {

    /**
     * @param menuDetailsSearchDTO
     * @return
     */
    Page<MenuDetailsSearchResultsDTO> searchMenuDetails(MenuDetailsSearchDTO menuDetailsSearchDTO);

}
