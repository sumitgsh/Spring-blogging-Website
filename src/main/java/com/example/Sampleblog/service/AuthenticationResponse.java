package com.example.Sampleblog.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
	public String authenticationToken;
    public String username;
    
    public AuthenticationResponse(String authenticationToken2, String username) {
    	this.authenticationToken=authenticationToken2;
    	this.username=username;
	}
}
