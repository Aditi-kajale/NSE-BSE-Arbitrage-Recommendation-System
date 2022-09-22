package com.citi.ver1.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.citi.ver1.BL.SaveStockService;
import com.citi.ver1.BL.SavedStocksService;
import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;

@SpringBootTest(classes= {SaveStockServiceTest.class})
@ExtendWith(MockitoExtension.class)
public class SaveStockServiceTest {
	@Mock
	User_Cart_Repo user_Cart_Repo;
	
	@InjectMocks
	SaveStockService saveStockService;
	
	
	//(int stockId, String companyName, BigDecimal closeNSE, BigDecimal closeBSE, BigDecimal diff,
		//BigDecimal percDiff, String dateTime, String email, double quantity, double profit)
		
	
	@Test
	public void test_insert()
	{
		Cart cart=new Cart(1,"Tech Mahindra Limited",new BigDecimal(1117.45),new BigDecimal(1114.70),new BigDecimal(0.25),new BigDecimal(0.05),"","gayatri.chadhari@gmail.com",4,53);
		saveStockService.insert(cart);
		verify(user_Cart_Repo,times(1)).insertInCart(cart.getCompanyName(), cart.getCloseNSE(),cart.getCloseBSE(), cart.getDiff(), cart.getPercDiff(), cart.getDateTime(), cart.getEmail(), cart.getQuantity(), cart.getProfit());
	}

	@Test
	public void test_delete()
	{
		List<Cart> myCart=new ArrayList<>();
		myCart.add(new Cart(1, "Mahindra", new BigDecimal(1117.45),new BigDecimal(1114.70), new BigDecimal(2.75), new BigDecimal(0.25), "14/09/22 18:57:17","gayatri.chadhari@gmail.com",5,53.5));
		myCart.add(new Cart(2, "Bajaj Finance Limited", new BigDecimal(7479.70),new BigDecimal(7483.45), new BigDecimal(3.75), new BigDecimal(0.05), "14/09/22 19:38:55","gayatri.chadhari@gmail.com",4,73));
		Cart expected=new Cart(1,"Tech Mahindra Limited",new BigDecimal(1117.45),new BigDecimal(1114.70),new BigDecimal(0.25),new BigDecimal(0.05),"14/09/22 19:38:55","gayatri.chadhari@gmail.com",4,53);
		
		when(user_Cart_Repo.select(expected.getEmail())).thenReturn(myCart);
		
		saveStockService.deleteStock(expected);
		verify(user_Cart_Repo,times(1)).delete(expected);
	}
	
}
