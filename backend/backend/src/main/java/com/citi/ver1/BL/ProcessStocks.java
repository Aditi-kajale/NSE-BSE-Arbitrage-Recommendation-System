package com.citi.ver1.BL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.Stock;
import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;

import yahoofinance.YahooFinance;

@SpringBootApplication
@Service
public class ProcessStocks {
	//a class for taking the stock data
	//at a particular time instant
	//and finding best arbitrage opportunities
	
	private static ArrayList<Stock>arbOpport=new ArrayList<>();
	public static void sortStocks() {
		Collections.sort(arbOpport,new StockComparator());
	}
	public List<Stock> sendTopStocks() {
		try {
			proc();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		List<Stock>topFive=new ArrayList<Stock>();
		
//		for(int i=0;i<5;i++) {
//			 
//			topFive.add(arbOpport.get(i));
//		}
		//arbOpport.clear();
		return arbOpport;
		
	}
	public static void proc() throws InterruptedException, IOException{
		
			 
		String[] symbols = new String[] {
				"APOLLOHOSP.NS","APOLLOHOSP.BO",
				"ASIANPAINT.NS","ASIANPAINT.BO",
				"AXISBANK.NS","AXISBANK.BO",
				"BAJAJ-AUTO.NS","BAJAJ-AUTO.BO",
				"BAJFINANCE.NS","BAJFINANCE.BO",
				"BHARTIARTL.NS","BHARTIARTL.BO",
				////
				"BPCL.NS","BPCL.BO",
				"BRITANNIA.NS","BRITANNIA.BO",
				"CIPLA.NS","CIPLA.BO",
				"COALINDIA.NS","COALINDIA.BO",
				"EICHERMOT.NS","EICHERMOT.BO",
				"HCLTECH.NS","HCLTECH.BO",
				////
				"HDFC.NS","HDFC.BO",
				"HEROMOTOCO.NS","HEROMOTOCO.BO",
				"ICICIBANK.NS","ICICIBANK.BO",
				"ITC.NS","ITC.BO",
				"INFY.NS","INFY.BO",
				"JSWSTEEL.NS","JSWSTEEL.BO",
				////
				"M&M.NS","M&M.BO",
				"RELIANCE.NS","RELIANCE.BO",
				"NESTLEIND.NS","NESTLEIND.BO",
				"ONGC.NS","ONGC.BO",
				"POWERGRID.NS","POWERGRID.BO",
				"SBILIFE.NS","SBILIFE.BO",
				/////
				"TCS.NS","TCS.BO",
				"TECHM.NS","TECHM.BO",
				"UPL.NS","UPL.BO",
				"WIPRO.NS","WIPRO.BO",

		};
//		

		Map<String, yahoofinance.Stock> stocks = YahooFinance.get(symbols); // single request
		for(int i=0;i<symbols.length;i=i+2) {
			//System.out.println(i);
			yahoofinance.Stock nse = stocks.get(symbols[i]);
			yahoofinance.Stock bse = stocks.get(symbols[i+1]);
			arbOpport.add(new Stock(nse.getName(),nse.getQuote().getPrice(),bse.getQuote().getPrice()));
		}
		
		sortStocks();	
		
	}
	

}