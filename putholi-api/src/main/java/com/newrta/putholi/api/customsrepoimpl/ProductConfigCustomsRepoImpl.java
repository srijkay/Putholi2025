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

import com.newrta.putholi.api.customsrepo.ProductConfigCustomsRepo;
import com.newrta.putholi.api.domain.ProductConfig;
import com.newrta.putholi.api.model.ProductConfigSearchDTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Slf4j
public class ProductConfigCustomsRepoImpl implements ProductConfigCustomsRepo {

	private static final String CONFIG_ID = "configId";
	/**
	 * Entity manager for the persistence context
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Implementations of the search role details
	 */
	@Override
	public Page<ProductConfig> searchProductConfig(ProductConfigSearchDTO productConfigSearchDTO) {
		log.info("::ProductConfig::searchProductConfig");

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<ProductConfig> cq = getCreateQueryForProductConfig(cb);
		Root<ProductConfig> root = getRootForProductConfig(cq);

		projectionForProductConfig(cq, cb, root);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<ProductConfig> q = createPredicatesForProductConfig(predicates, productConfigSearchDTO, cb, cq,
				root);
		int totalRecords = getProductConfigResultSize(getProductConfigResults(q));
		Pageable sortedByIdDesc = getPageableForProductConfig(productConfigSearchDTO, q, Sort.by(CONFIG_ID));

		/* return the product config search results */
		return new PageImpl<>(getProductConfigResults(q), sortedByIdDesc, totalRecords);
	}

	/**
	 * @param productConfigResults
	 * @return
	 */
	private int getProductConfigResultSize(List<ProductConfig> productConfigResults) {
		log.info("ProductConfigCustomsRepoImpl::getProductConfigResultSize");
		/* return the list size */
		return productConfigResults.size();
	}

	/**
	 * @param results
	 * @return
	 */
	private List<ProductConfig> getProductConfigResults(TypedQuery<ProductConfig> results) {
		log.info("ProductConfigCustomsRepoImpl::getProductConfigResults");
		/* return the result list */
		return results.getResultList();
	}

	/**
	 * @param productConfigSearchDTO
	 * @param q
	 * @param sort
	 * @return
	 */
	private Pageable getPageableForProductConfig(ProductConfigSearchDTO productConfigSearchDTO,
			TypedQuery<ProductConfig> q, Sort sort) {
		log.info("ProductConfigCustomsRepoImpl::getPageableForProductConfig");
		if (productConfigSearchDTO.getPageNumber() > 0 && productConfigSearchDTO.getPageSize() > 0) {
			q.setFirstResult((productConfigSearchDTO.getPageNumber() - 1) * productConfigSearchDTO.getPageSize());
			q.setMaxResults(productConfigSearchDTO.getPageSize());
		}
		/* return the page request of routing search dto */
		return PageRequest.of(productConfigSearchDTO.getPageNumber() - 1, productConfigSearchDTO.getPageSize(),
				sort.descending());
	}

	/**
	 * @param predicates
	 * @param productConfigSearchDTO
	 * @param cb
	 * @param cq
	 * @param rootModule
	 * @return
	 */
	private TypedQuery<ProductConfig> createPredicatesForProductConfig(List<Predicate> predicates,
			ProductConfigSearchDTO productConfigSearchDTO, CriteriaBuilder cb, CriteriaQuery<ProductConfig> cq,
			Root<ProductConfig> root) {
		log.info("ProductConfigCustomsRepoImpl::createPredicatesForModule");

		if (checkMandatory(productConfigSearchDTO.getCompanyCode())) {
			predicates.add(cb.like(cb.lower(root.get("configCode")),
					"%" + productConfigSearchDTO.getCompanyCode().toLowerCase() + "%"));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(CONFIG_ID)));

		return entityManager.createQuery(cq);
	}

	/**
	 * @param value
	 * @return
	 */
	private boolean checkMandatory(String value) {
		return value != null && !value.isEmpty();
	}

	/**
	 * @param cq
	 * @param cb
	 * @param root
	 */
	private void projectionForProductConfig(CriteriaQuery<ProductConfig> cq, CriteriaBuilder cb,
			Root<ProductConfig> root) {
		log.info("ProductConfigCustomsRepoImpl::projectionForProductConfig");
		cq.select(cb.construct(ProductConfig.class, root.get(CONFIG_ID), root.get("configCode"),
				root.get("configValue"), root.get("configDesc"), root.get("system"), root.get("status")));
	}

	/**
	 * @param cq
	 * @return
	 */
	private Root<ProductConfig> getRootForProductConfig(CriteriaQuery<ProductConfig> cq) {
		log.info("ProductConfigCustomsRepoImpl::getRootForProductConfig");
		return cq.from(ProductConfig.class);
	}

	/**
	 * @param cb
	 * @return
	 */
	private CriteriaQuery<ProductConfig> getCreateQueryForProductConfig(CriteriaBuilder cb) {
		log.info("ProductConfigCustomsRepoImpl::getCreateQueryForProductConfig");
		return cb.createQuery(ProductConfig.class);
	}

}
