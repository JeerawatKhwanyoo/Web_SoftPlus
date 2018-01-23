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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMailController {
    private final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    Environment env;

    JavaMailSender javaMailSender;

    private JavaMailSender mailSender;
    private SimpleMailMessage simpleMailMessage;


    public void sendEmail(String name,String email,String msg,String content) throws JSONException {


//        try {
//            //Convert String to Json Obj First
//            JSONObject joContent = new JSONObject(content);
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("inoncpe@gmail.com"));
//            message.setHeader("Content-Type", "text/html; charset=UTF-8");
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("inoncpe@gmail.com"));
//            message.setSubject(joContent.get("name").toString()+" From: "+joContent.get("email").toString(),"UTF-8");
//            message.setText(joContent.get("message").toString(),"UTF-8");
//            message.setContent(joContent.get("message").toString(),"UTF-8");



        final String auth_host = "mail.thaicreate.com";
        final String auth_port = "25";
        final String auth_email = "no-reply@thaicreate.com";
        final String auth_password = "password";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        try {

            LOGGER.info("start session for send mail.");
            Session mailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication
                        getPasswordAuthentication() {
                            return new PasswordAuthentication
                                    ("inoncpe@gmail.com","inon2537");
                        }
                    });

            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress("inoncpe@gmail.com")); // From
            LOGGER.info("set mail sender");

            /*** Recipient ***/
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("khwanyoo.ns@gmail.com")); // To
            message.setSubject(name + " From: " + email,"UTF-8");
            message.setContent(msg,"UTF-8");
            message.setText(msg,"UTF-8");
            message.setDescription(msg,"UTF-8");

//            siriradc64@gmail.com
//


            // create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            String  Attach = content;

            //fill message
            messageBodyPart.setText("");
            LOGGER.info("set mail content");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(Attach);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(content);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            LOGGER.info("set file atachment.");

            Transport.send(message);

            LOGGER.info("Mail Send Successfully.");

            Files.delete(new File(content).toPath());

            LOGGER.info("delete file name : {} success.",content);
            LOGGER.info("------------------------------------------");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
