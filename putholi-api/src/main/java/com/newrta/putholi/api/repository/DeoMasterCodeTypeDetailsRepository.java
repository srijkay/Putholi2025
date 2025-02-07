package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.newrta.putholi.api.customsrepo.DeoMasterCodeTypeDetailsCustomsRepo;
import com.newrta.putholi.api.domain.DeoMasterCodeTypeDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface DeoMasterCodeTypeDetailsRepository
		extends JpaRepository<DeoMasterCodeTypeDetails, Long>, DeoMasterCodeTypeDetailsCustomsRepo {

	/**
	 * 
	 * @param district
	 * @return
	 */

	boolean existsByDistrict(String district);

}
