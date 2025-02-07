package com.newrta.putholi.api.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePaymentDetailsDTO {

	private Long invoicePaymentId;
	
	private String paymentMethod;
	
	private String paymentFrom;
	
	private String paymentTo;
	
	private String bankName;
	
	private String referenceNumber;
	
	private BigDecimal paidAmount;
	
	private Date paymentDate;
	
	private String amountCollectedBy;
	
	private String accountHolderName;
	
	private String chequeNumber;
	
	private String payee;
	
	private Long invoiceId;
	
}
