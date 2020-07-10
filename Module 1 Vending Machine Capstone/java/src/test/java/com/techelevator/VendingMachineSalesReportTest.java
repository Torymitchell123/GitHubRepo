package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class VendingMachineSalesReportTest {
	VendingMachineSalesReport fileIn = new VendingMachineSalesReport(); 
	Map<String, Integer> testMapIn = new HashMap<>();
	
	@Test
	public void read_in_file_and_copy_values_to_map_test() throws FileNotFoundException {
		testMapIn = fileIn.loadPastSales();
		assertEquals(testMapIn, fileIn.loadPastSales());
		System.out.println(testMapIn);
	}
	
	@Test
	public void add_sales_to_map_test() throws FileNotFoundException {
		testMapIn = fileIn.loadPastSales();
		Integer oldValue = testMapIn.get("Potato Crisps");
		Integer expected = ++oldValue;
		fileIn.addSales(testMapIn, "Potato Crisps");
		assertEquals(expected, testMapIn.get("Potato Crisps"));
	}
	

}
