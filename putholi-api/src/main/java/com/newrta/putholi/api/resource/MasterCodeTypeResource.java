package com.newrta.putholi.api.resource;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.MasterCodeTypeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.MasterCodeList;
import com.newrta.putholi.api.model.MasterCodeSearchDTO;
import com.newrta.putholi.api.model.MasterCodeTypeDetailsDTO;
import com.newrta.putholi.api.service.MasterCodeTypeDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/mastercodetype")
public class MasterCodeTypeResource {

    /**
     * 
     */
    @Autowired(required = true)
    private MasterCodeTypeDetailsService masterCodeTypeDetailsService;

    /**
     * 
     */
    @Autowired(required = true)
    private ModelMapper modelMapper;

    /**
     * @param authorization
     * @param locale
     * @param masterCodeTypeDetailsDTO
     * @return
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ApiResultDTO> createMasterCodeType(
            @RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
            @RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
            @RequestBody MasterCodeTypeDetailsDTO masterCodeTypeDetailsDTO) {
        log.info("MasterCodeTypeResource-createMasterCodeType");
        return new ResponseEntity<>(masterCodeTypeDetailsService.createMasterCodeType(locale,
                modelMapper.map(masterCodeTypeDetailsDTO, MasterCodeTypeDetails.class)), HttpStatus.OK);

    }

    /**
     * @param authorization
     * @param locale
     * @param masterCodeTypeDetailsDTO
     * @return
     */
    @CrossOrigin
    @PutMapping
    public ResponseEntity<ApiResultDTO> modifyMasterCodeType(
            @RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
            @RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
            @RequestBody MasterCodeTypeDetailsDTO masterCodeTypeDetailsDTO) {
        log.info("MasterCodeTypeResource-modifyMasterCodeType");
        return new ResponseEntity<>(masterCodeTypeDetailsService.modifyMasterCodeType(locale,
                modelMapper.map(masterCodeTypeDetailsDTO, MasterCodeTypeDetails.class)), HttpStatus.OK);

    }

    /**
     * @param authorization
     * @param locale
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<MasterCodeTypeDetails> fetchMasterCodeTypeById(
            @RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
            @RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale, @PathVariable("id") Long id) {
        log.info("MasterCodeTypeResource-fetchMasterCodeTypeById");
        return masterCodeTypeDetailsService.findMasterCodeTypeDetailsById(id);
    }

    /**
     * @param authorization
     * @param locale
     * @param masterCodeSearchDTO
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/search")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<MasterCodeTypeDetails> searchMasterCodeTypes(
            @RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
            @RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
            @RequestBody MasterCodeSearchDTO masterCodeSearchDTO) {
        log.info("MasterCodeTypeResource-searchMasterCodeTypes");
        return masterCodeTypeDetailsService.searchMasterCodeType(locale, masterCodeSearchDTO);
    }

    /**
     * @param authorization
     * @param locale
     * @param codeType
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/active")
    @ResponseStatus(code = HttpStatus.OK)
    public MasterCodeList findActiveMasterCodeTypes(@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
            @RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale) {
        log.info("CommonResource-findActiveMasterCodeTypes");
        return new MasterCodeList(masterCodeTypeDetailsService.findMasterCodeTypes(CommonsConstants.YES));
    }

}
