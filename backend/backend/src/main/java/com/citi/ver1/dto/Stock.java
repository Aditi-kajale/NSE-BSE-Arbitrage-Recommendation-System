package com.citi.ver1.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Stock {
	private String companyName;
	private BigDecimal closeNSE;
	private BigDecimal closeBSE;
	private BigDecimal diff;
	private BigDecimal percDiff;
	
	public Stock(String companyName, BigDecimal closeNSE, BigDecimal closeBSE) {
		super();
		this.companyName = companyName;
		this.closeNSE = closeNSE;
		this.closeBSE = closeBSE;
		this.diff=(closeNSE.subtract(closeBSE)).abs();
		this.percDiff=this.diff.divide(closeBSE,4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
		
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
	@Override
	public String toString() {
		return "Stock [companyName=" + companyName + ", closeNSE=" + closeNSE + ", closeBSE=" + closeBSE + ", diff="
				+ diff + ", percDiff=" + percDiff + "]";
	}
}
