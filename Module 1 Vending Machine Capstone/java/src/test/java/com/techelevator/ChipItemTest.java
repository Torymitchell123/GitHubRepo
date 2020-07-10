package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ChipItemTest {
	ChipItem chip = new ChipItem();
	BigDecimal test = new BigDecimal("1.00");

	@Test
	public void buy_item_when_qty_greater_than_zero() {
		chip.buyItem();
		assertEquals(4, chip.getQuantity());
	}
	@Test
	public void buy_item_when_qty_is_zero_or_less() {
		chip.buyItem();
		chip.buyItem();
		chip.buyItem();
		chip.buyItem();
		chip.buyItem();
		chip.buyItem();
		assertEquals(0, chip.getQuantity());
	}
	@Test
	public void test_to_string() {
		chip.setPrice(test);
		chip.setName("dorito");
		assertEquals("dorito | $1.00 | 5 remaining", chip.toString());
	}
	

}
