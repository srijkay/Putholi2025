package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ContactUsInfoDTO;
import com.newrta.putholi.api.service.ContactUsService;
import com.newrta.putholi.api.constants.CommonsConstants;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SivaSreenivas
 *
 */
@RestController
@Data
@NoArgsConstructor
@Slf4j
public class ContactUsResource {
    
    /**
     * 
     */ 
    @Autowired(required = true)
    private ContactUsService contactUsService;
    
    /**
     * @param authorization
     * @param locale
     * @param contactUsInfoDTO 
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/api/contactus")
    public ResponseEntity<ApiResultDTO> sendContactUsFeedback(
            @RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
            @RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale, @RequestBody ContactUsInfoDTO contactUsInfoDTO) {
        log.info("ContactUsResource-sendContactUsFeedback");
        return new ResponseEntity<>(contactUsService.sendContactUsFeedback(contactUsInfoDTO, locale), HttpStatus.OK);
    }

}
