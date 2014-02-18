package web.tek.icouldntcareless.musicshop.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import web.tek.icouldntcareless.musicshop.helpers.HttpHandler;
import web.tek.icouldntcareless.musicshop.helpers.XMLParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name = "Items", eager = true)
@RequestScoped
public class Items implements Serializable {

	private HttpHandler httpHandler;
	private XMLParser xmlParser;
	
	private static final long serialVersionUID = 2597636401051332599L;
	
	private ArrayList<Item> itemList;
	
	@PostConstruct
	public void init() {
		httpHandler = new HttpHandler();
		xmlParser = new XMLParser();
		
//		URL requestUrl = new URL(ApplicationConstants.CLOUDURL+ApplicationConstants.LISTITEMS);
			
//		xmlParser.getRootElement(httpHandler.outputXMLonHTTP("GET", requestUrl, docToOutput), schema)
		
//		itemList = new ArrayList<>();
		itemList = new ArrayList<Item>(Arrays.asList(
				new Item("8", "Gibson", "www.guitar.dk", "1495", "3", "Very Nice!"),
				new Item("120", "Bummelum", "www.Trommer.dk", "14995", "8", "Very Super Nice!")));
		
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	
}
