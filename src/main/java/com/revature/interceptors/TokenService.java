package com.revature.interceptors;

import com.revature.beans.User;

public interface TokenService {

	String generateToken(User details);

	boolean validateToken(String token);

//	User getUserDetailsFromToken(String token);

	String getTokenId(String token);

}