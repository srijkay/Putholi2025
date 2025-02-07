package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.ApplicationConfigCustomsRepo;
import com.newrta.putholi.api.domain.ApplicationConfig;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface ApplicationConfigRepository
		extends JpaRepository<ApplicationConfig, Long>, ApplicationConfigCustomsRepo {

	/**
	 * @param configId
	 * @return
	 */
	ApplicationConfig findByConfigFor(String configFor);

}
