package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class AddressInfoDTO {

    private Long addressId;

    private String addressLine1;

    private String addressLine2;

    private String locality;

    private String city;

    private String district;

    private String state;

    private String country;

    private long pincode;

    private String createdBy;

    private String lastModifiedBy;

    private SchoolInfoDTO schoolInfoDTO;

}
