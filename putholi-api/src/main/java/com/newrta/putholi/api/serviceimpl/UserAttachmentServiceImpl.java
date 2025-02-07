package com.newrta.putholi.api.serviceimpl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.UserAttachment;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.repository.UserAttachmentRepository;
import com.newrta.putholi.api.service.UserAttachmentService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class UserAttachmentServiceImpl implements UserAttachmentService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserAttachmentRepository userProfileAttachmentRepository;

	/**
	 * 
	 */
	@Override
	public ApiResultDTO saveUserProfileAttachment(String locale, UserAttachment userProfileAttachment) {
		log.info("UserProfileAttachmentServiceImpl-saveUserProfileAttachment");

		if (userProfileAttachmentRepository.existsByUsernameAndUploadFor(userProfileAttachment.getUsername(),
				userProfileAttachment.getUploadFor())) {
			UserAttachment attachement = userProfileAttachmentRepository.findByUsernameAndUploadFor(
					userProfileAttachment.getUsername(), userProfileAttachment.getUploadFor());

			modifyUserProfileAttachment(locale, attachement);
		} else {
			userProfileAttachmentRepository.save(userProfileAttachment);
		}

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null))
				.refNo(Base64.getEncoder().encodeToString(userProfileAttachment.getFileData())).build();
	}

	/**
	 * 
	 */
	@Override
	public UserAttachment getUserAttachment(String username, String uploadFor) {
		log.info("UserProfileAttachmentServiceImpl-getUserAttachment");
		return userProfileAttachmentRepository.findByUsernameAndUploadFor(username, uploadFor);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeProfilePic(String username) {
		log.info("UserProfileAttachmentServiceImpl-removeProfilePic");

		userProfileAttachmentRepository.deleteByUsernameAndUploadFor(username, CommonsConstants.ID_PROOF);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Removed Pic Successfully!")
				.build();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyUserProfileAttachment(String locale, UserAttachment userAttachment) {
		log.info("UserProfileAttachmentServiceImpl-modifyUserProfileAttachment {} ");

		ApiResultDTO apiResultDTO;

		if (userProfileAttachmentRepository.existsById(userAttachment.getId())) {

			userProfileAttachmentRepository.save(userAttachment);
			return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc(LocaleConfig.getResourceValue("save.success", null, locale, null))
					.refNo(Base64.getEncoder().encodeToString(userAttachment.getFileData())).build();
		} else {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("ID Doesn't exists in the System").build();
		}
		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public UserAttachment findByUploadFor(String uploadFor) {
		log.info("UserProfileAttachmentServiceImpl-findByUploadFor");
		return userProfileAttachmentRepository.findByUploadFor(uploadFor);
	}

}
