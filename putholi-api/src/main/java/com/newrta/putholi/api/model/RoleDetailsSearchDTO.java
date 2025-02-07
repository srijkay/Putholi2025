package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class RoleDetailsSearchDTO {

    /**
     * 
     */
    private Long roleId;

    /**
     * 
     */
    private String roleCode;

    /**
     * 
     */
    private String roleDesc;

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
