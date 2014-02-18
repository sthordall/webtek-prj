package web.tek.icouldntcareless.musicshop.helpers;

import org.jdom2.Namespace;


/**
 * @author shiphter
 * Class containing application constants. Important URL's etc.
 * All Constants must be "public static final"
 */
public class ApplicationConstants {
	public static final Namespace WEBTEKNAMESPACE = Namespace.getNamespace(
			"w", "http://www.cs.au.dk/dWebTek/2014");
	public static final String SHOPID = "466";
	public static final String SHOPKEY = "79D23EFCA0DAAD24E5FFF385";
	public static final String CLOUDURL = "http://services.brics.dk/java4/cloud";
	public static final String CREATEITEM = "/createItem";
	public static final String MODIFYITEM = "/modifyItem";
	public static final String LISTITEMS = "/listItems?shopID="+SHOPID;
	
}
