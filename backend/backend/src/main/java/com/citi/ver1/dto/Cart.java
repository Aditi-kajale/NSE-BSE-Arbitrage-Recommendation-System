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
	public int getStockId() {
		return stockId;
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
	public BigDecimal getPercDiff() {
		return percDiff;
	}
	public void setPercDiff(BigDecimal percDiff) {
		this.percDiff = percDiff;
	}
	public String getUsername() {
		return email;
	}
	public void setUsername(String username) {
		this.email = username;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String date) {
		
		this.dateTime = date;
	}
	@Override
	public String toString() {
		return "User_Cart [stockId=" + stockId + ", email=" + email + ", companyName=" + companyName
				+ ", closeNSE=" + closeNSE + ", closeBSE=" + closeBSE + ", diff=" + diff + ", percDiff=" + percDiff
				+ ", dateTime=" + dateTime + "]";
	}
	
	
}
