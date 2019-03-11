package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Preferences;
import com.revature.repository.PreferencesRepository;


@Service
@Transactional
public class PreferencesService {

	
	@Autowired
	private PreferencesRepository repo;
	

	public void save(Preferences newPref) {
		repo.save(newPref);
	}

	public void saveAll(Iterable<Preferences> newPrefs) {
		repo.save(newPrefs);
	}


	public List<Preferences> getAll() {
		return repo.findAll();
	}

	public Preferences getById(int id) {
		return repo.getOne(id);
	}
	
	public Preferences getByName(String name) {
		return repo.findByName(name);
	}
}
