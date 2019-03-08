package com.revature.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.revature.beans.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;


//@Service
public class UserService {
	
//static List<User>users = new ArrayList<User>();

//	@Autowired
//	UserRepository repo;
//	
//	static {
//		users.add(new User("chocolate","dayZ666","iamthebomb",5,22,"Female",105.0));
//		users.add(new User("spicy","ZIZI","nah",6,20,"Male",155.0));
//	}
//	public User saveUser(User u) {
//		repo.save(u);
//		return u;
//	}
//	
//	public User findById(int id) {
//		return repo.findById(id);
//	}
//	
//	
//	public User findByUsername(String username) {
//		return repo.findByUsername(username);
//	}
}
