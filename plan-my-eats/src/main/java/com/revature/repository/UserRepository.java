package com.revature.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsernameLikeIgnoreCase(String username);
	
	User findByUsernameContaining(String contains);
	
	@Query("SELECT u FROM User u WHERE length(u.username) > ?1")
	List<User> lengthQuery(int length);
}
