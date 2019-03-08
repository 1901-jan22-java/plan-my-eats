package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.User;
import com.revature.repository.UserRepository;

//@Service
public class UserService {

	private static List<User> users = new ArrayList<User>();

	@Autowired
	UserRepository repo;

	static {
		users.add(new User("chocolate", "dayZ666", "iamthebomb", 5, 22, "Female", 105.0));
		users.add(new User("spicy", "ZIZI", "nah", 6, 20, "Male", 155.0));
	}

	public User saveUser(User u) {
		repo.save(u);
		return u;
	}

	public User findById(int id) {
		return repo.findById(id);
	}

	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
