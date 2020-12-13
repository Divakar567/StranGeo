package com.strangeo.user.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strangeo.user.model.UserAuthDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private ObjectMapper objectMapper;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
		this.authenticationManager = authenticationManager;
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {

			if (!request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
			}

			UserAuthDetails signInRequest = objectMapper.readValue(request.getInputStream(),
					UserAuthDetails.class);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword());
//			super.setDetails(request, authentication);
			return this.authenticationManager.authenticate(authentication);
		} catch (IOException e) {
			throw new BadCredentialsException("Invalid credentials payload");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

//		super.successfulAuthentication(request, response, chain, authResult);

		String jwtToken = Jwts.builder().setSubject(authResult.getName())
				.claim("authorities", authResult.getAuthorities()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()))
				.signWith(Keys.hmacShaKeyFor("StrangeSecretKey".getBytes())).compact();

		response.addHeader("Authorization", "Bearer " + jwtToken);
	}

}
