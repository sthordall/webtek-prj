package com.webtek.musicshop.Handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jdom2.JDOMException;
import org.json.JSONArray;
import org.json.JSONObject;

//import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("shop")
public class LoginHandler {
	HttpSession session;
	CloudHandler cloud = new CloudHandler();
	
	public LoginHandler(@Context HttpServletRequest servletRequest){
		session = servletRequest.getSession();
	}
	
//    @GET
//    @Path("cloudItems")
//    public String getItemsFromCloud() throws IOException, JDOMException
//    {
//    	ArrayList<Item> items = cloud.listItems();
//    	JSONArray mJSONArray = new JSONArray(items);
//    	
//    	return mJSONArray.toString();
//    }
//    @GET
//    @Path("loggedIn")
//    public String loggedIn()
//    {	
//    	if(session.getAttribute("userAttr") != null)
//    		
//    		return session.getAttribute("userAttr").toString();
//    	else
//    		return "NOT !!!";
//    }
    String r; 
    @POST
    @Path("login")
    public Response login(@FormParam("user") String user, @FormParam("password") String password) throws IOException, JDOMException { 

		r = cloud.login(user, password);
		r = cloud.toString();
		
		if (cloud.responseCode == 200)	{
			System.out.println("LOGIN SUCCESS!!!!!!!!");
			session.setAttribute("userAttr", user);
		}
		else {
			System.out.println("UNVALID USER?? RESPONSECODE: " + cloud.responseCode);
		}	
    		//String username = "perle";
    		/*if (session.getAttribute(user) != null) {
    			username = (String) session.getAttribute("username");
    			}*/
    	return Response.status(cloud.responseCode)
    			.entity("Logget ind som: " + r)
    			.build();
    			
    }
    
//    int counter = 0;
//    @POST
//    @Path("cart")
//    public String addToCart (@FormParam("id") String itemId,@FormParam("numberOfItems") int numberOfItems) {
//    
//    	HashMap<String,Integer> cart =	(HashMap<String,Integer>) session.getAttribute("cart"); // = new HashMap();
//    	
//    	if(cart == null)
//    	{
//    		cart = new HashMap<String, Integer>();
//    	}
//  
//    	if (cart.containsKey(itemId)) {
//    		cart.put(itemId, cart.get(itemId)+1);
//    	}
//    	else {
//    		cart.put(itemId, 1);
//    	}
//    	
//    	//if(itemStock > 0)
//    		
//    	counter++;
//    	
//    	
//    	session.setAttribute("cartAttr", cart);
//    	return "";
//    }
    
//	@GET
//    @Path("test")
//    public Response test() throws IOException, JDOMException { 
//    	//ArrayList<Customer> customers = cloud.listCustomers();	
//    	String username = "perle";
//		if (session.getAttribute("username") != null) {
//			username = (String) session.getAttribute("username");
//			}
//	
//    	
//    		
//				/*String r = cloud.login(username, "body");
//				if (cloud.responseCode == 200)	{
//					System.out.println("LOGIN SUCCESS!!!!!!!!");
//				}
//    			else {
//    				System.out.println("UNVALID USER?? RESPONSECODE: " + cloud.responseCode);
//    			}*/
//				
//				return Response.status(200)
//						.entity("Logget ind som: " + username)
//						.build();
//    		
//    }
//	
//	@GET
//	@Produces("text/plain")
//	public String Hello() {
//		return "It works!";
//	}
	

}

