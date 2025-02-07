package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailsDTO {

	private Long invoiceId;

	private String companyName;

	private String address;

	private String street;

	private String city;

	private long phoneNumber;

	private long pincode;

	private String comments;

	private String accountType;

	private String invoicePreparedBy;

	private String workStatus;

	private String discountDetails;

	private String itemDescription;

	private Date invoiceDate;

	private long quantity;

	private BigDecimal quotedUnitPrice;

	private BigDecimal quotedTax;

	private BigDecimal quotedShippingCost;

	private BigDecimal quotedAmount;

	private BigDecimal invoiceUnitPrice;

	private BigDecimal invoiceTax;

	private BigDecimal invoiceShippingCost;

	private BigDecimal invoiceAmount;

	private String bankName;

	private String vendorCode;

	private String ifscCode;

	private String paymentMode;

	private String accountNumber;

	private String invoiceStatus;

	private String utrNumber;

	private Date utrDate;

	private String rejectedReason;

	private String createdBy;

	private Date createdDate;

	private String lastModifiedBy;

	private RequirementDTO requirementDTO;

	private String volunteerName;
}
