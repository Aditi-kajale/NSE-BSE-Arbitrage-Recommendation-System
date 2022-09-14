package com.citi.ver1.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;
import java.util.List;

@Service
public class SavedStocksService {
	@Autowired
	private User_Cart_Repo user_Cart_Repo;
	public List<Cart> savedStocks(String email) {
		return user_Cart_Repo.select(email);
	}
}

