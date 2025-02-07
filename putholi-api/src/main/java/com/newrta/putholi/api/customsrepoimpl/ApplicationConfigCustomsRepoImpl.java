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

import com.newrta.putholi.api.customsrepo.ApplicationConfigCustomsRepo;
import com.newrta.putholi.api.domain.ApplicationConfig;
import com.newrta.putholi.api.model.GenericSearchDTO;


/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class ApplicationConfigCustomsRepoImpl implements ApplicationConfigCustomsRepo {

	/**
	 * 
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<ApplicationConfig> searchApplicationConfig(GenericSearchDTO searchDTO) {

		// criteria builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// create query
		CriteriaQuery<ApplicationConfig> cq = cb.createQuery(ApplicationConfig.class);

		// From Clause
		Root<ApplicationConfig> root = cq.from(ApplicationConfig.class);

		// Projections
		cq.select(cb.construct(ApplicationConfig.class,  root.get("configId"), root.get("configFor"),
				root.get("configValue"), root.get("module")));

		// Criteria (Search) --> Predicates
		if (searchDTO.getGenericSearchData() != null && !searchDTO.getGenericSearchData().isEmpty()) {

			Predicate configForPredicate = cb.like(cb.lower(root.get("configFor")),  
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");

			Predicate modulePredicate = cb.like(cb.lower(root.get("module")),
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");
			
			Predicate configValuePredicate = cb.like(cb.lower(root.get("configValue")),
					"%" + searchDTO.getGenericSearchData().toLowerCase() + "%");
 
 			cq.where(cb.or(configForPredicate,  modulePredicate, configValuePredicate))
					.orderBy(cb.desc(root.get("configId")));
		}

		// execute the query
		TypedQuery<ApplicationConfig> typedQuery = entityManager.createQuery(cq);

		// Implement Pagination
		List<ApplicationConfig> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (searchDTO.getPageNumber() > 0 && searchDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((searchDTO.getPageNumber() - 1) * searchDTO.getPageSize());
			typedQuery.setMaxResults(searchDTO.getPageSize());
		}

		Pageable pageable = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize(),
				Sort.by("configId").descending());

		// Return the Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
