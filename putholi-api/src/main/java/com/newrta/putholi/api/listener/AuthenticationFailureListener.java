package com.newrta.putholi.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.newrta.putholi.api.service.UserLoginAttemptService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Component
@Slf4j
@Setter
@Getter
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    /**
     * 
     */
    @Autowired(required = true)
    UserLoginAttemptService loginAttemptService;

    /**
     *
     */
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) getPrincipalFromAuth(event.getAuthentication());
        log.info("loginAttemptService-=-=-=-{}", username);
        loginAttemptService.updateFailAttempts(username);
    }

    /**
     * @param authentication
     * @return
     */
    private Object getPrincipalFromAuth(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
