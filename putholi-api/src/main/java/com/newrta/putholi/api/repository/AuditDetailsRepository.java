package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.AuditDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface AuditDetailsRepository extends JpaRepository<AuditDetails, Long> {

    /**
     * @param auditId
     * @return
     */
    AuditDetails findByAuditId(Long auditId);

}
