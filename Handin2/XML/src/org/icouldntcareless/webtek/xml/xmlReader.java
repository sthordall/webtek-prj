package org.icouldntcareless.webtek.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.output.XMLOutputter;

public class xmlReader {
	private static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace("http://www.cs.au.dk/dWebTek/2014");
	private static final String CLOUDURL = "http://services.brics.dk/java4/cloud";
	private static final String CREATEITEM = "/createItem";
	private static final String MODIFYITEM = "/modifyItem";
	
	public static void main(String[] args) {

		try {
			File xsdfile = new File(args[1]);
			XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(xsdfile);
			SAXBuilder builder = new SAXBuilder(schemafac);
			String msg = "No errors!";
			
			Document document = builder.build(new File(args[0]));

			URL url = new URL(CLOUDURL+CREATEITEM);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST"); 
			con.setRequestProperty(key, value);
			con.setDoOutput(true);
			con.connect();

			new XMLOutputter().output(document, con.getOutputStream());

			// Vi kan l�se fra responsen med http statuskode om request
			// er g�et fint

			int responseCode = con.getResponseCode();
			String itemID = con.getResponseMessage();

			if (responseCode != 200) {
				System.out.print("noget gik galt med responsen");
			}

			// Forbindelsen lukkes efterf�lgende

			con.disconnect();

			System.out.println(msg);
			
		} catch (Exception e) {
			System.out.println("An error occurred:" + e.getMessage());

			e.printStackTrace();
		}

	}

	public Item generateItemFromDoc(Document itemDocument) {
		// Get information from document
		Element rootElement = itemDocument.getRootElement();
		Element itemElement = rootElement.getChild("item", WEBTEKNAMESPACE);

		Item item = new Item(itemElement.getAttributeValue("itemID",
				WEBTEKNAMESPACE), itemElement.getAttributeValue("itemName",
				WEBTEKNAMESPACE), itemElement.getAttributeValue("itemURL",
				WEBTEKNAMESPACE), itemElement.getAttributeValue("itemPrice",
				WEBTEKNAMESPACE), itemElement.getAttributeValue("itemStock",
				WEBTEKNAMESPACE), itemElement.getAttributeValue(
				"itemDescription", WEBTEKNAMESPACE));

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
