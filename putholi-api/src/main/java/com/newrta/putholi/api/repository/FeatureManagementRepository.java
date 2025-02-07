package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newrta.putholi.api.customsrepo.FeatureManagementCustomsRepo;
import com.newrta.putholi.api.domain.FeatureManagement;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface FeatureManagementRepository extends JpaRepository<FeatureManagement, Long>, FeatureManagementCustomsRepo{
	
	/**
	 * @param featureId
	 * @return
	 */
	FeatureManagement findByFeatureId(Long featureId);

}
