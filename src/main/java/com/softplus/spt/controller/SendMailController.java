package com.softplus.spt.controller;

import com.softplus.spt.constant.ConstantVariableUtil;
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



        Properties props = new Properties();

        props.put("mail.smtp.host", ConstantVariableUtil.MAIL_SMTP_HOST);
        props.put("mail.smtp.socketFactory.port", ConstantVariableUtil.MAIL_SMTP_SOCKETFACTORY_PORT);
        props.put("mail.smtp.socketFactory.class",
                ConstantVariableUtil.MAIL_SMTP_SOCKETFACTORY_CLASS);
        props.put("mail.smtp.auth", ConstantVariableUtil.MAIL_SMTP_AUTH);
        props.put("mail.smtp.port", ConstantVariableUtil.MAIL_SMTP_PORT);

        try {

            LOGGER.info("start session for send mail.");
            Session mailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication
                        getPasswordAuthentication() {
                            return new PasswordAuthentication
                                    (ConstantVariableUtil.SENDER_MAIL_USERNAME,ConstantVariableUtil.SENDER_MAIL_PASSWORD);
                        }
                    });

            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress("inoncpe@gmail.com")); // From
            LOGGER.info("set mail sender");

            /*** Recipient ***/
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ConstantVariableUtil.RECIEVER_MAIL)); // To
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(ConstantVariableUtil.CC_MAIL));
            message.setSubject(ConstantVariableUtil.TITLE_MAIL,"UTF-8");
            message.setContent(msg,"UTF-8");
            message.setText(msg,"UTF-8");
            message.setDescription(msg,"UTF-8");

//            siriradc64@gmail.com
//


            // create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            String  Attach = content;

            //fill message
//            messageBodyPart.setText(name+'<br/>'+email,"UTF-8");
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
