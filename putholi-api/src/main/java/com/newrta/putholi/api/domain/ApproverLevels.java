package com.newrta.putholi.api.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "TPUO_APPROVERLEVELS")
public class ApproverLevels {

	@Id
	@GeneratedValue(generator = "TPUO_APPROVERLEVELS_APPROVER_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_APPROVERLEVELS_APPROVER_ID_SEQ", sequenceName = "TPUO_APPROVERLEVELS_APPROVER_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "APPROVER_ID", length = 25)
	private Long approverId;

	@Column(name = "LEVEL", length = 25, nullable = false)
	private String level;

	@Column(name = "TYPE", length = 3, nullable = false)
	private String type;

	@Column(name = "APPROVER_ROLES", length = 25, nullable = false)
	private String approverRoles;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "FEATURE_ID", nullable = false)
	private FeatureManagement featureManagement;

}
