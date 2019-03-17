package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Preference;
import com.revature.repositories.PreferenceRepository;

@Service
@Transactional
public class PreferenceService {

	@Autowired
	private static PreferenceRepository repo;

	public void save(Preference newPref) {
		repo.save(newPref);
	}

	public void saveAll(Iterable<Preference> newPrefs) {
		repo.save(newPrefs);
	}

	public List<Preference> getAll() {
		return repo.findAll();
	}

	public Preference getByName(String name) {
		return repo.getPreferenceByName(name);
	}

	public Preference getById(int id) {
		return repo.getOne(id);
	}

}
