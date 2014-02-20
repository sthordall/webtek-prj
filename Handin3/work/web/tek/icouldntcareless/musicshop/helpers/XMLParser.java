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

public class XMLParser {
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

	public String getDescriptionInHtml(Element itemDescription)
			throws JDOMException, IOException {
		String descriptionStr = "";

		for (Element descriptionChild : itemDescription.getChildren()) {

			switch (descriptionChild.getName()) {
			case "document":
				descriptionChild.setName("div");
				break;
			case "bold":
				descriptionChild.setName("b");
				break;
			case "italics":
				descriptionChild.setName("i");
				break;
			case "list":
				descriptionChild.setName("ul");
				break;
			case "item":
				descriptionChild.setName("li");
				break;
			default:
				break;
			}

			descriptionChild
					.setNamespace(ApplicationConstants.JSFHTMLNAMESPACE);

			descriptionStr = descriptionStr + descriptionChild.getText()
					+ getDescriptionInHtml(descriptionChild);
		}

		return descriptionStr;
	}
}
