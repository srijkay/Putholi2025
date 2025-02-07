package com.newrta.putholi.api.util;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.domain.UserLoginAttempts;
import com.newrta.putholi.api.model.UserDetailsDTO;
import com.newrta.putholi.api.repository.UserLoginAttemptsRepository;
import com.newrta.putholi.api.repository.UserLoginRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Transactional
@Slf4j
@Data
public class MyUserDetailsService implements UserDetailsService {

	private static final int MILLI_SECONDS = 1200000;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserLoginRepository userLoginRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserLoginAttemptsRepository userLoginAttemptsRepository;

	/**
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loaded -=-=-=- loadUserByUsername");

		UserLogin user = userLoginRepository.findByUserNameIgnoreCase(username);

		if (user == null) {
			throw new UsernameNotFoundException("User Not found: " + username);
		} else {
			return verifyUserDetails(user);
		}
	}

	private UserDetails verifyUserDetails(UserLogin user) {
		if ((CommonsUtil.getTime(user.getCredentialsExpiryDate())
				- CommonsUtil.getTime(Calendar.getInstance().getTime())) <= 0) {
			userLoginAttemptsRepository.updateChangePasswordRequired(true, user.getUserName());
		}

		UserLoginAttempts userAttempts = userLoginAttemptsRepository.findByUsername(user.getUserName());

		/* verifying whether the account is locked */
		if (!user.isAccountNonLocked()) {

			/* account is locked */

			/*
			 * checking the last login attempt with wrong credentials - less than 20 Minutes
			 */
			if ((CommonsUtil.getTime(Calendar.getInstance().getTime())
					- getUserLastModifiedTime(userAttempts)) <= MILLI_SECONDS) {

				/*
				 * identified its less than 20 Minutes and no change to the account is locked
				 */
				user.setAccountNonLocked(false);
			} else {

				/*
				 * its more than 20mins since the last wrong attempt and reseting fail attempts
				 * and also removing the account lock
				 */
				userLoginAttemptsRepository.resetFailAttempts(user.getUserName());
				userLoginAttemptsRepository.updateUserAccountLock(true, user.getUserName());
				user.setAccountNonLocked(true);
			}

			/* account non locked & checking no user attempts */
		} else if (userAttempts == null) {

			/* no entries in user attempts so account non locked */
			user.setAccountNonLocked(true);

			/* account non locked & checking user attempts available */
		} else if (listUserAttempts(userAttempts) > 0) {

			/* user attempts are available even the account is non locked */

			/* identifying the last user attempt */
			if ((CommonsUtil.getTime(Calendar.getInstance().getTime())
					- getUserLastModifiedTime(userAttempts)) >= MILLI_SECONDS) {

				/*
				 * user attempting after 20 Minutes and need to reset failed attempts and update
				 * account non lock as true
				 */
				userLoginAttemptsRepository.resetFailAttempts(user.getUserName());
				userLoginAttemptsRepository.updateUserAccountLock(true, user.getUserName());
			}

			/* account non locked */
			user.setAccountNonLocked(true);
		}
		return new UserDetailsDTO(user);

	}

	private int listUserAttempts(UserLoginAttempts userAttempts) {
		return userAttempts.getAttempts();
	}

	/**
	 * @param userAttempts
	 * @return
	 */
	private long getUserLastModifiedTime(UserLoginAttempts userAttempts) {
		return CommonsUtil.getTime(userAttempts.getLastModified());
	}

	/**
	 * @param username
	 * @return
	 */
	public UserLogin getUserDetailsByUserName(String username) {
		return userLoginRepository.findByUserNameIgnoreCase(username);
	}

	/**
	 * @param userLogin
	 * @return
	 */
	public UserLogin generateUserCredentials(UserLogin userLogin) {
		return userLoginRepository.save(userLogin);
	}

	/**
	 * @param userlogin
	 * @return
	 */
	public UserLogin updateUserCredentials(UserLogin userlogin) {
		return userLoginRepository.save(userlogin);
	}

	/**
	 * @param accountenabled
	 * @param username
	 */
	public void updateUserAccountEnabled(boolean accountenabled, String username) {
		userLoginAttemptsRepository.updateUserAccountEnabled(accountenabled, username);
	}

	/**
	 * @param userName
	 * @param encodePassword
	 */
	public void forgetPasswordChange(String userName, String encodePassword) {
		log.info("ApplicantUserDetailsService-forgetPasswordChange");
		userLoginRepository.forgetPasswordChange(userName, encodePassword);
	}

	/**
	 * 
	 * @param userLogin
	 * @return
	 */

	public UserLogin generateDonorCredentials(UserLogin userLogin) {
		return userLoginRepository.save(userLogin);
	}

	/**
	 * 
	 * @param username
	 * @return
	 */

	public UserLogin getDonorDetailsByUsername(String username) {
		return userLoginRepository.findByUserNameIgnoreCase(username);
	}

	/**
	 * @param username
	 */
	public void deleteByUserNameIgnoreCase(String username) {
		userLoginRepository.deleteByUserNameIgnoreCase(username);
	}

}
