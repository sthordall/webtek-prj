package com.webtek.musicshop.Handlers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.jdom2.JDOMException;

public class UserHandler {
	/**
	 * Our Servlet session. We will need this for the shopping basket
	 */
	@Context
	HttpSession session;

	CloudHandler cloudHandler;

	public UserHandler() {
		cloudHandler = new CloudHandler();
	}

	@POST
	@Path("login")
	public String login(@FormParam("user") String user,
			@FormParam("password") String password) throws IOException,
			JDOMException {

		Boolean isSucces = cloudHandler.login(user, password);

		if (isSucces) {
			return "success";
		} else {
			return "failure";
		}
	}
}
