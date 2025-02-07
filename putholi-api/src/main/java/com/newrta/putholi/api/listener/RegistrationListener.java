package com.newrta.putholi.api.listener;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.domain.UserLogin;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.model.UserVerificationTokenDTO;
import com.newrta.putholi.api.service.VerificationTokenService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Data
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	private static final int EXPIRATION = 60 * 24;

	/**
	 * 
	 */
	@Value("${app.url}")
	private String appUrl;

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Autowired(required = true)
	private VerificationTokenService verificationTokenService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	/**
	 * @param event
	 */
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		log.info("RegistrationListener-confirmRegistration");

		try {
			UserLogin user = event.getUser();
			String token = UUID.randomUUID().toString();
			verificationTokenService.createVerificationToken(user, token, EXPIRATION);

			token = new ObjectMapper().writeValueAsString(new UserVerificationTokenDTO(token, user.getLocale()));

			log.info("encrypted token -=-=-=-> {}", token);

			String confirmationUrl = appUrl + event.getAppUrl() + "/verify-account?token="
					+ Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));

			Map<String, Object> model = new HashMap<>();
			model.put("username", user.getUserName());
			model.put("confirmurl", confirmationUrl);
			model.put("clickhere",
					LocaleConfig.getResourceValue("user.verify.email.clickhere", null, user.getLocale(), null));

			jmsTemplate.convertAndSend("mailbox",
					new MailDTO(mailFrom, user.getEmailId(),
							LocaleConfig.getResourceValue("user.verify.email.subject", null, user.getLocale(), null),
							"email-verify", model));

			log.info("confirmation url -=-=-=-> {}", confirmationUrl);
		} catch (JsonProcessingException jse) {
			log.error("confirmRegistration-JsonProcessingException {} {}", jse.getCause(), jse);
		} catch (Exception exe) {
			log.error("confirmRegistration-Exception {} {}", exe.getCause(), exe);
		}
	}

}
