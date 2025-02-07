package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrta.putholi.api.customsrepo.SchoolInfoCustomsRepo;
import com.newrta.putholi.api.domain.SchoolInfo;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
/**
 * @author HP
 *
 */
@Repository
@Transactional
public interface SchoolInfoRepository extends JpaRepository<SchoolInfo, Long>, SchoolInfoCustomsRepo {

	/**
	 * @param schoolInfoId
	 * @return
	 */
	SchoolInfo findBySchoolInfoId(Long schoolInfoId);

	/**
	 * 
	 * @param createdBy
	 * @return
	 */

	SchoolInfo findBySchoolName(String schoolName);

	/**
	 * @param schoolRegNo
	 * @return
	 */
	Boolean existsBySchoolRegNoIgnoreCase(String schoolRegNo);

	/**
	 * @param schoolInfoId
	 */
	void deleteBySchoolInfoId(Long schoolInfoId);

	/**
	 * @param schoolInfoId
	 * @param status
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE SchoolInfo u SET u.schoolStatus =:schoolStatus  WHERE u.schoolInfoId =:schoolInfoId")
	void updateApprovalDetails(@Param("schoolInfoId") Long schoolInfoId, @Param("schoolStatus") String schoolStatus);

	/**
	 * @param schoolStatus
	 * @return
	 */

	@Query(value = "SELECT COUNT(*) FROM SchoolInfo s WHERE s.schoolStatus IN :schoolStatus and s.active=:active ")
	int checkPendingStatus(@Param("schoolStatus") List<String> schoolStatus, @Param("active") String active);

	/**
	 * @param schoolInfoId
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE SchoolInfo u SET  u.active=:active, u.schoolStatus =:schoolStatus  WHERE u.schoolInfoId =:schoolInfoId")
	void deleteBySchoolDetails(@Param("active") String active, @Param("schoolStatus") String schoolStatus,
			@Param("schoolInfoId") Long schoolInfoId);

	
	/**
	 * @param schoolInfoId
	 * @param volunteerName
	 * @param schoolStatus
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE SchoolInfo u SET u.volunteerName =:volunteerName, u.schoolStatus =:schoolStatus  WHERE u.schoolInfoId =:schoolInfoId")
	void updateVolunteerDetails(@Param("schoolInfoId") Long schoolInfoId, @Param("volunteerName") String volunteerName,
			@Param("schoolStatus") String schoolStatus);

}
