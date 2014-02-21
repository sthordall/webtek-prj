package web.tek.icouldntcareless.musicshop.beans;

import java.io.Serializable;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

import web.tek.icouldntcareless.musicshop.helpers.ApplicationConstants;
import web.tek.icouldntcareless.musicshop.helpers.HttpHandler;
import web.tek.icouldntcareless.musicshop.helpers.Validator;
import web.tek.icouldntcareless.musicshop.helpers.XMLParser;

@ManagedBean(name = "CreateCustomer", eager = true)
@ViewScoped
public class CreateCustomer implements Serializable {

	private static final long serialVersionUID = 8515895958919294161L;

	private String password;
	private String username;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String createCustomer() {
		XMLParser xmlParser = new XMLParser();
		Validator xmlValidator = new Validator();
		HttpHandler httpHandler = new HttpHandler();
		XMLOutputter outputter = new XMLOutputter();

		String validatorPath = "WEB-INF/xmlSchema/cloud.xsd";
		Path xmlpath = Paths.get(validatorPath);

		Document createDocument = xmlParser.getCreateCustomerRequest(
				ApplicationConstants.SHOPKEY, username, password,
				ApplicationConstants.WEBTEKNAMESPACE);

		try {
			outputter.output(createDocument, System.out);
			xmlValidator.validateXML(createDocument, xmlpath);
			if (httpHandler.outputXMLonHTTP("POST", new URL(
					ApplicationConstants.CREATECUSTOMER), createDocument) != false) {
				return "customerCreated";
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "customerNotCreated";
		}
		return "customerNotCreated";
	}
}
