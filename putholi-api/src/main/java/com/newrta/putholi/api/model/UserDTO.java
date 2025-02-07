package com.newrta.putholi.api.model;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
public class UserDTO {
    
    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String secretKey;
    /**
     * 
     */
    private String newsecretkey;
    /**
     * 
     */
    private String userip;
    /**
     * 
     */
    private String useragent;

}
