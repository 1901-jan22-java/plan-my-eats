package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Restaurant;
import com.revature.repositories.RestaurantRepository;

@Service
@Transactional
public class RestaurantService {

	@Autowired
	private RestaurantRepository repo;

	public Restaurant saveRestaurant(Restaurant r) {
		return repo.save(r);
	}

	public List<Restaurant> getRestaurantByType(String type) {
		return repo.findByType(type);
	}

	public Restaurant getRestaurantByName(String name) {
		return repo.findByName(name);
	}

	public List<Restaurant> findAll() {
		return repo.findAll();
	}

	public Restaurant getById(int id) {
		return repo.getOne(id);
	}
	public List<Restaurant> getRestaurant(){
		return repo.findAll();
	}
}
