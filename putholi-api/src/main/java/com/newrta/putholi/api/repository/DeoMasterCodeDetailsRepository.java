package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.newrta.putholi.api.customsrepo.DeoMasterCodeDetailsCustomsRepo;
import com.newrta.putholi.api.domain.DeoMasterCodeDetails;
import com.newrta.putholi.api.model.DeoMasterCodeResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface DeoMasterCodeDetailsRepository
		extends JpaRepository<DeoMasterCodeDetails, Long>, DeoMasterCodeDetailsCustomsRepo {
	

	/**
	 * @param active
	 * @return
	 */
	@Query(value = "SELECT new com.newrta.putholi.api.model.DeoMasterCodeResultDTO(d.deoName, d.district, d.deoEmail,  d.city, d.phoneNumber, d.deoOfficeAddress)"
			+ "FROM DeoMasterCodeDetails d WHERE d.active =:active ORDER BY d.district")
	List<DeoMasterCodeResultDTO> findByActive(@Param("active") String active);

	/**
	 * 
	 * @param city
	 * @param district
	 * @return
	 */
	boolean existsByCityAndDistrict(String city, String district);

}
