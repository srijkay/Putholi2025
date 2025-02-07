package com.newrta.putholi.api.service;

import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ContactUsInfoDTO;

/**
 * @author SivaSreenivas
 *
 */
public interface ContactUsService {

    /**
     * @param contactUsInfoDTO
     * @param locale
     * @return
     */
    ApiResultDTO sendContactUsFeedback(ContactUsInfoDTO contactUsInfoDTO, String locale);

}
