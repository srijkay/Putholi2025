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

import com.newrta.putholi.api.customsrepo.UserRegisterViewCustomRepo;
import com.newrta.putholi.api.domain.UserRegisterInfoDetails;
import com.newrta.putholi.api.model.UserRegisterViewDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class UserRegisterViewCustomRepoImpl implements UserRegisterViewCustomRepo {

	/**
	* 
	*/
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<UserRegisterInfoDetails> searchUserInfo(UserRegisterViewDetailsDTO userInfoDTO) {

		// Criteria Builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// Criteria Query
		CriteriaQuery<UserRegisterInfoDetails> cq = cb.createQuery(UserRegisterInfoDetails.class);

		// from clause
		Root<UserRegisterInfoDetails> root = cq.from(UserRegisterInfoDetails.class);

		// Define criteria
		List<Predicate> predicates = new ArrayList<>();

		if (userInfoDTO.getCity() != null) {
			predicates.add(cb.equal(cb.lower(root.get("city")), userInfoDTO.getCity().toLowerCase()));
		}

		if (userInfoDTO.getFeeType() != null) {
			predicates.add(cb.equal(cb.lower(root.get("feeType")), userInfoDTO.getFeeType().toLowerCase()));
		}

		if (userInfoDTO.getAmount() != null) {
			predicates.add(cb.equal((root.get("amount")), userInfoDTO.getAmount()));
		}

		if (userInfoDTO.getFromDate() != null && userInfoDTO.getTodate() != null) {
			predicates.add(cb.between(root.get("createdDate"), userInfoDTO.getFromDate(), userInfoDTO.getTodate()));
		}

		cq.where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get("createdDate")));
		// execute the query
		TypedQuery<UserRegisterInfoDetails> typedQuery = entityManager.createQuery(cq);

		// implementation Paginations
		List<UserRegisterInfoDetails> resultList = typedQuery.getResultList();

		int totalRecords = resultList.size();

		if (userInfoDTO.getPageNumber() > 0 && userInfoDTO.getPageSize() > 0) {
			typedQuery.setFirstResult((userInfoDTO.getPageNumber() - 1) * userInfoDTO.getPageSize());
			typedQuery.setMaxResults(userInfoDTO.getPageSize());
		}
		Pageable pageable = PageRequest.of(userInfoDTO.getPageNumber() - 1, userInfoDTO.getPageSize(),
				Sort.by("createdDate").descending());

		// Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
