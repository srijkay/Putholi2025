package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserRegisterViewDetailsDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1009738624443522811L;

	private String userName;

	private String city;

	private String country;

	private BigDecimal amount;

	private Date createdDate;

	private String feeType;

	private Date fromDate;

	private Date todate;
}
