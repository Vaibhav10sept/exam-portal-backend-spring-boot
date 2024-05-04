package com.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.config.JwtUtils;
import com.vaibhav.model.JwtRequest;
import com.vaibhav.model.JwtResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	private void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (Exception e) {
			throw new Exception("unauthorized: " + e.getMessage());
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			this.authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to authorize user: " + e.getMessage());
		}
		
		//exception not thrown, means user authenticated successfully
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserName());
		String token = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
