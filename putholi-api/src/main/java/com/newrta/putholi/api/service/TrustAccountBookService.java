package com.newrta.putholi.api.service;

import com.newrta.putholi.api.domain.TrustAccountBook;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.TrustAccountBookDTO;

/**
 * @author NEWRTA SOLUTION
 *
 */

public interface TrustAccountBookService {

	/**
	 * @param loggedUser
	 * @param trustAccountBook
	 * @return
	 */
	ApiResultDTO saveAccountBook(String loggedUser, TrustAccountBookDTO trustAccountBookDTO);

	/**
	 * @param loggedUser
	 * @param projectId
	 * @return
	 */
	TrustAccountBook getAccountBook(String loggedUser, Long projectId);

	/**
	 * @return
	 */
	TrustAccountBook findTopByOrderByIdDesc();
}
