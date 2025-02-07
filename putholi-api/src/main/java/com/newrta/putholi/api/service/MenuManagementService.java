package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.MenuDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchResultsDTO;
import com.newrta.putholi.api.model.MenuDetailsViewResultsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface MenuManagementService {

    /**
     * @param menuDetails
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> insertMenuDetails(MenuDetailsViewResultsDTO menuDetailsDTO, String loggeduser, String locale);

    /**
     * @param menuDetails
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> updateMenuDetails(MenuDetailsViewResultsDTO menuDetailsDTO, String loggeduser, String locale);

    /**
     * @param menuId
     * @param loggeduser
     * @return
     */
    ResponseEntity<ApiResultDTO> deleteMenudetails(Long menuId, String loggeduser, String locale);

    /**
     * @param menuId
     * @param loggeduser
     * @return
     */
    ResponseEntity<MenuDetails> viewMenuDetails(Long menuId, String loggeduser, String locale);

    /**
     * @param menuDetailsSearchDTO
     * @param loggeduser
     * @return
     */
    ResponseEntity<Page<MenuDetailsSearchResultsDTO>> searchMenuDetails(MenuDetailsSearchDTO menuDetailsSearchDTO,
	    String loggeduser, String locale);

    /**
     * @param loggeduser
     * @param locale
     * @return
     */
    ResponseEntity<List<MenuDetails>> getActiveMenu(String loggeduser, String locale);

}
