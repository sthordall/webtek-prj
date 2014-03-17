package com.webtek.musicshop.Handlers;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.json.JSONArray;

import com.webtek.musicshop.Model.Customer;
import com.webtek.musicshop.Model.Message;
import com.webtek.musicshop.Model.MessageContainer;
import com.google.gson.Gson;

/**
 * This class is our handler for handling message and persist messages
 * into your MessageContainer which emulates our Database
 * This class should be used In MessageHandler
 *
 */
@Path("messageHandler")
public class MessageHandler {
	HttpSession session;
	
	ArrayList<Message> messageList = new ArrayList<>();
	Message message;
	Date lastUpDate;
	Gson gson;
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
		lastUpDate = new Date(0);
		gson = new Gson();
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
   	
    	messageList = (ArrayList<Message>) context.getAttribute("messageContainer");
    	
    	for (int i = 0; i < messageList.size(); i++) {
			System.out.println(messageList.get(i).getMessage());
		}
    }
    
    @GET
    @Path("/getUserInfo")
    public String getUserInfo(){
    	Customer currentCustomer = (Customer) session.getAttribute("user");
    	
    	String json = gson.toJson(currentCustomer);
    	return json;  	
    }
    
    @GET
    @Path("/getNextMessage")
    public String getNextMessage(){
    	messageList = (ArrayList<Message>) context.getAttribute("messageContainer");
    	lastUpDate = (Date) context.getAttribute("lastUpDate");
    	
    	if(lastUpDate == null)
    		System.out.println("den er null");
    	else {
    		System.out.println(lastUpDate.toString() + ": 1");
		}
		
    	MessageContainer.getInstance().setMessageList(messageList);
    	message = MessageContainer.getInstance().getFirstAfter(lastUpDate);
    	System.out.println(MessageContainer.getInstance().getMessageList().size());
    	if(message == null){
    		System.out.println("noUpdate");
    		return "noUpdate";
    	}
    	System.out.println(message.getMessage());
    	lastUpDate = message.getDateSent();
    	System.out.println(lastUpDate.toString() + ": 2");
    	context.setAttribute("lastUpDate", lastUpDate);
    	context.setAttribute("messageContainer", MessageContainer.getInstance().getMessageList());
    	System.out.println(MessageContainer.getInstance().getMessageList().size() + "found new message");
    	String json = gson.toJson(message);
    	return json;
    }
    
    /*******************************Getters/Setters********************************************/

}
