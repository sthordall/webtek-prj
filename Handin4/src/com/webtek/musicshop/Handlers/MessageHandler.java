package com.webtek.musicshop.Handlers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.webtek.musicshop.Model.Message;
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
	@javax.ws.rs.core.Context 
	ServletContext context;
	
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
		//Test only (context is NULL, because it is not within the scope of the Servlet unlike the methods below)
//		System.out.println("MessageHandler Constructor: " + context);		
	}	
    
    /*******************************Webservice Methods*****************************************/
    
    @POST
    @Path("/sendMessage")
    public void sendMessage(@FormParam("messageBox") String message,
			@FormParam("userName") String user){
    	//Call SendMessage to save into Container(our emulating Database)
    	MessageContainer.getInstance().SendMessage(new Message(user, message));   	
    	//Save in Servlet
    	context.setAttribute("messageContainer", MessageContainer.getInstance().getMessageList());
 	
    	//Test only
//    	System.out.println("sendMessage: " + context);
    	
//    	Message message2 = new Message("kasper", "Jeg keder mig");
//    	MessageContainer.getInstance().SendMessage(message2);
    	context.setAttribute("messageContainer", MessageContainer.getInstance().getMessageList());
    	ArrayList<Message> messageList = new ArrayList<>();
    	messageList = (ArrayList<Message>) context.getAttribute("messageContainer");
    	
    	for (int i = 0; i < messageList.size(); i++) {
			System.out.println(messageList.get(i).getMessage());
		}
    }
    
    @GET
    @Path("/getUserInfo")
    public void getUserInfo(){
    	
    }
    
    /*******************************Getters/Setters********************************************/

}
