package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.MenuDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchDTO;
import com.newrta.putholi.api.model.MenuDetailsSearchResultsDTO;
import com.newrta.putholi.api.model.MenuDetailsViewResultsDTO;
import com.newrta.putholi.api.service.MenuManagementService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Data
@Slf4j
@RequestMapping(value = "/v1/api/menu")
public class MenuManagementResource {

    /**
     * 
     */
    @Autowired(required = true)
    MenuManagementService menuManagementService;

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param menuDetails
     * @return
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ApiResultDTO> saveMenudetails(@RequestHeader("Accept-Language") String locale,
            @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
            @RequestBody MenuDetailsViewResultsDTO menuDetailsDTO) {
        log.info("::MenuManagementResource::MenuManagementResource::");
        return menuManagementService.insertMenuDetails(menuDetailsDTO, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param menuDetails
     * @return
     */
    @CrossOrigin
    @PutMapping
    public ResponseEntity<ApiResultDTO> modifyMenudetails(@RequestHeader("Accept-Language") String locale,
            @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
            @RequestBody MenuDetailsViewResultsDTO menuDetailsDTO) {
        log.info("::MenuManagementResource::MenuManagementResource::");
        return menuManagementService.updateMenuDetails(menuDetailsDTO, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param menuId
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/{menuId}")
    public ResponseEntity<MenuDetails> viewMenudetails(@RequestHeader("Accept-Language") String locale,
            @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken,
            @RequestBody @PathVariable Long menuId) {
        log.info("::MenuManagementResource::viewMenudetails:: {}", menuId);
        return menuManagementService.viewMenuDetails(menuId, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @param menuDetailsSearchDTO
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/search")
    public ResponseEntity<Page<MenuDetailsSearchResultsDTO>> searchMenuDetails(
            @RequestHeader("Accept-Language") String locale, @RequestHeader("loggeduser") String loggeduser,
            @RequestHeader("Authorization") String authToken, @RequestBody MenuDetailsSearchDTO menuDetailsSearchDTO) {
        log.info("::MenuManagementResource::searchMenudetails::");
        return menuManagementService.searchMenuDetails(menuDetailsSearchDTO, loggeduser, locale);
    }

    /**
     * @param locale
     * @param loggeduser
     * @param authToken
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/actives")
    public ResponseEntity<List<MenuDetails>> fetchActiveMenuDetails(@RequestHeader("Accept-Language") String locale,
            @RequestHeader("loggeduser") String loggeduser, @RequestHeader("Authorization") String authToken) {
        log.info("::MenuManagementResource::fetchActiveMenuDetails::");
        return menuManagementService.getActiveMenu(loggeduser, locale);
    }

}
