package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.revature.beans.Preference;


@Repository
public interface PreferencesRepository extends JpaRepository<Preference,Integer>  {
 
	
	//update preferences
	
	@Query("SELECT p FROM Preferences p WHERE p.name = ?1")
	Preference findByName(String name);
}
