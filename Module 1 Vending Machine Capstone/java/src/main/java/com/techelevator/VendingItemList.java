package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingItemList {

	public Map<String, MasterVendingClass> getVendingItemMap() throws FileNotFoundException {

		File itemFile = new File(
				"vendingmachine.csv");

		Map<String, MasterVendingClass> availableItems = new LinkedHashMap<>();

		Scanner fileReader = new Scanner(itemFile);

		while (fileReader.hasNextLine()) {
			String vendingItem = fileReader.nextLine();
			String[] holdItem = vendingItem.split("\\|");
			BigDecimal price = new BigDecimal(holdItem[2]);
			
			switch (holdItem[3]) { //Changed this doc to Big decimal only via changing the setPrice line and instantiating BigDecimal Price

			case "Chip":
				ChipItem chipItem = new ChipItem();
				chipItem.setName(holdItem[1]);
				chipItem.setSlotNum(holdItem[0]);
				chipItem.setPrice(price);
				availableItems.put(holdItem[0], chipItem);
				break;

			case "Candy":
				CandyItem candyItem = new CandyItem();
				candyItem.setName(holdItem[1]);
				candyItem.setSlotNum(holdItem[0]);
				candyItem.setPrice(price);
				availableItems.put(holdItem[0], candyItem);
				break;

			case "Drink":
				DrinkItem drinkItem = new DrinkItem();
				drinkItem.setName(holdItem[1]);
				drinkItem.setSlotNum(holdItem[0]);
				drinkItem.setPrice(price);
				availableItems.put(holdItem[0], drinkItem);
				break;

			case "Gum":
				GumItem gumItem = new GumItem();
				gumItem.setName(holdItem[1]);
				gumItem.setSlotNum(holdItem[0]);
				gumItem.setPrice(price);
				availableItems.put(holdItem[0], gumItem);
				break;
				
			default:
				System.out.println("Nothing Matches Document Selected");

			}

			
		} fileReader.close();
		return availableItems;
	}

}
