package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FeatureManagementSearchDTO extends GenericSearchDTO {

	private static final long serialVersionUID = -8574849782351291078L;

	private Long featureId;

	private String featureName;

	private String module;

	private String status;

	private String createdBy;

	private String lastModifiedBy;

}
