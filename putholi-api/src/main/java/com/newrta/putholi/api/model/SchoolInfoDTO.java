package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class SchoolInfoDTO {

	private Long schoolInfoId;

	private String schoolName;

	private String schoolType;

	private String schoolRegNo;

	private int noOfStudents;

	private int noOfTeachers;

	private String educationalDistrict;

	private String schoolStatus;

	private String volunteerName;

	private String comments;

	private String schoolDescription;

	private String schoolUrl;

	private String active;

	private String createdBy;

	private String lastModifiedBy;

	private AddressInfoDTO addressInfoDTO;

	private ContactInfoDTO contactsInfoDTO;

}
