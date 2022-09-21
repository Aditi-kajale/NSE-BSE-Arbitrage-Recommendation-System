package com.citi.ver1.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue
	private int stockId;
	private String companyName;
	private BigDecimal closeNSE;
	private BigDecimal closeBSE;
	private BigDecimal diff;
	private BigDecimal percDiff;
	private String dateTime;
	private String email;
	private double quantity;
	private double profit;
	
	public Cart(int stockId, String companyName, BigDecimal closeNSE, BigDecimal closeBSE, BigDecimal diff,
			BigDecimal percDiff, String dateTime, String email, double quantity, double profit) {
		super();
		this.stockId = stockId;
		this.companyName = companyName;
		this.closeNSE = closeNSE;
		this.closeBSE = closeBSE;
		this.diff = diff;
		this.percDiff = percDiff;
		this.dateTime = dateTime;
		this.email = email;
		this.quantity = quantity;
		this.profit = profit;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public int getStockId() {
		return stockId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public BigDecimal getCloseNSE() {
		return closeNSE;
	}
	public void setCloseNSE(BigDecimal closeNSE) {
		this.closeNSE = closeNSE;
	}
	public BigDecimal getCloseBSE() {
		return closeBSE;
	}
	public void setCloseBSE(BigDecimal closeBSE) {
		this.closeBSE = closeBSE;
	}
	public BigDecimal getDiff() {
		return diff;
	}
	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}
	public Cart(int stockId, String companyName, BigDecimal closeNSE, BigDecimal closeBSE, BigDecimal diff,
			BigDecimal percDiff, String dateTime, String email, double quantity) {
		super();
		this.stockId = stockId;
		this.companyName = companyName;
		this.closeNSE = closeNSE;
		this.closeBSE = closeBSE;
		this.diff = diff;
		this.percDiff = percDiff;
		this.dateTime = dateTime;
		this.email = email;
		this.quantity = quantity;
	}
	public BigDecimal getPercDiff() {
		return percDiff;
	}
	public void setPercDiff(BigDecimal percDiff) {
		this.percDiff = percDiff;
	}
	public Cart(int stockId, String companyName, BigDecimal closeNSE, BigDecimal closeBSE, BigDecimal diff,
			BigDecimal percDiff, String dateTime, String email) {
		super();
		this.stockId = stockId;
		this.companyName = companyName;
		this.closeNSE = closeNSE;
		this.closeBSE = closeBSE;
		this.diff = diff;
		this.percDiff = percDiff;
		this.dateTime = dateTime;
		this.email = email;
	}
	public Cart() {
		super();
	}
	public Cart(String companyName, BigDecimal closeNSE, BigDecimal closeBSE, BigDecimal diff, BigDecimal percDiff,
			String dateTime, String email) {
		super();
		this.companyName = companyName;
		this.closeNSE = closeNSE;
		this.closeBSE = closeBSE;
		this.diff = diff;
		this.percDiff = percDiff;
		this.dateTime = dateTime;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String date) {
		
		this.dateTime = date;
	}
	@Override
	public String toString() {
		return "Cart [stockId=" + stockId + ", email=" + email + ", companyName=" + companyName
				+ ", closeNSE=" + closeNSE + ", closeBSE=" + closeBSE + ", diff=" + diff + ", percDiff=" + percDiff
				+ ", dateTime=" + dateTime + "]";
	}
	
	
}
