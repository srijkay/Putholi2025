package com.newrta.putholi.api.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.newrta.putholi.api.model.MailDTO;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
@Service
@Getter
public class EmailUtilService {

	/**
	 * 
	 */
	@Autowired(required = true)
	private JavaMailSender javaMailSender;

	/**
	 * 
	 */
	@Autowired
	Configuration fmConfiguration;

	/**
	 * 
	 */
	public EmailUtilService() {
		super();
	}

	/**
	 * @param javaMailSender
	 */
	public EmailUtilService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * @param mail
	 * @throws TemplateException, MessagingException
	 * @throws IOException
	 */
	public void sendMail(MailDTO mail) throws IOException, TemplateException, MessagingException {
		log.info("EmailUtilService::sendMail {}", mail);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setSubject(mail.getMailSubject());
		mimeMessageHelper.setFrom(mail.getMailFrom());
		mimeMessageHelper.setTo(mail.getMailTo());

		if (mail.getMailCc() != null) {
			String[] mailCcArray = mail.getMailCc();
			if (!"undefined".equals(mailCcArray[0])) {
				mimeMessageHelper.setCc(mail.getMailCc());
			}
		}

		mail.setMailContent(geContentFromTemplate(mail.getModel(), mail.getDocType()));
		mimeMessageHelper.setText(mail.getMailContent(), true);

		if (mail.getAttachments() != null) {
			Map<String, List<Object>> schoolattach = mail.getAttachments();

			List<Object> imageNames = schoolattach.get("imageNames");
			List<Object> schoolAttach = schoolattach.get("imageData");

			for (int i = 0; i < schoolAttach.size(); i++) {
				byte[] doc = Base64.getDecoder().decode(schoolAttach.get(i).toString());
				ByteArrayDataSource imageDataSource = new ByteArrayDataSource(doc, "image/png");
				mimeMessageHelper.addAttachment(imageNames.get(i).toString(), imageDataSource);
			}
		}
		
		if(mail.getAttachmentFilePath()!=null) {
			 FileSystemResource file = new FileSystemResource(new File(mail.getAttachmentFilePath()));
			 mimeMessageHelper.addAttachment(mail.getFileName(), file);
		}
		
		javaMailSender.send(mimeMessageHelper.getMimeMessage());

	}

	/**
	 * @param model
	 * @return
	 */
	public String geContentFromTemplate(Map<String, Object> model, String docType)
			throws IOException, TemplateException {
		log.info("EmailUtilService::geContentFromTemplate");

		StringBuilder content = new StringBuilder();
		content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(docType + ".ftl"),
				model));
		return content.toString();
	}

}
