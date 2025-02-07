package com.newrta.putholi.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.repository.AuditDetailsRepository;
import com.newrta.putholi.api.service.AuditDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class AuditDetailsServiceImpl implements AuditDetailsService {

    /**
     * 
     */
    @Autowired(required = true)
    AuditDetailsRepository auditDetailsRepository;

    /**
     *
     */
    @Override
    public AuditDetails saveAuditDetails(AuditDetails auditDetails) {
	log.info("::AuditDetailsServiceImpl::saveAuditDetails::");
	return auditDetailsRepository.save(auditDetails);
    }

}
