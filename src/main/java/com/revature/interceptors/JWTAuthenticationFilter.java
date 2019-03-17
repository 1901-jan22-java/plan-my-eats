package com.revature.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class JWTAuthenticationFilter extends HandlerInterceptorAdapter{
	
	TokenService tokenService = ImpTokenService.getInstance();
	
//	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<User> authorize(@RequestBody User user){
//		System.out.println("Attempting to find JWT token");
//		final String token = user.getToken();
//		
//		
//		if (token == null) {
//			System.out.println("Authentication failed");
//			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
//		}
//		
//		if (tokenService.validateToken(token)) {
//			System.out.println("Token is invalid! Log in again to request a new token!");
//			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
//		}
//		
//		System.out.println("JWT found and is valid! Passing request on");
//		return null;
//	}
	
	// This method is called before the controller
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

    	System.out.println(request.getRequestURI().split("/")[2]);
    	if(request.getRequestURI().split("/")[2].equals("login") || request.getRequestURI().split("/")[2].equals("register")) {
    		System.out.println("This is a login request.");
    		return true;
    	}
    	final String token = request.getHeader("Authorization");

        if (token == null) {
			System.out.println("Authentication Failed.");
			response.resetBuffer();
//			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		boolean valid = tokenService.validateToken(token);
		System.out.println(valid);
		if (!valid) {
			System.out.println("Authentication failed: Token is invalid");
//			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		} else {
			System.out.println("JWT found and is valid! Passing request on");
			return true;
		}
		
		
    }
}
