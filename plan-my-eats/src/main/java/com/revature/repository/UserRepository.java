package com.revature.repository;



import com.revature.beans.Restuarant;
import com.revature.beans.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer>{

	@Query("SELECT u from User u WHERE u.username = ?1")
	User findByUsername(String username);
	
	User save(User u);
	
	User add(User u);
	
	User findById(int id);
	
	List<User> findAll();
	
	@Query("SELECT u from User u WHERE u.restuarants = ?1")
	public List<User> countByRestuarant(Restuarant rest);
}
