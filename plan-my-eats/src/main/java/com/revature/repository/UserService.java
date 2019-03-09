package com.revature.repository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.revature.beans.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

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
