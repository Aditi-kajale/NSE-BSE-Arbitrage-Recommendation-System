package com.citi.ver1.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;
@Service
public class SaveStockService {
	@Autowired
	private User_Cart_Repo user_Cart_Repo;
	public void insert(Cart cart) {
		user_Cart_Repo.insertInCart(cart.getCompanyName(), cart.getCloseNSE(), cart.getCloseBSE(), cart.getDiff(), cart.getPercDiff(), cart.getDateTime(), cart.getEmail());
	}
}

