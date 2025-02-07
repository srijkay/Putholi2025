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

import com.newrta.putholi.api.customsrepo.SchoolInfoCustomsRepo;
import com.newrta.putholi.api.domain.SchoolInfoDetails;
import com.newrta.putholi.api.model.SchoolDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class SchoolInfoCustomsRepoImpl implements SchoolInfoCustomsRepo {

	private static final String SCHOOL_INFO_ID = "schoolInfoId";

	/**
	 * 
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<SchoolInfoDetails> searchSchoolInfo(SchoolDetailsDTO searchDTO) {

		// 1. get criteria builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// 2. create query
		CriteriaQuery<SchoolInfoDetails> cq = cb.createQuery(SchoolInfoDetails.class);

		// 3. define From
		Root<SchoolInfoDetails> root = cq.from(SchoolInfoDetails.class);

		// 4. Projections
		cq.select(cb.construct(SchoolInfoDetails.class, root.get(SCHOOL_INFO_ID), root.get("schoolRegNo"),
				root.get("schoolName"), root.get("schoolType"), root.get("primaryContact"), root.get("schoolStatus"),
				root.get("schoolStatusCode"), root.get("createdBy"), root.get("createdDate"), root.get("locality"), root.get("district"),
				root.get("active")));

		// 5. Define Criteria (Search) --> Predicates
		List<Predicate> predicates = new ArrayList<>();
		if (searchDTO.getStatus() != null && !searchDTO.getStatus().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("schoolStatusCode")), searchDTO.getStatus().toLowerCase()));
		}

		if (searchDTO.getSchoolStatusCode() != null && !searchDTO.getSchoolStatusCode().isEmpty()) {
			predicates.add(root.get("schoolStatusCode").in(searchDTO.getSchoolStatusCode()));
		}

		if (searchDTO.getLoggedUser() != null && !searchDTO.getLoggedUser().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("createdBy")), searchDTO.getLoggedUser().toLowerCase()));
		}

		if (searchDTO.getActive() != null && !searchDTO.getActive().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("active")), searchDTO.getActive().toLowerCase()));
		}

		if (searchDTO.getLocality() != null && !searchDTO.getLocality().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("locality")), searchDTO.getLocality().toLowerCase()));
		}

		if (searchDTO.getSchoolName() != null && !searchDTO.getSchoolName().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("schoolName")),"%"+ searchDTO.getSchoolName().toLowerCase() +"%"));
		}

		if (searchDTO.getSchoolType() != null && !searchDTO.getSchoolType().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("schoolType")), searchDTO.getSchoolType().toLowerCase()));
		}

		if (searchDTO.getDistrict() != null && !searchDTO.getDistrict().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("district")), searchDTO.getDistrict().toLowerCase()));
		}

		if (searchDTO.getFromDate() != null && searchDTO.getTodate() != null) {
			predicates.add(cb.between(root.get("createdDate"), searchDTO.getFromDate(), searchDTO.getTodate()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(SCHOOL_INFO_ID)));
		// 6. execute the query
		TypedQuery<SchoolInfoDetails> typedQuery = entityManager.createQuery(cq);

		// 7. Implement Pagination
		List<SchoolInfoDetails> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (searchDTO.getPageNumber() > 0 && searchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((searchDTO.getPageNumber() - 1) * searchDTO.getPageSize());
			typedQuery.setMaxResults(searchDTO.getPageSize());
		}

		Pageable pageable = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize(),
				Sort.by(SCHOOL_INFO_ID).descending());

		// 8. Return the Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
