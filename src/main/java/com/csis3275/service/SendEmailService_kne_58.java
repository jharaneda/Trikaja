package com.csis3275.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailService_kne_58{
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail_kne_58(String toAddress, String body, String Subject) {
		
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setFrom("trikajahelp@gmail.com");
		emailMessage.setSubject(Subject);
		emailMessage.setTo(toAddress);
		emailMessage.setText(body);
		
		mailSender.send(emailMessage);
	}
}
