package com.revature.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Preference;
import com.revature.beans.User;
import com.revature.services.PreferenceService;
import com.revature.services.UserService;

public class TestApp {

	private static final Logger log = Logger.getLogger(TestApp.class.getName());

	@Autowired
	static UserService us;
	@Autowired
	static PreferenceService ps;

	public static void main(String[] args) {
//		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		testRandomSelect();
	}

	public static void testRandomSelect() {
		
		List<Integer> a = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			a.add((int) (Math.random() * 20));
		}

		Collections.shuffle(a);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 2 && i < a.size(); i++) {
			sb.append(a.get(i) + "+");
		}

		sb.setLength(Math.max(sb.length() - 1, 0));

		log.info("Array: " + a);
		log.info("StringBuilder: \"" + sb + "\"");
		log.info("String Replace: \"" + sb.toString().replace("+", " ") + "\"");
		log.info("String Replace: \"" + sb.toString().replace("+", " ").replace(" ", "+") + "\"");

	}

	public static void testServices() {
		List<User> users = new ArrayList<>();
		List<Preference> preferences = new ArrayList<>();

		users.add(new User("chocolate", "dayZ666", "iamthebomb", 5, 22, "Female", 105.0));
		users.add(new User("spicy", "ZIZI", "nah", 6, 20, "Male", 155.0));

		preferences.add(new Preference("Vegetarian"));
		preferences.add(new Preference("Vegan"));
		preferences.add(new Preference("Pescatarian"));
		preferences.add(new Preference("Chinese"));
		preferences.add(new Preference("Indian"));

		log.info(users);
		log.info(preferences);

		us.saveAll(users);
		ps.saveAll(preferences);
	}

}
