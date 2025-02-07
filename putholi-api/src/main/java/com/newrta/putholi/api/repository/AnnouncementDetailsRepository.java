package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.AnnouncementDetailsCustomRepo;
import com.newrta.putholi.api.domain.AnnouncementDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface AnnouncementDetailsRepository
		extends JpaRepository<AnnouncementDetails, Long>, AnnouncementDetailsCustomRepo {

	/**
	 * @param announcementId
	 * @return
	 */
	AnnouncementDetails findByAnnouncementId(Long announcementId);

}
