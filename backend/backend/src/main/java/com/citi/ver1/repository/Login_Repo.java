package com.citi.ver1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citi.ver1.dto.User;

public interface Login_Repo extends JpaRepository<User, String>{
	@Query("select u from User u where u.email=:email and u.password=:password")
	List<User>findUser(@Param("email")String email,
			@Param("password")String password);
}
