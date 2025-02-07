package com.newrta.putholi.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.UserAttachment;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
@Transactional
public interface UserAttachmentRepository extends JpaRepository<UserAttachment, Long> {

	/**
	 * @param username
	 * @param uploadFor
	 * @return
	 */
	UserAttachment findByUsernameAndUploadFor(String username, String uploadFor);

	/**
	 * @param uploadFor
	 * @return
	 */
	UserAttachment findByUploadFor(String uploadFor);

	/**
	 * @param username
	 * @param uploadFor
	 */
	void deleteByUsernameAndUploadFor(String username, String uploadFor);

	/**
	 * @param username
	 * @return
	 */
	boolean existsByUsernameAndUploadFor(String username, String uploadFor);

}
