package com.newrta.putholi.api.listener;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.util.EmailUtilService;

import freemarker.template.TemplateException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@Slf4j
@Component
public class MailReceiver {

    /**
     * 
     */
    @Autowired(required = true)
    private EmailUtilService emailUtilService;

    /**
     * @param mailDTO
     */
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(MailDTO mailDTO) {
	log.info("MailReceiver-receiveMessage");
	try {
	    emailUtilService.sendMail(mailDTO);
	} catch (IOException ioe) {
	    log.error("MailReceiver-IOException {}", ioe);
	} catch (TemplateException txe) {
	    log.error("MailReceiver-TemplateException {}", txe);
	} catch (MessagingException mse) {
	    log.error("MailReceiver-MessagingException {}", mse);
	}

    }

}
