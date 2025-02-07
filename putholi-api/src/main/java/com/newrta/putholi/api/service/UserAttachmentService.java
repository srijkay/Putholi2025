package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.UserAttachment;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserAttachmentService {


    /**
     * @param userProfileAttachment
     * @return
     */
    ApiResultDTO saveUserProfileAttachment(String locale, UserAttachment userProfileAttachment);
    
    
    /**
     * @param locale
     * @param userProfileAttachment
     * @return
     */
    ApiResultDTO modifyUserProfileAttachment(String locale, UserAttachment userProfileAttachment);

    /**
     * @param username
     * @return
     */
    UserAttachment getUserAttachment(String username, String uploadFor);

    /**
     * @param username
     * @return
     */
    ApiResultDTO removeProfilePic(String username);

    
    /**
     * @param uploadFor
     * @return
     */
    UserAttachment findByUploadFor(String uploadFor);

}
