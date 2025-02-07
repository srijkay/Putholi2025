package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
public class QuotationInfoDTO {

	private Long quotationId;

	private String companyName;

	private String address;

	private String street;

	private String city;

	private long phoneNumber;

	private long pincode;

	private String quotationPreparedBy;

	private String comments;

	private String warranty;

	private Date quotationDate;

	private Date quotationValidDate;

	private String discountDetails;

	private String itemDescription;

	private long quantity;

	private BigDecimal unitPrice;

	private BigDecimal tax;

	private BigDecimal shippingCost;

	private BigDecimal totalAmount;

	private String createdBy;

	private String lastModifiedBy;
	
	private String quotateStatus;
	
	private RequirementDTO requirementDTO;

}
