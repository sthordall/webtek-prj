package org.icouldntcareless.webtek.xml;

import java.io.File;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

public class Program {
	
	private static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace(
			"w", "http://www.cs.au.dk/dWebTek/2014");
	private static final String CLOUDURL = "http://services.brics.dk/java4/cloud";
	private static final String CREATEITEM = "/createItem";
	private static final String MODIFYITEM = "/modifyItem";
	private static final String SHOPKEY = "79D23EFCA0DAAD24E5FFF385";
	
	public static void main(String[] args) {
		try {
			xmlReader reader = new xmlReader();
			Element rootElement = reader.getRootElement(new File(args[0]), new File(args[1]));
			Document createItemDocument = reader.getCreateItemRequest(rootElement, SHOPKEY, WEBTEKNAMESPACE);
			Document responseDocument = reader.outputXMLonHTTP("POST", new URL(CLOUDURL + CREATEITEM), createItemDocument);
			Document modifyItemDocument = reader.getModifyItemRequest(rootElement, responseDocument.getRootElement(), SHOPKEY, WEBTEKNAMESPACE);
			reader.outputXMLonHTTP("POST", new URL(CLOUDURL + MODIFYITEM), modifyItemDocument);
		} catch (Exception e) {
			System.out.println("An error occurred:" + e.getMessage());
		}
		
	}
}
