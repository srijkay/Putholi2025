package com.newrta.putholi.api.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class MenuDetailsViewResultsDTO {

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
    private String menuNameOther;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Date createdDate;

    /**
    * 
    */
    private String createdBy;

    /**
     * 
     */
    private String moduleCode;

  
}
