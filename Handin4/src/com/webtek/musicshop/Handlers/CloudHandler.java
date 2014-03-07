package com.webtek.musicshop.Handlers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

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

	public boolean login(String customer, String password) throws IOException,
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
			return httpHandler.outputXMLonHTTP("POST", loginUrl, loginDocument);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
			return false;
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
