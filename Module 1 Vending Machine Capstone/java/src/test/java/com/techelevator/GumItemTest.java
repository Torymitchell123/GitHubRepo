package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class GumItemTest {

	GumItem gum = new GumItem();
	BigDecimal test = new BigDecimal("1.00");

	@Test
	public void buy_item_when_qty_greater_than_zero() {
		gum.buyItem();
		assertEquals(4, gum.getQuantity());
	}
	@Test
	public void buy_item_when_qty_is_zero_or_less() {
		gum.buyItem();
		gum.buyItem();
		gum.buyItem();
		gum.buyItem();
		gum.buyItem();
		gum.buyItem();
		assertEquals(0, gum.getQuantity());
	}
	@Test
	public void test_to_string() {
		gum.setPrice(test);
		gum.setName("dorito");
		assertEquals("dorito | $1.00 | 5 remaining", gum.toString());
	}
	

}

