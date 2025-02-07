package com.newrta.putholi.api.service;

import com.newrta.putholi.api.model.ApiResultDTO;
/**
 * @author NEWRTA SOLUTIONS
 *
 */
import com.newrta.putholi.api.model.CompletionofRequirementsDTO;

public interface CompletionofRequirementsService {

	ApiResultDTO saveCompletionofRequirementsInfo(String loggedUser, CompletionofRequirementsDTO completionofrequirementsDTO);

}
