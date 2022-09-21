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
	
	@Autowired
	private ProcessStocks processStocks;
	
	@Autowired 
	private SaveStockService saveStockService;
	
	@Autowired 
	private SavedStocksService savedStocksService;
	
	@Autowired 
	private LoginService loginService;

	@Autowired 
	private SignUpService signUpService;
	
	@PostMapping(value = "/login", consumes = {"application/json"})
	public ResponseEntity<?> login(@RequestBody User user)
	{
		Optional<User> user1=loginService.findUser(user.getEmail());
		try
		{
			if(user1.get().getPassword().compareTo(user.getPassword()) == 0)
			{
				return ResponseEntity.ok(user1);
			}
			else
			{
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
	public ResponseEntity<?> saveStock(@RequestBody Cart stock) {
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
	public List<Stock> topStocks(){
		return processStocks.sendTopStocks();
	}
	
	@RequestMapping("/liveStocks")
	public List<Stock> liveStocks(){
		return processStocks.sendLiveStocks();
	}
	
	@PostMapping(value = "/savedStocks", consumes = {"application/json"})
	public List<Cart> saveStock(@RequestBody User user){
		return savedStocksService.savedStocks(user.getEmail());
	}
	
	@PostMapping(value = "/delete", consumes = {"application/json"})
	public ResponseEntity<?> deleteStock(@RequestBody Cart stock) {
		try
		{
			saveStockService.deleteStock(stock);
			return ResponseEntity.ok(stock);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();		
	}
}
