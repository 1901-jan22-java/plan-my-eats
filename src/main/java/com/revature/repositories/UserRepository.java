package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE length(u.username) > ?1")
	List<User> lengthQuery(int length);

	User findByUserId(int id);

	User findByUsername(String username);

	User findByUsernameLikeIgnoreCase(String username);

	User findByUsernameContaining(String username);

//	@Query(value = "SELECT u from User u WHERE u.restaurants LIKE '%':restaurant'%'")
//	List<User> findByRestaurants(@Param("restaurant") String restaurant);
//
//	@Query(value = "SELECT u from User u WHERE u.recipes LIKE '%':recipe'%'")
//	List<User> findByRecipes(@Param("recipe") String recipe);

	@Modifying
	@Query("update User u set u.preferences=?1 WHERE u.username = ?2")
	User setUserPreferencesByUsername(String preference, String username);

	@Modifying
	@Query("update User u set u.height=?1 WHERE u.username = ?2")
	User setUserHeightByUsername(int height, String username);

	@Modifying
	@Query("update User u set u.weight=?1 WHERE u.username = ?2")
	User setUserWeightByUsername(double weight, String username);

	@Modifying
	@Query("update User u set u.age=?1 WHERE u.username = ?2")
	User setUserAgeByUsername(int age, String username);

	@Modifying
	@Query("update User u set u.gender=?1 WHERE u.username = ?2")
	User setUserGenderByUsername(String gender, String username);

	@Modifying
	@Query("update User u set u.height=?1,u.weight=?2,u.gender=?3,u.age=?4 WHERE u.username = ?5")
	User setUserInfoByUsername(int height, double weight, String gender, int age, String username);

//	@Modifying
//	@Query("update User u set u.preference =?1 u.height=?2,u.weight=?3,u.gender=?4,u.age=?5 WHERE u.username = ?6")
//	User setUserAllInfoByUsername(String preference, int height, double weight, String gender, int age,
//			String username);

}