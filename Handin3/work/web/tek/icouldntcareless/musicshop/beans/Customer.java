package web.tek.icouldntcareless.musicshop.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Customer", eager = true)
@SessionScoped
public class Customer implements Serializable {

	private static final long serialVersionUID = 748705489315002184L;
	private String customerID;
	private String customerName;
	private String customerPass;
	private Boolean customerIsAdmin;

	@PostConstruct
	public void init() {
		customerID = "Hans";
		customerIsAdmin = true;
	}

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

	public Boolean getCustomerIsAdmin() {
		return customerIsAdmin;
	}

	public void setCustomerIsAdmin(Boolean customerIsAdmin) {
		this.customerIsAdmin = customerIsAdmin;
	}
}
