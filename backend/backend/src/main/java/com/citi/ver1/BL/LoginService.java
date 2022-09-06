package com.citi.ver1.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.User;
import com.citi.ver1.repository.Login_Repo;


@Service
public class LoginService {
	@Autowired
	private Login_Repo loginRepo;
	public void addUser(User userD) {
		loginRepo.save(userD);
	}
	public List<User> findUserFromDB(String email, String password) {
		// TODO Auto-generated method stub
		return loginRepo.findUser(email, password);
	}
	
}
