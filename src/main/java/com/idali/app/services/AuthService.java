package com.idali.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.idali.app.dto.AuthenticationResponse;
import com.idali.app.dto.LoginRequest;
import com.idali.app.dto.RegisterRequest;
import com.idali.app.entities.LigneCommande;
import com.idali.app.entities.User;
import com.idali.app.repository.UserRepository;
import com.idali.app.security.JwtProvider;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtProvider jwtProvider; 
	
	
	
	public void signup(RegisterRequest registerRequest) {
		User user= new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		
		userRepository.save(user);
	}



	private String encodePassword(String password) {
 		return passwordEncoder.encode(password);
	}



	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate= 
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String authenticationToken= jwtProvider.generateToken(authenticate);
        //return new AuthenticationResponse(authenticationToken, loginRequest.getUsername()); 
		User user = new User(this.LoggedUser().getIdUser(),
				this.LoggedUser().getNomUser(),
				this.LoggedUser().getAdresse(),
				this.LoggedUser().getTel(),
				this.LoggedUser().getEmail(),
				this.LoggedUser().getUserName(),
				this.LoggedUser().isActivated()
		);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername(),user);
		
	}

 

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User pricipal = (org.springframework.security.core.userdetails.User)SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return Optional.of(pricipal);
	}
	
	
	public User LoggedUser() {
		org.springframework.security.core.userdetails.User user =  
				this.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No user logged in")); 
		return userRepository.findByUserName(user.getUsername()).orElse(null); 
	}
 
	
    
    
 
}
