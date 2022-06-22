package com.agpro.controles;


import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import java.util.Properties;



	public class smtpExchange {

//		private void sendEmail(String to) {
//	       try {
//	           // all values as variables to clarify its usage
//	           InputStream inputStream = getClass().getResourceAsStream("/dock-magazine.pdf");
//	           String from = "sender@test.com";
//	           String subject = "Your PDF";
//	           String text = "Here there is your <b>PDF</b> file!";
//	           String fileName = "file.pdf";
//	           String mimeType = "application/pdf";
//	           String para="seba.alvarez@gmail.com";
//	           to=para;
//	           CommonsEmailService.send(from, to, subject, text, inputStream, fileName, mimeType);
//	           Notification.show("Email sent");
//	 
//	       } catch (Exception  e) {
//	           e.printStackTrace();
//	           Notification.show("Error sending the email"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
//	       }
//
//	   }
	
	String user_auth = "ambiente.salta@gmail.com";
	String user_password = "magxgmxsjhctfaku";
	
	public void SendMail() {


		String host = "smtp.gmail.com";
		String port = "465";
		
		// String from = "testbrg@codespring.ro";
		String from = "ambiente.salta@gmail.com";
		String to = "seba.alvarez@gmail.com";
		String Subject = "Hello Word";
		String MessageText = "Mensajito con Java Mail<br>" + "<b>de</b> los <i>buenos</i>. " + "poque si";
		
		
		Properties mailProp = new Properties();
		
		mailProp.setProperty("mail.user", user_auth);
		mailProp.setProperty("mail.password", user_password);
		
		
		mailProp.put("mail.smtp.host", host);
		mailProp.put("mail.smtp.port", port);
		/*
		 * Itt jonnek a TLS beallitasok
		 */
		mailProp.put("mail.smtp.starttls.enable", "true");
		mailProp.put("mail.smtp.auth", "true");
		mailProp.put("mail.transport.protocol","smtp");
		mailProp.put("mail.smtp.socketFactory.port", port);
		mailProp.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		mailProp.put("mail.smtp.socketFactory.fallback", "false");

		// Optionalisan debug
		mailProp.put("mail.debug", "true");

		try {
			/*
			 * Az Autentikalast ez a fuggveny vegzi el. Tulajdonkeppen a
			 * beepitet fuggvenyt a JavaMail lib-bol irjuk felul, hogy nekunk
			 * dolgozzon :D Majd a vegen az Instancot ezzel toltjuk fel.
			 */
			
//			Session mailSession = Session.getInstance(mailProp,null);
//			Message msg1 = new MimeMessage(mailSession);
//			msg1.setSubject("Mensaje de L�nea de C�digo");
//			msg1.setFrom(new InternetAddress("ambiente.salta@gmail.com","L�nea de C�digo"));
//			msg1.addRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress("seba.alvarez@gmail.com") });
//			
//			
//			DataHandler dh = new DataHandler("Texto del mensaje","text/plain");
//			msg1.setDataHandler(dh);
//			javax.mail.Transport.send(msg1);
			
			
			
			Authenticator myAuth = new MyAuthenticator();
			Session session = Session.getDefaultInstance(mailProp, myAuth);

			// Szepen lehet debugolni :D
			session.setDebug(true);

//			Message msg = new MimeMessage(mailSession);
			
			MimeMessage mymsg = new MimeMessage(session);
			
			mymsg.setSubject(Subject);
			mymsg.setFrom(new InternetAddress(from));
			mymsg.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
			
			
			// Texto Simple
			mymsg.setText(MessageText,"ISO-8859-1","html");
			
			
//			// Texto complejo
//			
//			// Texto del mensaje
//			BodyPart texto = new MimeBodyPart();
//			texto.setText("Mensajito con Java Mail<br>" + "<b>de</b> los <i>buenos</i>." + "poque si");
//			
//			
//			// Cargamos la imagen
//			BodyPart adjunto = new MimeBodyPart();
//			adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\Seba\\Pictures\\libroAbiertoA.jpg")));
//
//			// Opcional. De esta forma transmitimos al receptor el nombre original del
//			// fichero de imagen.
//			adjunto.setFileName("libroAbiertoA.jpg");
//			
//			
//			MimeMultipart multiParte = new MimeMultipart();
//			multiParte.addBodyPart(texto);
//			multiParte.addBodyPart(adjunto);
//
//
//			// Se mete el texto y la foto adjunta.
//			mymsg.setContent(multiParte);
			
			
			
//			Transport t = session.getTransport("smtp");
//
//			// Aqui usuario y password de gmail
//			t.connect("chuidiang@gmail.com","la password");
//			t.sendMessage(mymsg,mymsg.getAllRecipients());
//			t.close();
//			
			
			Transport.send(mymsg);
			session.getDebugOut();

		} catch (Exception e) {
			Notification.show("Se produjo un error al enviar un mail ",e.getMessage(),Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private class MyAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user_auth, user_password);
		}
	}

}
