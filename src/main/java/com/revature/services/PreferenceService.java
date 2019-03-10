package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Preference;
import com.revature.repositories.PreferenceRepository;

@Service
public class PreferenceService {

	private static List<Preference> preferences = new ArrayList<>();

	static {
		preferences.add(new Preference("Vegetarian"));
		preferences.add(new Preference("Vegan"));
		preferences.add(new Preference("Pescatarian"));
		preferences.add(new Preference("Chinese"));
		preferences.add(new Preference("Indian"));
	}
	
	@Autowired
	PreferenceRepository repo;
	
	public Preference getById(int id) {
		return repo.getPreferenceByPrefId(id);
	}
	
}
