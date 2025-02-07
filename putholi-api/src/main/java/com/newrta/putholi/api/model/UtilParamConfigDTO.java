package com.newrta.putholi.api.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.newrta.putholi.api.constants.UtilParamPropertyConstants;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Component
public class UtilParamConfigDTO {
    
    /**
     * 
     */
    public UtilParamConfigDTO() {
        super();
    }
    
    /**
     * @param userPwdExpiryFlag
     */
    @Value("${userpassword.expiry.flag}")
    private void setUserPwdExpiryFlag(String userPwdExpiryFlag) {
        UtilParamPropertyConstants.setUserPwdExpiryFlag(userPwdExpiryFlag);
    }
    
    /**
     * @param userAccExpiryFlag
     */
    @Value("${useraccount.expiry.flag}")
    private void setUserAccFlag(String userAccExpiryFlag) {
        UtilParamPropertyConstants.setUserAccExpiryFlag(userAccExpiryFlag);
    }

}
