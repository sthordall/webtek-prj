package org.icouldntcareless.webtek.xml;

import org.jdom2.Namespace;

public class DeleteProgram {
	
	private static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace(
			"w", "http://www.cs.au.dk/dWebTek/2014");
	private static final String CLOUDURL = "http://services.brics.dk/java4/cloud";
	private static final String DELETE = "/deleteItem";
	private static final String SHOPKEY = "79D23EFCA0DAAD24E5FFF385";

	public static void main(String[] args) {
		
		xmlReader delete = new xmlReader();
		
		delete.DeleteProduct(SHOPKEY, "2168", WEBTEKNAMESPACE, CLOUDURL+DELETE);
	}
	//jsdjds

}
