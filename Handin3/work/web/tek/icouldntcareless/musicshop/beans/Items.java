package web.tek.icouldntcareless.musicshop.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name = "Items", eager = true)
@ApplicationScoped
public class Items implements Serializable {
	
	private static final long serialVersionUID = 2597636401051332599L;

	private ArrayList<Item> itemList;
	
	@PostConstruct
	public void init() {
		itemList = new ArrayList<Item>(Arrays.asList(
				new Item("8", "Gibson", "www.guitar.dk", "1495", "3", "Very Nice!"),
				new Item("110", "Bummelum", "www.Trommer.dk", "14995", "8", "Very Super Nice!")));
		
	}
	
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	
}
