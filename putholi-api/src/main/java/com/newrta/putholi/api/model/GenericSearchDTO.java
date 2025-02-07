package com.newrta.putholi.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Getter
@Setter
public class GenericSearchDTO implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1434417907985208700L;
    
    /**
     * 
     */
    private String loggedUser;

    /**
     * 
     */
    private Date startDate;
    
    /**
     * 
     */
    private Date endDate;
    
    /**
     * 
     */
    private int pageNumber;
    
    /**
     * 
     */
    private int pageSize;
    
    /**
     * 
     */
    private String role;
    
    /**
     * 
     */
    private String genericSearchData;
    
    /**
     * 
     */
    private String period;
    
    /**
     * 
     */
    private String status;

}
