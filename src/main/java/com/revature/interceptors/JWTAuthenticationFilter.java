package com.revature.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class JWTAuthenticationFilter extends HandlerInterceptorAdapter {

	private static final Logger log = Logger.getLogger(JWTAuthenticationFilter.class);

	TokenService tokenService = ImpTokenService.getInstance();


	// This method is called before the controller
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info(request.getRequestURI().split("/")[2]);
		if (request.getRequestURI().split("/")[2].equals("login")
				|| request.getRequestURI().split("/")[2].equals("register") || request.getMethod().equalsIgnoreCase("Options")) {
			log.info("This is a login request.");
			return true;
		}
		final String token = request.getHeader("Authorization");
		log.error(token);
		if (token == null) {
			log.info("Authentication Failed.");
			response.resetBuffer();
//			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		boolean valid = tokenService.validateToken(token);
		log.info(valid);
		if (!valid) {
			log.error("Authentication failed: Token is invalid");
//			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		} else {
			log.info("JWT found and is valid! Passing request on");
			return true;
		}

	}
}
