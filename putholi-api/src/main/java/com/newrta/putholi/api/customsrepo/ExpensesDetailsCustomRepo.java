package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.model.ExpensesViewDetailsSearchDTO;

/**
 * @author NEWRTA SOLUTONS
 *
 */
public interface ExpensesDetailsCustomRepo {

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<ExpensesDetails> searchExpensesDetails(ExpensesViewDetailsSearchDTO searchDTO);

}
