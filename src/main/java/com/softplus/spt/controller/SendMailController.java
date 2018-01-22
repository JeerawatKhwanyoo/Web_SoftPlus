package com.softplus.spt.controller;

import flexjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailController {
    private final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    Environment env;

    private JavaMailSender mailSender;
    private SimpleMailMessage simpleMailMessage;


    public void sendEmail(String content) {


        try {
            //Convert String to Json Obj First
            JSONObject joContent = new JSONObject(content);

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
                    return new PasswordAuthentication("inoncpe@gmail.com","inon2537");
                }
            });

//            String pathFile = env.getProperty("netgloo.paths.uploadedFiles");
//            pathFile = pathFile.replaceAll('\',"\\\\");
//            pathFile += "\\222.txt";

//            Message msg = new MimeMessage(session);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("inoncpe@gmail.com"));
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("inoncpe@gmail.com"));
            message.setSubject(joContent.get("name").toString()+" From: "+joContent.get("email").toString(),"UTF-8");
            message.setText(joContent.get("message").toString(),"UTF-8");




//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            FileSystemResource file = new FileSystemResource(new File("d:\\test.pdf"));
//            Transport.send(message);



            Multipart multipart = new MimeMultipart();
            Message msg = new MimeMessage(session);
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("your text");

            MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource("D:\\test.pdf"); // ex : "C:\\test.pdf"
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("test.pdf"); // ex : "test.pdf"
            multipart.addBodyPart(textBodyPart);  // add the text part
            multipart.addBodyPart(attachmentBodyPart); // add the attachement part
            msg.setContent(multipart);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
