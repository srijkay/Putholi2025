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

import com.newrta.putholi.api.customsrepo.AnnouncementDetailsCustomRepo;
import com.newrta.putholi.api.domain.AnnouncementDetails;
import com.newrta.putholi.api.model.AnnouncementDetailsDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class AnnouncementDetailsCustomRepoImpl implements AnnouncementDetailsCustomRepo {

	/**
	* 
	*/
	@PersistenceContext
	EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Page<AnnouncementDetails> searchAnnouncemnetInfo(AnnouncementDetailsDTO announcementdetailsDTO) {

		// Criteria Builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// Criteria Query
		CriteriaQuery<AnnouncementDetails> cq = cb.createQuery(AnnouncementDetails.class);

		// from clause
		Root<AnnouncementDetails> root = cq.from(AnnouncementDetails.class);

		// projections
		cq.select(cb.construct(AnnouncementDetails.class, root.get("announcementId"), root.get("announcementRole"),
				root.get("effectiveDate"), root.get("expiryDate"), root.get("announcementDescription")));

		// Define criteria
		if (announcementdetailsDTO.getAnnouncementRole() != null
				&& !announcementdetailsDTO.getAnnouncementRole().isEmpty()) {
			Predicate announcementRolePredicate = cb.like(cb.lower(root.get("announcementRole")),
					"%" + announcementdetailsDTO.getAnnouncementRole().toLowerCase() + "%");

			cq.where(cb.or(announcementRolePredicate)).orderBy(cb.desc(root.get("announcementId")));
		}

		// execute the query
		TypedQuery<AnnouncementDetails> typedQuery = entityManager.createQuery(cq);

		// implementation Paginations
		List<AnnouncementDetails> resultList = typedQuery.getResultList();
		int totalRecords = resultList.size();

		if (announcementdetailsDTO.getPageNumber() > 0 && announcementdetailsDTO.getPageSize() > 0) {
			typedQuery.setFirstResult(
					(announcementdetailsDTO.getPageNumber() - 1) * announcementdetailsDTO.getPageSize());
			typedQuery.setMaxResults(announcementdetailsDTO.getPageSize());
		}
		Pageable pageable = PageRequest.of(announcementdetailsDTO.getPageNumber() - 1,
				announcementdetailsDTO.getPageSize(), Sort.by("announcementId").descending());

		// Page Interface
		return new PageImpl<>(typedQuery.getResultList(), pageable, totalRecords);
	}

}
