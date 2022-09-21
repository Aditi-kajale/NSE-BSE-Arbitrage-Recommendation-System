package com.citi.ver1.BL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.Stock;
import com.citi.ver1.dto.Cart;
import com.citi.ver1.repository.User_Cart_Repo;

import yahoofinance.YahooFinance;

@SpringBootApplication
@Service
//a class for taking the stock data at a particular time instant and finding best arbitrage opportunities
public class ProcessStocks {

	public static void sortStocks(ArrayList<Stock>arbOpport) {
		Collections.sort(arbOpport,new StockComparator());
	}
	
	public List<Stock> sendTopStocks() {
		ArrayList<Stock>arbOpport = new ArrayList<>();
		try {
			arbOpport=getStockData();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		List<Stock>topFive=new ArrayList<Stock>();
		
		for(int i=0;i<5;i++) {
			topFive.add(arbOpport.get(i));
		}
		return topFive;
	}
	
	public List<Stock> sendLiveStocks() {
		ArrayList<Stock>arbOpport = new ArrayList<>();
		try {
			arbOpport=getStockData();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return arbOpport;
	}
	
	
	public static ArrayList<Stock> getStockData() throws InterruptedException, IOException{
		String[] symbols = new String[] {
				"APOLLOHOSP.NS","APOLLOHOSP.BO",
				"ASIANPAINT.NS","ASIANPAINT.BO",
				"AXISBANK.NS","AXISBANK.BO",
				"ADANIPORTS.NS","ADANIPORTS.BO",
				"BAJAJ-AUTO.NS","BAJAJ-AUTO.BO",
				"BAJFINANCE.NS","BAJFINANCE.BO",
				"BAJAJFINSV.NS","BAJAJFINSV.BO",
				"BHARTIARTL.NS","BHARTIARTL.BO",
				"BPCL.NS","BPCL.BO",
				"BRITANNIA.NS","BRITANNIA.BO",
				"CIPLA.NS","CIPLA.BO",
				"COALINDIA.NS","COALINDIA.BO",
				"DIVISLAB.NS","DIVISLAB.BO",
				"DRREDDY.NS","DRREDDY.BO",
				"EICHERMOT.NS","EICHERMOT.BO",
				"GRASIM.NS","GRASIM.BO",
				"HCLTECH.NS","HCLTECH.BO",
				"HDFC.NS","HDFC.BO",
				"HDFCLIFE.NS","HDFCLIFE.BO",
				"HDFCBANK.NS","HDFCBANK.BO",
				"HEROMOTOCO.NS","HEROMOTOCO.BO",
				"HINDALCO.NS","HINDALCO.BO",
				"HINDUNILVR.NS","HINDUNILVR.BO",
				"ICICIBANK.NS","ICICIBANK.BO",
				"ITC.NS","ITC.BO",
				"INDUSINDBK.NS","INDUSINDBK.BO",
				"INFY.NS","INFY.BO",
				"JSWSTEEL.NS","JSWSTEEL.BO",
				"KOTAKBANK.NS","KOTAKBANK.BO",
				"LT.NS","LT.BO",
				"M&M.NS","M&M.BO",
				"MARUTI.NS","MARUTI.BO",
				"NESTLEIND.NS","NESTLEIND.BO",
				"NTPC.NS","NTPC.BO",
				"ONGC.NS","ONGC.BO",
				"POWERGRID.NS","POWERGRID.BO",
				"RELIANCE.NS","RELIANCE.BO",
				"SBILIFE.NS","SBILIFE.BO",
				"SBIN.NS","SBIN.BO",
				"SHREECEM.NS","SHREECEM.BO",
				"SUNPHARMA.NS","SUNPHARMA.BO",
				"TCS.NS","TCS.BO",
				"TECHM.NS","TECHM.BO",
				"TITAN.NS","TITAN.BO"  ,
				"TATASTEEL.NS","TATASTEEL.BO",
				"TATACONSUM.NS","TATACONSUM.BO",
				"TATAMOTORS.NS","TATAMOTORS.BO",
				"UPL.NS","UPL.BO",
				"ULTRACEMCO.NS","ULTRACEMCO.BO",
				"WIPRO.NS","WIPRO.BO",
				
		};

		ArrayList<Stock>arbOpport = new ArrayList<>();
		Set<String>set=new HashSet<String>();
		Map<String, yahoofinance.Stock> stocks = YahooFinance.get(symbols); // single request
		for(int i=0;i<symbols.length;i=i+2) {
			yahoofinance.Stock nse = stocks.get(symbols[i]);
			yahoofinance.Stock bse = stocks.get(symbols[i+1]);
			arbOpport.add(new Stock(nse.getName(),nse.getQuote().getPrice(),bse.getQuote().getPrice()));
		}
		
		sortStocks(arbOpport);	
		return arbOpport;
	}

	

}