package com.newrta.putholi.api.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor

public class ApproverLevelsDTO {

	private Long approverId;

	private String level;

	private String type;

	private List<String> approverRoles;

	private FeatureManagementDTO featureManagementDTO;

}
