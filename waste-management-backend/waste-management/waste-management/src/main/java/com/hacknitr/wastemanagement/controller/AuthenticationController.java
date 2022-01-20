package com.hacknitr.wastemanagement.controller;

import java.security.Principal;
import java.util.Date;

import com.hacknitr.wastemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hacknitr.wastemanagement.model.JwtRequest;
import com.hacknitr.wastemanagement.model.JwtResponse;
import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.service.impl.UserDetailsServiceImpl;
import com.hacknitr.wastemanagement.util.JwtUtils;


@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@PostMapping("/generatetoken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest authrequest) throws Exception {
		
		System.out.println("AuthRequest data is: "+authrequest);
		
		try {

			System.out.println(this.userDetailsServiceImpl.loadUserByUsername(authrequest.getUsername()));
			
			System.out.println("user with name"+authrequest.getUsername()+" "+"has requested to access the data");
		   
			authenticate(authrequest.getUsername(),authrequest.getPassword());
		}
		
		catch(Exception e) {
			throw new Exception("Invalid username and password please try again!");
		}
		
		String token=jwtUtil.generateToken(authrequest.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	private void authenticate(String username,String password) throws Exception {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		catch(DisabledException e) {
			throw new Exception("User is disabled "+e.getMessage());
		}
		catch(BadCredentialsException e) {
			throw new Exception("user credential is invalid!! please try with valid credentials "+e.getMessage());
		}
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName ="nitinraj123";
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
			System.out.println("current user logged in is" + currentUserName);
		}

		return (User)this.userDetailsServiceImpl.loadUserByUsername(currentUserName);
	}
	
	@GetMapping("/jwt-token-status/{token}")
	public boolean isTokengetsExpiredOrNot(@PathVariable("token") String token) {

		System.out.println("token status request is generated by the client"+token);
		return this.jwtUtil.extractExpiration(token).before(new Date());
	}

}
