package org.icouldntcareless.webtek.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

public class xmlReader {
	private static String WebTekNameSpace = "http://www.cs.au.dk/dWebTek/2014";
	private static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace(WebTekNameSpace);
	
	public static void main(String[] args) {

		// Generate JDOM Document from given file

	}

	public Item generateItemFromDoc(Document itemDocument){
		// Get information from document
		Element rootElement = itemDocument.getRootElement();
		Element itemElement = rootElement.getChild("item", WEBTEKNAMESPACE);
		
		Item item = new Item(itemElement.getAttributeValue("itemID", WEBTEKNAMESPACE),
								itemElement.getAttributeValue("itemName", WEBTEKNAMESPACE), 
								itemElement.getAttributeValue("itemURL", WEBTEKNAMESPACE), 
								itemElement.getAttributeValue("itemPrice", WEBTEKNAMESPACE), 
								itemElement.getAttributeValue("itemStock", WEBTEKNAMESPACE), 
								itemElement.getAttributeValue("itemDescription", WEBTEKNAMESPACE));
		
		
		return item;
	}

	public Document generateCreateItemDoc(Item item) {
		// Generate "CreateItem" XML request, with initial item data

		return null;
	}

	public String httpPostCreateItem(Document createItemDoc) {
		// HTTP POST request "CreateItem" to cloud server

		// Get response from cloud server
		return null;
	}

	public Document generateModifyItemDoc(Item item) {
		// Generate "ModifyItem" XML request, with rest of item data
		return null;
	}

	public String httpPostModifyItem(Document modifyItemDoc,
			String serverResponse) {
		// /Use response from cloud server, to send "ModifyItem"

		return null;
	}

}
