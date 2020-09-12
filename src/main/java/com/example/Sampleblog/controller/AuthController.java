package com.example.Sampleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sampleblog.dto.LoginRequest;
import com.example.Sampleblog.dto.RegisterRequest;
import com.example.Sampleblog.service.AuthService;
import com.example.Sampleblog.service.AuthenticationResponse;

import io.jsonwebtoken.security.InvalidKeyException;

@RestController
@RequestMapping("api/auth")
public class AuthController 
{
	@Autowired
	AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity sigup(@RequestBody RegisterRequest registerRequest)
	{		
		authService.signup(registerRequest);
		return new ResponseEntity(HttpStatus.OK);	
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws InvalidKeyException, Exception
	{
		return authService.login(loginRequest);
				
	}	
}
