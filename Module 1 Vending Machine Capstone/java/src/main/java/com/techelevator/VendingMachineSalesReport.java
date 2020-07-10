package com.techelevator;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineSalesReport implements Closeable{

	private final File salesReport = new File("SalesReport.txt");
	private PrintWriter writer;
	private double totalMoniesSpent;
	Map<String, Integer> nullMap = new LinkedHashMap<>();
	
	public VendingMachineSalesReport() {	
	}
	
	public VendingMachineSalesReport(String salesReport) throws Exception {
		if (!this.salesReport.exists()) {
		this.salesReport.createNewFile();
		writer = new PrintWriter(this.salesReport);
		} else {
			writer = new PrintWriter(this.salesReport);
		}
	}
	
	public Map<String, Integer> loadPastSales() throws FileNotFoundException {
	if (salesReport.exists()) {
		Map<String, Integer> salesHistoryMap = new LinkedHashMap<>();
		Scanner pastSalesReader = new Scanner(salesReport);
		while (pastSalesReader.hasNextLine()) {
			String pastSale = pastSalesReader.nextLine();
			String[] salesHistoryArray = pastSale.split("\\|");
			String[] getSales = pastSale.split("$");
				if (salesHistoryArray.length < 2) {
					break;
				} else if (salesHistoryArray[0].startsWith("**")) {
					totalMoniesSpent = Double.parseDouble(salesHistoryArray[0]);
				} else {
					salesHistoryMap.put(salesHistoryArray[0], Integer.parseInt(salesHistoryArray[1]));
		} 
	} return salesHistoryMap;
} else {
	return nullMap;
}
	}
	
	public void addSales(Map<String, Integer> valueMap, String itemName) {
		if (!valueMap.containsKey(itemName)) {
			valueMap.put(itemName, 1);
		} else valueMap.put(itemName, valueMap.get(itemName) + 1);
	}
	
	public void Write(Map<String, Integer> newValueMap) {
		try{
			for (String value : newValueMap.keySet()) {	
			writer.println(value + "|" + newValueMap.get(value));
			writer.flush();
		}
		}
	
		catch(Exception ex)
		{
			throw ex;
		}

	}
	
	public void writeDateTimeTotalSpent(String dateTime) {
			writer.println(dateTime);
		}

	public void addToTotalMoniesSpent(BigDecimal itemValue) {
		totalMoniesSpent += itemValue.doubleValue();
	}
	
	public double getTotalMoniesSpent() {
		return totalMoniesSpent;
	}
	
	public void writeTotalSpent() {
		writer.println("***TOTAL MONIES SPENT***" + " " + totalMoniesSpent);
	}
	
	public void writeMessage(String message) {
		writer.println(message);
	}
	
	@Override
	public void close() throws IOException {

		try{
			writer.close();
		}
		catch(Exception ex) {
			throw new IOException(ex.getMessage());
		}
		finally {}
		
	}
	
}