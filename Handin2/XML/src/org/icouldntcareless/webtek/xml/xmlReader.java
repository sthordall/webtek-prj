package org.icouldntcareless.webtek.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.output.XMLOutputter;

public class xmlReader {
	private static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace(
			"w", "http://www.cs.au.dk/dWebTek/2014");

	private static final String CLOUDURL = "http://services.brics.dk/java4/cloud";
	private static final String CREATEITEM = "/createItem";
	private static final String MODIFYITEM = "/modifyItem";
	private static final String SHOPKEY = "79D23EFCA0DAAD24E5FFF385";

	public static void main(String[] args) {

		try {
			File xsdfile = new File(args[1]);
			XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(xsdfile);
			SAXBuilder builder = new SAXBuilder(schemafac);
			String msg = "No errors!";

			Document document = builder.build(new File(args[0]));
			Element item = document.getRootElement();

			Element createItem = new Element("createItem");
			createItem.addNamespaceDeclaration(WEBTEKNAMESPACE);

			createItem.setNamespace(WEBTEKNAMESPACE);
			createItem.addContent(new Element("shopKey").setText(SHOPKEY)
					.setNamespace(WEBTEKNAMESPACE));
			createItem.addContent(item.getChild("itemName", WEBTEKNAMESPACE)
					.clone());

			Document createItemDocument = new Document(createItem);
			XMLOutputter outputter = new XMLOutputter();

			URL url = new URL(CLOUDURL + CREATEITEM);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.connect();

			outputter.output(createItemDocument, con.getOutputStream());

			Document responseDocument = builder.build((InputStream) con
					.getContent());

			if (con.getResponseCode() != 200) {
				System.out.print("noget gik galt med responsen");
			}

			con.disconnect();

			// modifyItem(item, new Element("itemID").setText(
			// responseDocument.getRootElement().getText())
			// .setNamespace(WEBTEKNAMESPACE));

			// Create modifyItem element
			Element modifyItem = new Element("modifyItem");

			// Add namespace to modifyItem element
			modifyItem.addNamespaceDeclaration(WEBTEKNAMESPACE);
			modifyItem.setNamespace(WEBTEKNAMESPACE);

			// Add shopKey element to modifyItem element
			modifyItem.addContent(new Element("shopKey").setText(SHOPKEY)
					.setNamespace(WEBTEKNAMESPACE));

			// Add item content to modifyItem element
			List<Element> itemChildren = item.getChildren();
			for (Element element : itemChildren) {
				if (element.getName() == "itemID") {
					modifyItem.addContent(new Element("itemID").setText(
							responseDocument.getRootElement().getText())
							.setNamespace(WEBTEKNAMESPACE));
				} else {
					modifyItem.addContent(element.clone());
				}

			}

			Document modifyItemDocument = new Document(modifyItem);
			XMLOutputter modOutputter = new XMLOutputter();

			// Output modifyItem element as document, over HTTP
			URL modurl = new URL(CLOUDURL + MODIFYITEM);

			HttpURLConnection modcon = (HttpURLConnection) modurl
					.openConnection();
			modcon.setRequestMethod("POST");
			modcon.setDoOutput(true);
			modcon.connect();

			modOutputter.output(modifyItemDocument, modcon.getOutputStream());

			if (modcon.getResponseCode() != 200) {
				System.out.print("noget gik galt med responsen: "
						+ modcon.getResponseMessage());
			}

			modcon.disconnect();

		} catch (Exception e) {
			System.out.println("An error occurred:" + e.getMessage());

			e.printStackTrace();
		}

	}

	
	public static void modifyItem(Element itemElement, Element itemID) {
		try {

			// Create modifyItem element
			Element modifyItem = new Element("modifyItem");

			// Add namespace to modifyItem element
			modifyItem.addNamespaceDeclaration(WEBTEKNAMESPACE);
			modifyItem.setNamespace(WEBTEKNAMESPACE);

			// Add shopKey element to modifyItem element
			modifyItem.addContent(new Element("shopKey").setText(SHOPKEY)
					.setNamespace(WEBTEKNAMESPACE));

			// Add item content to modifyItem element
			List<Element> itemChildren = itemElement.getChildren();
			for (Element element : itemChildren) {
				if (element.getName() == "itemID") {
					modifyItem.addContent(itemID);
				} else {
					modifyItem.addContent(element.clone());
				}

			}

			Document modifyItemDocument = new Document(modifyItem);
			XMLOutputter modOutputter = new XMLOutputter();

			// Output modifyItem element as document, over HTTP
			URL modurl = new URL(CLOUDURL + MODIFYITEM);

			HttpURLConnection modcon = (HttpURLConnection) modurl
					.openConnection();
			modcon.setRequestMethod("POST");
			modcon.setDoOutput(true);
			modcon.connect();

			modOutputter.output(modifyItemDocument, modcon.getOutputStream());

			if (modcon.getResponseCode() != 200) {
				System.out.print("noget gik galt med responsen: "
						+ modcon.getResponseMessage());
			}

			modcon.disconnect();

		} catch (Exception e) {
			System.out.println("An error occurred:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
