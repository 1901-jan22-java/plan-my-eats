package com.revature.services.interfaces;

import org.springframework.stereotype.Service;

import com.revature.beans.User;

@Service
public interface TokenService {

	String generateToken(User details);

	boolean validateToken(String token);

	String getTokenId(String token);

//	User getUserDetailsFromToken(String token);

}