package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newrta.putholi.api.domain.RequirementInfoDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface RequirementViewRepository extends JpaRepository<RequirementInfoDetails, Long> {

}
