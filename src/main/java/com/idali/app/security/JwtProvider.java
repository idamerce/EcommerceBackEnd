package com.idali.app.security;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.idali.app.Exceptions.EcommerceException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
 
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {

	
	private KeyStore keyStore;

	@PostConstruct
	public void init() {
		try {
        keyStore = KeyStore.getInstance("JKS");
        InputStream resourceAsStream = getClass().getResourceAsStream("/ecommerce.jks");
        keyStore.load(resourceAsStream, "ecommerceSecureCode".toCharArray());
	    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
	        throw new EcommerceException("Exception occured while loading keystore");
	    }
	}

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.signWith(getPrivateKey()).compact();
	}

	public boolean validateToken(String jwt) {

		 Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		  return true;
	}

	private PublicKey getPublicKey() {
		// TODO Auto-generated method stub
		try {
			return (PublicKey)keyStore.getCertificate("ecommerce").getPublicKey();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			throw new EcommerceException("Exception happened while retrieving public key from keystore");
		}
	}

	private PrivateKey getPrivateKey() {
		// TODO Auto-generated method stub
		try {
			return (PrivateKey)keyStore.getKey("ecommerce", "ecommerceSecureCode".toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new EcommerceException("Exception happened while retrieving private key from keystore");
		}
 	}

	public String getUserNameFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
		// System.out.println("claims-->"+claims);
		// Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
		return claims.getSubject();
	}

	
/*
	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(key).compact();
	}

	public boolean validateToken(String jwt) {

		 Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
		  return true;
	}

	public String getUserNameFromJwt(String token) {

		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		// System.out.println("claims-->"+claims);
		// Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
		return claims.getSubject();
	}
*/
}
