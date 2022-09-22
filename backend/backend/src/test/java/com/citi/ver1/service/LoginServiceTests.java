package com.citi.ver1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.times;

import com.citi.ver1.BL.LoginService;
import com.citi.ver1.dto.User;
import com.citi.ver1.repository.Login_Repo;

@SpringBootTest(classes= {LoginServiceTests.class})
@ExtendWith(MockitoExtension.class)
public class LoginServiceTests {

	@Mock
	Login_Repo login_Repo;
	
	@InjectMocks
	LoginService loginService;
	
	@Test
	public void test_findUserFromDB()
	{
		
		//String email="gayatri.chaudhari@gmail.com";
		Optional<User> temp=Optional.of(new User("gayatri.chaudhari@gmail.com","gaya"));
		when(login_Repo.findById("gayatri.chaudhari@gmail.com")).thenReturn(temp);
		Optional<User> user=loginService.findUser("gayatri.chaudhari@gmail.com");
		assertEquals(temp,user);
	}
	 @Test
	 public void test_addUser()
	 {
		 User user=new User("gayatri.chaudhari@gmail.com","Gayatri");
		 loginService.addUser(user);
		 verify(login_Repo,times(1)).save(user);
	 }
	
	 

}
