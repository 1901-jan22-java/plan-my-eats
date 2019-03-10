package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.User;
import com.revature.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository repo;
	
	public List<User> getAll() {
		return repo.findAll();
	}

	public User saveUser(User u) {
		repo.save(u);
		return u;
	}

	public void saveAll(Iterable<User> list) {
		repo.save(list);
	}
	
	public User findById(int id) {
		return repo.findByUserId(id);
	}

	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
