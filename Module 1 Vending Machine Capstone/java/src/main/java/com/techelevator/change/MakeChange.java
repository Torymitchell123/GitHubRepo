package com.techelevator.change;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class MakeChange {

		public String makeChange (BigDecimal currentBalance) {
		Coin[] coins = new Coin[] {new Quarter(), new Dime(), new Nickel()};
		double converter = (currentBalance.doubleValue() * 100);
		String result = "";
		
		LinkedHashMap<Coin, Integer> change = new LinkedHashMap<>();
		
			for (Coin coin : coins) { 
			
				if (converter <= 0) { break; }
				int cnt = (int)converter / coin.getValue();
				if (cnt > 0) {
					converter = (double)((int)converter % (coin.getValue() * cnt));
					change.put(coin, cnt);
			}
		}
		
			for (Coin coin : change.keySet()) {
				result += (change.get(coin) + " " + coin.getName() + "(s)" + " "); 
		}
		
		
		return result;
	}
	
	
}
