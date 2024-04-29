package com.demo;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.impl.EmailService;



@SpringBootTest
public class EmailSenderTest {
	
	@Autowired
	private EmailService emailService;
	
	@Test
	void emailSendTest() {
		System.out.println("sending email");
		emailService.sendEmail("omijadhav007@gmail.com", "email from spring boot ", "this email is send using spring boot while create email service   ");
	}
	
	@Test
	void sendHtmlInEmail() {
		String html="" + 
				"<h1 style='color:red;border:1px solid red;'> welcome to our project</h1>"+
				"";
		System.out.println("sending email with html");
		emailService.sendEmailWithHtml("omijadhav007@gmail.com", "email from spring boot","this email is send using spring boot while create email service");
	}
	@Test
	void emailWithSendFile() {
		System.out.println("sending email with a file ");
		emailService.sendEmailWithFile("omijadhav007@gmail.com", "email with file  ","this email contains file ",new File("F:\\Spring Project\\bookstore_spring_boot_project-main\\bookStore\\src\\main\\resources\\static\\images\\bg.png"));
	}
	
	
}

	


