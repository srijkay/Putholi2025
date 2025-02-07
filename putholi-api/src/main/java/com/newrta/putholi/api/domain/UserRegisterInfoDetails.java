package com.newrta.putholi.api.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "vpuo_user_register")
public class UserRegisterInfoDetails {

	@Id
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "FEE_TYPE")
	private String feeType;

}
