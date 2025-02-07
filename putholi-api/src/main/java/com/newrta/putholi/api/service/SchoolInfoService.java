package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.SchoolInfo;
import com.newrta.putholi.api.domain.SchoolInfoDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.SchoolDetailsDTO;
import com.newrta.putholi.api.model.SchoolInfoDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface SchoolInfoService {

	/**
	 * @param loggedUser
	 * @param schoolInfoDTO
	 * @return
	 */
	ApiResultDTO saveSchoolInfo(String loggedUser, SchoolInfoDTO schoolInfoDTO);

	/**
	 * @param loggedUser
	 * @param schoolInfoId
	 * @return
	 */
	SchoolInfo fetchSchoolInfoByUser(String loggedUser, Long schoolInfoId);

	/**
	 * @param loggedUser
	 * @param schoolInfoId
	 * @return
	 */
	ApiResultDTO removeSchoolInfo(String loggedUser, Long schoolInfoId);

	/**
	 * @param loggedUser
	 * @param schoolInfoDTO
	 * @return
	 */
	ApiResultDTO modifySchoolInfo(String loggedUser, SchoolInfoDTO schoolInfoDTO);

	/**
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	Page<SchoolInfoDetails> searchSchoolInfo(String loggedUser, SchoolDetailsDTO searchDTO);

	/**
	 * 
	 * @param loggedUser
	 * @param createdBy
	 * @return
	 */

	SchoolInfo findBySchoolName(String loggedUser, String schoolName);

	/**
	 * @param schoolInfoId
	 * @param schoolStatus
	 */
	void updateApprovalDetails(Long schoolInfoId, String schoolStatus);
	
	
	/**
	 * @param schoolInfoId
	 * @param volunteerName
	 * @param schoolStatus
	 */
	void updateVolunteerDetails(Long schoolInfoId, String volunteerName,  String schoolStatus);

	/**
	 * 
	 * @param schoolInfoId
	 * @return
	 */

	SchoolInfo fetchSchoolInfo(Long schoolInfoId);

	/**
	 * @param schoolStatus
	 * @return
	 */
	int checkPendingStatus(List<String> schoolStatus, String active);

	
	/**
	 * 
	 * @param schoolRegNo
	 * @return
	 */

	Boolean existBySchoolRegNoIgnoreCase(String schoolRegNo);

	/**
	 * @param schoolInfoId
	 * @param active
	 */
	void deleteSchoolDetails(String active, String schoolStatus, Long schoolInfoId);

	

	/**
	 * 
	 * @param authorization
	 * @param loggedUser
	 * @param address
	 * @param emailId
	 * @param schoolName
	 * @return
	 */
	ResponseEntity<ApiResultDTO> volunteerAssignedEmail(String authorization, String loggedUser, String address,
			String emailId, String schoolName);
}
