package web.tek.icouldntcareless.musicshop.beans;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

import org.jdom2.Element;

import web.tek.icouldntcareless.musicshop.helpers.ApplicationConstants;
import web.tek.icouldntcareless.musicshop.helpers.HttpHandler;

@ManagedBean(name = "Items", eager = true)
@NoneScoped
public class Items implements Serializable {

	private HttpHandler httpHandler;

	private static final long serialVersionUID = 2597636401051332599L;

	private ArrayList<Item> itemList;

	@PostConstruct
	public void init() {
		httpHandler = new HttpHandler();

		try {
			URL requestUrl = new URL(ApplicationConstants.CLOUDURL
					+ ApplicationConstants.LISTITEMS);
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
							itemChild.getChildText("itemDescription",
									ApplicationConstants.WEBTEKNAMESPACE)));
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
