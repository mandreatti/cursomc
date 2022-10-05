package com.mandreatti.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mandreatti.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS autehenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
		
	}

}
