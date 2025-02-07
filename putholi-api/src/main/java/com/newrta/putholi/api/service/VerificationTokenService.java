package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.VerificationToken;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface VerificationTokenService {

    /**
     * @param verificationToken
     * @return
     */
    UserLogin getUser(String verificationToken);

    /**
     * @param user
     * @param token
     */
    void createVerificationToken(UserLogin user, String token, int expiry);

    /**
     * @param VerificationToken
     * @return
     */
    VerificationToken getVerificationToken(String VerificationToken);

}
