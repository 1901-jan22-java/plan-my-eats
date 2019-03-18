package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Preference;
import com.revature.beans.Recipe;
import com.revature.beans.Restaurant;
import com.revature.beans.User;
import com.revature.repositories.UserRepository;

@Transactional
@Service
public class UserService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repo;

	public List<User> getAll() {
		return repo.findAll();
	}

	public User saveUser(User u) {
		repo.save(u);

		return u;
	}

	public void saveAll(Iterable<User> list) {
		repo.save(list);
	}

	public User findById(int id) {
		return repo.findByUserId(id);
	}

	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}

	public User updateUserGender(String gender, String username) {
		return repo.setUserGenderByUsername(gender, username);
	}

	public User updateUserWeight(double weight, String username) {
		return repo.setUserWeightByUsername(weight, username);
	}

	public User updateUserAge(int age, String username) {
		return repo.setUserAgeByUsername(age, username);
	}

	public User updateUserHeight(int height, String username) {
		return repo.setUserHeightByUsername(height, username);
	}

	public void updatePreferences(User u, List<Preference> pref, int id) {
		u = repo.getOne(id);
		u.setPreferences(pref);
		repo.save(u);
	}

	public void updateUser(int id, List<Preference> pref, List<Recipe> rec, List<Restaurant> rest) {
		User u = repo.getOne(id);
		u.setPreferences(pref);
		u.setRecipes(rec);
		u.setRestaurants(rest);
		repo.save(u);
	}

	// the method below will most likely change to append to strings
	public User updateUserPreferences(String preference, String username) {
		return repo.setUserPreferencesByUsername(preference, username);
	}

	public User updateUserInfo(int height, double weight, String gender, int age, String username) {
		return repo.setUserInfoByUsername(height, weight, gender, age, username);
	}

	public User updatePreferences(List<Preference> pref, int id) {
		return repo.setUserPreferenceByUserId(pref, id);
	}

//	public User updateUserAllInfo(String preference, int height, double weight, String gender, int age,
//			String username) {
//		return repo.setUserAllInfoByUsername(preference, height, weight, gender, age, username);
//	}

}
