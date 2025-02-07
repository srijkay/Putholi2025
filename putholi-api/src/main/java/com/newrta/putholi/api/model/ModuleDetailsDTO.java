package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
public class ModuleDetailsDTO {

    /**
     * 
     */
    private Long moduleId;

    /**
     * 
     */
    private String moduleCode;

    /**
     * 
     */
    private String moduleDesc;

    /**
     * 
     */
    private String moduleDescOther;
    /**
     * 
     */
    private String active;

    /**
     * 
     */
    private String createdBy;

    /**
     * 
     */
    private String updatedBy;

}
