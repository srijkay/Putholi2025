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
public class ApprovalHistoryDetailsDTO {

	private Long approvalHistId;

	private String username;

	private String actionBy;

	private Date actionDate;

	private String role;

	private String status;

	private String remarks;

	private String type;

	private String changeRequestRole;
}
