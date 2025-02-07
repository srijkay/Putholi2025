package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.DonorInfoViewCustomRepo;
import com.newrta.putholi.api.domain.DonorInfoDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface DonorInfoViewRepository extends JpaRepository<DonorInfoDetails, String>, DonorInfoViewCustomRepo {

}
