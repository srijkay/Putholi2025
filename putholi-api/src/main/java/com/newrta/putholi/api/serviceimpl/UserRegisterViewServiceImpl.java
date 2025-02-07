package com.newrta.putholi.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.UserRegisterInfoDetails;
import com.newrta.putholi.api.model.UserRegisterViewDetailsDTO;
import com.newrta.putholi.api.repository.UserRegisterViewRepository;
import com.newrta.putholi.api.service.UserInfoViewService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTION
 *
 */
@Slf4j

@Service
public class UserRegisterViewServiceImpl implements UserInfoViewService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserRegisterViewRepository userRegRepo;

	/**
	 *
	 */
	@Override
	public Page<UserRegisterInfoDetails> searchUserInfo(String loggedUser, UserRegisterViewDetailsDTO searchDTO) {
		log.info("UserRegisterViewServiceImpl-searchUserInfo");

		try {
			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.SEARCH)
					.auditModule("USERVIEW").auditDesc("UserRegisterViewServiceImpl-searchUserInfo")
					.auditValue(new ObjectMapper().writeValueAsString(searchDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
		} catch (JsonProcessingException jpe) {
			log.error("UserRegisterViewServiceImpl-searchUserInfo-JsonProcessingException {} {}", jpe.getCause(), jpe);
		}
		return userRegRepo.searchUserInfo(searchDTO);
	}

}
