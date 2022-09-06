package com.citi.ver1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citi.ver1.dto.Cart;


public interface User_Cart_Repo extends JpaRepository<Cart, Integer> {
	@Query("select s from Cart s where s.email=:email")
	List<Cart>findAllStocks(@Param("email")String username);
}
