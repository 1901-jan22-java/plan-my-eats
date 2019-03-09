package com.revature.beans;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "PME_USERS")
@Component
public class User {

	@Id
	@Column(name = "USER_ID")
	@SequenceGenerator(name = "PME_U_SEQ_GEN", sequenceName = "PME_U_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PME__SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int userId;

	@Column(nullable = false, unique = true, name = "USERNAME")
	private String username;

	@Column(nullable = false, name = "PASSWORD")
	private String password;

	@Column(nullable = false, name = "HEIGHT")
	private int height;// IN INCHES!

	@Column(nullable = false, name = "AGE")
	private int age;

	@Column(nullable = false, name = "GENDER")
	private String gender;

	@Column(nullable = false, name = "WEIGHT")
	private double weight;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "RECIPE_HISTORY", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "RECIPE_ID"))
	private Set<Recipe> recipes = new HashSet<Recipe>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "RESTAURANT_HISTORY", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "RESTUARANT_ID"))
	private Set<Restaurant> restaurants = new HashSet<Restaurant>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "PREFERENCE_PROFILE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "PREF_ID"))
	private Set<Preferences> preferences = new HashSet<Preferences>();

	public User() {
	}

	public User(String preference, String username, String password, int height, int age, String gender,
			double weight) {
		this.username = username;
		this.password = password;
		this.height = height;
		this.age = age;
		this.gender = gender;
		this.weight = weight;

		// this.recipes = recipes;
		// this.restaurants = restaurants;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public Set<Preferences> getPreferences() {
		return preferences;
	}

	public void setPreferences(Set<Preferences> preferences) {
		this.preferences = preferences;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", height=" + height
				+ ", age=" + age + ", gender=" + gender + ", weight=" + weight + ", recipes=" + recipes
				+ ", restaurants=" + restaurants + ", preferences=" + preferences + "]";
	}

}
