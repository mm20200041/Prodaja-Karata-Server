/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
/**
 *
 * @author marko
 */
public class EmailSender {


    private static final String SMTP_HOST = "smtp-relay.brevo.com";
    private static final int SMTP_PORT = 587;

    private static final String SMTP_USER = "9c700b001@smtp-brevo.com"; 
    private static final String SMTP_PASS = konfiguracija.Konfiguracija.getInstanca().getProperty("smtp.pass");


    public static void posaljiMailSaPDF(
            String to,
            String subject,
            String content,
            List<File> pdfs
    ) throws Exception {

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", SMTP_HOST);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("marko.misovc45@gmail.com", "KK Partizan Tickets"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(content, "UTF-8");
        multipart.addBodyPart(textPart);

        for (File f : pdfs) {
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(f);
            multipart.addBodyPart(attachment);
        }

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Email poslat.");
    }
}


