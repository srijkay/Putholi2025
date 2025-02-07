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
@Table(name = "TPUO_SCHOOL_APPROVAL_HISTORY_DETAILS")
public class SchoolApprovalHistoryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TPUO_SCHOOL_APPROVAL_HISTORY_DETAILS_APPROVAL_HIS_ID_SEQ")
	@SequenceGenerator(name = "TPUO_SCHOOL_APPROVAL_HISTORY_DETAILS_APPROVAL_HIS_ID_SEQ", sequenceName = "TPUO_SCHOOL_APPROVAL_HISTORY_DETAILS_APPROVAL_HIS_ID_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "APPROVAL_HIS_ID", length = 25)
	private Long approvalHistId;

	@Column(name = "SCHOOL_ID", length = 25)
	private Long schoolInfoId;

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

	@Column(name = "QUOTATION_ID", length = 25)
	private Long quotationId;

	@Column(name = "REQUIREMENT_ID", length = 25)
	private Long requirementId;

	@Column(name = "CONSOLIDATE_ID", length = 25)
	private Long consolidateId;

	@Column(name = "INVOICE_ID", length = 25)
	private Long invoiceId;

	@Column(name = "EXPENSES_ID", length = 25)
	private Long expensesId;

	@Column(name = "type", length = 25)
	private String type;
}
