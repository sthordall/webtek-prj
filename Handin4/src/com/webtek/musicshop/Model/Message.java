package com.webtek.musicshop.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is responsible for persisting data object between parts of our application
 * It is our Model for containing data
 *
 */
public class Message implements Serializable{
    /**
	 * It implements Serializable which is used to exchange between all parts of our application. 
	 * When Message object is transmitted it should be serialized.
	 */
	private static final long serialVersionUID = -6097873166236858754L;
	private Date dateSent;
    private String user;
    private String message;
    /*******************************Constructors***********************************************/
    public Message(String user, String message){
    	this.user = user;
    	this.message = message;
    }
     
    /*******************************Business Logic Methods*************************************/
        
    //Should not contain any method in this section.
    
    /*******************************Getters/Setters********************************************/
	public Date getDateSent() {
		return dateSent;
	}
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
