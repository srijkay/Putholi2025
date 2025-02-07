package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.UserManagementCustomsRepo;
import com.newrta.putholi.api.domain.UserManagement;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface UserManagementRepository extends JpaRepository<UserManagement, String>, UserManagementCustomsRepo {

	/**
	 * @param statusCode
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM UserManagement u WHERE u.statusCode IN :statusCode and u.roleCode IN :roleCode")
	int countofstatusCode(@Param("statusCode") List<String> statusCode, @Param("roleCode") List<String> roleCode);
}
