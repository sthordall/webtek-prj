package com.webtek.musicshop.Model;

import java.io.Serializable;

/**
 * Customer class is used for login purposes on the site. When a user types
 * username and password in the form, it is stored in this class' fields.
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 748705489315002184L;
	private String customerID;
	private String customerName;
	private String customerPass;
	private Boolean isLoggedIn;

	public String getCustomerPass() {
		return customerPass;
	}

	public void setCustomerPass(String customerPass) {
		this.customerPass = customerPass;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
