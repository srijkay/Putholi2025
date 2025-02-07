package com.newrta.putholi.api.serviceimpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.TrustAccountBook;
import com.newrta.putholi.api.domain.TrustMemberAccountBook;
import com.newrta.putholi.api.model.AdminDashboardDTO;
import com.newrta.putholi.api.service.AdminDashboardService;
import com.newrta.putholi.api.service.ProjectAccountBookService;
import com.newrta.putholi.api.service.TrustAccountBookService;
import com.newrta.putholi.api.service.TrustMemberAccountBookService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class AdminDashboardServiceImpl implements AdminDashboardService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private TrustAccountBookService trustBookService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ProjectAccountBookService projectAccountBook;

	/**
	 * 
	 */
	@Autowired(required = true)
	private TrustMemberAccountBookService memberAccountService;

	/**
	 *
	 */
	@Override
	public AdminDashboardDTO fetchFundDetails(String loggedUser, Long consolidateId) {
		log.info("AdminDashboardServiceImpl -fetchFundDetails");

		TrustAccountBook trustAccountBook = trustBookService.findTopByOrderByIdDesc();

		BigDecimal amount = projectAccountBook.findBalanceAmountByProjectId(consolidateId);
		TrustMemberAccountBook memberAccountBook = memberAccountService.findTopByOrderByTrustBookIdDesc();

		AdminDashboardDTO adminDashboardDTO = new AdminDashboardDTO();
		if (trustAccountBook != null) {
			adminDashboardDTO.setTotalAmount(trustAccountBook.getBalanceAmount());
		}
		if (amount != null) {
			adminDashboardDTO.setCollectedAmount(amount);
		}
		if (memberAccountBook != null) {
			adminDashboardDTO.setTrustFund(memberAccountBook.getBalanceAmount());
		}
		return adminDashboardDTO;
	}

}
