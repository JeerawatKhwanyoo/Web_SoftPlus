package com.softplus.spt.controller;

import flexjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailController {

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

            String pathFile = env.getProperty("netgloo.paths.uploadedFiles");
//            pathFile = pathFile.replaceAll('\',"\\\\");
            pathFile += "\\222.txt";


            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("inoncpe@gmail.com"));
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("inoncpe@gmail.com"));
            message.setSubject(joContent.get("name").toString()+" From: "+joContent.get("email").toString(),"UTF-8");
            message.setText(joContent.get("message").toString(),"UTF-8");


            MimeMessageHelper helper = new MimeMessageHelper(message,true);

//            helper.setFrom(simpleMailMessage.getFrom());
//            helper.setTo(simpleMailMessage.getTo());
//            helper.setSubject(simpleMailMessage.getSubject());
//            helper.setText(String.format(
//                    simpleMailMessage.getText(), "inoncpe@gmail.com", content));

            FileSystemResource file = new FileSystemResource("D:\\222.txt");
            helper.addAttachment(file.getFilename(), file);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
