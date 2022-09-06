package com.citi.ver1.BL;
import com.citi.ver1.dto.Stock;
import java.util.Comparator;

public class StockComparator implements Comparator<Stock>{
	 public int compare(Stock s1, Stock s2) {
	        if (s1.getDiff() == s2.getDiff())
	            return 0;
	        else if (s1.getDiff().compareTo(s2.getDiff())<0)
	            return 1;
	        else
	            return -1;
	  }
}
