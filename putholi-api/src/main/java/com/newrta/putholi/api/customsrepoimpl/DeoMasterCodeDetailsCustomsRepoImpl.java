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

import com.newrta.putholi.api.customsrepo.DeoMasterCodeDetailsCustomsRepo;
import com.newrta.putholi.api.domain.DeoMasterCodeDetails;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */

public class DeoMasterCodeDetailsCustomsRepoImpl implements DeoMasterCodeDetailsCustomsRepo {
	/**
	 * Entity manager for the persistence context
	 */
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Page<DeoMasterCodeDetails> searchDeoMasterCodes(DeoMasterCodeSearchDTO deoMasterCodeSearchDTO) {
		// 1. get criteria builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// 2. create query
		CriteriaQuery<DeoMasterCodeDetails> cq = cb.createQuery(DeoMasterCodeDetails.class);

		// 3. define From
		Root<DeoMasterCodeDetails> root = cq.from(DeoMasterCodeDetails.class);

		// 4. Projections
		cq.select(cb.construct(DeoMasterCodeDetails.class, root.get("id"), root.get("deoName"), root.get("district"),
				root.get("town"), root.get("active")));

		// 5. Define Criteria (Search) --> Predicates
		List<Predicate> predicates = new ArrayList<>();


		if (deoMasterCodeSearchDTO.getLoggedUser() != null && !deoMasterCodeSearchDTO.getLoggedUser().isEmpty()) {
			predicates.add(
					cb.equal(cb.lower(root.get("createdBy")), deoMasterCodeSearchDTO.getLoggedUser().toLowerCase()));
		}

		if (deoMasterCodeSearchDTO.getActive() != null && !deoMasterCodeSearchDTO.getActive().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("active")), deoMasterCodeSearchDTO.getActive().toLowerCase()));
		}

		if (deoMasterCodeSearchDTO.getDeoName() != null && !deoMasterCodeSearchDTO.getDeoName().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("deoName")), deoMasterCodeSearchDTO.getDeoName().toLowerCase()));
		}

		if (deoMasterCodeSearchDTO.getDistrict() != null && !deoMasterCodeSearchDTO.getDistrict().isEmpty()) {
			predicates
					.add(cb.equal(cb.lower(root.get("district")), deoMasterCodeSearchDTO.getDistrict().toLowerCase()));
		}

		if (deoMasterCodeSearchDTO.getTown() != null && !deoMasterCodeSearchDTO.getTown().isEmpty()) {
			predicates
					.add(cb.equal(cb.lower(root.get("town")), deoMasterCodeSearchDTO.getTown().toLowerCase()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get("id")));
		// 6. execute the query
		TypedQuery<DeoMasterCodeDetails> typedQuery = entityManager.createQuery(cq);

		// 7. Implement Pagination
		List<DeoMasterCodeDetails> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (deoMasterCodeSearchDTO.getPageNumber() > 0 && deoMasterCodeSearchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult(
					(deoMasterCodeSearchDTO.getPageNumber() - 1) * deoMasterCodeSearchDTO.getPageSize());
			typedQuery.setMaxResults(deoMasterCodeSearchDTO.getPageSize());
		}

		Pageable pageable = PageRequest.of(deoMasterCodeSearchDTO.getPageNumber() - 1,
				deoMasterCodeSearchDTO.getPageSize(), Sort.by("schoolInfoId").descending());

		// 8. Return the Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);

	}

}
