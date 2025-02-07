package com.newrta.putholi.api.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ContactUsInfoDTO;
import com.newrta.putholi.api.model.MailDTO;
import com.newrta.putholi.api.service.ContactUsService;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SivaSreenivas
 *
 */
@Service
@Data 
@NoArgsConstructor
@Slf4j
public class ContactUsServiceImpl implements ContactUsService {

	/**
	 * 
	 */
	@Value("${mail.sent.from}")
	private String mailFrom;

	/**
	 * 
	 */
	@Value("${mail.support}")
	private String support;

	/**
	 * 
	 */
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	/**
	 *
	 */
	@Override
	public ApiResultDTO sendContactUsFeedback(ContactUsInfoDTO contactUsInfoDTO, String locale) {
		log.info("ContactUsServiceImpl-sendContactUsFeedback");
		String mailToId = support;
		Map<String, Object> model = new HashMap<>();

		model.put("contactname", contactUsInfoDTO.getContactName());
		model.put("contactemail", contactUsInfoDTO.getContactEmail());
		model.put("contactnumber", contactUsInfoDTO.getPhoneNumber());
		model.put("comment", contactUsInfoDTO.getContactComment());
		jmsTemplate.convertAndSend("mailbox",
				new MailDTO(mailFrom, mailToId, contactUsInfoDTO.getSubject(), "contactus", model));

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Thanks For the Info/Details, Will get back to you shortly").build();
	}

}
