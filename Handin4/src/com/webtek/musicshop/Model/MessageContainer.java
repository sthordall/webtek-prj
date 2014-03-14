package com.webtek.musicshop.Model;

import java.util.ArrayList;

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
	
	Message message;
	private static MessageContainer instance = null;
	
	/*******************************Constructors***********************************************/
	
	
    /*******************************Singleton**************************************************/
    
	public static MessageContainer getInstance() {
		if (instance == null) {
			instance = new MessageContainer();
		}
		return instance;
	}
    
    /*******************************Business Logic Methods*************************************/
    
	public void AddMessage(Message message){
		
	}
	
	public ArrayList<Message> GetListOfMessages(){
		
	}
    
    
    
    /*******************************Getters/Setters********************************************/
	

}
