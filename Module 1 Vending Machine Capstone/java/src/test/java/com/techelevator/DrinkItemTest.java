package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class DrinkItemTest {

	DrinkItem drink = new DrinkItem();
	BigDecimal test = new BigDecimal("1.00");

	@Test
	public void buy_item_when_qty_greater_than_zero() {
		drink.buyItem();
		assertEquals(4, drink.getQuantity());
	}
	@Test
	public void buy_item_when_qty_is_zero_or_less() {
		drink.buyItem();
		drink.buyItem();
		drink.buyItem();
		drink.buyItem();
		drink.buyItem();
		drink.buyItem();
		assertEquals(0, drink.getQuantity());
	}
	@Test
	public void test_to_string() {
		drink.setPrice(test);
		drink.setName("dorito");
		assertEquals("dorito | $1.00 | 5 remaining", drink.toString());
	}
	

}



