package com.newrta.putholi.api.service;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.DonorInfoDetails;
import com.newrta.putholi.api.model.DonorInfoViewDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface DonorInfoViewService {

	/**
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	Page<DonorInfoDetails> searchDonorInfo(String loggedUser, DonorInfoViewDetailsDTO searchDTO);

}
