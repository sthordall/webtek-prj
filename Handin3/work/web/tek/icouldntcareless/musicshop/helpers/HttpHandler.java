package web.tek.icouldntcareless.musicshop.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class HttpHandler {
	
	public Document outputXMLonHTTP(String httpRequestType, URL url,
			Document docToOutput) throws ProtocolException, IOException,
			JDOMException {
		XMLOutputter outputter = new XMLOutputter();

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(httpRequestType);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.connect();

		outputter.output(docToOutput, con.getOutputStream());

		Document responseDocument = null;

		try {
			SAXBuilder builder = new SAXBuilder();
			responseDocument = builder.build((InputStream) con.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (con.getResponseCode() != 200) {
			System.out.print("An network error occurred: "
					+ con.getResponseCode() + " - " + con.getResponseMessage());
		}

		con.disconnect();

		return responseDocument;
	}
	
	
}
