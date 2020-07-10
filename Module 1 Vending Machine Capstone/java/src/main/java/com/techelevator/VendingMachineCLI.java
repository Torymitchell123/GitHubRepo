package com.techelevator;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.change.MakeChange;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, "4"};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private static final String FEED_MONEY_1 = "add $1";
	private static final String FEED_MONEY_2 = "add $2";
	private static final String FEED_MONEY_5 = "add $5";
	private static final String FEED_MONEY_10 = "add $10";
	private static final String[] FEED_MONEY_OPTIONS = { FEED_MONEY_1, FEED_MONEY_2, FEED_MONEY_5, FEED_MONEY_10 };

	private Menu menu;
	
	Map<String, MasterVendingClass> myMap = new LinkedHashMap<>();
	VendingItemList holdItem = new VendingItemList();
	CustomerBalance myBalance = new CustomerBalance();
	CustomerBalance balanceForLog = new CustomerBalance();
	Scanner input = new Scanner(System.in);
	MakeChange customerChange = new MakeChange();
	List<String> logList = new ArrayList<>();
	String dateTime = (LocalDate.now() + " " + LocalTime.now());
	private Map<String, Integer> salesReport = new LinkedHashMap<>();
	VendingMachineSalesReport fileWriter = new VendingMachineSalesReport();
	
	public VendingMachineCLI(Menu menu) throws Exception {
		salesReport = fileWriter.loadPastSales();
		this.menu = menu;
	}
	
	//Feed money option was written into a method
	public void displayFeedMoneyMenu() throws Exception {
		while (true) {
			System.out.println("\n");
			System.out.println("Current Money Provided: " + "$" + String.format("%.2f", myBalance.getCurrentBalance()));
			String feedMoneyChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);
			balanceForLog.returnToZero();

			switch (feedMoneyChoice) {
			case FEED_MONEY_1:
				myBalance.addToCurrentBalance(BigDecimal.valueOf(1));
				balanceForLog.addToCurrentBalance(BigDecimal.valueOf(1));
				break;
			case FEED_MONEY_2:
				myBalance.addToCurrentBalance(BigDecimal.valueOf(2));
				balanceForLog.addToCurrentBalance(BigDecimal.valueOf(2));
				break;
			case FEED_MONEY_5:
				myBalance.addToCurrentBalance(BigDecimal.valueOf(5));
				balanceForLog.addToCurrentBalance(BigDecimal.valueOf(5));
				break;
			case FEED_MONEY_10:
				myBalance.addToCurrentBalance(BigDecimal.valueOf(10));
				balanceForLog.addToCurrentBalance(BigDecimal.valueOf(10));
				break;
			} logList.add(dateTime + " FEED MONEY: " + balanceForLog.formatBalanceToCurrency() + " " + myBalance.formatBalanceToCurrency());
			return;
		}
	}
	@SuppressWarnings("rawtypes") //needed for for each loop line 88 Map.Entry
	public void displayMainMenu() throws Exception {  // ----------------->>> display MainMenu was method originally provided
		myMap = holdItem.getVendingItemMap();
		while (true) {
			String choice = (String) menu.getChoiceFromMainOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (Map.Entry mapElement : myMap.entrySet()) {
					System.out.println(mapElement.getKey() + " | " + mapElement.getValue().toString());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayPurchaseMenu(); //-------------------------->>> calling the display purchase menu below	
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Now Exiting Vending Machine");
				System.exit(1);
			} else if (choice.equals("4")) {
				VendingMachineSalesReport mySalesReport = new VendingMachineSalesReport("SalesReport.txt");
				mySalesReport.writeDateTimeTotalSpent(dateTime);
				mySalesReport.Write(salesReport);
				mySalesReport.writeTotalSpent();
				mySalesReport.close();
				System.out.println("\nSales Report Generated");
		} 
	}
	}
	@SuppressWarnings("rawtypes") 
	public void displayPurchaseMenu() throws Exception { // --------->>> purchase menu written into a method that displays when option 2 is selected from main menu
		while (true) {
			String secondChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (secondChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
				displayFeedMoneyMenu();

			} else if (secondChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				System.out.println();
				for (Map.Entry mapElement : myMap.entrySet()) {
					System.out.println(mapElement.getKey() + " | " + mapElement.getValue().toString());
					}
					System.out.println();
					System.out.println("You currently have " + myBalance.formatBalanceToCurrency() + " to spend");
					System.out.println();
					System.out.print("Please select an option to purchase from above: ");
					String purchaseChoice = input.nextLine();
					
					if(myMap.get(purchaseChoice) == null) {
						System.out.println("Invalid choice please try again");
					}	else if (myMap.get(purchaseChoice).getPrice().doubleValue() > myBalance.getCurrentBalance().doubleValue()) {
							System.out.println("\n**PLEASE ADD FUNDS**");
					}	else {
							balanceForLog.returnToZero();
							balanceForLog.addToCurrentBalance(myBalance.getCurrentBalance());
							myMap.get(purchaseChoice).buyItem(); // ------------------------->>>**extra add quantity selection**
							myBalance.subFromCurrentBalance(myMap.get(purchaseChoice).getPrice());
							System.out.println("You have " + myBalance.formatBalanceToCurrency() + " remaining");
							
									logList.add(dateTime + " " + myMap.get(purchaseChoice).getName() + " " + myMap.get(purchaseChoice).getSlotNum() + 
									" " + balanceForLog.formatBalanceToCurrency() + " " +  myBalance.formatBalanceToCurrency());
					
									fileWriter.addSales(salesReport, myMap.get(purchaseChoice).getName());
									fileWriter.addToTotalMoniesSpent(myMap.get(purchaseChoice).getPrice());
					}
					
			} else if (secondChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				balanceForLog.returnToZero();
				balanceForLog.addToCurrentBalance(myBalance.getCurrentBalance());;
				System.out.println();
				System.out.println("Thank you for shopping, your change is " + customerChange.makeChange(myBalance.getCurrentBalance()));
				System.out.println("\nObama would be proud!");
				myBalance.returnToZero();
				
									logList.add(dateTime + " " + "GIVE CHANGE: " + balanceForLog.formatBalanceToCurrency() + " " + myBalance.formatBalanceToCurrency());
									
									File logFile = new File("Log.txt");
									logFile.createNewFile();
									try (PrintWriter file = new PrintWriter(logFile)) {  //because we do not have the program running within a single method, we were unable to create a 
									for (String line : logList) {						// logger file and method that writes to it, thus we instantiated a list, adding log info to the list 
										file.println(line);								// <------- code to the left writes each line of the log list to a file	
									}		
			} return;
		}
	}
}

	public static void main(String[] args) throws Throwable {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.displayMainMenu();
		
	}

}
