package com.newrta.putholi.api.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class SchoolApprovalHistoryDetailsDTO {

	private Long approvalHistId;

	private Long schoolInfoId;

	private Long consolidateId;

	private String actionBy;

	private Date actionDate;

	private String role;

	private String status;

	private String remarks;

	private Long quotationId;

	private Long expensesId;

	private Long requirementId;

	private Long invoiceId;

	private String type;

	private int quotationAmount;
}
