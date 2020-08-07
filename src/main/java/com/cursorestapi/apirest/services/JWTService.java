package com.cursorestapi.apirest.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
		return token;
	}
	/*
	public String getUser(String headerAuth) {
		if (headerAuth == null || !headerAuth.startsWith("Bearer ")) {
			throw new SecurityException();
		}
		
		String token = headerAuth.substring(TokenFilter.TOKEN_INDEX);
		
		String subject = null;
		
		try {
			subject = Jwts
					.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (SignatureException e) {
			throw new SecurityException("Token inválido ou expirado!");
		}
		return subject;
	}
	*/
}
