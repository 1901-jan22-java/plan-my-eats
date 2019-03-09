package com.revature.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;



@Service
@Transactional
public class UserService {
	
static List<User>users = new ArrayList<User>();
@Autowired
private UserRepository repo;

	
	public User saveUser(User u) {
		repo.save(u);
		return u;
	}
	
	public User findById(int id) {
		return repo.getOne(id);
	}
	
	
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
