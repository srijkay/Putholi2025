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

import com.newrta.putholi.api.customsrepo.ConsolidateInfoCustomRepo;
import com.newrta.putholi.api.domain.ConsolidateInfoDetails;
import com.newrta.putholi.api.model.ConsolidateSearchDTO;

/**
 * @author NERTA SOLUTIONS
 *
 */
public class ConsolidateInfoCustomRepoImpl implements ConsolidateInfoCustomRepo {

	public static final String CONSOLIDATEID = "consolidateId";

	/**
	* 
	*/
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<ConsolidateInfoDetails> searchConsolidateInfo(ConsolidateSearchDTO searchDTO) {

		// Criteria Builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// Criteria Query
		CriteriaQuery<ConsolidateInfoDetails> cq = cb.createQuery(ConsolidateInfoDetails.class);

		// from clause
		Root<ConsolidateInfoDetails> root = cq.from(ConsolidateInfoDetails.class);

		// Define criteria
		List<Predicate> predicates = new ArrayList<>();
		if (searchDTO.getLoggedUser() != null && !searchDTO.getLoggedUser().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("createdBy")), searchDTO.getLoggedUser().toLowerCase()));
		}

		if (searchDTO.getActive() != null && !searchDTO.getActive().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("active")), searchDTO.getActive().toLowerCase()));
		}

		if (searchDTO.getSchoolName() != null && !searchDTO.getSchoolName().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("schoolName")), searchDTO.getSchoolName().toLowerCase()));
		}

		if (searchDTO.getSchoolType() != null && !searchDTO.getSchoolType().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("schoolType")), searchDTO.getSchoolType().toLowerCase()));
		}

		if (searchDTO.getConsolidateId() != null) {
			predicates.add(cb.equal((root.get(CONSOLIDATEID)), searchDTO.getConsolidateId()));
		}

		if (searchDTO.getLocality() != null && !searchDTO.getLocality().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("locality")), searchDTO.getLocality().toLowerCase()));
		}

		if (searchDTO.getConsolidateStatusCode() != null && !searchDTO.getConsolidateStatusCode().isEmpty()) {
			predicates.add(root.get("consolidateStatusCode").in(searchDTO.getConsolidateStatusCode()));
		}

		if (searchDTO.getStatus() != null && !searchDTO.getStatus().isEmpty()) {
			predicates.add(root.get("consolidateStatusCode").in(searchDTO.getStatus()).not());
		}

		if (searchDTO.getGenericSearchData() != null && !searchDTO.getGenericSearchData().isEmpty()) {
			predicates
					.add(cb.equal(cb.lower(root.get("volunteerName")), searchDTO.getGenericSearchData().toLowerCase()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(CONSOLIDATEID)));

		TypedQuery<ConsolidateInfoDetails> typedQuery = entityManager.createQuery(cq);

		// implementation Paginations
		List<ConsolidateInfoDetails> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (searchDTO.getPageNumber() > 0 && searchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((searchDTO.getPageNumber() - 1) * searchDTO.getPageSize());
			typedQuery.setMaxResults(searchDTO.getPageSize());
		}
		Pageable pageable = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize(),
				Sort.by(CONSOLIDATEID).descending());

		// Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);

	}

}
