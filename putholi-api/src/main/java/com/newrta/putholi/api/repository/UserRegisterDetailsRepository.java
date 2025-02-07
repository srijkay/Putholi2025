package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.domain.UserRegisterDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Transactional
public interface UserRegisterDetailsRepository extends JpaRepository<UserRegisterDetails, String> {

	/**
	 * @param username
	 * @return
	 */
	UserRegisterDetails findByUserNameIgnoreCase(String username);

	/**
	 * @param district
	 * @return
	 */
	@Query(value = "FROM UserRegisterDetails  r WHERE r.role =:role and r.active=:active and r.district=:district ")
	List<UserRegisterDetails> findByDistrictIgnoreCase(@Param("role") String role, @Param("active") String active,
			@Param("district") String district);

	/**
	 * @param username
	 * @return
	 */
	boolean existsByUserNameIgnoreCaseAndActive(String username, String active);

	/**
	 * @param username
	 * @param emailId
	 * @return
	 */
	boolean existsByUserNameIgnoreCaseAndEmailId(String username, String emailId);

	/**
	 * @param active
	 * @param username
	 * @return
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE {h-schema}tsys_applicant_user SET active =:active  WHERE username =:username", nativeQuery = true)
	int updateAccountStatus(@Param("active") String active, @Param("username") String username);

	/**
	 * @param emailId
	 * @return
	 */
	boolean existsByEmailIdIgnoreCaseAndActive(String emailId, String active);

	/**
	 * @param emailid
	 * @return
	 */
	UserRegisterDetails findByEmailIdIgnoreCase(String emailid);

	/**
	 * @param requirementId
	 */
	@Modifying(clearAutomatically = true)
	void deleteByUserNameIgnoreCase(String username);

	/**
	 * @param username
	 * @param status
	 * @param active
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE UserRegisterDetails u SET u.status =:status, u.active =:active WHERE u.userName =:username")
	void updateApprovalDetails(@Param("username") String username, @Param("status") String status,
			@Param("active") String active);

	/**
	 * @param username
	 * @param status
	 * @param active
	 * @param role
	 * @param changeRequestRole
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE UserRegisterDetails u SET u.status =:status, u.active =:active, u.role=:role, u.changeRequestRole=:changeRequestRole WHERE u.userName =:username")
	void updateApprovalDetailsAndRole(@Param("username") String username, @Param("status") String status,
			@Param("active") String active, @Param("role") String role,
			@Param("changeRequestRole") String changeRequestRole);

	/**
	 * @param username
	 * @param status
	 * @param active
	 * @param changeRequestRole
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE UserRegisterDetails u SET u.status =:status, u.active =:active, u.changeRequestRole=:changeRequestRole WHERE u.userName =:username")
	void updateApprovalDetailsAndChangeRole(@Param("username") String username, @Param("status") String status,
			@Param("active") String active, @Param("changeRequestRole") String changeRequestRole);

	/**
	 *
	 * @param status
	 * @return
	 */
	@Query(value = "SELECT COUNT(*) FROM UserRegisterDetails r WHERE r.status = :status ")
	int checkPendingReqStatus(@Param("status") String status);

	/**
	 * @param orderId
	 * @return
	 */
	UserRegisterDetails findByOrderId(String orderId);

	/**
	 * @param userName
	 * @param orderId
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE UserRegisterDetails u SET u.orderId =:orderId WHERE u.userName =:userName")
	void updateOrderId(@Param("userName") String userName, @Param("orderId") String orderId);

	/**
	 * @param role
	 * @return
	 */
	List<UserRegisterDetails> findByRoleAndActive(String role, String active);
}
