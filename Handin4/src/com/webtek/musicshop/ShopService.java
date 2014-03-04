package com.webtek.musicshop;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.webtek.musicshop.Handlers.CloudHandler;
import com.webtek.musicshop.Model.Item;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("shop")
public class ShopService {
    /**
     * Out Servlet session. We will need this for the shopping basket
     */
    @Context HttpSession session;
    
    //private ArrayList<Item> itemList;
    CloudHandler cloudHandler = new CloudHandler();

    /**
     * Make the price increase per request (for the sake of example)
     */
    private static int priceChange = 0;

    @GET
    @Path("items")
    public String getItems() {
    	//System.out.println("getItems called");
        //You should get the items from the cloud server.
        //In the template we just construct some simple data as an array of objects
        JSONArray array = new JSONArray();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id", 1);
        jsonObject1.put("name", "Stetson hat");
        jsonObject1.put("price", 200 + priceChange);
        array.put(jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id", 2);
        jsonObject2.put("name", "Rifle");
        jsonObject2.put("price", 500 + priceChange);
        array.put(jsonObject2);

        priceChange++;

        //You can create a MessageBodyWriter so you don't have to call toString() every time
        return array.toString();
    }
    
//	@GET
//    @Path("productlist")
//    public String ReturnProductList(){
//		System.out.println("ReturnProductList called...");
//    	
//    	//itemList = (ArrayList<Item>) session.getAttribute("itemList");
//    	itemList = cloudHandler.getItemList();
//    	JSONArray array = new JSONArray(itemList);
//
//    	return array.toString();
//    }
//    
//    private JSONArray convertItemListToJsonArray(ArrayList<Item> itemList){
//    	JSONArray array = new JSONArray(itemList);
//    	return array;
//    	
//    }
}
