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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TPUO_APPLICATION_CONFIG")
public class ApplicationConfig {

	@Id
	@GeneratedValue(generator = "TPUO_APPLICATION_CONFIG_CONFIG_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "TPUO_APPLICATION_CONFIG_CONFIG_ID_SEQ", sequenceName = "TPUO_APPLICATION_CONFIG_CONFIG_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "CONFIG_ID", length = 25)
	private Long configId;

	@Column(name = "CONFIG_FOR", nullable = false, length = 255)
	private String configFor;

	@Column(name = "CONFIG_VALUE", nullable = false, length = 255)
	private String configValue;

	@Column(name = "MODULE", nullable = false, length = 3)
	private String module;

	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;

	@Column(name = "CREATED_BY", length = 25, nullable = false)
	private String createdBy;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
	private Date createdDate;

	@Column(name = "LAST_MODIFIED_BY", length = 25, nullable = true)
	private String lastModifiedBy;

	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE", nullable = true, insertable = false, updatable = true)
	private Date lastModifiedDate;

	public ApplicationConfig(Long configId, String configFor, String configValue, String module) {
		super();
		this.configId = configId;
		this.configFor = configFor;
		this.configValue = configValue;
		this.module = module;
	}

	


}
