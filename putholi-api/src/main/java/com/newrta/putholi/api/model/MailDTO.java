package com.newrta.putholi.api.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Builder
@AllArgsConstructor
public class MailDTO {

	/**
	 * 
	 */
	private String mailFrom;

	/**
	 * 
	 */
	private String mailTo;

	/**
	 * 
	 */
	private String[] mailCc;

	/**
	 * 
	 */
	private String mailSubject;

	/**
	 * 
	 */
	private String mailContent;

	/**
	 * 
	 */
	private String contentType;

	/**
	 * 
	 */
	private String docType;

	/**
	 * 
	 */
	private Map<String, Object> model;

	/**
	 * 
	 */
	private Map<String, List<Object>> attachments;

	/**
	 * 
	 */
	private String attachmentFilePath;
	
	/**
	 * 
	 */
	private String fileName;

	/**
	 * 
	 */
	public MailDTO() {
		contentType = "text/html";
	}

	/**
	 * @param mailFrom
	 * @param mailTo
	 * @param mailSubject
	 * @param docType
	 * @param model
	 */
	public MailDTO(String mailFrom, String mailTo, String mailSubject, String docType, Map<String, Object> model) {
		super();
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.mailSubject = mailSubject;
		this.docType = docType;
		this.model = model;
	}
}
