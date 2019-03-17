package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Preference;


@Repository
public interface PreferencesRepository extends JpaRepository<Preference,Integer>  {

}
