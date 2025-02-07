package com.newrta.putholi.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TPUO_ADDRESS")
public class AddressInfo {

    @Id
    @GeneratedValue(generator = "TPUO_ADDRESS_ADDRESS_ID_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "TPUO_ADDRESS_ADDRESS_ID_SEQ", sequenceName = "TPUO_ADDRESS_ADDRESS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "ADDRESS_ID", length = 25)
    private Long addressId;

    @Column(name = "ADDRESS_LINE_1", length = 255, nullable = false)
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2", length = 255, nullable = true)
    private String addressLine2;

    @Column(name = "LOCALITY", length = 255, nullable = false)
    private String locality;

    @Column(name = "CITY", length = 255, nullable = false)
    private String city;

    @Column(name = "DISTRICT", length = 255, nullable = false)
    private String district;

    @Column(name = "STATE", length = 3, nullable = false)
    private String state;

    @Column(name = "COUNTRY", length = 3, nullable = false)
    private String country;

    @Column(name = "PINCODE", length = 6, nullable = false)
    private long pincode;

    @Column(name = "CREATED_BY", length = 25, nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
    private Date createdDate;

    @Column(name = "LAST_MODIFIED_BY", length = 25, nullable = true)
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE", nullable = true, insertable = false, updatable = true)
    private Date lastModifiedDate;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "SCHOOL_INFO_ID", nullable = false, unique = true)
    private SchoolInfo schoolInfo;

}
