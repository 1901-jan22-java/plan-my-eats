package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.Preference;
import com.revature.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


	@Modifying
	@Query("update User u set u.preferences=?1 WHERE u.userId = ?2")
	void setUserPreferenceByUserId(Set<Preference> s, Integer userId);

	@Query("SELECT u FROM User u WHERE length(u.username) > ?1")
	List<User> lengthQuery(int length);

	User findByUserId(int id);

	User findByUsername(String username);

	User findByUsernameLikeIgnoreCase(String username);

	User findByUsernameContaining(String username);

	@Modifying
	@Query("update User u set u.preferences=?1 WHERE u.username = ?2")
	User setUserPreferenceByUsername(String preference, String username);

	@Modifying
	@Query("update User u set u.preferences=?1 WHERE u.userId = ?2")
	User setUserPreferenceByUserId(Set<Preference> s, int id);

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

}
