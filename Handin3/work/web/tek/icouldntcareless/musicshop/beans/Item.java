package web.tek.icouldntcareless.musicshop.beans;

import java.io.Serializable;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import web.tek.icouldntcareless.musicshop.helpers.ApplicationConstants;
import web.tek.icouldntcareless.musicshop.helpers.HttpHandler;
import web.tek.icouldntcareless.musicshop.helpers.Validator;
import web.tek.icouldntcareless.musicshop.helpers.XMLParser;

/**
 * @author dxong
 * The class is our MODEL, which is responsible/handling Modify and Create Items
 * and Adjusting the stock on an item.
 */
/**
 * @author dxong
 * 
 */
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

	/**
	 * This method is initiated via a button on CreateItem.jsf Initiates a JDOM,
	 * with given data and persist data into cloud
	 * 
	 * @return Success: Redirects back to Management.jsf Fail: Redirect back to
	 *         CreateItem.jsf
	 */
	public String CreateItem() {
		XMLParser xmlParser = new XMLParser();
		Validator xmlValidator = new Validator();
		HttpHandler httpHandler = new HttpHandler();

		// // String validatorPath =
		// // "/Users/dxong/git/WebTekProject/Handin3/xmlSchema/cloud.xsd";
		// String validatorPath = "WEB-INF/xmlSchema/cloud.xsd";
		// // String validatorPath = "WebTekProject" + File.separator +
		// "Handin3"+
		// // File.separator + "WEB-INF" + File.separator + "xmlSchema" +
		// // File.separator + "cloud.xsd";
		// System.out.println(validatorPath);
		// Path xmlpath = Paths.get(validatorPath);
		//
		// System.out.println(xmlpath);

		Document createDocument = xmlParser.CreateDocItemFromItemName(
				this.itemName, ApplicationConstants.SHOPKEY,
				ApplicationConstants.WEBTEKNAMESPACE);

		XMLOutputter outputter = new XMLOutputter();

		// Validating Document to Persist up to cloud
		try {
			outputter.output(createDocument, System.out);
			// xmlValidator.validateXML(createDocument, xmlpath);
			if (httpHandler.outputXMLonHTTP("POST", new URL(
					ApplicationConstants.CREATEITEM), createDocument) != false) {
				return "itemCreated";
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "itemNotCreated";
		}
		return "itemNotCreated";
	}

	/**
	 * Before ModifyItem.jsf is rendered, this method is callled to initate the
	 * page with a given itemID
	 */
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

	/**
	 * Method is initated via a button. Upon execution, this method tries to
	 * save the item into cloud
	 * 
	 * @return Success: Redirects back to Management.jsf Fail: Redibeck back to
	 *         ModifyItem.jsf
	 */
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

		modifyitem.addContent(new Element("itemDescription").addContent(
				new Element("document").setText(this.itemDescription)
						.setNamespace(ApplicationConstants.WEBTEKNAMESPACE))
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));

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

	/**
	 * Before AdjustStock.jsf is rendered, this method is callled to initate the
	 * page with a given itemID
	 */
	public void RetrieveItemToAdjust() {
		System.out.println("RetrieveItemToAdjust called...");
		System.out.println(this.itemID);
		Items itemlist = new Items();
		itemlist.init();
		for (Item item : itemlist.getItemList()) {

			if (item.itemID.equals(this.itemID)) {
				System.out.println("itemFound");
				this.itemName = item.itemName;
				this.itemStock = item.itemStock;
			}
		}
	}

	/**
	 * Method is called via button on AdjustStock.jsf. The method validates the
	 * input if it contains integer only
	 * 
	 * @return Success: Redirects back to Management.jsf Fail: Redirect back to
	 *         AdjustStock.jsf
	 */
	public String ItemStockAdjust() {
		System.out.println("ItemStockAdjust called...");

		String regex = "[0-9]+";

		if (!this.itemStock.matches(regex)) {
			return "stockNotAdjusted";
		}

		HttpHandler handler = new HttpHandler();

		Element adjustStock = new Element("adjustItemStock");
		adjustStock
				.addNamespaceDeclaration(ApplicationConstants.WEBTEKNAMESPACE);
		adjustStock.setNamespace(ApplicationConstants.WEBTEKNAMESPACE);

		adjustStock.addContent(new Element("shopKey").setText(
				ApplicationConstants.SHOPKEY).setNamespace(
				ApplicationConstants.WEBTEKNAMESPACE));
		adjustStock.addContent(new Element("itemID").setText(this.itemID)
				.setNamespace(ApplicationConstants.WEBTEKNAMESPACE));
		adjustStock.addContent(new Element("adjustment")
				.setText(this.itemStock).setNamespace(
						ApplicationConstants.WEBTEKNAMESPACE));

		Document document = new Document(adjustStock);
		XMLOutputter outputter = new XMLOutputter();

		try {
			outputter.output(document, System.out);

			// Returns null if the responseCode is not 200 when persisting data
			// up to the cloud
			if (handler.outputXMLonHTTP("POST", new URL(
					ApplicationConstants.ADJUSTSTOCK), document) != false) {
				return "stockAdjusted";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "stockNotAdjusted";
		}
		return "stockNotAdjusted";

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
