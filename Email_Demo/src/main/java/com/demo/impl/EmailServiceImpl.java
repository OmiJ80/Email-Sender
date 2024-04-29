package com.demo.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.*;



@Service
public class EmailServiceImpl implements EmailService  {

	private JavaMailSender mailSender;
	
	private   Logger logger=LoggerFactory.getLogger(EmailServiceImpl.class);
	
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("omijadhav6080@gmail.com");
		 mailSender.send(simpleMailMessage);
		 logger.info("Email has been sent");
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
	
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("omijadhav6080@gmail.com");
		mailSender.send(simpleMailMessage);
    
	
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		
		MimeMessage simpleMailMessage=mailSender.createMimeMessage();
		
		
		try {
			MimeMessageHelper helper =new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("omijadhav6080@gmail.com");
			helper.setText(htmlContent,true);
			
			 mailSender.send(simpleMailMessage);
			 logger.info("Email has been sent");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
			helper.setFrom("omijadhav6080@gmail.com");
			helper.setTo(to);
			helper.setText(message);
			helper.setSubject(subject);
			FileSystemResource fileSystemResource=new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(),file);
			mailSender.send(mimeMessage);
			logger.info("email send success ");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
		
		
MimeMessage mimeMessage=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
			helper.setFrom("omijadhav6080@gmail.com");
			helper.setTo(to);
			helper.setText(message);
			helper.setSubject(subject);
			File file =new File("bg.png");
			try {
				Files.copy(is,file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileSystemResource fileSystemResource=new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(),file);
			
			mailSender.send(mimeMessage);
			logger.info("email send success ");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	

}

