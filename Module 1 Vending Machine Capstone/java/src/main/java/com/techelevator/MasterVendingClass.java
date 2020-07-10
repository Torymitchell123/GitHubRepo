package com.techelevator;

import java.math.BigDecimal;

public interface MasterVendingClass {

	
	 public String getName();
	 
	 public void setName(String name);
	 
	 public BigDecimal getPrice();
	 
	 public void setPrice(BigDecimal price);
	 
	 public String getSlotNum();
	 
	 public void setSlotNum(String slotNum);
	 
	 public int getQuantity();
	 
	 public void buyItem(); 

	 public String dispenseMessage(); 
	
	 
	 
	
	
	 

	 
	 
	 
	 

}
