package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Preference;

@Repository
@Transactional
public interface PreferenceRepository extends JpaRepository<Preference, Integer> {

	Preference getPreferenceByPrefId(int prefId);

	Preference getPreferenceByName(String name);

}
