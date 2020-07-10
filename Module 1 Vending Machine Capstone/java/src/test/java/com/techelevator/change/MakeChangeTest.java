package com.techelevator.change;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MakeChangeTest {

	MakeChange myTest = new MakeChange();
	
	@Test
	public void test_return_quarters_only() {
		assertEquals("8 Quarter(s) ", myTest.makeChange(BigDecimal.valueOf(2)));
	}
	
	@Test
	public void test_return_dimes_only() {
		assertEquals("2 Dime(s) ", myTest.makeChange(BigDecimal.valueOf(.20)));
	}
	
	@Test
	public void test_return_nickles_only() {
		assertEquals("1 Nickel(s) ", myTest.makeChange(BigDecimal.valueOf(.05)));
	}
	
	@Test
	public void test_return_multiple_coins() {
		assertEquals("4 Quarter(s) 2 Dime(s) ", myTest.makeChange(BigDecimal.valueOf(1.2)));
	}

}
