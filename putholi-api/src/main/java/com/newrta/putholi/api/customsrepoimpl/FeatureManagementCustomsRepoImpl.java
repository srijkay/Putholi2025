package com.newrta.putholi.api.customsrepoimpl;

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
import com.newrta.putholi.api.customsrepo.FeatureManagementCustomsRepo;
import com.newrta.putholi.api.domain.FeatureManagement;
import com.newrta.putholi.api.model.FeatureManagementSearchDTO;

public class FeatureManagementCustomsRepoImpl implements FeatureManagementCustomsRepo {

	/**
	* 
	*/
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */

	@Override
	public Page<FeatureManagement> searchFeatureManagement(FeatureManagementSearchDTO searchDTO) {
		// 1. get criteria builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// 2. create query
		CriteriaQuery<FeatureManagement> cq = cb.createQuery(FeatureManagement.class);

		// 3. define From
		Root<FeatureManagement> root = cq.from(FeatureManagement.class);

		// 4. Projections
		cq.select(cb.construct(FeatureManagement.class, root.get("featureId"), root.get("featureName"),
				root.get("module"), root.get("filterType"), root.get("status")));

		// 5. Define Criteria (Search) --> Predicates
		if (searchDTO.getFeatureName() != null && !searchDTO.getFeatureName().isEmpty()) {
			Predicate featureNamePredicate = cb.like(cb.lower(root.get("featureName")),
					"%" + searchDTO.getFeatureName().toLowerCase() + "%");
			cq.where(cb.or(featureNamePredicate)).orderBy(cb.desc(root.get("featureId")));
		}
		if (searchDTO.getModule() != null && !searchDTO.getModule().isEmpty()) {
			Predicate modulePredicate = cb.like(cb.lower(root.get("module")),
					"%" + searchDTO.getModule().toLowerCase() + "%");
			cq.where(cb.or(modulePredicate)).orderBy(cb.desc(root.get("featureId")));
		}

		// 6. execute the query
		TypedQuery<FeatureManagement> typedQuery = entityManager.createQuery(cq);

		// 7. Implement Pagination
		List<FeatureManagement> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (searchDTO.getPageNumber() > 0 && searchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((searchDTO.getPageNumber() - 1) * searchDTO.getPageSize());
			typedQuery.setMaxResults(searchDTO.getPageSize());
		}

		Pageable pageable = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize(),
				Sort.by("Id").descending());

		// 8. Return the Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}
}
