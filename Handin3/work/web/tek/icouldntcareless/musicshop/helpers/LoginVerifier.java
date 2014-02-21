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
import org.jdom2.output.XMLOutputter;

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

	public String loginToAdminsite() {

		System.out.println("loginToAdminsite invoked");
		
		HttpHandler httphandler = new HttpHandler();
		XMLParser xmlParser = new XMLParser();
		URL url = null;
		Element ResponseElement = null;
		
		System.out.println("Initializations done.");

		try {
			url = new URL(ApplicationConstants.LOGIN);
			System.out.println("login url constructed");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Username: "+customer.getCustomerName() + "\n" + "Password: "+customer.getCustomerPass());
		
		//Checking if username is Admin
		if(customer.getCustomerName()=="iccladmin"){
			customer.setCustomerIsAdmin(true);
		}
		
		Document doc = xmlParser.getLoginRequest(customer.getCustomerName(),
				customer.getCustomerPass());
		
		XMLOutputter outputter = new XMLOutputter();
		try {
			outputter.output(doc, System.out);
		} catch(Exception e){
			e.printStackTrace();
		}

		try {
			Boolean loginDocumentResponse = httphandler.outputXMLonHTTP(
					"POST", url, doc);
			
			//Test ved output to System.out
			System.out.println(loginDocumentResponse.booleanValue());
			
			if(loginDocumentResponse){
				return "Login_successful";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Login_Unsuccessful";
		}

		
		return "Login_Unsuccessful";
	}
}