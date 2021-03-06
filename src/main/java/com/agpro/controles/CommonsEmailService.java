package com.agpro.controles;


//import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.MessagingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;



public class CommonsEmailService {


	    /**
	     * Sends an email message with no attachments.
	     *
	     * @param from       email address from which the message will be sent.
	     * @param recipients the recipients of the message.
	     * @param subject    subject header field.
	     * @param text       content of the message.
	     * @throws MessagingException
	     * @throws IOException
	     */
	    public static void send(String from, Collection<String> recipients, String subject, String text)
	            throws IOException, EmailException {
	        send(from, recipients, subject, text, null, null, null);
	    }

	    /**
	     * Sends an email message to one recipient with one attachment.
	     *
	     * @param from       email address from which the message will be sent.
	     * @param recipient  the recipients of the message.
	     * @param subject    subject header field.
	     * @param text       content of the message.
	     * @param attachment attachment to be included with the message.
	     * @param fileName   file name of the attachment.
	     * @param mimeType   mime type of the attachment.
	     * @throws MessagingException
	     * @throws IOException
	     */
	    public static void send(String from, String recipient, String subject, String text, InputStream attachment,
	                            String fileName, String mimeType)
	            throws IOException, EmailException {
	        send(from, Arrays.asList(recipient), subject, text, Arrays.asList(attachment), Arrays.asList(fileName),
	                Arrays.asList(mimeType));
	    }

	    /**
	     * Sends an email message with attachments.
	     *
	     * @param from        email address from which the message will be sent.
	     * @param recipients  array of strings containing the recipients of the message.
	     * @param subject     subject header field.
	     * @param text        content of the message.
	     * @param attachments attachments to be included with the message.
	     * @param fileNames   file names for each attachment.
	     * @param mimeTypes   mime types for each attachment.
	     * @throws MessagingException
	     * @throws IOException
	     */
	    public static void send(String from, Collection<String> recipients, String subject, String text,
	                            List<InputStream> attachments, List<String> fileNames, List<String> mimeTypes)
	            throws EmailException, IOException {

	        // check for null references
	        Objects.requireNonNull(from);
	        Objects.requireNonNull(recipients);

	        
	        
	        // load email configuration from properties file
//	        Properties properties = new Properties();
//	        properties.load(CommonsEmailService.class.getResourceAsStream("/mail.properties"));
//	        String host = properties.getProperty("smtp.gmail.com");
//	        String port = properties.getProperty("465");
//	        String ssl = properties.getProperty("true");
//	        String username = properties.getProperty("ambiente.salta@gmail.com");
//	        String password = properties.getProperty("sebastian$1");

	        
	        System.out.println("Antes de configure SMTP connection");
	        // create an email message with html support
	        HtmlEmail email = new HtmlEmail();

	        // configure SMTP connection
	        email.setHostName("smtp.gmail.com");
	        email.setSmtpPort(Integer.parseInt("465"));
	        email.setAuthentication("ambiente.salta@gmail.com", "sebastian$1");
	        email.setSSLOnConnect(Boolean.parseBoolean("true"));

	        // set its properties accordingly
	        email.setFrom(from);
	        email.addTo(recipients.toArray(new String[]{}));
	        email.setSubject(subject);
	        email.setHtmlMsg(text);
	        

	        
System.out.println("Antes de adjuntar");
//	        if (attachments != null) {
//	            for (int i = 0; i < attachments.size(); i++) {
//	                // create a data source to wrap the attachment and its mime type
//	                ByteArrayDataSource dataSource = new ByteArrayDataSource(attachments.get(i), mimeTypes.get(i));
//
//	                // add the attachment
//	                email.attach(dataSource, fileNames.get(i), "attachment");
//	            }
//	        }

	        // send it!
	        email.send();
	    }

	
}
