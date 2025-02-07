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

import com.newrta.putholi.api.customsrepo.RequirementInfoCustomRepo;
import com.newrta.putholi.api.domain.RequirementInfoDetails;
import com.newrta.putholi.api.model.RequirementSearchDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class RequirementInfoCustomRepoImpl implements RequirementInfoCustomRepo {

	private static final String REQUIREMENT_ID = "requirementId";

	/**
	* 
	*/
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<RequirementInfoDetails> searchRequirementInfo(RequirementSearchDTO requirementDTO) {

		// Criteria Builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// Criteria Query
		CriteriaQuery<RequirementInfoDetails> cq = cb.createQuery(RequirementInfoDetails.class);

		// from clause
		Root<RequirementInfoDetails> root = cq.from(RequirementInfoDetails.class);

		
		// Define criteria
		List<Predicate> predicates = new ArrayList<>();
		
		if (requirementDTO.getDistrict() != null && !requirementDTO.getDistrict().isEmpty()) {
			predicates.add(
					cb.like(cb.lower(root.get("district")), "%" + requirementDTO.getDistrict().toLowerCase() + "%"));
		}
		
		
		if (requirementDTO.getSchoolName() != null && !requirementDTO.getSchoolName().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("schoolName")), "%" + requirementDTO.getSchoolName().toLowerCase() + "%"));
		}

		if (requirementDTO.getReqStatusCode() != null && !requirementDTO.getReqStatusCode().isEmpty()) {
			predicates.add(root.get("reqStatusCode").in(requirementDTO.getReqStatusCode()).not());

		}

		if (requirementDTO.getConsolidateStatus() != null && !requirementDTO.getConsolidateStatus().isEmpty()) {
			predicates.add(root.get("consolidateStatus").in(requirementDTO.getConsolidateStatus()).not());
		}

		if (requirementDTO.getReqStatus() != null && !requirementDTO.getReqStatus().isEmpty()) {
			predicates.add(root.get("reqStatusCode").in(requirementDTO.getReqStatus()));
		}

		if (requirementDTO.getActive() != null && !requirementDTO.getActive().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("active")), requirementDTO.getActive().toLowerCase()));
		}

		if (requirementDTO.getStatus() != null && !requirementDTO.getStatus().isEmpty()) {
			Predicate consolidateStatus = cb.equal(cb.lower(root.get("status")),
					requirementDTO.getStatus().toLowerCase());

			Predicate requirementStatus = cb.equal(cb.lower(root.get("reqStatusCode")),
					requirementDTO.getStatus().toLowerCase());

			predicates.add(cb.or(consolidateStatus, requirementStatus));

		}

		if (requirementDTO.getLoggedUser() != null && !requirementDTO.getLoggedUser().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("volunteerName")), requirementDTO.getLoggedUser().toLowerCase()));
		}

		if (requirementDTO.getCreatedBy() != null && !requirementDTO.getCreatedBy().isEmpty()) {
			predicates.add(cb.equal(cb.lower(root.get("createdBy")), requirementDTO.getCreatedBy().toLowerCase()));
		}

		if (requirementDTO.getLocality() != null && !requirementDTO.getLocality().isEmpty()) {
			predicates.add(
					cb.like(cb.lower(root.get("locality")), "%" + requirementDTO.getLocality().toLowerCase() + "%"));
		}

		

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(REQUIREMENT_ID)));

		// execute the query
		TypedQuery<RequirementInfoDetails> typedQuery = entityManager.createQuery(cq);

		// implementation Paginations
		List<RequirementInfoDetails> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (requirementDTO.getPageNumber() > 0 && requirementDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((requirementDTO.getPageNumber() - 1) * requirementDTO.getPageSize());
			typedQuery.setMaxResults(requirementDTO.getPageSize());
		}
		Pageable pageable = PageRequest.of(requirementDTO.getPageNumber() - 1, requirementDTO.getPageSize(),
				Sort.by(REQUIREMENT_ID).descending());

		// Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
