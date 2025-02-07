package com.newrta.putholi.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class MasterCodeSearchDTO extends GenericSearchDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 6886194669499477728L;

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
    private String desc;

    /**
     * 
     */
    private String active;

}
