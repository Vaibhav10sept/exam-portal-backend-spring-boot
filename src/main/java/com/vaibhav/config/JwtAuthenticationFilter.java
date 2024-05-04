package com.vaibhav.config;

import java.io.IOException;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vaibhav.service.implementation.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
	            userName = jwtUtils.extractUsername(jwtToken);
	        } catch (Exception e) {
	            System.out.println("Error extracting username from JWT token: " + e.getMessage());
	        }
		}
		else {
			System.out.println("invalid token");
		}
		System.out.println("requestTokenHeader " + requestTokenHeader);
		System.out.println("jwtToken " + jwtToken);
		System.out.println("username " + userName);
		//we got the token, now validate the token
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
			
			if(jwtUtils.validateToken(jwtToken, userDetails)) {
				//means token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		else {
			System.out.println("token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}
	
}
