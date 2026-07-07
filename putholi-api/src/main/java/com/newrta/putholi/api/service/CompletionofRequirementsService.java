package com.newrta.putholi.api.service;

import com.newrta.putholi.api.model.ApiResultDTO;
/**
 * @author NEWRTA SOLUTIONS
 *
 */
import com.newrta.putholi.api.model.CompletionofRequirementsDTO;
import com.newrta.putholi.api.model.ProjectCountDTO;

/**
 * @author vijaya
 *
 */
public interface CompletionofRequirementsService {

	/**
	 * @param loggedUser
	 * @param completionofrequirementsDTO
	 * @return
	 */
	ApiResultDTO saveCompletionofRequirementsInfo(String loggedUser,
			CompletionofRequirementsDTO completionofrequirementsDTO);

	/**
	 * @param loggedUser
	 * @return
	 */
	ProjectCountDTO summaryCount(String loggedUser);
}
