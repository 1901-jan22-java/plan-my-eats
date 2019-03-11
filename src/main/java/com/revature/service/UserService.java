package com.revature.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.User;
import com.revature.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;


@Service
@Transactional
public class UserService {
	
static List<User>users = new ArrayList<User>();

@Autowired
private UserRepository repo;

	
	static {
		users.add(new User("chocolate","dayZ666","iamthebomb",5,22,"Female",105.0));
		users.add(new User("spicy","ZIZI","nah",6,20,"Male",155.0));
	}
	public User saveUser(User u) {
		repo.save(u);
		return u;
	}
	
	public User findById(int id) {
		return repo.getOne(id);
	}
	
	
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}
	public List<User> findByRestauarnt(String restaurant){
		return repo.findByRestaurant(restaurant);
	}
	
	public List<User> findByRecipe(String recipe){
		return repo.findByRecipe(recipe);
	}
	public User updateUserGender(String gender,String username) {
		return repo.setUserGenderByUsername(gender, username);
	}
	public User updateUserWeight(double weight,String username) {
		return repo.setUserWeightByUsername(weight, username);
	}
	public User updateUserAge(int age,String username) {
		return repo.setUserAgeByUsername(age, username);
	}
	public User updateUserHeight(int height,String username) {
		return repo.setUserHeightByUsername(height, username);
	}
	
	//the method below will most likely change to append to strings 
	public User updateUserPreference(String preference,String username) {
		return repo.setUserPreferenceByUsername(preference, username);
	}
	public User updateUserInfo(int height,double weight, String gender,int age,String username) {
		return repo.setUserInfoByUsername(height,weight,gender,age, username);
	}
	public User updateUserAllInfo(String preference,int height,double weight,String gender,int age,String username) {
		return repo.setUserAllInfoByUsername(preference,height,weight,gender,age, username);
	}
	
	
}
