package com.newrta.putholi.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Setter
@Getter
public class QuotationAttachmentsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2921104232249375000L;

	/**
	 * 
	 */
	private Long attachmentId;

	/**
	 * 
	 */
	private Long requirementId;

	/**
	 * 
	 */
	private long quotationId;

	/**
	 * 
	 */
	private long invoiceId;

	/**
	 * 
	 */
	private long schoolInfoId;
	
	/**
	 * 
	 */
	private Long expensesId;

	/**
	 * 
	 */
	private String uploadFor;

	/**
	 * 
	 */
	private String fileName;

	/**
	 * 
	 */
	private String fileType;

	/**
	 * 
	 */
	private long fileSize;

}
