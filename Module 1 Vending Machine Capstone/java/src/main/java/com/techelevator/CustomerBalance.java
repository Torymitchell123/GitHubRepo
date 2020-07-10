package com.techelevator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomerBalance {

	private BigDecimal currentBalance =  new BigDecimal(0);
	private BigDecimal zeroBalance = new BigDecimal(0);
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void addToCurrentBalance(BigDecimal addAmount) {
		currentBalance = currentBalance.add(addAmount);
	}

	public void subFromCurrentBalance(BigDecimal subAmount) {
		if (subAmount.doubleValue() <= currentBalance.doubleValue()) {
			currentBalance = currentBalance.subtract(subAmount);
		}
	}

	public void returnToZero() {
	currentBalance = zeroBalance;
	}

	public String toString() {
		return "You have $" + getCurrentBalance() + " remaining";
	}
	
	public String formatBalanceToCurrency() {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		return numberFormat.format(currentBalance.doubleValue());
	}
}
