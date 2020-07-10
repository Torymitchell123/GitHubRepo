package com.techelevator;



import java.io.FileNotFoundException;


import org.junit.Test;

public class VendingItemListTest {

	VendingItemList items = new VendingItemList();
	
	@Test
	public void print_menu_test() throws FileNotFoundException {
		System.out.println(items.getVendingItemMap());
	}
	
}
