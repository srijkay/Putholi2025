package com.newrta.putholi.api.service;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.AnnouncementDetails;
import com.newrta.putholi.api.model.AnnouncementDetailsDTO;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface AnnouncementDetailsService {

	/**
	 * @param announcementDetails
	 * @return
	 */
	ApiResultDTO saveAnnouncementInfo(String loggedUser, AnnouncementDetailsDTO announcementDetailsDTO);

	/**
	 * @param announcementDetails
	 * @return
	 */
	ApiResultDTO modifyAnnouncementInfo(String loggedUser, AnnouncementDetailsDTO announcementDetailsDTO);

	/**
	 * @param announcementId
	 * @return
	 */
	AnnouncementDetails fectchAnnouncementInfo(String loggedUser, Long announcementId);

	/**
	 * @param announcementId
	 * @return
	 */
	ApiResultDTO removeAnnouncementInfo(String loggedUser, Long announcementId);

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<AnnouncementDetails> searchAnnouncementInfo(String loggedUser, AnnouncementDetailsDTO searchDTO);
}
