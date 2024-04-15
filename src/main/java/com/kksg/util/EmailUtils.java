package com.kksg.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	
	public boolean sendEmail(String subject, String to, String body, File file) {
		
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.addTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			helper.addAttachment("plans", file);
			
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
