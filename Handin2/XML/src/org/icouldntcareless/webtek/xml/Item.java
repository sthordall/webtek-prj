package org.icouldntcareless.webtek.xml;

import java.net.URI;

public class Item {
	private Integer itemID;
	private String itemName;
	private URI itemURL;
	private Integer itemPrice;
	private Integer itemStock;
	private Integer itemDescription;

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public URI getItemURL() {
		return itemURL;
	}

	public void setItemURL(URI itemURL) {
		this.itemURL = itemURL;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemStock() {
		return itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

	public Integer getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(Integer itemDescription) {
		this.itemDescription = itemDescription;
	}
}
