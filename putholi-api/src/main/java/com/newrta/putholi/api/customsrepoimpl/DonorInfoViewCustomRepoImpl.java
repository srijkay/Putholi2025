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

import com.newrta.putholi.api.customsrepo.DonorInfoViewCustomRepo;
import com.newrta.putholi.api.domain.DonorInfoDetails;
import com.newrta.putholi.api.model.DonorInfoViewDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class DonorInfoViewCustomRepoImpl implements DonorInfoViewCustomRepo {

	/**
	* 
	*/
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<DonorInfoDetails> searchDonorInfo(DonorInfoViewDetailsDTO donorInfoDTO) {

		// Criteria Builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// Criteria Query
		CriteriaQuery<DonorInfoDetails> cq = cb.createQuery(DonorInfoDetails.class);

		// from clause
		Root<DonorInfoDetails> root = cq.from(DonorInfoDetails.class);

		// Define criteria
		List<Predicate> predicates = new ArrayList<>();

		if (donorInfoDTO.getCity() != null) {
			predicates.add(cb.equal(cb.lower(root.get("city")), donorInfoDTO.getCity().toLowerCase()));
		}

		if (donorInfoDTO.getAmount() != null) {
			predicates.add(cb.equal((root.get("amount")), donorInfoDTO.getAmount()));
		}

		if (donorInfoDTO.getFromDate() != null && donorInfoDTO.getTodate() != null) {
			predicates.add(cb.between(root.get("createdDate"), donorInfoDTO.getFromDate(), donorInfoDTO.getTodate()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get("createdDate")));
		// execute the query
		TypedQuery<DonorInfoDetails> typedQuery = entityManager.createQuery(cq);

		// implementation Paginations
		List<DonorInfoDetails> resultList = typedQuery.getResultList();

		int totalRecords = resultList.size();

		if (donorInfoDTO.getPageNumber() > 0 && donorInfoDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((donorInfoDTO.getPageNumber() - 1) * donorInfoDTO.getPageSize());
			typedQuery.setMaxResults(donorInfoDTO.getPageSize());
		}
		Pageable pageable = PageRequest.of(donorInfoDTO.getPageNumber() - 1, donorInfoDTO.getPageSize(),
				Sort.by("createdDate").descending());

		// Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
