package com.webtek.musicshop.Handlers;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    /**
     * Reads and validates XML input according to a specified XMLSchema.
     *
     * @param xmlToReadAndValidate as the XML to read and validate
     * @param schemaToValidateWith as the XMLSchema to validate with
     * @return the validated document
     * @throws org.jdom2.JDOMException       if the XML is invalid XML or if the XML does not conform to
     *                             the schema
     * @throws java.io.IOException
     */
    @SuppressWarnings("deprecation")
    public Document readAndValidateXML(InputStream xmlToReadAndValidate,
                                              Path schemaToValidateWith) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.setProperty(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        builder.setProperty(
                "http://java.sun.com/xml/jaxp/properties/schemaSource",
                schemaToValidateWith.toFile());
        return builder.build(xmlToReadAndValidate);
    }

    /**
     * Reads XML input according to a specified XMLSchema.
     *
     * @param xmlToRead as the XML to read
     * @return the read document
     * @throws org.jdom2.JDOMException       if the XML is not wellformed XML
     * @throws java.io.IOException
     */
    @SuppressWarnings("deprecation")
    public Document readXML(InputStream xmlToRead) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        return builder.build(xmlToRead);
    }

    /**
     * @see #readAndValidateXML(java.io.InputStream, java.nio.file.Path)
     */
    public void validateXML(Document document, Path schemaToValidateWith) throws IOException, JDOMException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new XMLOutputter().output(document, out);
        readAndValidateXML(new ByteArrayInputStream(out.toByteArray()), schemaToValidateWith);
    }
}