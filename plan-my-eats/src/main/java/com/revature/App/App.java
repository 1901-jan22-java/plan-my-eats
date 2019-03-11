package com.revature.App;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.User;
import com.revature.service.UserService;

public class App {
	
	@Autowired
	UserService service;
	
	public static void main(String[] args) {
		UserService service = new UserService();
		User u = new User("dayZ666","iamthebomb",5,22,"Female",105.0);
//		users.add(new User("spicy","ZIZI","nah",6,20,"Male",155.0));
		service.saveUser(u);
	}
}
