package com.newrta.putholi.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2921104232249375000L;

	/**
	 *  
	 */
	private Long requirementId;

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
	private byte[] fileData;

	/**
	 * 
	 */
	private long fileSize;

	/**
	 * 
	 */
	private String fileType;

	/**
	 * 
	 */
	private String schoolName;

	/**
	 * 
	 */
	private String districtDesc;

	/**
	 * 
	 */
	private String city;
}
