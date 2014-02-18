package web.tek.icouldntcareless.musicshop.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.io.Serializable;

@ManagedBean(name = "Item", eager = true)
@RequestScoped
public class Item implements Serializable {
	private static final long serialVersionUID = 148246321600366013L;

	private String itemID;
	
	private String itemName;

	private String itemURL;
	
	private String itemPrice;
	
	private String itemStock;
	
	private String itemDescription;
	
	public Item(String itemID, String itemName, String itemURL,
			String itemPrice, String itemStock, String itemDescription) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemURL = itemURL;
		this.itemPrice = itemPrice;
		this.itemStock = itemStock;
		this.itemDescription = itemDescription;
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

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemStock() {
		return itemStock;
	}

	public void setItemStock(String itemStock) {
		this.itemStock = itemStock;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
}
