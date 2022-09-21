package com.citi.ver1.BL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.User;
import com.citi.ver1.repository.Login_Repo;


@Service
public class LoginService {
	@Autowired
	private Login_Repo loginRepo;
	public void addUser(User user) {
		loginRepo.save(user);
	}
	public List<User> findUserFromDB(String email, String password) {
		return loginRepo.findUser(email, password);
	}
	public Optional<User> findUser(String email) {
		return loginRepo.findById(email);
	}
	
	
}
