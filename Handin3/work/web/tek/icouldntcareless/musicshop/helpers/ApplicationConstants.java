package web.tek.icouldntcareless.musicshop.helpers;

import org.jdom2.Namespace;

/**
 * Class containing application constants. Important URL's etc. All Constants
 * must be "public static final"
 */
public class ApplicationConstants {
	public static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace("w",
			"http://www.cs.au.dk/dWebTek/2014");
	public static final Namespace CUSTOMJSFTAGNAMESPACE = Namespace
			.getNamespace("customjsf", "http://dWebTek.dk/jsf/tag");
	public static final Namespace JSFHTMLNAMESPACE = Namespace.getNamespace(
			"h", "http://java.sun.com/jsf/html");
	public static final String SHOPID = "446";
	public static final String SHOPKEY = "79D23EFCA0DAAD24E5FFF385";
	public static final String CLOUDURL = "http://services.brics.dk/java4/cloud";
	public static final String CREATEITEM = CLOUDURL + "/createItem";
	public static final String MODIFYITEM = CLOUDURL + "/modifyItem";
	public static final String LISTITEMS = CLOUDURL + "/listItems?shopID="
			+ SHOPID;
	public static final String LISTCUSTOMERS = CLOUDURL + "/listCustomers";
	public static final String LOGIN = CLOUDURL + "/login";
	public static final String CREATECUSTOMER = CLOUDURL + "/createCustomer";
	public static final String ADJUSTSTOCK = CLOUDURL + "/adjustItemStock";
}