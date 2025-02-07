package com.newrta.putholi.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "TPUO_INVOICE_PAYMENT_DETAILS")
public class InvoicePaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TPUO_INVOICE_PAYMENT_ID_SEQ")
	@SequenceGenerator(name = "TPUO_INVOICE_PAYMENT_ID_SEQ", sequenceName = "TPUO_INVOICE_PAYMENT_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "INVOICE_PAYMENT_ID", length = 25, nullable = false)
	private Long invoicePaymentId;

	@Column(name = "PAYMENT_METHOD", length = 25, nullable = false)
	private String paymentMethod;

	@Column(name = "PAYMENT_FROM", length = 50, nullable = true)
	private String paymentFrom;

	@Column(name = "PAYMENT_TO", length = 50, nullable = true)
	private String paymentTo;

	@Column(name = "BANK_NAME", length = 50, nullable = false)
	private String bankName;

	@Column(name = "REFERENCE_NUMBER", length = 255, nullable = true)
	private String referenceNumber;

	@Column(name = "PAID_AMOUNT", scale = 2, precision = 10, nullable = false)
	private BigDecimal paidAmount;

	@Column(name = "PAYMENT_DATE", nullable = false)
	private Date paymentDate;

	@Column(name = "AMMOUNT_COLLECTED_BY", length = 255, nullable = true)
	private String amountCollectedBy;

	@Column(name = "ACCOUNT_HOLDER_NAME", length = 255, nullable = true)
	private String accountHolderName;

	@Column(name = "CHEQUE_NUMBER", length = 50, nullable = true)
	private String chequeNumber;

	@Column(name = "PAYEE", length = 50, nullable = true)
	private String payee;

	@Column(name = "INVOICE_ID", length = 25, nullable = false)
	private Long invoiceId;
}
