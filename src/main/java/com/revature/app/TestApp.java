package com.revature.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Preference;
import com.revature.beans.User;
import com.revature.services.PreferenceService;
import com.revature.services.UserService;

public class TestApp {

	@Autowired
	static UserService us;
	@Autowired
	static PreferenceService ps;

	public static void Main(String[] args) {
		List<User> users = new ArrayList<>();
		List<Preference> preferences = new ArrayList<>();

		users.add(new User("chocolate", "dayZ666", "iamthebomb", 5, 22, "Female", 105.0));
		users.add(new User("spicy", "ZIZI", "nah", 6, 20, "Male", 155.0));

		preferences.add(new Preference("Vegetarian"));
		preferences.add(new Preference("Vegan"));
		preferences.add(new Preference("Pescatarian"));
		preferences.add(new Preference("Chinese"));
		preferences.add(new Preference("Indian"));

		us.saveAll(users);
		ps.saveAll(preferences);
	}

}
