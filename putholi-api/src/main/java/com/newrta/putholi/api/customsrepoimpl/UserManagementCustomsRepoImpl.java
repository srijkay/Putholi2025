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

import com.newrta.putholi.api.customsrepo.UserManagementCustomsRepo;
import com.newrta.putholi.api.domain.UserManagement;
import com.newrta.putholi.api.model.UserManagementDTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
public class UserManagementCustomsRepoImpl implements UserManagementCustomsRepo {

	/**
	 * 
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<UserManagement> searchUserManagement(UserManagementDTO searchDTO) {
		log.info("UserManagementCustomsRepoImpl-searchUserManagement");

		// 1. get criteria builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// 2. create query
		CriteriaQuery<UserManagement> cq = cb.createQuery(UserManagement.class);

		// 3. define From
		Root<UserManagement> root = cq.from(UserManagement.class);
		// 4. Projections - Not Applicable

		// 5. Define Criteria (Search) --> Predicates
		List<Predicate> predicates = new ArrayList<>();
		if (searchDTO.getStatusCode() != null && !searchDTO.getStatusCode().isEmpty()) {
			predicates.add((root.get("statusCode")).in(searchDTO.getStatusCode()));
		}

		if (searchDTO.getUserRole() != null && !searchDTO.getUserRole().isEmpty()) {
			predicates.add(root.get("roleCode").in(searchDTO.getUserRole()));
		}

		if (searchDTO.getRoleCode() != null && !searchDTO.getRoleCode().isEmpty()) {
			predicates.add(root.get("roleCode").in(searchDTO.getRoleCode()).not());
		}

		if (searchDTO.isAccountEnabled()) {
			predicates.add(cb.equal(root.get("accountEnabled"), searchDTO.isAccountEnabled()));
		}

		if (searchDTO.getFromDate() != null && searchDTO.getTodate() != null) {
			predicates.add(cb.between(root.get("createdDate"), searchDTO.getFromDate(), searchDTO.getTodate()));
		}

		if (searchDTO.getGenericSearchData() != null && !searchDTO.getGenericSearchData().isEmpty()) {
			Predicate usernamePredicate = cb.like(cb.lower(root.get("userName")),
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");

			Predicate firstNamePredicate = cb.like(cb.lower(root.get("firstName")),
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");

			Predicate emailIdPredicate = cb.like(cb.lower(root.get("emailId")),
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");

			Predicate districtPredicate = cb.like(cb.lower(root.get("district")),
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");

			predicates.add(cb.or(usernamePredicate, firstNamePredicate, emailIdPredicate, districtPredicate));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.asc(root.get("userName")));

		// 6. execute the query
		TypedQuery<UserManagement> typedQuery = entityManager.createQuery(cq);

		// 7. Implement Pagination
		List<UserManagement> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (searchDTO.getPageNumber() > 0 && searchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((searchDTO.getPageNumber() - 1) * searchDTO.getPageSize());
			typedQuery.setMaxResults(searchDTO.getPageSize());
		}

		Pageable pageable = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize(),
				Sort.by("userName").descending());

		// 8. Return the Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}
}
