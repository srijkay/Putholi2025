package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesViewDetailsSearchDTO extends GenericSearchDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624655602097792350L;

	private Long expensesId;

	private String vendorName;

	private String utrNumber;

	private String bankName;

	private String accountNum;

	private String ifscCode;

	private Date transactionDate;

	private BigDecimal amount;

	private String status;

}
