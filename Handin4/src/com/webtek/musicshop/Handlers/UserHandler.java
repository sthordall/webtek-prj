package com.webtek.musicshop.Handlers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.jdom2.JDOMException;

import com.webtek.musicshop.Model.Customer;

@Path("userhandler")
public class UserHandler {
	/**
	 * Our Servlet session. We will need this for the shopping basket
	 */
	HttpSession session;
	CloudHandler cloudHandler;

	public UserHandler(@Context HttpServletRequest servletRequest) {
		cloudHandler = new CloudHandler();
		session = servletRequest.getSession();
	}

	@POST
	@Path("login")
	public String login(@FormParam("username") String user,
			@FormParam("password") String password) throws IOException,
			JDOMException, URISyntaxException {

		Boolean isSucces = false;

		String customerID = cloudHandler.login(user, password);

		if (customerID != "failure") {
			isSucces = true;
		}

		if (isSucces) {
			Customer customer = new Customer();
			customer.setCustomerName(user);
			customer.setIsLoggedIn(true);
			customer.setCustomerID(customerID);
			session.setAttribute("user", customer);
			return "success";
		} else {
			return "failure";
		}
	}

	@POST
	@Path("logout")
	public void logout() {
		session.setAttribute("user", new Customer());
		session.setAttribute("basket", new HashMap<String, Integer>());
	}

	@GET
	@Path("loggedin")
	public String loggedIn() {
		try {
			Customer currentCustomer = (Customer) session.getAttribute("user");
			if (currentCustomer != null && currentCustomer.getIsLoggedIn()) {
				return "loggedIn";
			} else {
				return "notLoggedIn";
			}
		} catch (Exception e) {
			return "notLoggedIn";
		}

	}
}
