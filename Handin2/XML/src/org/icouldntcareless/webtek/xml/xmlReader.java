package org.icouldntcareless.webtek.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.output.XMLOutputter;

public class xmlReader {

	public Element getRootElement(File xml, File schema) throws JDOMException,
			IOException {
		XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(schema);
		SAXBuilder builder = new SAXBuilder(schemafac);

		Document document = builder.build(xml);
		return document.getRootElement();

	}

	public Document getCreateItemRequest(Element item, String shopKey,
			Namespace namespace) {
		Element createItem = new Element("createItem");
		createItem.addNamespaceDeclaration(namespace);

		createItem.setNamespace(namespace);
		createItem.addContent(new Element("shopKey").setText(shopKey)
				.setNamespace(namespace));
		createItem.addContent(item.getChild("itemName", namespace).clone());

		return new Document(createItem);
	}

	public Document outputXMLonHTTP(String httpRequestType, URL url,
			Document docToOutput) throws ProtocolException, IOException,
			JDOMException {
		XMLOutputter outputter = new XMLOutputter();

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(httpRequestType);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.connect();

		outputter.output(docToOutput, con.getOutputStream());

		Document responseDocument = null;

		try {
			SAXBuilder builder = new SAXBuilder();
			responseDocument = builder.build((InputStream) con.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (con.getResponseCode() != 200) {
			System.out.print("An network error occurred: "
					+ con.getResponseCode() + " - " + con.getResponseMessage());
		}

		con.disconnect();

		return responseDocument;
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
				modifyItem.addContent(new Element("itemID").setText(itemID
						.getText()).setNamespace(namespace));
			} else if (element.getName() == "itemStock") {
				// DO NOTHING
			} else {
				modifyItem.addContent(element.clone());
			}
		}
		
		return new Document(modifyItem);

	}


public void DeleteProduct(String shopkey, String itemid, Namespace namespace, String urlDelete){
	//Create Delete element 
	Element deleteItem = new Element("deleteItem");
	
	// Add namespace to deleteItem element
	deleteItem.addNamespaceDeclaration(namespace);
	deleteItem.setNamespace(namespace);

	// Add shopKey element to modifyItem element
	deleteItem.addContent(new Element("shopKey").setText(shopkey)
			.setNamespace(namespace));
	
	// Add itemID element to modifyItem element
	deleteItem.addContent(new Element("itemID").setText(itemid)
			.setNamespace(namespace));
	
	Document deleteItemDocument = new Document(deleteItem);
	XMLOutputter modOutputter = new XMLOutputter();
	try{
	// Output modifyItem element as document, over HTTP
	URL modurl = new URL(urlDelete);

	HttpURLConnection modcon = (HttpURLConnection) modurl
			.openConnection();
	modcon.setRequestMethod("POST");
	modcon.setDoOutput(true);
	modcon.connect();

	modOutputter.output(deleteItemDocument, System.out);//modcon.getOutputStream());
	modOutputter.output(deleteItemDocument, modcon.getOutputStream());
	
	if (modcon.getResponseCode() != 200) {
		System.out.print("noget gik galt med request af /deleteItem: "
				+ modcon.getResponseMessage() + ". URL for delete er : " + urlDelete);
		
	}

	modcon.disconnect();

} catch (Exception e) {
	System.out.println("An error occurred:" + e.getMessage());

	e.printStackTrace();
}
}
}
// private static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace(
// "w", "http://www.cs.au.dk/dWebTek/2014");
//
// private static final String CLOUDURL =
// "http://services.brics.dk/java4/cloud";
// private static final String CREATEITEM = "/createItem";
// private static final String MODIFYITEM = "/modifyItem";
// private static final String SHOPKEY = "79D23EFCA0DAAD24E5FFF385";
//
// public static void main(String[] args) {
// try {
//
// } catch (Exception e) {
// // TODO: handle exception
// }
//
//
// try {
// File xsdfile = new File(args[1]);
// XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(xsdfile);
// SAXBuilder builder = new SAXBuilder(schemafac);
// String msg = "No errors!";
//
// Document document = builder.build(new File(args[0]));
// Element item = document.getRootElement();
//
// Element createItem = new Element("createItem");
// createItem.addNamespaceDeclaration(WEBTEKNAMESPACE);
//
// createItem.setNamespace(WEBTEKNAMESPACE);
// createItem.addContent(new Element("shopKey").setText(SHOPKEY)
// .setNamespace(WEBTEKNAMESPACE));
// createItem.addContent(item.getChild("itemName", WEBTEKNAMESPACE)
// .clone());
//
// Document createItemDocument = new Document(createItem);
// XMLOutputter outputter = new XMLOutputter();
//
// URL url = new URL(CLOUDURL + CREATEITEM);
//
// HttpURLConnection con = (HttpURLConnection) url.openConnection();
// con.setRequestMethod("POST");
// con.setDoOutput(true);
// con.setDoInput(true);
// con.connect();
//
// outputter.output(createItemDocument, con.getOutputStream());
//
// Document responseDocument = builder.build((InputStream) con
// .getContent());
//
// if (con.getResponseCode() != 200) {
// System.out.print("noget gik galt med responsen");
// }
//
// con.disconnect();
//
// // modifyItem(item, new Element("itemID").setText(
// // responseDocument.getRootElement().getText())
// // .setNamespace(WEBTEKNAMESPACE));
//
// // Create modifyItem element
// Element modifyItem = new Element("modifyItem");
//
// // Add namespace to modifyItem element
// modifyItem.addNamespaceDeclaration(WEBTEKNAMESPACE);
// modifyItem.setNamespace(WEBTEKNAMESPACE);
//
// // Add shopKey element to modifyItem element
// modifyItem.addContent(new Element("shopKey").setText(SHOPKEY)
// .setNamespace(WEBTEKNAMESPACE));
//
// // Add item content to modifyItem element
// List<Element> itemChildren = item.getChildren();
// for (Element element : itemChildren) {
// if (element.getName() == "itemID") {
// modifyItem.addContent(new Element("itemID").setText(
// responseDocument.getRootElement().getText())
// .setNamespace(WEBTEKNAMESPACE));
// } else {
// modifyItem.addContent(element.clone());
// }
//
// }
//
// Document modifyItemDocument = new Document(modifyItem);
// XMLOutputter modOutputter = new XMLOutputter();
//
// // Output modifyItem element as document, over HTTP
// URL modurl = new URL(CLOUDURL + MODIFYITEM);
//
// HttpURLConnection modcon = (HttpURLConnection) modurl
// .openConnection();
// modcon.setRequestMethod("POST");
// modcon.setDoOutput(true);
// modcon.connect();
//
// modOutputter.output(modifyItemDocument, modcon.getOutputStream());
//
// if (modcon.getResponseCode() != 200) {
// System.out.print("noget gik galt med responsen: "
// + modcon.getResponseMessage());
// }
//
// modcon.disconnect();
//
// } catch (Exception e) {
// System.out.println("An error occurred:" + e.getMessage());
//
// e.printStackTrace();
// }
//
// }