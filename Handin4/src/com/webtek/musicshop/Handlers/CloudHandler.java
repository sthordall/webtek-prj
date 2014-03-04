package com.webtek.musicshop.Handlers;

import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.webtek.musicshop.Model.ApplicationConstants;
import com.webtek.musicshop.Model.Item;

public class CloudHandler {

	private HttpHandler httpHandler;
	private XMLParser xmlParser;

	private ArrayList<Item> itemList;
	
	@Context HttpSession session;

	public CloudHandler() {
		httpHandler = new HttpHandler();
		xmlParser = new XMLParser();
		XMLOutputter xmlOutputter = new XMLOutputter();

		try {
			URL requestUrl = new URL(ApplicationConstants.LISTITEMS);
			Element responseRoot = httpHandler.HttpRequest("GET", requestUrl)
					.getRootElement();
			
			xmlOutputter.output(responseRoot, System.out);

			if (responseRoot == null) {
				throw new Exception("Response from itemList request was null");
			} else {
				itemList = new ArrayList<Item>();

				for (Element itemChild : responseRoot.getChildren()) {

					String itemDescription = xmlParser
							.generateItemDescriptionHTML(itemChild
									.clone()
									.getChild(
											"itemDescription",
											ApplicationConstants.WEBTEKNAMESPACE));

					itemList.add(new Item(itemChild.getChildText("itemID",
							ApplicationConstants.WEBTEKNAMESPACE), itemChild
							.getChildText("itemName",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemChild.getChildText("itemURL",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemChild.getChildText("itemPrice",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemChild.getChildText("itemStock",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemDescription));
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
		/*Save into session*/
		System.out.println("Before Session method");
		//addItemListInSession();
		System.out.println("After Session method");
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	private void addItemListInSession(){
		session.setAttribute("itemList", this.itemList);
	}

}
