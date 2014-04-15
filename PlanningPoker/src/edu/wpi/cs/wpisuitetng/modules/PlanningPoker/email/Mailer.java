/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributor: team struct-by-lightning
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * A Mailer manages the sending of emails to email addresses added to the
 * Mailer. The Mailer operates in two modes, normal and debug.
 * 
 * In normal mode, emails are routed through the Google SMTP server via the
 * team's gmail account (struct.by.lightning@gmail.com), and the emails actually
 * reach their recipient.
 * 
 * In debug mode, the emails are instead routed to the Mailtrap.io SMTP server,
 * where they are placed into an inbox accessible accessible to the team. This
 * prevents unnecessary spam during testing.
 * 
 * 
 * 
 * @author Alec Thompson
 * @author Long Nguyen
 * @version $Revision: 1.0 $
 */
public class Mailer {
	private Session session;
	private MimeMessage message;
	private Transport transport;

	// test information
	private String host = "mailtrap.io";
	private String login = "structbylightning-17dcc3f2b944376c";
	private String pass = "ce1a24cb171342c3";
	
	// TODO: Create a wpi-suite email address and mailtrap account that we can give people access to in the documentation.

	private final boolean DEBUG = true;

	public Mailer() {
		session = createSmtpSession();
		session.setDebug(true);
		message = new MimeMessage(session);

		try {
			// testing
			if (DEBUG)
				transport = session.getTransport("smtp");
			// release
			String from = "struct.by.lightning@gmail.com";

			// set the message to be from struct by lightning
			message.setFrom(new InternetAddress(from));

			// set the header line
			message.setSubject("Great news ladies and gentlemen!");
			message.setText("Our favorite past time in which we predict the effort in which we must exert our fingers and minds has begun anew! I request that all ye whom have felt the stern blow of Thor's mighty hammer attend the session, so we may reach a general consensus!");
		} catch (MessagingException mex) {
			System.out.println("Message creation failed");
			mex.printStackTrace();
		}
	}

	/**
	 * Alternate constructor that takes an email as a parameter
	 * 
	 * @param emailAddress
	 *            Hard-coded email address
	 */
	public Mailer(String emailAddress) {
		session = createSmtpSession();
		session.setDebug(true);
		message = new MimeMessage(session);

		try {
			// testing
			if (DEBUG)
				transport = session.getTransport("smtp");
			// release
			String from = "struct.by.lightning@gmail.com";

			// set the message to be from struct by lightning
			message.setFrom(new InternetAddress(from));

			// set the header line
			message.setSubject("Great news ladies and gentlemen!");
			message.setText("Our favorite past time in which we predict the effort in which we must exert our fingers and minds has begun anew! I request that all ye whom have felt the stern blow of Thor's mighty hammer attend the session, so we may reach a general consensus!");
			this.addEmail(emailAddress);
		} catch (MessagingException mex) {
			System.out.println("Message creation failed");
			mex.printStackTrace();
		}
	}

	/**
	 * Alternate constructor that takes an array of email addresses as a
	 * parameter
	 * 
	
	 * @param emailArray String[]
	 */
	public Mailer(String[] emailArray) {
		session = createSmtpSession();
		session.setDebug(true);
		message = new MimeMessage(session);

		try {
			if (DEBUG)
				transport = session.getTransport("smtp");
			String from = "struct.by.lightning@gmail.com";

			// set the message to be from struct by lightning
			message.setFrom(new InternetAddress(from));

			// set the header line
			message.setSubject("Great news ladies and gentlemen!");
			message.setText("Our favorite past time in which we predict the effort in which we must exert our fingers and minds has begun anew! I request that all ye whom have felt the stern blow of Thor's mighty hammer attend the session, so we may reach a general consensus!");
			this.addEmailArray(emailArray);
		} catch (MessagingException mex) {
			System.out.println("Message creation failed");
			mex.printStackTrace();
		}
	}

	/**
	 * Creates an SMTP session, configured for using the Google SMTP server with
	 * the Struct by Lightning gmail account being used to send the emails
	 * 
	
	 * @return Google SMTP session */
	private Session createSmtpSession() {
		// final version settings
		if (DEBUG) {
			// test with mailtrap
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", login);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "2525");
			props.put("mail.smtp.auth", "true");

			return Session.getDefaultInstance(props);
		} else {
			final Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.port", "" + 587);
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.transport.protocol", "smtp");

			return Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"struct.by.lightning@gmail.com",
									"Donthackthis!12358");
						}
					});
		}
	}

	/**
	 * Sends the email to the added recipients
	 * 
	
	 * @return true if the email sends, false otherwise */
	public boolean send() {
		try {
			if (DEBUG)
				transport.connect(host, login, pass);
			// send the message
			System.out.println("Ready to send message");
			// testing
			if (DEBUG) {
				transport.sendMessage(message, message.getAllRecipients());
			} else { // release
				Transport.send(message);
			}
			System.out.println("Sent message successfully");
			if (DEBUG)
				transport.close();
			return true;
		} catch (MessagingException mex) {
			System.out.println("Send failed");
			mex.printStackTrace();
			return false;
		}
	}

	/**
	 * Adds a recipient to the Mailer object
	 * 
	 * @param recipient
	 *            The target's email address
	
	 * @return true if the recipient is added, false otherwise */
	public boolean addEmail(String recipient) {
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recipient));
			return true;
		} catch (MessagingException mex) {
			System.out.println("Recipient not added");
			mex.printStackTrace();
			return false;
		}
	}

	/**
	 * Adds multiple recipients to the mailer object from an array
	 * 
	 * @param recArr
	 *            Array of email addresses (strings!)
	
	 * @return true if all addresses were added successfully, false otherwise */
	public boolean addEmailArray(String[] recArr) {
		boolean isSuccess = true;
		for (String s : recArr) {
			isSuccess &= this.addEmail(s);
		}
		return isSuccess;
	}

	/**
	 * Method addEmailFromUsers.
	 * @param userList List<User>
	
	 * @return boolean */
	public boolean addEmailFromUsers(List<User> userList) {
		boolean isSuccess = true;
		for (User u : userList) {
			if (u.getEmail() != null)
				isSuccess &= this.addEmail(u.getEmail());
		}
		return isSuccess;
	}
}
