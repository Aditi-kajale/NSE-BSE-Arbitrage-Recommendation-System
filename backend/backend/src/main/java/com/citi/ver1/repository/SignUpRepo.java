package com.citi.ver1.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citi.ver1.dto.User;

public interface SignUpRepo extends JpaRepository<User, String>{
	@Modifying
    @Transactional
	@Query(value = "insert into user (email, password) VALUES (?1, ?2);", nativeQuery = true)
	   void insertUser(String email, String password);
}
