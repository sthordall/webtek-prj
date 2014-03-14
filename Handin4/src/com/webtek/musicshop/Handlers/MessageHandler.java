package com.webtek.musicshop.Handlers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.webtek.musicshop.Model.MessageContainer;

/**
 * This class is our handler for handling message and persist messages
 * into your MessageContainer which emulates our Database
 * This class should be used In MessageHandler
 *
 */
@Path("messageHandler")
public class MessageHandler {
	HttpSession session;
//	@Context ServletContext context;
//	@javax.ws.rs.core.Context 
//	ServletContext context;
	
//    @Context
//    public void setServletContext(ServletContext context) {
//        System.out.println("servlet context set here");
//        this.context = context;
//    }
	
	/*******************************Constructors***********************************************/
	public MessageHandler(@Context HttpServletRequest servletRequest) {
		if (servletRequest.getSession() != null) {
			session = servletRequest.getSession();
		}
		//Test only
//		System.out.println("MessageHandler Constructor: " + context);		
	}	
    
    /*******************************Webservice Methods*****************************************/
    
    @GET
    @Path("/sendMessage")
    public void sendMessage(){
    	//Test only
//    	System.out.println("sendMessage: " + context);
    }
    
    /*******************************Getters/Setters********************************************/

}
