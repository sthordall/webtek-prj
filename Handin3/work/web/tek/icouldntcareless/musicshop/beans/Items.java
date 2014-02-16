package web.tek.icouldntcareless.musicshop.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class Items implements Serializable {
	
	private static final long serialVersionUID = 2597636401051332599L;

	private List<Item> itemList;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	
}
