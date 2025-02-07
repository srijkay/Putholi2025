package com.newrta.putholi.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.domain.AuditDetails;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Data
@Slf4j
public class CommonQueueUtilService {

    /**
     * 
     */
    @Autowired(required = true)
    private JmsTemplate jmsTemplate;

    /**
     * @param audit
     */
    public void sendAuditDetailsToQueue(AuditDetails auditDetails) {
	log.info("CommonQueueUtilService::sendAuditDetailsToQueue");
	jmsTemplate.convertAndSend("auditbox", auditDetails);
    }

}
