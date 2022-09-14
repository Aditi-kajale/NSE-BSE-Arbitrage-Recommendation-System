package com.citi.ver1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.ver1.BL.LoginService;
import com.citi.ver1.BL.ProcessStocks;
import com.citi.ver1.BL.SaveStockService;

import com.citi.ver1.BL.SavedStocksService;
import com.citi.ver1.BL.SignUpService;
import com.citi.ver1.dto.Cart;
import com.citi.ver1.dto.Stock;
import com.citi.ver1.dto.User;
import com.citi.ver1.repository.Login_Repo;
import com.citi.ver1.repository.SignUpRepo;
import com.citi.ver1.repository.User_Cart_Repo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
	private String curEmail;
	
	@Autowired(required=false)
	private ProcessStocks processStocks;
	
	@Autowired
	Login_Repo loginRepo;
	
	@Autowired 
	SaveStockService saveStockService;
	@Autowired 
	SavedStocksService savedStocksService;
	
	
	@Autowired
	User_Cart_Repo user_Cart_Repo;
	
	@Autowired 
	LoginService loginService;
	
	
	@Autowired
	SignUpRepo SignUpRepo;
	
	@Autowired 
	SignUpService signUpService;
	
	@PostMapping(value = "/login", consumes = {"application/json"})
	public ResponseEntity<?> Login(@RequestBody User user2)
	{
		Optional<User> user1=loginRepo.findById(user2.getEmail());
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
	
	
	@PostMapping(value = "/signUp", consumes = {"application/json"})
	public ResponseEntity<?> signUp(@RequestBody User user)
	{
		System.out.println(user);
		try
		{
			signUpService.insert(user);
			return ResponseEntity.ok(user);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();	
	}

	
	@PostMapping(value = "/save", consumes = {"application/json"})
	public ResponseEntity<?> addStock(@RequestBody Cart stock) {
		try
		{
			DateFormat dfor = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date obj = new Date();
			stock.setDateTime(dfor.format(obj));
			saveStockService.insert(stock);
			return ResponseEntity.ok(stock);
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
	
	@RequestMapping("/liveStocks")
	public List<Stock> sendLiveStocks(){
		return processStocks.sendLiveStocks();
	}
	
	@PostMapping(value = "/savedStocks", consumes = {"application/json"})
	public List<Cart> sendSaveStocks(@RequestBody User user){
		System.out.println(user.getEmail());
		return savedStocksService.savedStocks(user.getEmail());
	}

}
