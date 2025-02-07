package com.newrta.putholi.api.serviceimpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.UserLoginAttempts;
import com.newrta.putholi.api.repository.UserLoginAttemptsRepository;
import com.newrta.putholi.api.repository.UserLoginRepository;
import com.newrta.putholi.api.service.UserLoginAttemptService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class UserLoginAttemptServiceImpl implements UserLoginAttemptService {

    /**
     * 
     */
    private static final int MAX_ATTEMPTS = 3;

    /**
     * 
     */
    @Autowired(required = true)
    private UserLoginAttemptsRepository userLoginAttemptsRepository;

    /**
     * 
     */
    @Autowired(required = true)
    private UserLoginRepository userLoginRepository;

    /**
     * 
     */
    @Override
    @Transactional
    public void updateFailAttempts(String username) {
	log.info("UserLoginAttemptServiceImpl-updateFailAttempts");

	UserLoginAttempts user = getUserAttempts(username);
	if (user == null) {
	    log.info("-=-=-=-user is NULL=-=-=-=-");
	    if (userLoginRepository.existsByUserNameIgnoreCase(username)) {
		log.info("-=-=-=-user is NULL and exists by username =-=-=-=-");
		// if no record, insert a new
		userLoginAttemptsRepository.save(new UserLoginAttempts(username, 1, new Date()));
	    }
	} else {
	    log.info("-=-=-=-User Is Not NULL =-=-=-");
	    if (userLoginRepository.existsByUserNameIgnoreCase(username)) {
		log.info("-=-=-=-user is not NULL and exists by username =-=-=-=- {}", listUserAttempts(user));
		// update attempts count, +1
		userLoginAttemptsRepository
			.save(new UserLoginAttempts(username, listUserAttempts(user) + 1, new Date()));
	    }
	    log.info("-=-=-=-MAX_ATTEMPTS Verification-=-=-=- {}", listUserAttempts(user));
	    if (listUserAttempts(user) + 1 >= MAX_ATTEMPTS) {
		// locked user
		userLoginAttemptsRepository.updateUserAccountLock(false, username);
	    }
	}

    }

    /**
     * @param user
     * @return
     */
    public int listUserAttempts(UserLoginAttempts user) {
	return user.getAttempts();
    }

    /**
     * 
     */
    @Override
    @Transactional
    public void resetFailAttempts(String username) {
	log.info("UserLoginAttemptServiceImpl-resetFailAttempts");
	userLoginAttemptsRepository.resetFailAttempts(username);
    }

    /**
     * 
     */
    @Override
    public UserLoginAttempts getUserAttempts(String username) {
	log.info("UserLoginAttemptServiceImpl-getUserAttempts");
	return userLoginAttemptsRepository.findByUsername(username);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public void updateUserAccountLock(boolean status, String username) {
	log.info("UserLoginAttemptServiceImpl-updateUserAccountLock");
	userLoginAttemptsRepository.updateUserAccountLock(status, username);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public void updateUserAccountEnableAndRole(boolean result, String userName, String role) {
	log.info("UserLoginAttemptServiceImpl-updateUserAccountEnableAndRole");
	userLoginAttemptsRepository.updateUserAccountEnableAndRole(result, userName, role);
    }

    /**
     * 
     */
    @Override
    @Transactional
    public void updateUserAccountEnabled(boolean accountenabled, String username) {
	log.info("UserLoginAttemptServiceImpl-updateUserAccountEnabled");
	userLoginAttemptsRepository.updateUserAccountEnabled(accountenabled, username);
    }

    /**
     * 
     */
    @Override
    public boolean verifyUserName(String userName) {
	log.info("UserLoginAttemptServiceImpl-verifyUserName");
	return userLoginRepository.existsByUserNameIgnoreCase(userName);
    }

}
