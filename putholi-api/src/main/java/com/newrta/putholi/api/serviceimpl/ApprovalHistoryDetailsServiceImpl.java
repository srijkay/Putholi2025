package com.newrta.putholi.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ApprovalHistoryDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.repository.ApprovalHistoryDetailsRepository;
import com.newrta.putholi.api.service.ApprovalHistoryDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class ApprovalHistoryDetailsServiceImpl implements ApprovalHistoryDetailsService {
    
    /**
     * 
     */
    @Autowired(required = true)
    private ApprovalHistoryDetailsRepository approvalHistDtlsRepo;

    /**
     *
     */
    @Override
    public ApiResultDTO saveApprovalHistoryDetails(ApprovalHistoryDetails approvalHistDtls) {
	log.info("ApprovalHistoryDetailsServiceImpl-saveApprovalHistoryDetails");

	approvalHistDtlsRepo.save(approvalHistDtls);
	return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Approval Details Saved Successfully!").build();
    }

    /**
     *
     */
    @Override
    public List<ApprovalHistoryDetails> fetchApprovalHistoryDetails(String username, String type) {
	log.info("ApprovalHistoryDetailsServiceImpl-fetchApprovalHistoryDetails");
	return approvalHistDtlsRepo.findByUsernameAndType(username, type);
    }

}
