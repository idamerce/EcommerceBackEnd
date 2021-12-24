package com.idali.app.dto;

import com.idali.app.entities.User;

public class AuthenticationResponse {
	private String authenticationToken;
	private String username;
	private User user;

	
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AuthenticationResponse() {
	}

	public AuthenticationResponse(String authenticationToken, String username) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
	}
	public AuthenticationResponse(String authenticationToken, String username,User user) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
		this.user = user;
	}

	
	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}

	
}
