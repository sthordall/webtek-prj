package web.tek.icouldntcareless.musicshop.helpers;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jdom2.Document;
import org.jdom2.Element;

@ManagedBean(name = "LoginVerifier", eager = true)
@RequestScoped
public class LoginVerifier implements Serializable {

	private static final long serialVersionUID = 3378387119805935454L;
	
	
	public Document getListOfCustomers(){
		HttpHandler httpHandler = new HttpHandler();
		URL listCustomers;
		Document tempDoc = null;
		try {
			listCustomers = new URL(ApplicationConstants.LISTCUSTOMERS);
			tempDoc = httpHandler.HttpRequest("GET", listCustomers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Element> customerElementList = tempDoc.getRootElement().getChildren();
		
		List<String> customerList = new ArrayList<String>();
		
		for (Element element : customerElementList) {
			element.getChildText("customerName", ApplicationConstants.WEBTEKNAMESPACE);
		}
		
		
		return tempDoc;
	}
	
	
}
