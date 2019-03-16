package com.revature.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Preference;
import com.revature.beans.User;
import com.revature.services.PreferenceService;
import com.revature.services.UserService;

public class TestApp {

	private static final Log log = LogFactory.getLog(TestApp.class);

	@Autowired
	static UserService us;
	@Autowired
	static PreferenceService ps;

	public static void main(String[] args) {
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

		System.out.println("Array: " + a);
		System.out.println("StringBuilder: \"" + sb + "\"");
		System.out.println("String Replace: \"" + sb.toString().replace("+", " ") + "\"");
		System.out.println("String Replace: \"" + sb.toString().replace("+", " ").replace(" ", "+") + "\"");
		// We need to figure out how to get loggers working... .-.
//		log.info(a);
//		log.info(sb);
		
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
