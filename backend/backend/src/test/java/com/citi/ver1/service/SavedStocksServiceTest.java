package com.citi.ver1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.citi.ver1.BL.SavedStocksService;
import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;

@SpringBootTest(classes= {SavedStocksServiceTest.class})
@ExtendWith(MockitoExtension.class)
public class SavedStocksServiceTest {
	
	@Mock
	User_Cart_Repo user_Cart_Repo;
	
	@InjectMocks
	SavedStocksService savedStocksService;
	
	@Test
	public void test_savedStocks() //valid input
	{
		
		List<Cart> myCart=new ArrayList<>();
		myCart.add(new Cart(1, "Mahindra", new BigDecimal(1117.45),new BigDecimal(1114.70), new BigDecimal(2.75), new BigDecimal(0.25), "14/09/22 18:57:17","gayatri.chadhari@gmail.com",5,53.5));
		myCart.add(new Cart(2, "Bajaj Finance Limited", new BigDecimal(7479.70),new BigDecimal(7483.45), new BigDecimal(3.75), new BigDecimal(0.05), "14/09/22 19:38:55","gayatri.chadhari@gmail.com",4,73));
		String email="gayatri.chadhari@gmail.com";
		when(user_Cart_Repo.select(email)).thenReturn(myCart);
		assertEquals(2, savedStocksService.savedStocks(email).size());
	}
	
	@Test
	public void test_null_savedStocks() //null input
	{
		List<Cart> myCart=new ArrayList<>();
		String email="";
		
		when(user_Cart_Repo.select(email)).thenReturn(myCart);
		assertEquals(0, savedStocksService.savedStocks(email).size());
	}
	
	

}
