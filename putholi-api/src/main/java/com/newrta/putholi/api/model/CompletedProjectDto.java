package com.newrta.putholi.api.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vijaya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletedProjectDto {

	private String schoolName;
	private String locality;
	private String districtDesc;
	private String city;
	private Long consolidateId;
	private String paymentId;
	
	private Long projectId;
	private BigDecimal totalAmount;
	private String assetName;

	public CompletedProjectDto(String schoolName, String locality, String districtDesc, String city, Long consolidateId,
			String paymentId, Long projectId, BigDecimal totalAmount) {
		this.schoolName = schoolName;
		this.locality = locality;
		this.districtDesc = districtDesc;
		this.city = city;
		this.consolidateId = consolidateId;
		this.paymentId = paymentId;
		this.projectId = projectId;
		this.totalAmount = totalAmount;
	}
}