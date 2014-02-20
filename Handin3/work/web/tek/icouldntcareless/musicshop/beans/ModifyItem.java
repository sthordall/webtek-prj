package web.tek.icouldntcareless.musicshop.beans;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "modifyItem", eager = true)
@RequestScoped
public class ModifyItem implements Serializable{
	
	private static final long serialVersionUID = -7405929614749438260L;

	@ManagedProperty(value="#{Item}")
	private Item BeanItem;

	
//	@PostConstruct
//	public void Init(){
//		this.itemId = "666";
//		this.itemName = "Devil";
//	}
	
	public ModifyItem(){
		System.out.println("ModifyItem Initiated");
	}
	

	
//	public String getItemId(){
//		if(itemBean != null){
//			return itemBean.getItemId();
//		}
//		return this.itemId;
//	}
//	
//	public String getItemName(){
//		if(itemName != null){
//			return itemBean.getItemName();
//		}
//		return this.itemName;
//	}
//	
//	public void setItemBean(ItemBean itemBean){
//		this.itemBean = itemBean;
//	}
	
//	public void SaveItem(){
//		this.itemBean.setItemName(itemName);
//		this.itemBean.setItemId(itemId);
//		
//	}
}
