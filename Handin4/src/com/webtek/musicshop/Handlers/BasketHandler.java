package com.webtek.musicshop.Handlers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.json.JSONArray;

import com.webtek.musicshop.Model.Item;
import com.webtek.musicshop.Model.ItemBasket;

@Path("baskethandler")
public class BasketHandler {

	HttpSession session;

	private ArrayList<Item> itemList;

	CloudHandler cloudHandler = new CloudHandler();

	/**************************** Constructors ****************************************/

	public BasketHandler(@Context HttpServletRequest servletRequest) {
		if (servletRequest.getSession() != null) {
			session = servletRequest.getSession();
		}
	}

	/**************************** Business Logic **************************************/
	/**
	 * Retrieve the one product that is to be added to basket and add it to
	 * array of products if there is already products in a session basket.
	 */
	@POST
	@Path("addProductsTobasket/{itemid}")
	public String addProductsToBasket(@PathParam("itemid") String itemID) {
		// Retrieve itemID
		System.out.println("Got request with id: " + itemID);
		
		HashMap<String, Integer> basketHashMap;
		
		// The data should be saved in a hashmap(key, value)
		// By using hashmap instead of list, it makes it easier to count how
		// many items
		// of the current itemID in hashmap instead of list
		
		if(session.getAttribute("basket") == null){
			basketHashMap = new HashMap<String, Integer>();
			basketHashMap.put(itemID, 1);
		}
		else{
			basketHashMap = (HashMap<String, Integer>) session.getAttribute("basket");			
			if(basketHashMap.containsKey(itemID)){
				basketHashMap.put(itemID, basketHashMap.get(itemID)+1);
			}
			else{
				basketHashMap.put(itemID, 1);
			}		
		}
		session.setAttribute("basket", basketHashMap);
		//TEST
		HashMap<String, Integer> testHashMap = (HashMap<String, Integer>) session.getAttribute("basket");
		System.out.print(testHashMap.get(itemID).toString());
		return "success";
	}
	
	@GET
	@Path("checkoutBasket")
	public String CheckoutBasket(){
		
		Item itemToAdd = new Item();
		
//		ArrayList<Item> itemList = (ArrayList<Item>) session.getAttribute("itemList");
//		for(int i = 0; i < itemList.size(); i++){
//			if(itemID == itemList.get(i).getItemID()){
//				 itemToAdd = itemList.get(i);
//			}
//		}
		// Make another Cloud call to check whether the current item is higher
		// than 0
		// Return success or fail depending on whether the item is higher than 0
		// Only save it if it is higher than 0
		
		return "";
	}
	
	@GET
	@Path("productlist")
	public String ReturnProductList() {

		// itemList = (ArrayList<Item>) session.getAttribute("itemList");
		itemList = cloudHandler.getItemList();
		session.setAttribute("itemList", itemList);
		return convertItemListToJsonArray(itemList).toString();
	}

	
	@GET
	@Path("updatecart")
	public String UpdateCart(){
		System.out.println("UpdateCart called...");
		HashMap<String, Integer> basketHashMap = (HashMap<String, Integer>) session.getAttribute("basket");
		ArrayList<Item> itemList = (ArrayList<Item>) session.getAttribute("itemList");
		
		ArrayList<ItemBasket> itemBasket = new ArrayList<ItemBasket>();
		for(int i = 0; i < itemList.size(); i++){
			if(basketHashMap.containsKey(itemList.get(i).getItemID())){
				
				itemBasket.add(new ItemBasket(itemList.get(i).getItemID(), itemList.get(i).getItemName()
						, itemList.get(i).getItemURL(), Integer.parseInt(itemList.get(i).getItemPrice()), 
						Integer.toString(basketHashMap.get(itemList.get(i).getItemID()))));
			}
		}		
		return new JSONArray(itemBasket).toString();
	}
	/**************************** Helper Methods*****************************************/
	
	private JSONArray convertItemListToJsonArray(ArrayList<Item> itemList) {
		JSONArray array = new JSONArray(itemList);
		return array;
	}
	

	/**************************** Getters And Setters************************************/
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
}

// @POST
// @Path("addProductsTobasket")
// public String addProductsToBasket(@FormParam("")){
// /*Retrieve the item that is to be added to basket
// * The item should be taken from the Servlet Context(ApplicationScoped)
// *
// * How to identify this product?!?!?!?
// *
// * */
// JSONArray array = new JSONArray();
//
// // JSONObject jsonObject1 = new JSONObject();
// // jsonObject1.put("id", 1);
// // jsonObject1.put("name", "Stetson hat");
// // jsonObject1.put("price", 200);
// // array.put(jsonObject1);
//
//
// //You can create a MessageBodyWriter so you don't have to call toString()
// every time
// return array.toString();
//
// }

