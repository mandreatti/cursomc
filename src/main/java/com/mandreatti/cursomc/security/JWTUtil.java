package com.mandreatti.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@PropertySource(value = { "classpath:application.properties" })
public class JWTUtil {
	@Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

		public String generateToken(String username) {
			
			Date dataExpiracao = new Date(System.currentTimeMillis()+ Long.parseLong(expiration));
			
			return Jwts.builder()
					.setSubject(username)
					.setExpiration(dataExpiracao)
					.signWith(SignatureAlgorithm.HS512, secret)
					.compact();
		}


}
