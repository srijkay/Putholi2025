package com.newrta.putholi.api.customsrepoimpl;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.customsrepo.DeoMasterCodeTypeDetailsCustomsRepo;
import com.newrta.putholi.api.domain.DeoMasterCodeTypeDetails;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
public class DeoMasterCodeTypeDetailsCustomsRepoImpl implements DeoMasterCodeTypeDetailsCustomsRepo {

	/**
	 * Entity manager for the persistence context
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * 
	 */

	@Override
	public Page<DeoMasterCodeTypeDetails> searchDeoMasterCodeTypes(DeoMasterCodeSearchDTO deoMasterCodeSearchDTO) {
		log.info("DeoMasterCodeTypeDetailsCustomsRepoImpl-searchDeoMasterCodeTypes");

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeoMasterCodeTypeDetails> cq = getCreateQueryForSystem(cb);
		Root<DeoMasterCodeTypeDetails> root = getRootForDeoMasterCodeTypeDetails(cq);

		projectionForDeoMasterCodeTypeDetails(cq, cb, root);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<DeoMasterCodeTypeDetails> q = createPredicatesForDeoMasterCodeTypeDetails(predicates,
				deoMasterCodeSearchDTO, root, cb, cq);
		int totalRecords = getDeoMasterCodeTypeDetailsResultSize(getDeoMasterCodeTypeDetailsResultList(q));

		Pageable sortedByIdDesc = getPageableForDeoMasterCodeTypeDetails(deoMasterCodeSearchDTO, q, Sort.by("id"));

		/* return the MasterCodeDetails results DTO */
		return new PageImpl<>(getDeoMasterCodeTypeDetailsResultList(q), sortedByIdDesc, totalRecords);
	}

	private List<DeoMasterCodeTypeDetails> getDeoMasterCodeTypeDetailsResultList(
			TypedQuery<DeoMasterCodeTypeDetails> q) {
		log.info("DeoMasterCodeTypeDetailsCustomsRepoImpl::getDeoMasterCodeTypeDetailsResultList");
		/*
		 * return DeoMasterCodeTypeDetailsCustomsRepoImpl::
		 * getDeoMasterCodeTypeDetailsResultList
		 */
		return q.getResultList();
	}

	private int getDeoMasterCodeTypeDetailsResultSize(
			List<DeoMasterCodeTypeDetails> deoMasterCodeTypeDetailsResultList) {
		log.info("DeoMasterCodeTypeDetailsCustomsRepoImpl::getDeoMasterCodeTypeDetailsResultSize");
		/*
		 * return DeoMasterCodeTypeDetailsCustomsRepoImpl::
		 * getDeoMasterCodeTypeDetailsResultSize
		 */
		return deoMasterCodeTypeDetailsResultList.size();
	}

	private Pageable getPageableForDeoMasterCodeTypeDetails(DeoMasterCodeSearchDTO deoMasterCodeSearchDTO,
			TypedQuery<DeoMasterCodeTypeDetails> q, Sort sort) {
		log.info("DeoMasterCodeTypeDetailsCustomsRepoImpl::getPageableForDeoMasterCodeTypeDetails");
		if (deoMasterCodeSearchDTO.getPageNumber() > 0 && deoMasterCodeSearchDTO.getPageSize() > 0) {
			q.setFirstResult((deoMasterCodeSearchDTO.getPageNumber() - 1) * deoMasterCodeSearchDTO.getPageSize());
			q.setMaxResults(deoMasterCodeSearchDTO.getPageSize());
		}
		/*
		 * return MasterCodeTypeDetailsCustomsRepoImpl::
		 * getPageableForMasterCodeTypeDetails
		 */
		return PageRequest.of(deoMasterCodeSearchDTO.getPageNumber() - 1, deoMasterCodeSearchDTO.getPageSize(),
				sort.ascending());
	}

	/**
	 * @param value
	 * @return
	 */
	private boolean checkMandatory(String value) {
		return value != null && !value.isEmpty();
	}

	private TypedQuery<DeoMasterCodeTypeDetails> createPredicatesForDeoMasterCodeTypeDetails(List<Predicate> predicates,
			DeoMasterCodeSearchDTO deoMasterCodeSearchDTO, Root<DeoMasterCodeTypeDetails> root, CriteriaBuilder cb,
			CriteriaQuery<DeoMasterCodeTypeDetails> cq) {
		log.info("DeoMasterCodeTypeDetailsCustomsRepoImpl::createPredicatesForDeoMasterCodeTypeDetails");



		if (checkMandatory(deoMasterCodeSearchDTO.getDeoName())) {
			predicates.add(cb.or(
					cb.like(cb.lower(root.get("deoName")), "%" + deoMasterCodeSearchDTO.getDeoName().toLowerCase())));

		}
		
	
        if (checkMandatory(deoMasterCodeSearchDTO.getDistrict())) {
			predicates.add(cb.or(
					cb.like(cb.lower(root.get("district")), "%" + deoMasterCodeSearchDTO.getDistrict().toLowerCase())));

		}

		if (checkMandatory(deoMasterCodeSearchDTO.getActive())) {
			predicates.add(cb.equal(cb.lower(root.get("active")), deoMasterCodeSearchDTO.getActive().toLowerCase()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get("id")));
		/*
		 * return DeoMasterCodeTypeDetailsCustomsRepoImpl::
		 * createPredicatesForMasterCodeTypeDetails
		 */
		return entityManager.createQuery(cq);
	}

	private void projectionForDeoMasterCodeTypeDetails(CriteriaQuery<DeoMasterCodeTypeDetails> cq, CriteriaBuilder cb,
			Root<DeoMasterCodeTypeDetails> root) {
		log.info("DeoMasterCodeDetailsCustomsRepoImpl::projectionForDeoMasterCodeTypeDetails");
		cq.select(cb.construct(DeoMasterCodeTypeDetails.class, root.get("id"), root.get("deoName"),
				root.get("district"),  root.get("active")));

	}

	private Root<DeoMasterCodeTypeDetails> getRootForDeoMasterCodeTypeDetails(
			CriteriaQuery<DeoMasterCodeTypeDetails> cq) {
		log.info("DeoMasterCodeDetailsCustomsRepoImpl::getRootForDeoMasterCodeTypeDetails");
		/*
		 * return
		 * DeoMasterCodeDetailsCustomsRepoImpl::getRootForDeoMasterCodeTypeDetails
		 */
		return cq.from(DeoMasterCodeTypeDetails.class);
	}

	private CriteriaQuery<DeoMasterCodeTypeDetails> getCreateQueryForSystem(CriteriaBuilder cb) {
		log.info("DeoMasterCodeDetailsCustomsRepoImpl::getCreateQueryForSystem");
		/* DeoMasterCodeDetailsCustomsRepoImpl::getCreateQueryForSystem */
		return cb.createQuery(DeoMasterCodeTypeDetails.class);
	}

}
