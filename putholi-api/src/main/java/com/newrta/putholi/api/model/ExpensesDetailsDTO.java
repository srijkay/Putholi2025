package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpensesDetailsDTO {

	private Long expensesId;

	private String vendorName;

	private String utrNumber;

	private Date utrDate;

	private String bankName;

	private String accountNum;

	private String ifscCode;

	private Date transactionDate;

	private BigDecimal amount;

	private String address;

	private long mobileNumber;

	private String category;

	private String description;

	private String status;

	private String other;

	private String rejectedReason;

	private String createdBy;

	private String lastModifiedBy;

}
