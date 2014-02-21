//package web.tek.icouldntcareless.musicshop.helpers;

//import java.io.Serializable;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//
//@ManagedBean(name = "LoginVerifier", eager = true)
//@RequestScoped
//public class LoginVerifier implements Serializable {
//
//	private static final long serialVersionUID = 3378387119805935454L;
//	
//	
//	public Document getListOfCustomers(){
//		HttpHandler httpHandler = new HttpHandler();
//		URL listCustomers;
//		Document tempDoc = null;
//		try {
//			listCustomers = new URL(ApplicationConstants.LISTCUSTOMERS);
//			tempDoc = httpHandler.HttpRequest("GET", listCustomers);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		List<Element> customerElementList = tempDoc.getRootElement().getChildren();
//		
//		List<String> customerList = new ArrayList<String>();
//		
//		for (Element element : customerElementList) {
//			element.getChildText("customerName", ApplicationConstants.WEBTEKNAMESPACE);
//		}
//		
//		
//		return tempDoc;
//	}
//	
//	
//}







package web.tek.icouldntcareless.musicshop.helpers;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import web.tek.icouldntcareless.musicshop.beans.Customer;

@ManagedBean(name = "LoginVerifier", eager = true)
@RequestScoped
public class LoginVerifier implements Serializable {

	private static final long serialVersionUID = 3378387119805935454L;

	@ManagedProperty(value = "#{Customer}")
	private Customer customer;

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	public Document getListOfCustomers() {
//		HttpHandler httpHandler = new HttpHandler();
//		URL listCustomers;
//		Document tempDoc = null;
//		try {
//			listCustomers = new URL(ApplicationConstants.LISTCUSTOMERS);
//			tempDoc = httpHandler.HttpRequest("GET", listCustomers);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// List<Element> customerElementList =
//		// tempDoc.getRootElement().getChildren();
//		//
//		// for (Element element : customerElementList) {
//		// element.getChildText("customerName",
//		// ApplicationConstants.WEBTEKNAMESPACE);
//		// }
//
//		return tempDoc;
//	}

	public Document loginToAdminsite() {

		HttpHandler httphandler = new HttpHandler();
		XMLParser xmlParser = new XMLParser();
		URL url = null;
		Element ResponseElement = null;

		try {
			url = new URL(ApplicationConstants.LOGIN);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		Document doc = xmlParser.getLoginRequest(customer.getCustomerName(),
				customer.getCustomerPass());

		try {
//			Document loginDocumentResponse = httphandler.outputXMLonHTTP(
//					"POST", url, doc);
//			ResponseElement = loginDocumentResponse.getRootElement();

			if (ResponseElement == null)
				System.out
						.print("Custom message when ResponseElement equals null");
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return new Document();
	}
}

