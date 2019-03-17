package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Preference;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Integer> {

	Preference getPreferenceByPrefId(int prefId);

	Preference getPreferenceByName(String name);

}
