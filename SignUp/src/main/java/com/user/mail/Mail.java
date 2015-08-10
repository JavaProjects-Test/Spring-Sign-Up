package com.user.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public boolean sendMail(String username, String token) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"bostaniaonline@gmail.com", "bostaniaonline123");
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bostaniaonline@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(username));
			message.setSubject("Bostania Online Sign Up");
			message.setContent(
					"<h1 style=color:blue> Bostania Online Service</h1><br><h3>Yoy have logged in to Bostania.com</h3><br>"
							+ "For create password <a href=http://localhost:8080/SignUp/serviceverification/"+token+">click here</a>"

					, "text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

}
