package com.newrta.putholi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.ApprovalHistoryDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface ApprovalHistoryDetailsRepository extends JpaRepository<ApprovalHistoryDetails, Long> {

    /**
     * @param username
     * @return
     */
    List<ApprovalHistoryDetails> findByUsernameAndType(String username, String type);
   

}
