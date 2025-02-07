package com.newrta.putholi.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class MasterCodeResultDTO {

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String codeType;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String descriptionOther;

    /**
     * @param code
     * @param description
     * @param descriptionOther
     */
    public MasterCodeResultDTO(String code, String description, String descriptionOther) {
	this.code = code;
	this.description = description;
	this.descriptionOther = descriptionOther;
    }

    /**
     * @param code
     * @param codeType
     * @param description
     * @param descriptionOther
     */
    public MasterCodeResultDTO(String code, String codeType, String description, String descriptionOther) {
	super();
	this.code = code;
	this.codeType = codeType;
	this.description = description;
	this.descriptionOther = descriptionOther;
    }

}
