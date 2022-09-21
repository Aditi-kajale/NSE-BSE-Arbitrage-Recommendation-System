package com.citi.ver1.repository;


import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.citi.ver1.dto.Cart;


public interface User_Cart_Repo extends JpaRepository<Cart, Integer> {
	@Modifying
	@Transactional
	@Query(value = "insert into Cart (company_name, closense, closebse, diff, perc_diff, date_time, email, quantity, profit) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9);", nativeQuery = true)
	void insertInCart(String companyName, BigDecimal closeNSE, BigDecimal closeBSE, BigDecimal diff, BigDecimal percDiff, String dateTime, String email, double quantity, double profit);

	@Query("SELECT u FROM Cart u WHERE u.email = ?1")
	List<Cart> select(String email);
}
