package com.example.Sampleblog.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Sampleblog.model.User;
import com.example.Sampleblog.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user=userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("No user name found"+username));
		return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                true, true, true, true,
                getAuthorities("ROLE_USER"));}

	  private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
	        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
	    }
}
