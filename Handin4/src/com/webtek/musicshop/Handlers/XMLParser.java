package com.webtek.musicshop.Handlers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;

import com.webtek.musicshop.Model.ApplicationConstants;

public class XMLParser {
	public Element getRootElement(File xml, File schema) throws JDOMException,
			IOException {
		XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(schema);
		SAXBuilder builder = new SAXBuilder(schemafac);

		Document document = builder.build(xml);
		return document.getRootElement();

	}

	// public Document GenerateDocumentFromItem(Item item, Namespace namespace,
	// String shopKey){
	// Element modifyitem = new Element("modifyItem");
	// modifyitem.addNamespaceDeclaration(namespace);
	// modifyitem.setNamespace(namespace);
	//
	// modifyitem.addContent(new Element("shopKey").setText(shopKey)
	// .setNamespace(namespace));
	// modifyitem.addContent(new Element("itemID").setText(item.getItemID()));
	// modifyitem.addContent(new
	// Element("itemName").setText(item.getItemName()));
	// modifyitem.addContent(new
	// Element("itemPrice").setText(item.getItemPrice()));
	// modifyitem.addContent(new Element("itemURL").setText(item.getItemURL()));
	// modifyitem.addContent(new
	// Element("itemDescription").setText(item.getItemDescription()));
	//
	// return new Document(modifyitem);
	// }

//	public Document getCreateItemRequest(Element item, String shopKey,
//			Namespace namespace) {
//		Element createItem = new Element("createItem");
//		createItem.addNamespaceDeclaration(namespace);
//
//		createItem.setNamespace(namespace);
//		createItem.addContent(new Element("shopKey").setText(shopKey)
//				.setNamespace(namespace));
//		createItem.addContent(item.getChild("itemName", namespace).clone());
//
//		return new Document(createItem);
//	}
	
	public Document CreateDocItemFromItemName(String itemName, String shopKey,
			Namespace namespace) {
		Element createItem = new Element("createItem");
		createItem.addNamespaceDeclaration(namespace);

		createItem.setNamespace(namespace);
		createItem.addContent(new Element("shopKey").setText(shopKey)
				.setNamespace(namespace));
		createItem.addContent(new Element("itemName").setText(itemName).setNamespace(namespace));

		return new Document(createItem);
	}

	public Document getLoginRequest(String username, String password) {

		Element login = new Element("login");
		login.addNamespaceDeclaration(ApplicationConstants.WEBTEKNAMESPACE);

		login.setNamespace(ApplicationConstants.WEBTEKNAMESPACE);
		login.addContent(new Element("customerName").setText(username)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));
		login.addContent(new Element("customerPass").setText(password)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));

		return new Document(login);
	}

	public Document getModifyItemRequest(Element item, Element itemID,
			String shopKey, Namespace namespace) {

		Element modifyItem = new Element("modifyItem");
		modifyItem.addNamespaceDeclaration(namespace);
		modifyItem.setNamespace(namespace);

		modifyItem.addContent(new Element("shopKey").setText(shopKey)
				.setNamespace(namespace));

		List<Element> itemChildren = item.getChildren();
		for (Element element : itemChildren) {
			if (element.getName() == "itemID") {
				modifyItem.addContent(new Element("itemID").setText(
						itemID.getText()).setNamespace(namespace));
			} else if (element.getName() == "itemStock") {
				// DO NOTHING
			} else {
				modifyItem.addContent(element.clone());
			}
		}

		return new Document(modifyItem);

	}
    
    public Document getCreateCustomerRequest(String shopKey,
                                             String customerName, String customerPass, Namespace namespace) {
        
		Element createCustomer = new Element("createCustomer");
		createCustomer.addNamespaceDeclaration(namespace);
		createCustomer.setNamespace(namespace);
        
		createCustomer.addContent(new Element("shopKey").setText(shopKey)
                                  .setNamespace(namespace));
		createCustomer.addContent(new Element("customerName").setText(
                                                                      customerName).setNamespace(namespace));
		createCustomer.addContent(new Element("customerPass").setText(
                                                                      customerPass).setNamespace(namespace));
        
		return new Document(createCustomer);
        
	}

	public String generateItemDescriptionHTML(Element root) {
		String result = "";
		result += generateHTMLFromElement(root, true);
		result += root.getText();
		List<Element> children = root.getChildren();
		for (Element child : children) {
			result += generateItemDescriptionHTML(child);
		}
		result += generateHTMLFromElement(root, false);
		return result;
	}

	private String generateHTMLFromElement(Element element, Boolean start) {
		String result = "<";
		String tag = element.getName();
		if (!start) {
			result += "/";
		}
		if (tag == "document") {
			result += "div>";
		} else if (tag == "italics") {
			result += "i>";
		} else if (tag == "bold") {
			result += "b>";
		} else if (tag == "list") {
			result += "ul>";
		} else if (tag == "item") {
			result += "li>";
		} else {
			return "";
		}
		return result;
	}
}