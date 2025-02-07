package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.UserLoginAttempts;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserLoginAttemptService {

    /**
     * @param username
     */
    void updateFailAttempts(String username);

    /**
     * @param username
     */
    void resetFailAttempts(String username);

    /**
     * @param username
     * @return
     */
    UserLoginAttempts getUserAttempts(String username);

    /**
     * @param status
     * @param username
     */
    void updateUserAccountLock(boolean status, String username);

    /**
     * @param b
     * @param userName
     * @param role
     */
    void updateUserAccountEnableAndRole(boolean result, String userName, String role);

    /**
     * @param accountenabled
     * @param username
     * @return
     */
    void updateUserAccountEnabled(boolean accountenabled, String username);

    /**
     * @param roleId
     * @param loggeduser
     * @param locale
     * @return
     */
    boolean verifyUserName(String userName);
}
