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
public class DonorInfoViewDetailsDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -643861734922706434L;

	private String emailId;

	private String city;

	private String country;

	private BigDecimal amount;

	private Date createdDate;

	private Date fromDate;

	private Date todate;

}
