package com.newrta.putholi.api.model;

import java.util.List;

import com.newrta.putholi.api.domain.ApprovalHistoryDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalHistoryDetailsList {
    /**
     * 
     */
    private List<ApprovalHistoryDetails> approvalHistoryDetailsDTOs;

}
