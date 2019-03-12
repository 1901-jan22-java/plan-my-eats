package com.revature.repository;

import org.springframework.data.repository.query.Param;

import com.revature.beans.Preferences;
import com.revature.beans.Restaurant;
import com.revature.beans.User;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	@Query("SELECT u from User u WHERE u.username = ?1")
	User findByUsername(String username);
	
	
   @Modifying
   @Query("update User u set u.preferences=?1 WHERE u.username = ?2")
    User setUserPreferenceByUsername(String preference,String username);
   
   //@Modifying
   @Query("update User u set u.preferences=?1 WHERE u.userId = ?2")
   User setUserPreferenceByUserId(Set<Preferences> s,int id);
   
   @Modifying
   @Query("update User u set u.height=?1 WHERE u.username = ?2")
    User setUserHeightByUsername(int height,String username);
   
   @Modifying
   @Query("update User u set u.weight=?1 WHERE u.username = ?2")
    User setUserWeightByUsername(double weight,String username);
   
   @Modifying
   @Query("update User u set u.age=?1 WHERE u.username = ?2")
    User setUserAgeByUsername(int age,String username);
   
   @Modifying
   @Query("update User u set u.gender=?1 WHERE u.username = ?2")
    User setUserGenderByUsername(String gender,String username);
   
   @Modifying
   @Query("update User u set u.height=?1,u.weight=?2,u.gender=?3,u.age=?4 WHERE u.username = ?5")
    User setUserInfoByUsername(int height, double weight, String gender, int age,String username);
   
//   @Modifying
//   @Query("update User u set u.preferences =?1 u.height=?2,u.weight=?3,u.gender=?4,u.age=?5 WHERE u.username = ?6")
//    User setUserAllInfoByUsername(Preferences preference,int height, double weight, String gender, int age,String username);
   
   
   
//   @Modifying
//   @Query("update User u set u.height=?1,u.weight=?2,u.gender=?3,u.age=?4 WHERE u.username = ?5")
//    User setUserInfoByUsername(int height, double weight, String gender, int age,String username);
//   
//   @Modifying
//   @Query("update User u set u.preference =?1 u.height=?2,u.weight=?3,u.gender=?4,u.age=?5 WHERE u.username = ?6")
//    User setUserAllInfoByUsername(String preference,int height, double weight, String gender, int age,String username);
}



