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
@Table(name = "TPUO_CONTACTS")
public class ContactInfo {

    @Id
    @GeneratedValue(generator = "TPUO_CONTACTS_CONTACTS_ID_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "TPUO_CONTACTS_CONTACTS_ID_SEQ", sequenceName = "TPUO_CONTACTS_CONTACTS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "CONTACTS_ID", length = 25)
    private Long contactsId;

    @Column(name = "PRI_EMAIL", length = 255, nullable = false)
    private String primaryEmail;

    @Column(name = "PRI_NAME", length = 255, nullable = false)
    private String primaryName;

    @Column(name = "PRI_NUM", length = 13, nullable = false)
    private String primaryNumber;

    @Column(name = "PRI_DESIGNATION", length = 255, nullable = false)
    private String primaryDesignation;

    @Column(name = "SEC_EMAIL", length = 255, nullable = false)
    private String secondaryEmail;

    @Column(name = "SEC_NAME", length = 255, nullable = false)
    private String secondaryName;

    @Column(name = "SEC_NUM", length = 13, nullable = false)
    private String secondaryNumber;

    @Column(name = "SEC_DESIGNATION", length = 255, nullable = false)
    private String secondaryDesignation;

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
