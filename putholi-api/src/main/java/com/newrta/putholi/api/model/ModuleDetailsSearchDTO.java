package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class ModuleDetailsSearchDTO {

    /**
     * 
     */
    private String moduleCode;

    /**
     * 
     */
    private String active;

    /**
     * 
     */
    private String moduleDesc;

    /**
     * 
     */
    private int pageSize;

    /**
     * 
     */
    private int pageNumber;

}
