package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class ContactInfoDTO {

    private Long contactsId;

    private String primaryEmail;

    private String primaryName;

    private String primaryNumber;

    private String primaryDesignation;

    private String secondaryEmail;

    private String secondaryName;

    private String secondaryNumber;

    private String secondaryDesignation;

    private String createdBy;

    private String lastModifiedBy;

    private SchoolInfoDTO schoolInfoDTO; 
}
