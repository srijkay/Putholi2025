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
public class FeatureManagementDTO {

	private Long featureId;

	private String featureName;

	private String module;

	private String status;

	private String filterType;

	private String userName;

	private String role;

	private String organisation;

	private String isApprovalApplicable;

	private String isAutoApproval;

	private String levelofApproval;

	private String createdBy;

	private String lastModifiedBy;

	private List<ApproverLevelsDTO> approverLevelsDTO;

}
