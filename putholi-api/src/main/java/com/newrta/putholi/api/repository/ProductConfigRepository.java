package com.newrta.putholi.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.ProductConfig;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface ProductConfigRepository extends JpaRepository<ProductConfig, String> {

   
	/**
	 * @param configCode
	 * @return
	 */
	boolean existsByCompanyCode(String companyCode);

}
