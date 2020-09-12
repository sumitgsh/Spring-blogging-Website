package com.example.Sampleblog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Sampleblog.dto.LoginRequest;
import com.example.Sampleblog.dto.RegisterRequest;
import com.example.Sampleblog.model.User;
import com.example.Sampleblog.repository.UserRepository;
import com.example.Sampleblog.security.JwtProvider;

import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class AuthService {

	@Autowired
	UserRepository userrep;  
	
	@Autowired
	PasswordEncoder passencode;
	
	@Autowired
	JwtProvider jwtProvider;
	
	  @Autowired
	  private AuthenticationManager authenticationManager;
	
	public void signup(RegisterRequest registerRequest)
	{
		User user=new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		
		userrep.save(user);
		
	}
	
	
	
	
	public String encodePassword(String str)
	{
		return passencode.encode(str);
	} 
	
    public AuthenticationResponse login(LoginRequest loginRequest) throws InvalidKeyException, Exception 
    {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }
    
    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
	
	
}


