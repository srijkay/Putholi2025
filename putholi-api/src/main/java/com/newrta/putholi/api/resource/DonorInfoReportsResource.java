package com.newrta.putholi.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.domain.DonorInfoDetails;
import com.newrta.putholi.api.model.DonorInfoViewDetailsDTO;
import com.newrta.putholi.api.service.DonorInfoViewService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
@RestController
@RequestMapping(value = "/v1/api/donorreports")
public class DonorInfoReportsResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private DonorInfoViewService donorInfoService;

	/**
	 * @param authorization
	 * @param loggedUser
	 * @param searchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	public Page<DonorInfoDetails> searchDonorInfo(@RequestHeader String authorization,
			@RequestHeader String loggedUser, @RequestBody DonorInfoViewDetailsDTO searchDTO) {
		log.info("DonorInfoReportsResource-searchDonorInfo {} {}", loggedUser, searchDTO);

		return donorInfoService.searchDonorInfo(loggedUser, searchDTO);
	}

}
