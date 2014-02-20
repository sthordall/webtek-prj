package web.tek.icouldntcareless.musicshop.helpers;

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

import web.tek.icouldntcareless.musicshop.beans.Item;


public class XMLParser {
	
	public Element getRootElement(File xml, File schema) throws JDOMException,
			IOException {
		XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(schema);
		SAXBuilder builder = new SAXBuilder(schemafac);

		Document document = builder.build(xml);
		return document.getRootElement();

	}
	
//	public Document GenerateDocumentFromItem(Item item, Namespace namespace, String shopKey){
//		Element modifyitem = new Element("modifyItem");
//		modifyitem.addNamespaceDeclaration(namespace);
//		modifyitem.setNamespace(namespace);
//		
//		modifyitem.addContent(new Element("shopKey").setText(shopKey)
//				.setNamespace(namespace));
//		modifyitem.addContent(new Element("itemID").setText(item.getItemID()));
//		modifyitem.addContent(new Element("itemName").setText(item.getItemName()));
//		modifyitem.addContent(new Element("itemPrice").setText(item.getItemPrice()));
//		modifyitem.addContent(new Element("itemURL").setText(item.getItemURL()));
//		modifyitem.addContent(new Element("itemDescription").setText(item.getItemDescription()));
//		
//		return new Document(modifyitem);
//	}
	
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
	
}
