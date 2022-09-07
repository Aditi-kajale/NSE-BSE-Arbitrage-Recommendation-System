package com.citi.ver1.BL;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.User;
import com.citi.ver1.repository.SignUpRepo;

@Service
public class SignUpService {
	@Autowired
	private SignUpRepo signUpRepo;
	public void insert(User user) {
		signUpRepo.insertUser(user.getEmail(), user.getPassword());
	}
}