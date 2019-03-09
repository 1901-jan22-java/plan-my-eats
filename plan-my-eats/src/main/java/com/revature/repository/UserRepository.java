package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	@Query("SELECT u from User u WHERE u.username = ?1")
	User findByUsername(String username);

}
