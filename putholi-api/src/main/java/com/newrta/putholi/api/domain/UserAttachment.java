package com.newrta.putholi.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Table(name = "user_attachment", uniqueConstraints = @UniqueConstraint(columnNames = { "USERNAME", "UPLOAD_FOR" }))
@Data
@NoArgsConstructor
public class UserAttachment {

	 /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_attachment_id_seq")
    @SequenceGenerator(name = "user_attachment_id_seq", sequenceName = "user_attachment_id_seq", allocationSize = 1)
    @Column(name = "id", length = 25)
    private Long id;
    
    /**
     * 
     */
    @Column(name = "USERNAME", length = 25)
    private String username;
    
    /**
     * 
     */
    @Column(name = "UPLOAD_FOR", length = 2)
    private String uploadFor;

    /**
     * 
     */
    @Column(name = "FILE_TYPE", length = 10, nullable = false)
    private String fileType;

    /**
     * 
     */
    @Lob
    @Column(name = "FILE_DATA", nullable = false)
    private byte[] fileData;

    /**
     * 
     */
    @CreationTimestamp
    @Column(name = "CREATED_DATE", insertable = true, updatable = false)
    private Date createdDate;

    /**
     * 
     */
    @UpdateTimestamp
    @Column(name = "UPDATED_DATE", insertable = false, updatable = true)
    private Date updatedDate;

    /**
     * @param username
     * @param uploadFor
     * @param fileType
     * @param fileData
     */
    public UserAttachment(String username, String uploadFor, String fileType, byte[] fileData) {
	super();
	this.username = username;
	this.uploadFor = uploadFor;
	this.fileType = fileType;
	this.fileData = fileData;
    }

	/**
	 * @param id
	 * @param username
	 * @param uploadFor
	 * @param fileType
	 * @param fileData
	 */
	public UserAttachment(Long id, String username, String uploadFor, String fileType, byte[] fileData) {
		super();
		this.id = id;
		this.username = username;
		this.uploadFor = uploadFor;
		this.fileType = fileType;
		this.fileData = fileData;
	}


}
