package com.csis3275.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import com.csis3275.service.SendEmailService_kne_58;

class EmailServiceTest {
	private SendEmailService_kne_58 mailService = mock(SendEmailService_kne_58.class);
	private static MimeMessage mimeMessage = new MimeMessage((Session)null);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	void testEmail() throws MessagingException {
		
			mailService.sendEmail_kne_58("example@example.com", "hello", "hi");
			assertEquals("example@example.com", mimeMessage.getRecipients(RecipientType.TO)[0].toString());
	}

}
