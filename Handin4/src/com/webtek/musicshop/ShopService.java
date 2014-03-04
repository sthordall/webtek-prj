package com.webtek.musicshop;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("shop")
public class ShopService {
	/**
	 * Out Servlet session. We will need this for the shopping basket
	 */
	@Context
	HttpSession session;

	@GET
	@Path("items")
	public String getItems() {
		// You should get the items from the cloud server.
		// In the template we just construct some simple data as an array of
		// objects
		JSONArray array = new JSONArray();

		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("itemId", 9830);
		jsonObject1.put("itemName", "Guitar");
		jsonObject1.put("itemURL",
				"http://www.dv247.com/assets/products/30619_l.jpg");
		jsonObject1.put("itemPrice", 1200);
		jsonObject1.put("itemStock", 8);
		jsonObject1
				.put("itemDescription",
						"Professionally develop go forward channels after low-risk high-yield metrics.");
		array.put(jsonObject1);

		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("itemId", 8570);
		jsonObject2.put("itemName", "Drum");
		jsonObject2
				.put("itemURL",
						"http://marching.premier-percussion.com/catalogue/traditional-series/products_pictures/0186IWC%20(a).jpg");
		jsonObject2.put("itemPrice", 1900);
		jsonObject2.put("itemStock", 14);
		jsonObject2
				.put("itemDescription",
						"Appropriately supply premium alignments vis-a-vis future-proof niches. Intrinsicly e-enable cross-unit markets and innovative outsourcing.");
		array.put(jsonObject2);

		priceChange++;

		// You can create a MessageBodyWriter so you don't have to call
		// toString() every time
		return array.toString();
	}
}
