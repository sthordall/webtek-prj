package web.tek.icouldntcareless.musicshop.beans;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jdom2.Element;

import web.tek.icouldntcareless.musicshop.helpers.ApplicationConstants;
import web.tek.icouldntcareless.musicshop.helpers.HttpHandler;
import web.tek.icouldntcareless.musicshop.helpers.XMLParser;

@ManagedBean(name = "Items", eager = true)
@ViewScoped
public class Items implements Serializable {

	private HttpHandler httpHandler;
	private XMLParser xmlParser;

	private static final long serialVersionUID = 2597636401051332599L;

	private ArrayList<Item> itemList;

	@PostConstruct
	public void init() {
		httpHandler = new HttpHandler();
		xmlParser = new XMLParser();

		try {
			URL requestUrl = new URL(ApplicationConstants.LISTITEMS);
			Element responseRoot = httpHandler.HttpRequest("GET", requestUrl)
					.getRootElement();

			if (responseRoot == null) {
				throw new Exception("Response from itemList request was null");
			} else {
				itemList = new ArrayList<Item>();

				for (Element itemChild : responseRoot.getChildren()) {

					itemList.add(new Item(itemChild.getChildText("itemID",
							ApplicationConstants.WEBTEKNAMESPACE), itemChild
							.getChildText("itemName",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemChild.getChildText("itemURL",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemChild.getChildText("itemPrice",
									ApplicationConstants.WEBTEKNAMESPACE),
							itemChild.getChildText("itemStock",
									ApplicationConstants.WEBTEKNAMESPACE),
							xmlParser.getDescriptionInHtml(itemChild.getChild(
									"itemDescription",
									ApplicationConstants.WEBTEKNAMESPACE))));
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

}
