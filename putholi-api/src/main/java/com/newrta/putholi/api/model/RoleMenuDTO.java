package com.newrta.putholi.api.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
public class RoleMenuDTO {

    /**
     * 
     */
    private Long roleId;

    /**
     * 
     */
    private List<Long> menuId;

    /**
     * 
     */
    private String createdBy;

    /**
     * 
     */
    @CreationTimestamp
    private Date createdDate;

}
