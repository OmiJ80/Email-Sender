package com.demo.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.EmailRequest;
import com.demo.helper.CustomResponse;

import com.demo.impl.EmailService;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class EmailController {

	private EmailService emailService;
	
	
	public EmailController(EmailService emailService) {
	
		
		this.emailService = emailService;
	}


	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
		return ResponseEntity.ok(
				CustomResponse.builder().message("email send succesfully").httpStatus(HttpStatus.OK).success(true).build()
				);
	
	
	}
	
	@PostMapping("/send-with-file")
	public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest request,@RequestPart MultipartFile file) throws IOException{
		emailService.sendEmailWithFile(request.getTo(),request.getSubject(),request.getMessage(),file.getInputStream());
		return ResponseEntity.ok(
				CustomResponse.builder().message("email send succesfully").httpStatus(HttpStatus.OK).success(true).build()
				);
	}
}

