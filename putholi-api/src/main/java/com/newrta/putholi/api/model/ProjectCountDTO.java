package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vijaya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCountDTO {

	private Long requirementsCompleted;

	private Long schoolsCount;
	
	private Long progressSchoolCount;

	private BigDecimal contributeAmount;

	private List<ProjectDetailsDTO> projectDetailsDTO;
}
