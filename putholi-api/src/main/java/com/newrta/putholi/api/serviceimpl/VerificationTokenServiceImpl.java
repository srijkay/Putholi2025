/**
 * 
 */
package com.newrta.putholi.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.VerificationToken;
import com.newrta.putholi.api.repository.VerificationTokenRepository;
import com.newrta.putholi.api.service.VerificationTokenService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class VerificationTokenServiceImpl implements VerificationTokenService {

    /**
     * 
     */
    @Autowired(required = true)
    private VerificationTokenRepository verificationTokenRepository;

    /**
     * 
     */
    @Override
    public UserLogin getUser(String verificationToken) {
	log.info("VerificationTokenServiceImpl-getUser");
	return verificationTokenRepository.findByToken(verificationToken).getUserLogin();
    }

    /**
    * 
    */
    @Override
    public void createVerificationToken(UserLogin user, String token, int expiry) {
	log.info("ApplicantVerificationTokenServiceImpl-createVerificationToken");
	verificationTokenRepository.save(new VerificationToken(user, token, expiry));

    }

    /**
     * 
     */
    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
	log.info("VerificationTokenServiceImpl-getVerificationToken");
	return verificationTokenRepository.findByToken(verificationToken);
    }

}
