package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.DonorInfoDetails;
import com.newrta.putholi.api.model.DonorInfoViewDetailsDTO;

/**
 * @author NEWRTA SOLTIONS
 *
 */
public interface DonorInfoViewCustomRepo {

	/**
	 * @param donorInfoDTO
	 * @return
	 */
	Page<DonorInfoDetails> searchDonorInfo(DonorInfoViewDetailsDTO donorInfoDTO);
}
