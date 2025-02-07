package com.newrta.putholi.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@Builder
@AllArgsConstructor
public class UserAuthenticationDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4083256839777087972L;

    /**
     * 
     */
    private String status;
    /**
     * 
     */
    private String statusDescription;
    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String firstName;
    
    /**
     * 
     */
    private String mobileNumber;
    
    /**
     * 
     */
    private String lastName;
    /**
     * 
     */
    private String emailId;
    /**
     * 
     */
    private String title;
    /**
     * 
     */
    private String accessToken;
    /**
     * 
     */
    private String profilePic;
    /**
     * 
     */
    private boolean changePasswordRequired;
    /**
     * 
     */
    private String roles;
    
    /**
     * 
     */
    private String userStatus;

}
