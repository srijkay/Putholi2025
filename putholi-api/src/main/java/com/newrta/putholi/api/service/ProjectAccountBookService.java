package com.newrta.putholi.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.newrta.putholi.api.domain.ProjectAccountBook;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ProjectAccountBookDTO;

/**
 * @author NEWRTA SOLUTION
 *
 */
public interface ProjectAccountBookService {

	/**
	 * @param loggedUser
	 * @param projectAccountBook
	 * @return
	 */
	ApiResultDTO saveProjectAccountBook(String loggedUser, ProjectAccountBookDTO projectAccountBookDTO);

	/**
	 * @param loggedUser
	 * @param projectId
	 * @return
	 */
	List<ProjectAccountBook> findByProjectId(String loggedUser, Long projectId);

	/**
	 * @return
	 */
	ProjectAccountBook findTopByOrderByIdDesc();

	/**
	 * @param projectId
	 * @return
	 */
	BigDecimal findBalanceAmountByProjectId(Long projectId);

	/**
	 * @param endDate
	 * @return
	 */
	List<ProjectAccountBook> findByCreatedDateBetween(Date endDate);
}
