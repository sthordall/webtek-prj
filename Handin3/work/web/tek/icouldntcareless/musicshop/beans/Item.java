package web.tek.icouldntcareless.musicshop.beans;

import java.io.Serializable;
import java.io.StringReader;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import web.tek.icouldntcareless.musicshop.helpers.ApplicationConstants;
import web.tek.icouldntcareless.musicshop.helpers.HttpHandler;

@ManagedBean(name = "Item", eager = true)
@SessionScoped
public class Item implements Serializable {
	private static final long serialVersionUID = 148246321600366013L;

	private String itemID;

	private String itemName;

	private String itemURL;

	private String itemPrice;

	private String itemStock;

	private String itemDescription;

	public Item() {
	}

	public Item(String itemID, String itemName, String itemURL,
			String itemPrice, String itemStock, String itemDescription) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemURL = itemURL;
		this.itemPrice = itemPrice;
		this.itemStock = itemStock;
		this.itemDescription = itemDescription;
	}

	public void RetrieveItemToModify() {
		System.out.println("RetrieveItemToModify called...");
		System.out.println(this.itemID);
		Items itemlist = new Items();
		itemlist.initForModify();
		for (Item item : itemlist.getItemList()) {

			if (item.itemID.equals(this.itemID)) {
				System.out.println("itemFound");
				this.itemName = item.itemName;
				this.itemDescription = item.itemDescription;
				this.itemPrice = item.itemPrice;
				this.itemURL = item.itemURL;
			}
		}
	}

	public String SaveItemToModify() {
		System.out.println("SaveItemToModify called...");
		HttpHandler handler = new HttpHandler();
		Element modifyitem = new Element("modifyItem");
		modifyitem
				.addNamespaceDeclaration(ApplicationConstants.WEBTEKNAMESPACE);
		modifyitem.setNamespace(ApplicationConstants.WEBTEKNAMESPACE);
		System.out.println(this.itemURL);
		modifyitem.addContent(new Element("shopKey").setText(
				ApplicationConstants.SHOPKEY).setNamespace(
				ApplicationConstants.WEBTEKNAMESPACE));
		modifyitem.addContent(new Element("itemID").setText(this.itemID)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));
		modifyitem.addContent(new Element("itemName").setText(this.itemName)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));
		modifyitem.addContent(new Element("itemPrice").setText(this.itemPrice)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));
		modifyitem.addContent(new Element("itemURL").setText(this.itemURL)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));
		// modifyitem.addContent(new
		// Element("itemDescription").setText(this.itemDescription).setNamespace(ApplicationConstants.WEBTEKNAMESPACE));

		try {
			SAXBuilder saxBuilder = new SAXBuilder();

			modifyitem.addContent(new Element("itemDescription").addContent(
					saxBuilder.build(new StringReader(this.itemDescription))
							.getRootElement().clone()).setNamespace(
					ApplicationConstants.WEBTEKNAMESPACE));
		} catch (Exception e) {

			e.printStackTrace();
		}

		Document document = new Document(modifyitem);
		XMLOutputter outputter = new XMLOutputter();

		try {
			outputter.output(document, System.out);

			// Returns null if the responseCode is not 200 when persisting data
			// up to the cloud
			if (handler.outputXMLonHTTP("POST", new URL(
					ApplicationConstants.MODIFYITEM), document) != false) {
				return "Modified";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "NotModified";
		}
		return "NotModified";
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemURL() {
		return itemURL;
	}

	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemStock() {
		return itemStock;
	}

	public void setItemStock(String itemStock) {
		this.itemStock = itemStock;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

}
