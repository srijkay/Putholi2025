package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.VerificationToken;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    /**
     * @param token
     * @return
     */
    VerificationToken findByToken(String token);

}
