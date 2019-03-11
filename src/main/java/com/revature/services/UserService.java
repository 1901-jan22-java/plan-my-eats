package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.User;
import com.revature.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository repo;

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

	public List<User> findByRestauarnt(String restaurant) {
		return repo.findByRestaurant(restaurant);
	}

	public List<User> findByRecipe(String recipe) {
		return repo.findByRecipe(recipe);
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

	// the method below will most likely change to append to strings
	public User updateUserPreference(String preference, String username) {
		return repo.setUserPreferenceByUsername(preference, username);
	}

	public User updateUserInfo(int height, double weight, String gender, int age, String username) {
		return repo.setUserInfoByUsername(height, weight, gender, age, username);
	}

	public User updateUserAllInfo(String preference, int height, double weight, String gender, int age,
			String username) {
		return repo.setUserAllInfoByUsername(preference, height, weight, gender, age, username);
	}

}
