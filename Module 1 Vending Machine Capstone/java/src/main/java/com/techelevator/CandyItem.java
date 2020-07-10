package com.techelevator;

import java.math.BigDecimal;

public class CandyItem implements MasterVendingClass {

	private String name;
	private BigDecimal price = new BigDecimal(0);
	private String slotNum;
	private int quantity = 5;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String getSlotNum() {
		return slotNum;
	}

	@Override
	public void setSlotNum(String slotNum) {
		this.slotNum = slotNum;
	}
	@Override
	 public String toString() { 
		 return  getName() + " | $" + getPrice() + " | " + getQuantity() + " remaining";
	 }
	 

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String dispenseMessage() {
		return "Munch Munch, Yum!";
	}
	
	@Override
	public void buyItem() {
		String message;
		if (this.quantity > 0) {
			this.quantity -= 1;
			message = getName() + " purchased, $"+ getPrice()  +  "\n" + dispenseMessage();
		}else {
			message = "Item is sold out";
		}System.out.println("\n" + message);
	}
}