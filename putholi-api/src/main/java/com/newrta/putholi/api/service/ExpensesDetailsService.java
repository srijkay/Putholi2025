package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ExpensesDetailsDTO;
import com.newrta.putholi.api.model.ExpensesViewDetailsSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ExpensesDetailsService {

	/**
	 * @param loggedUser
	 * @param expensesDetailsDTO
	 * @return
	 */
	ApiResultDTO saveExpensesDetails(String loggedUser, ExpensesDetailsDTO expensesDetailsDTO);

	/**
	 * @param loggedUser
	 * @param expensesDetailsDTO
	 * @return
	 */
	ApiResultDTO modifyExpensesDetails(String loggedUser, ExpensesDetailsDTO expensesDetailsDTO);

	/**
	 * @param loggedUser
	 * @param ExpressId
	 * @return
	 */
	ExpensesDetails findByExpensesId(String loggedUser, Long expressId);

	/**
	 * @param searchDTO
	 * @return
	 */
	Page<ExpensesDetails> searchExpensesDetails(String loggedUser, ExpensesViewDetailsSearchDTO searchDTO);

	/**
	 * @param expensesId
	 * @param status
	 */
	void updateStatusByExpensesId(Long expensesId, String status);

	/**
	 * @param status
	 * @return
	 */
	List<ExpensesDetails> findByStatus(String status);

	/**
	 * 
	 */
	void generatedExcelFile();

	/**
	 * @param status
	 * @return
	 */
	int checkPendingStatus(List<String> status);

	/**
	 * @param expensesDTO
	 * @return
	 */
	Long updateExpensesPayment(ExpensesDetailsDTO expensesDTO);

	/**
	 * @param file
	 * @return
	 */
	public ApiResultDTO updateExpensesPayment(MultipartFile file);

}
