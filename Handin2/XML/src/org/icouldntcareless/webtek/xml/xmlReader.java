package org.icouldntcareless.webtek.xml;

import org.jdom2.Document;

public class xmlReader {

	public static void main(String[] args) {

		// Generate JDOM Document from given file

		// Get information from document

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
