package com.citi.ver1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.citi.ver1.BL.LoginService;
import com.citi.ver1.BL.ProcessStocks;
import com.citi.ver1.dto.Stock;
import com.citi.ver1.dto.Cart;
import com.citi.ver1.dto.User;
import com.citi.ver1.repository.Login_Repo;
import com.citi.ver1.repository.User_Cart_Repo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
	private String curEmail;
	
	@Autowired(required=false)
	private ProcessStocks processStocks;
	
	@Autowired(required=false)
	User_Cart_Repo ucr;
	
	@RequestMapping(method=RequestMethod.POST,value="/topic")
	public String addStock(@RequestBody Cart user_stock) {
		DateFormat dfor = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date obj = new Date();
		user_stock.setDateTime(dfor.format(obj));
		ucr.save(user_stock);
		return "insert succ";
	}
	
	@PostMapping(value = "/login", consumes = {"application/json"})
	public ResponseEntity<?> Login(@RequestBody User user2)
	{
		Optional<User> user1=loginRepo.findById(user2.getEmail());
		System.out.println(user1);
		try
		{
			if(user1.get().getPassword().compareTo(user2.getPassword()) == 0)
			{
				System.out.println("succ");
				return ResponseEntity.ok(user1);
			}
			else
			{
				System.out.println("fail");

				return (ResponseEntity<?>) ResponseEntity.internalServerError();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();		
	}
	
	
	@RequestMapping("/top")
	public List<Stock> disp(){
		return processStocks.sendTopStocks();
	}
	
	@Autowired
	Login_Repo loginRepo;
	
	@Autowired 
	LoginService loginService;
	
	@RequestMapping(method=RequestMethod.POST,value="/reg")
	public String loginUser(@RequestBody User user) {
		loginRepo.save(user);
		return "inserted";
		
	}
	
	@PostMapping("/find/{email}/{password}")
	public String findUserFromDB(@PathVariable String email,@PathVariable String password){
		if(loginService.findUserFromDB(email,password).size()==0) {
			return "failed";
		}
		else {
			curEmail=email;
			return "succ";
		}
	}
	
	@GetMapping("/findStock")
	public List<Cart> findStocksFromDB(String username) {
		// TODO Auto-generated method stub
		return ucr.findAllStocks(curEmail);
	}
	
	
}