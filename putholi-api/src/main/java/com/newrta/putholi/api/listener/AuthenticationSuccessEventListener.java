package com.newrta.putholi.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.newrta.putholi.api.model.UserDetailsDTO;
import com.newrta.putholi.api.service.UserLoginAttemptService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Component
@Data
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    /**
     * 
     */
    @Autowired(required = true)
    UserLoginAttemptService loginAttemptService;

    /**
     *
     */
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        log.info("-=-=-event.getAuthentication().getPrincipal()-=-=- {}",
                getPrincipalFromAuth(event.getAuthentication()));
        UserDetailsDTO username = (UserDetailsDTO) getPrincipalFromAuth(event.getAuthentication());
        log.info("loginAttemptService-=-=-=- {}", getUsernameFromUserDetails(username));
        loginAttemptService.resetFailAttempts(getUsernameFromUserDetails(username));
    }

    /**
     * @param authentication
     * @return
     */
    private Object getPrincipalFromAuth(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * @param userDetails
     * @return
     */
    private String getUsernameFromUserDetails(UserDetailsDTO userDetails) {
        return userDetails.getUsername();
    }

}
