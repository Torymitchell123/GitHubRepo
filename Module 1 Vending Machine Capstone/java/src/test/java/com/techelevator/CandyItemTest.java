package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Test;

public class CandyItemTest {
	CandyItem candy = new CandyItem();
	BigDecimal test = new BigDecimal("1.00");

	@Test
	public void buy_item_when_qty_greater_than_zero() {
		candy.buyItem();
		assertEquals(4, candy.getQuantity());
	}
	@Test
	public void buy_item_when_qty_is_zero_or_less() {
		candy.buyItem();
		candy.buyItem();
		candy.buyItem();
		candy.buyItem();
		candy.buyItem();
		candy.buyItem();
		assertEquals(0, candy.getQuantity());
	}
	@Test
	public void test_to_string() {
		candy.setPrice(test);
		candy.setName("dorito");
		assertEquals("dorito | $1.00 | 5 remaining", candy.toString());
	}
	

}

	

