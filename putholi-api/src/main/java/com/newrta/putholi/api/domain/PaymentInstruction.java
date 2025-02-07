package com.newrta.putholi.api.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "TPUO_PAYMENT_INSTRUCTION")
public class PaymentInstruction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TPUO_PAYMENT_INSTRUCTION_PAYMENT_NUM_SEQ")
	@GenericGenerator(name = "TPUO_PAYMENT_INSTRUCTION_PAYMENT_NUM_SEQ", strategy = "com.newrta.putholi.api.util.DatePrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = "valuePrefix", value = "P"), })
	@Column(name = "PAYMENT_ID", length = 25, nullable = false)
	private String paymentId;

	@Column(name = "AMOUNT_PAID", scale = 2, precision = 10, nullable = false)
	private BigDecimal amountPaid;

	@Column(name = "PAYMENT_CURRENCY", length = 10, nullable = true)
	private String paymentCurrency;

	@Column(name = "PAYMENT_METHOD", length = 25, nullable = true)
	private String paymentMethod;

	@Column(name = "INSTRUCTION_TYPE", length = 10, nullable = false)
	private String instrType;

	@Column(name = "STATUS", length = 50, nullable = false)
	private String status;

	@Column(name = "USER_ID", length = 50, nullable = false)
	private String userId;

	@Column(name = "CONSOLIDATE_ID", length = 25, nullable = true)
	private long consolidateId;

	@CreationTimestamp
	@Column(name = "PAYMENT_DATE", nullable = false, insertable = true, updatable = false)
	private Date paymentDate;

	@Column(name = "order_id", nullable = false, length = 25)
	private String orderId;

	@Column(name = "web_url", length = 255, nullable = true)
	private String webUrl;

}
