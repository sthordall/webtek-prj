package com.webtek.musicshop.Handlers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;

import com.webtek.musicshop.Model.ApplicationConstants;
import com.webtek.musicshop.Model.Item;

public class CloudHandler {

	private HttpHandler httpHandler;
	private XMLParser xmlParser;
	private static String validatorPath = "xmlSchema/cloud.xsd";
	private Validator validator;

	private ArrayList<Item> itemList;

	@Context
	HttpSession session;

	@Context
	ServletContext servletContext;

	public CloudHandler() {
		validator = new Validator();
		httpHandler = new HttpHandler();
		xmlParser = new XMLParser();

		LoadItemsList();
	}

	public void LoadItemsList() {
		try {
			URL requestUrl = new URL(ApplicationConstants.LISTITEMS);
			Element responseRoot = httpHandler.HttpRequest("GET", requestUrl)
					.getRootElement();

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
	}

	public String login(String customer, String password) throws IOException,
			JDOMException, URISyntaxException {
		Namespace ns = ApplicationConstants.WEBTEKNAMESPACE;
		Element root = new Element("login", ns);

		// CustomerName
		Element custNameElement = new Element("customerName", ns);
		custNameElement.setText(customer);
		root.addContent(custNameElement);

		// CustomerPassword
		Element custPassElement = new Element("customerPass", ns);
		custPassElement.setText(password);
		root.addContent(custPassElement);

		// Create Document
		Document loginDocument = new Document(root);

		try {
			// // Validate
			// validator.validateXML(loginDocument, Paths.get(validatorPath));

			// Send request and return true if logged in, otherwise false
			URL loginUrl = new URL(ApplicationConstants.LOGIN);
			return httpHandler.outputXMLonHTTP("POST", loginUrl, loginDocument)
					.getRootElement().getChildText("customerID", ns);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * Sellitems function to send sellitems request to server. Can return the
	 * following strings: "failure", "success", "ok", "error" and "itemSoldOut"
	 * 
	 * @param hashMap
	 * @param customerId
	 */
	public String sellItems(HashMap<String, Integer> hashMap, String customerId) {
		Namespace ns = ApplicationConstants.WEBTEKNAMESPACE;
		Element root = new Element("sellItems", ns);
		Iterator<Entry<String, Integer>> iterator = hashMap.entrySet()
				.iterator();

		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();

			// ShopKey
			Element shopKeyElement = new Element("shopKey", ns);
			shopKeyElement.setText(ApplicationConstants.SHOPKEY);
			root.addContent(shopKeyElement);

			// ItemID
			Element itemIdElement = new Element("itemID", ns);
			itemIdElement.setText(entry.getKey());
			root.addContent(itemIdElement);

			// CustomerID
			Element customerIdElement = new Element("customerID", ns);
			customerIdElement.setText(customerId);
			root.addContent(customerIdElement);

			// SaleAmount
			Element amountElement = new Element("saleAmount", ns);
			amountElement.setText(entry.getValue().toString());
			root.addContent(amountElement);

			// AdjustStock
			String subtractResponse = SubstractItemStock(entry.getKey(),
					entry.getValue());
			if (subtractResponse != "stockAdjusted") {
				return subtractResponse;
			}

			// CreateDocument
			Document sellItemsDocument = new Document(root);

			try {
				// // Validate
				// validator.validateXML(sellItemsDocument,
				// Paths.get(validatorPath));

				// Send request and return true if logged in, otherwise false
				URL sellItemsUrl = new URL(ApplicationConstants.SELLITEMS);
				Document respDocument = httpHandler.outputXMLonHTTP("POST",
						sellItemsUrl, sellItemsDocument);

				String sellItemsResp = "failure";

				if (respDocument == null) {
					return sellItemsResp;
				}

				Iterator<Element> sellItemsRespIterator = respDocument
						.getRootElement().getChildren().iterator();

				while (sellItemsRespIterator.hasNext()) {
					sellItemsResp = sellItemsRespIterator.next().getName();
				}

				return sellItemsResp;

			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
				e.printStackTrace();
				return "failure";
			}
		}

		return "success";
	}

	public String SubstractItemStock(String itemId, Integer subtractItemStock) {

		Integer currentItemStock = 0;
		Integer newItemStock;

		// Find itemstock on item
		for (Item item : itemList) {
			if (item.getItemID().equals(itemId)) {
				currentItemStock = Integer.parseInt(item.getItemStock());
			}
		}

		// Calculate new itemstock
		newItemStock = currentItemStock - subtractItemStock;
		if (newItemStock < 0) {
			return "notEnoughStock";
		}

		Element adjustStock = new Element("adjustItemStock");
		adjustStock
				.addNamespaceDeclaration(ApplicationConstants.WEBTEKNAMESPACE);
		adjustStock.setNamespace(ApplicationConstants.WEBTEKNAMESPACE);

		adjustStock.addContent(new Element("shopKey").setText(
				ApplicationConstants.SHOPKEY).setNamespace(
				ApplicationConstants.WEBTEKNAMESPACE));
		adjustStock.addContent(new Element("itemID").setText(itemId)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));

		newItemStock = (0 - subtractItemStock);

		adjustStock.addContent(new Element("adjustment").setText(
				newItemStock.toString()).setNamespace(
				ApplicationConstants.WEBTEKNAMESPACE));

		Document document = new Document(adjustStock);

		try {
			httpHandler.outputXMLonHTTP("POST", new URL(
					ApplicationConstants.ADJUSTSTOCK), document);
			return "stockAdjusted";

		} catch (Exception e) {
			e.printStackTrace();
			return "stockNotAdjusted";
		}

	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	private void addItemListInSession() {
		session.setAttribute("itemList", this.itemList);
	}

}
