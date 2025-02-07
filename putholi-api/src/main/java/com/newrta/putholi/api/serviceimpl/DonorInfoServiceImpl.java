package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.DonorInfo;
import com.newrta.putholi.api.repository.DonorInfoRepository;
import com.newrta.putholi.api.service.DonorInfoService;
import com.newrta.putholi.api.util.CommonsUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data

public class DonorInfoServiceImpl implements DonorInfoService {
	/**
	 * 
	 */
	@Autowired(required = true)
	private DonorInfoRepository donorInfoRepository;

	/**
	 * 
	 */

	@Override
	public boolean verifyEmailExists(String emailId) {
		log.info("DonorInfoServiceImpl-verifyEmailExists");
		return donorInfoRepository.existsByEmailIdIgnoreCase(emailId);
	}

	/**
	 *
	 */
	@Override
	public DonorInfo saveDonorInfo(DonorInfo donorInfo) {
		log.info("DonorInfoServiceImpl-saveDonorInfo");
		donorInfo.setOrderId(CommonsUtil.getNextSessionID());
		return donorInfoRepository.save(donorInfo);
	}

	/**
	 *
	 */
	@Override
	public DonorInfo getDonorInfoByEmailId(String emailId) {
		log.info("DonorInfoServiceImpl-getDonorInfoByEmailId");
		return donorInfoRepository.findByEmailIdIgnoreCase(emailId);
	}

	/**
	 * 
	 */

	@Override
	public DonorInfo getUserRegisterDetailsByUserName(String username) {
		log.info("DonorInfoServiceImpl-getUserRegisterDetailsByUserName");
		return donorInfoRepository.findByEmailIdIgnoreCase(username);
	}

	/**
	 *
	 */
	@Override
	public void updateOrderId(String orderId, String emailId) {
		log.info("DonorInfoServiceImpl-updateOrderId");
		donorInfoRepository.updateOrderId(orderId, emailId);
	}

	/**
	 *
	 */
	@Override
	public DonorInfo findByOrderId(String orderId) {
		log.info("DonorInfoServiceImpl-findByOrderId");
		return donorInfoRepository.findByOrderId(orderId);
	}

	/**
	 *
	 */
	@Override
	public List<DonorInfo> getDonorDetails() {
		log.info("DonorInfoServiceImpl-getDonorDetails");
		return donorInfoRepository.findAll();
	}
}
