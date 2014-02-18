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

/**
 * @author shiphter
 * Handles all http requests
 */
public class HttpHandler {
	
	
	
	/**
	 * Outputs XML JDOM Document on given URL.
	 * Returns response as JDOM Document, if no response, NULL is returned.
	 * 
	 * @param httpRequestType
	 * @param url
	 * @param docToOutput
	 * @return Document
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws JDOMException
	 */
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
	
	/**
	 * Request on URL parameter, and returns response as a JDOM Document.
	 * If no response, return NULL.
	 * 
	 * @param httpRequestType
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Document HttpRequest(String httpRequestType, URL url) throws IOException {
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(httpRequestType);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.connect();

		Document responseDocument = null;
		
		try {
			SAXBuilder builder = new SAXBuilder();
			responseDocument = builder.build(con.getInputStream());
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
