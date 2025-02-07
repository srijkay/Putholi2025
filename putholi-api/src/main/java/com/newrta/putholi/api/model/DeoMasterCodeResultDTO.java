package com.newrta.putholi.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class DeoMasterCodeResultDTO {


	private String deoName;
	/**
	 * 
	 */

	private String district;

	/**
	 * 
	 */

	private String deoEmail;

	/**
	 * 
	 */

	private String city;

	/**
	 * 
	 */

	private String phoneNumber;

	/**
	 * 
	 */

	private String deoOfficeAddress;

	/**
	 * 
	 * @param code
	 * @param codeType
	 * @param deoName
	 * @param deoEmail
	 * @param district
	 * @param city
	 * @param phoneNumber
	 * @param deoOfficeAddress
	 */
	public DeoMasterCodeResultDTO(String deoName, String district,  String deoEmail,
			String city, String phoneNumber, String deoOfficeAddress) {
		super();
		this.deoName = deoName;
		this.district = district;
		this.deoEmail = deoEmail;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.deoOfficeAddress = deoOfficeAddress;

	}

}
