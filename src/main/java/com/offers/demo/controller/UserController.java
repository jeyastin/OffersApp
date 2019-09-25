package com.offers.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.offers.demo.service.MailService;
import com.offers.demo.service.UserService;
import com.offers.demo.transport.MailTO;
import com.offers.demo.transport.UserTO;

import io.swagger.annotations.Api;

@RestController
@Api
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@PostMapping("/addUserOutlets")
	@CrossOrigin
	public UserTO addUserOutlets(HttpServletRequest request, HttpServletResponse response, @RequestBody UserTO userTO){
		UserTO reponse = userService.addUserOutlets(userTO);
		return reponse;
		
	}
	
	@PostMapping("/sendMail")
	@CrossOrigin
	public MailTO sendMail(HttpServletRequest request, HttpServletResponse response,@RequestBody MailTO mailTO){
		return mailService.sendMail(mailTO);
		
	}
	
	@GetMapping("/getUserOutlets/{email}")
	@CrossOrigin
	public UserTO getUserOutlets(HttpServletRequest request, HttpServletResponse response, @PathVariable(name="email") String email){
		UserTO reponse = userService.getUserOutlets(email);
		return reponse;
		
	}
	

}
