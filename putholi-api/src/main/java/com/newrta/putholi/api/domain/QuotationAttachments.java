package com.newrta.putholi.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Entity
@Table(name = "TPUO_ATTACHMENTS")
@NoArgsConstructor
public class QuotationAttachments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2921104232249375000L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TPUO_ATTACHMENTS_ID_SEQ")
	@SequenceGenerator(name = "TPUO_ATTACHMENTS_ID_SEQ", sequenceName = "TPUO_ATTACHMENTS_ID_SEQ", allocationSize = 1)
	@Column(name = "ATTACHMENT_ID", length = 25)
	private Long attachmentId;

	/**
	 * 
	 */
	@Column(name = "REQUIREMENT_ID")
	private Long requirementId;

	/**
	 * 
	 */
	@Column(name = "QUOTATION_ID")
	private long quotationId;

	/**
	 * 
	 */
	@Column(name = "INVOICE_ID")
	private long invoiceId;

	/**
	 * 
	 */
	@Column(name = "EXPENSES_ID")
	private Long expensesId;

	/**
	 * 
	 */
	@Column(name = "SCHOOL_INFO_ID")
	private long schoolInfoId;

	/**
	* 
	*/
	@Column(name = "UPLOAD_FOR", length = 2)
	private String uploadFor;

	/**
	 * 
	 */
	@Column(name = "FILE_NAME", length = 100, nullable = false)
	private String fileName;

	/**
	 * 
	 */
	@Column(name = "FILE_TYPE", length = 15, nullable = false)
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
	@Column(name = "FILE_SIZE")
	private long fileSize;

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
	 * 
	 * @param attachmentId
	 * @param requirementId
	 * @param quotationId
	 * @param invoiceId
	 * @param schoolInfoId
	 * @param consolidateId
	 * @param uploadFor
	 * @param fileName
	 * @param fileType
	 * @param fileData
	 * @param fileSize
	 */

	public QuotationAttachments(Long attachmentId, Long requirementId, long quotationId, long invoiceId,
			long schoolInfoId, Long expensesId, String uploadFor, String fileName, String fileType, byte[] fileData,
			long fileSize) {
		super();
		this.attachmentId = attachmentId;
		this.requirementId = requirementId;
		this.quotationId = quotationId;
		this.invoiceId = invoiceId;
		this.schoolInfoId = schoolInfoId;
		this.expensesId = expensesId;
		this.uploadFor = uploadFor;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileData = fileData;
		this.fileSize = fileSize;
	}

}
