package web.tek.icouldntcareless.musicshop.helpers;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;

public class XMLParser {
	public Element getRootElement(File xml, File schema) throws JDOMException,
			IOException {
		XMLReaderJDOMFactory schemafac = new XMLReaderXSDFactory(schema);
		SAXBuilder builder = new SAXBuilder(schemafac);

		Document document = builder.build(xml);
		return document.getRootElement();

	}
}
