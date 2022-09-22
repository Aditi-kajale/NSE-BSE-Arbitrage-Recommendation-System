package com.citi.ver1.services;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.times;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.citi.ver1.BL.SignUpService;
import com.citi.ver1.dto.User;
import com.citi.ver1.repository.SignUpRepo;

@SpringBootTest(classes= {SignUpServiceTests.class})
@ExtendWith(MockitoExtension.class)
public class SignUpServiceTests {
	@Mock
	SignUpRepo signUpRepo;
	
	@InjectMocks
	SignUpService signUpService;
	@Test
	public void test_insert()
	{
		User user=new User("gayatrichaudhari@gmail.com","Gaya");
		signUpService.insert(user);
		verify(signUpRepo,times(1)).insertUser(user.getEmail(), user.getPassword());
	}
	

}
