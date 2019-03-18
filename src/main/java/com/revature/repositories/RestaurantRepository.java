package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Restaurant;

@Transactional
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	@Query("Select r from Restaurant r WHERE r.type = ?1")
	List<Restaurant> findByType(String type);

	@Query("Select r from Restaurant r WHERE r.location =?1")
	List<Restaurant> findByLocation(String location);

	@Query("Select r from Restaurant r WHERE r.name = ?1")
	Restaurant findByName(String name);

}
