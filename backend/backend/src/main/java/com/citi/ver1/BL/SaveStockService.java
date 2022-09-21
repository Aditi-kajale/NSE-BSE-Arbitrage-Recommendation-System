package com.citi.ver1.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;
@Service
public class SaveStockService {
	@Autowired
	private User_Cart_Repo user_Cart_Repo;
	public void insert(Cart cart) {
		user_Cart_Repo.insertInCart(cart.getCompanyName(), cart.getCloseNSE(), cart.getCloseBSE(), cart.getDiff(), cart.getPercDiff(), cart.getDateTime(), cart.getEmail(), cart.getQuantity(), cart.getProfit());
	}
	
	
	public void deleteStock(Cart cart)
	{
		List<Cart> list=  user_Cart_Repo.select(cart.getEmail());
		for(int i=0;i<list.size();i++)
		{
			if(cart.getDateTime().compareTo(list.get(i).getDateTime())==0)
			{
				cart.setStockId(list.get(i).getStockId());
				user_Cart_Repo.delete(cart);
				break;
			}
		}
		
		
	}
}

