package com.newrta.putholi.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "TPUO_COMPLETION_OF_REQUIREMENTS")
public class CompletionofRequirements {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "TPUO_COMPLETION_OF_REQUIREMENTS_COMPLETION_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_COMPLETION_OF_REQUIREMENTS_COMPLETION_ID_SEQ", sequenceName = "TPUO_COMPLETION_OF_REQUIREMENTS_COMPLETION_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "COMPLETION_ID", length = 25)
	private Long completionId;

	/**
	 * 
	 */
	@Column(name = "DATE_OF_COMPLETION", nullable = false)
	private Date dateOfCompletion;

	/**
	 * 
	 */
	@Column(name = "COMPLETION_OF_PAYMENT", length = 6, nullable = false)
	private String completionOfPayment;

	/**
	 * 
	 */

	@Column(name = "REMARKS", length = 255, nullable = false)
	private String remarks;
	
	
	@Column(name = "REQUIREMENT_ID", length = 25, nullable = false)
	private Long requirementId;

	/**
	 * 
	 */
	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	/**
	 * 
	 */
	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
	private Date createdDate;

}
