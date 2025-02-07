package com.newrta.putholi.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.service.AuditDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Component
@Data
@Slf4j
public class AuditListener {
    
    /**
     * 
     */
    @Autowired(required = true)
    private AuditDetailsService auditService;

    /**
     * @param auditDetails
     */
    @JmsListener(destination = "auditbox", containerFactory = "myFactory")
    public void receiveMessage(AuditDetails auditDetails) {
        log.info("AuditReceiver::receiveMessage::auditbox");
        auditService.saveAuditDetails(auditDetails);
    }

}
