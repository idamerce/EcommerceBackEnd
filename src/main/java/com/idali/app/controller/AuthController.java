package com.idali.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idali.app.dto.AuthenticationResponse;
import com.idali.app.dto.LoginRequest;
import com.idali.app.dto.RegisterRequest;
import com.idali.app.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<RegisterRequest> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<RegisterRequest>(registerRequest,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
 		//return new ResponseEntity(HttpStatus.OK);
	}

}
