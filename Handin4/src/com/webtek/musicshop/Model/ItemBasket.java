package com.webtek.musicshop.Model;

public class ItemBasket {
	
	private String itemID;

	private String itemName;

	private String itemURL;

	private int itemPrice;
	
	private String itemCount;

	public ItemBasket() {
	}

	public ItemBasket(String itemID, String itemName, String itemURL,
			int itemPrice, String itemCount) {
		super();
		this.setItemID(itemID);
		this.itemName = itemName;
		this.itemURL = itemURL;
		this.itemPrice = itemPrice;
		this.setItemCount(itemCount);	
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemURL() {
		return itemURL;
	}

	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemCount() {
		return itemCount;
	}

	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
}
