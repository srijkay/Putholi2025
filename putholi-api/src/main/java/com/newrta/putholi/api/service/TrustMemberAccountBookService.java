package com.newrta.putholi.api.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.newrta.putholi.api.domain.TrustMemberAccountBook;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.TrustMemberAccountBookDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface TrustMemberAccountBookService {

	/**
	 * @return
	 */
	TrustMemberAccountBook findTopByOrderByTrustBookIdDesc();

	/**
	 * @param loggedUser
	 * @param trustMemberAccountBookDTO
	 * @return
	 */
	ApiResultDTO saveTrustMemberAccountBook(String loggedUser, TrustMemberAccountBookDTO trustMemberAccountBookDTO);

	/**
	 * @param request
	 * @param locale
	 * @param authorization
	 * @param emailId
	 * @param address
	 * @return
	 */
	ResponseEntity<ApiResultDTO> memberAccountRegistrationEmail(HttpServletRequest request, String locale,
			String authorization, String emailId, String address);

	/**
	 * @param endDate
	 * @return
	 */
	List<TrustMemberAccountBook> findByCreatedDateBetween(Date endDate);
}
