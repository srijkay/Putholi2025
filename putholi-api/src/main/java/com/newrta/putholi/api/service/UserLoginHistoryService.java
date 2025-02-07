package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.UserLoginHistory;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface UserLoginHistoryService {

    /**
     * @param userLoginHistory
     * @return
     */
    UserLoginHistory saveUserLoginHistory(UserLoginHistory userLoginHistory);
}
