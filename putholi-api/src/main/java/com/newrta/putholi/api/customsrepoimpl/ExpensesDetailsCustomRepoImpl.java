package com.newrta.putholi.api.customsrepoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.newrta.putholi.api.customsrepo.ExpensesDetailsCustomRepo;
import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.model.ExpensesViewDetailsSearchDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
public class ExpensesDetailsCustomRepoImpl implements ExpensesDetailsCustomRepo {

	public static final String EXPENSESID = "expensesId";

	/**
	 * 
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<ExpensesDetails> searchExpensesDetails(ExpensesViewDetailsSearchDTO searchDTO) {
		log.info("ExpensesDetailsCustomRepoImpl-searchExpensesDetails");

		// Criteria Builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// Criteria Query
		CriteriaQuery<ExpensesDetails> cq = cb.createQuery(ExpensesDetails.class);

		// from clause
		Root<ExpensesDetails> root = cq.from(ExpensesDetails.class);

		// Define criteria
		List<Predicate> predicates = new ArrayList<>();

		if (searchDTO.getVendorName() != null && !searchDTO.getVendorName().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("vendorName")), searchDTO.getVendorName().toLowerCase()));
		}

		if (searchDTO.getUtrNumber() != null && !searchDTO.getUtrNumber().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("utrNumber")), searchDTO.getUtrNumber().toLowerCase()));
		}

		if (searchDTO.getAmount() != null) {
			predicates.add(root.get("amount").in(searchDTO.getAmount()));
		}

		if (searchDTO.getTransactionDate() != null) {
			predicates.add(root.get("transactionDate").in(searchDTO.getTransactionDate()).not());
		}

		if (searchDTO.getStatus() != null && !searchDTO.getStatus().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("status")), searchDTO.getStatus().toLowerCase()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(EXPENSESID)));

		TypedQuery<ExpensesDetails> typedQuery = entityManager.createQuery(cq);

		// implementation Paginations
		List<ExpensesDetails> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (searchDTO.getPageNumber() > 0 && searchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((searchDTO.getPageNumber() - 1) * searchDTO.getPageSize());
			typedQuery.setMaxResults(searchDTO.getPageSize());
		}
		Pageable pageable = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize(),
				Sort.by(EXPENSESID).descending());

		// Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
