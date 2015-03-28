package com.daybook.util;
/*
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;*/
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.daybook.dto.MailDto;

public class Mail{
	/*private String host;
	private Properties properties;*/
	private Session session;
	private InternetAddress from;

	public Mail(){
		/*host = "mymail.server.org";
		properties = System.getProperties();
	    properties.setProperty("mail.smtp.host", host);
	    session = Session.getDefaultInstance(properties);*/

		final String username = "daybook12345@gmail.com";
		final String password = "DB12345XX";
		
		
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		try{
			from = new InternetAddress("DayBook"+"<no-reply@daybook.com>");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	}
	
	/*
	 * This method sends email to the user for the account activation
	 * This method takes mail DTO as parameter and set mailSend status true if email sent to user email.
	 */
	public void sendMail(MailDto mail){

	    try{	       
	       MimeMessage message = new MimeMessage(session);	// Create a default MimeMessage object.
	       message.setFrom(from);
	       message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
	       message.setSubject(mail.getSubject());
	       message.setText(mail.getMessage());
	       Transport.send(message);		// Send message
	       System.out.println("Activation email sent.");
	       mail.setMailStatus(true);
	       /*
	       Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("to-email@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");
			*/
	    }
	    catch (MessagingException e) {
	       e.printStackTrace();
	    }
	}

}
