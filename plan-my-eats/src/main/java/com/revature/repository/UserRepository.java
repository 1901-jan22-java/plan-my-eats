package com.revature.repository;



import com.revature.beans.Restuarant;
import com.revature.beans.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	@Query("SELECT u from User u WHERE u.username = ?1")
	User findByUsername(String username);


}
