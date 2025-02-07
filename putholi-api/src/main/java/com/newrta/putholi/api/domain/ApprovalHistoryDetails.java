package com.newrta.putholi.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Data
@Table(name = "APPROVAL_HISTORY_DETAILS")
public class ApprovalHistoryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "approval_history_details_approval_his_id_seq")
	@SequenceGenerator(name = "approval_history_details_approval_his_id_seq", sequenceName = "approval_history_details_approval_his_id_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "APPROVAL_HIS_ID", length = 25)
	private Long approvalHistId;

	@Column(name = "USERNAME", length = 25)
	private String username;

	@Column(name = "ACTION_BY", length = 25, nullable = false)
	private String actionBy;

	@CreationTimestamp
	@Column(name = "ACTION_DATE", insertable = true, updatable = false)
	private Date actionDate;

	@Column(name = "ACTION_ROLE", length = 10, nullable = false)
	private String role;

	@Column(name = "STATUS", length = 6, nullable = false)
	private String status;

	@Column(name = "REMARKS", length = 512, nullable = true)
	private String remarks;

	@Column(name = "TYPE", length = 25, nullable = true)
	private String type;
	
	@Column(name = "CHANGE_REQUEST_ROLE", length = 25, nullable = true)
	private String changeRequestRole;

}
