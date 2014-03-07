package com.webtek.musicshop.Handlers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.webtek.musicshop.Model.*;

@Path("baskethandler")
public class BasketHandler {
    /**
     * Out Servlet session. We will need this for the shopping basket
     */
    @Context HttpSession session;
    //@Context ServletContext servletContext; 
    
    private ArrayList<Item> itemList;
    CloudHandler cloudHandler = new CloudHandler();
    
    public ArrayList<Item> getItemList() {
		return itemList;
	}
    
    public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	public BasketHandler(){
    }
      
    @SuppressWarnings("unchecked")
	@GET
    @Path("productlist")
    public String ReturnProductList(){
    	
    	//itemList = (ArrayList<Item>) session.getAttribute("itemList");
    	itemList = cloudHandler.getItemList();

    	return convertItemListToJsonArray(itemList).toString();
    }
    
    private JSONArray convertItemListToJsonArray(ArrayList<Item> itemList){
    	JSONArray array = new JSONArray(itemList);
    	return array;
    	
    }

    /**
     * Retrieve the one product that is to be added to basket
     * and add it to array of products if there is already products
     * in a session basket.
     */
    @POST
    @Path("addProductsTobasket")
    public String addProductsToBasket(@FormParam("itemID") String itemID){
    	
    	//Retrieve itemID
    	
    	//Lookup on Cloud to retrieve information regarding the specific item
    	
    	//If the item is found(which it should, otherwise send fail), save the data into our item model
    	//The data should be saved in a hashmap(key, value)
    	//By using hashmap instead of list, it makes it easier to count how many items 
    	//of the current itemID in hashmap instead of list
    	
    	//Make another Cloud call to check whether the current item is higher than 0
    	//Return success or fail depending on whether the item is higher than 0
    	
    	//Save the hashmap into a session(TODO: Instantiate Session in Constructor)
    	//Only save it if it is higher than 0
    	

    	
    	
    	return "";
    	
    }
}
    
//    @POST
//    @Path("addProductsTobasket")
//    public String addProductsToBasket(@FormParam("")){
//    	/*Retrieve the item that is to be added to basket
//    	 * The item should be taken from the Servlet Context(ApplicationScoped)
//    	 * 
//    	 * How to identify this product?!?!?!?
//    	 * 
//    	 * */
//        JSONArray array = new JSONArray();
//
////        JSONObject jsonObject1 = new JSONObject();
////        jsonObject1.put("id", 1);
////        jsonObject1.put("name", "Stetson hat");
////        jsonObject1.put("price", 200);
////        array.put(jsonObject1);
//
//
//        //You can create a MessageBodyWriter so you don't have to call toString() every time
//        return array.toString();
//    	
//    }


