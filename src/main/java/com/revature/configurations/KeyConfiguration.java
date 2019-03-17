package com.revature.configurations;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.revature.services.RecipeService;

public class KeyConfiguration {

	private static final Logger log = Logger.getLogger(KeyConfiguration.class);
	public static final Properties PROPS = new Properties();

	static {
		String file = "api.properties";

		try {
			PROPS.load(RecipeService.class.getClassLoader().getResourceAsStream(file));
		} catch (IOException e) {
			log.error("Could not load file: " + file + "\nWorking Directory: "
					+ RecipeService.class.getClassLoader().getResource(""), e);
		}
	}

}
