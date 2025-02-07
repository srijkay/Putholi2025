package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.AnnouncementDetails;
import com.newrta.putholi.api.model.AnnouncementDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface AnnouncementDetailsCustomRepo {
	
	/**
	 * @param searchDTO
	 * @return
	 */
	Page<AnnouncementDetails> searchAnnouncemnetInfo(AnnouncementDetailsDTO announcementdetailsDTO);

}
