package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.revature.beans.Preferences;


@Repository
public interface PreferencesRepository extends JpaRepository<Preferences,Integer>  {
 
	@Query("SELECT p FROM Preferences p WHERE p.name = ?1")
	Preferences findByName(String name);
}
