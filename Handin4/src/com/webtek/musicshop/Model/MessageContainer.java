package com.webtek.musicshop.Model;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;

/**
 * This class is responsible for containing Message objects
 * which needs to be instantiated only once(perhaps make it as Singleton)
 * It has to contain a some sort of a list of Message objects
 */
public class MessageContainer {
	//Our ServletContext (Application Scoped)
	@javax.ws.rs.core.Context 
	ServletContext context;
	
	//AraryList to be saved into our ServletContext, but it has to 
	ArrayList<Message> messageList = new ArrayList<>();

	private static MessageContainer instance = null;
	
	/*******************************Constructors***********************************************/
	
	//Singleton
	
    /*******************************Singleton**************************************************/
    
	public static MessageContainer getInstance() {
		if (instance == null) {
			instance = new MessageContainer();
		}
		return instance;
	}
    
    /*******************************Business Logic Methods*************************************/
    
	/**
	 * Add Message Object into ArrayList and sets date to current Date
	 * @param message
	 * the object to add into ArrayList
	 */
	public void SendMessage(Message message){
		message.setDateSent(new Date());
		messageList.add(message);
	}
	
    public Message getFirstAfter(Date after) {
    	
    	//When we get messageList for first time from Servlet
    	if(messageList == null){
    		messageList = new ArrayList<>();
    	}
    	//First time entering Support page
        if(messageList.isEmpty()){
        	setFirstMessage();
        }
            
        if(after == null)
            return messageList.get(0);
        for(Message m : messageList) {
            if(m.getDateSent().after(after))
                return m;
        }
        return null;
    }
    
    private void setFirstMessage(){
		Message message = new Message("Admin", "Welcome to Support Chat");
		MessageContainer.getInstance().SendMessage(message); 
    }
    
    /*******************************Getters/Setters********************************************/
	public ArrayList<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<Message> messageList) {
		this.messageList = messageList;
	}
}
