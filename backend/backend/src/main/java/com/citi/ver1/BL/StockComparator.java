package com.citi.ver1.BL;
import com.citi.ver1.dto.Stock;
import java.util.Comparator;

public class StockComparator implements Comparator<Stock>{
	 public int compare(Stock s1, Stock s2) {
	        if (s1.getPercDiff() == s2.getPercDiff())
	            return 0;
	        else if (s1.getPercDiff().compareTo(s2.getPercDiff())<0)
	            return 1;
	        else
	            return -1;
	  }
}
