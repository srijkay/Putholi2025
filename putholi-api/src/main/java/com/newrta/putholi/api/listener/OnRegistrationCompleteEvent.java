package com.newrta.putholi.api.listener;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.newrta.putholi.api.domain.UserLogin;

import lombok.Getter;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Setter
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -6015156424062015091L;

    /**
     * 
     */
    private String appUrl;
    /**
     * 
     */
    private Locale locale;
    /**
     * 
     */
    private UserLogin user;

    /**
     * @param appUrl
     * @param locale
     * @param user
     */
    public OnRegistrationCompleteEvent(UserLogin user, Locale locale, String appUrl) {
	super(user);
	this.appUrl = appUrl;
	this.locale = locale;
	this.user = user;
    }

}
