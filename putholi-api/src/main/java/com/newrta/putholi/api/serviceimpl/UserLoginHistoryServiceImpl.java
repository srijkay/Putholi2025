package com.newrta.putholi.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.UserLoginHistory;
import com.newrta.putholi.api.repository.UserLoginHistoryRepository;
import com.newrta.putholi.api.service.UserLoginHistoryService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class UserLoginHistoryServiceImpl implements UserLoginHistoryService {

    /**
     * 
     */
    @Autowired(required = true)
    private UserLoginHistoryRepository userLoginHistoryRepository;

    /**
     * 
     */
    @Override
    public UserLoginHistory saveUserLoginHistory(UserLoginHistory userLoginHistory) {
	log.info("UserLoginHistoryServiceImpl-saveUserLoginHistory");
	return userLoginHistoryRepository.save(userLoginHistory);
    }

}
