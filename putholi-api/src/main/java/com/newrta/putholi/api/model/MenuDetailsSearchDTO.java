package com.newrta.putholi.api.model;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
public class MenuDetailsSearchDTO {

    /**
     * 
     */

    private Long menuId;

    /**
     * 
     */
    private String menuCode;

    /**
     * 
     */
    private String menuName;

    /**
     * 
     */

    private String moduleCode;
    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private int pageSize;

    /**
     * 
     */
    private int pageNumber;

}
