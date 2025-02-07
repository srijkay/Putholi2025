package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.CompletionofRequirements;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository

public interface CompletionofRequirementsRepository extends JpaRepository<CompletionofRequirements, Long> {

	CompletionofRequirements findBycompletionId(Long completionId);
}
