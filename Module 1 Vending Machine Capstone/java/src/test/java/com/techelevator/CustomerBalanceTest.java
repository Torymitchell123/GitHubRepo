package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class CustomerBalanceTest {
	CustomerBalance testBalance = new CustomerBalance();
	
	@Test
	public void add_money_test() {
		testBalance.addToCurrentBalance(BigDecimal.valueOf(5));
		assertEquals(5, testBalance.getCurrentBalance().doubleValue(), .005);
	}
	
	@Test
	public void return_balance() {
		assertEquals(0.00, testBalance.getCurrentBalance().doubleValue(), 0.05);
	}
	
	@Test
	public void sub_money_test() {
		testBalance.subFromCurrentBalance(BigDecimal.valueOf(5));
		assertEquals(0, testBalance.getCurrentBalance().doubleValue(), 0.05);
	}
	@Test
	public void sub_money_test_if_greater_than_balance() {
		testBalance.addToCurrentBalance(BigDecimal.valueOf(10));
		testBalance.subFromCurrentBalance(BigDecimal.valueOf(15));
		assertEquals(10, testBalance.getCurrentBalance().doubleValue(), 0.05);
	}
	@Test
	public void to_string_test() {
		assertEquals("You have $0 remaining", testBalance.toString());
	}
	
	@Test 
	public void balance_to_zero_test() {
		testBalance.addToCurrentBalance(BigDecimal.valueOf(5));
		testBalance.returnToZero();
		assertEquals(0, testBalance.getCurrentBalance().doubleValue(), 0.05);
	}
	
	@Test
	public void convert_to_currency_test() {
		assertEquals("$0.00", testBalance.formatBalanceToCurrency());
	}





}
