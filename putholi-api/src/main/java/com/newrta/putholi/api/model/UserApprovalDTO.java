package com.newrta.putholi.api.model;

import java.util.List;

import com.newrta.putholi.api.domain.ApprovalHistoryDetails;
import com.newrta.putholi.api.domain.UserRegisterDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApprovalDTO {

    private UserRegisterDetails userRegisterDetails;

    private List<ApprovalHistoryDetails> approvalHistDtlsDTOs;

    private String pic;

}
