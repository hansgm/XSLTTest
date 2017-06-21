package meylingmedia.nl.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.XMLConstants;
import javax.xml.validation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MainM implements ErrorListener, ErrorHandler {
	public SourceContainer sourceContainer;
	public String filePath;
	public String stackTrace;

	public void saveAs(String fileName) throws Exception {
		File fi = new File(fileName);
		FileOutputStream fs = new FileOutputStream(fi);
		ObjectOutputStream objOut = new ObjectOutputStream(fs);
		try {
			filePath = fi.getAbsolutePath();
			objOut.writeObject(sourceContainer);
		} finally {
			objOut.close();
		}
	}

	public void load(String fileName) throws Exception {
		File fi = new File(fileName);
		FileInputStream fs = new FileInputStream(fi);
		ObjectInputStream objIn = new ObjectInputStream(fs);
		try {
			filePath = fi.getAbsolutePath();
			sourceContainer = (SourceContainer) objIn.readObject();
		} finally {
			objIn.close();
		}
	}

	public void setFields(String iXsltSource, String iXmlSource,
			String iXsltText, String iXmlText) {
		sourceContainer.addToStackIfChanged(sourceContainer.xsltVersions,
				iXsltSource, iXsltText);
		sourceContainer.addToStackIfChanged(sourceContainer.xmlVersions,
				iXmlSource, iXmlText);
	}

	public String transform() {
		StringWriter sw = new StringWriter();
		stackTrace = "";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			TransformerFactory tfactory = TransformerFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					sourceContainer.getLastXml().source)));
			StreamSource stylesource = new StreamSource(new StringReader(
					sourceContainer.getLastXslt().source));
			Transformer transformer = tfactory.newTransformer(stylesource);
			transformer.setErrorListener(this);
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
			sw.close();

			return prettyPrint(sw.toString());

		} catch (Exception e) {
			// e.printStackTrace();
			Writer p = new StringWriter();
			PrintWriter pw = new PrintWriter(p);
			e.printStackTrace(pw);
			stackTrace += p.toString();
			return e.toString();
		}
	}

	public String xmlCleanUp(String stringIn) {
		return stringIn.replaceAll(">\\s*<", "><");
	}


	String XSDvalidate() {
		String retString = "";

		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(new StringReader(
					sourceContainer.getLastXslt().source)));
			Validator validator = schema.newValidator();
			validator.setErrorHandler(this);
			validator.validate(new StreamSource(new StringReader(
					sourceContainer.getLastXml().source)));

		} catch (Exception e) {
			retString = "";
			e.printStackTrace();
		}

		return retString;
	}

	public String prettyPrint(String stringIn) {
		String retString = xmlCleanUp(stringIn);

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(new InputSource(new StringReader(
					retString)));

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

			// transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
			// "4");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");

			StreamResult result = new StreamResult(new StringWriter());

			DOMSource source = new DOMSource(document);

			transformer.transform(source, result);

			retString = result.getWriter().toString();
		} catch (Exception e) {
			retString = stringIn;
		}

		return retString;
	}

	MainM() {
		sourceContainer = new SourceContainer();
	}

	@Override
	public void warning(TransformerException exception)
			throws TransformerException {
		// TODO Auto-generated method stub
		stackTrace += "\nWarning: " + exception.getMessage();

	}

	@Override
	public void error(TransformerException exception)
			throws TransformerException {
		// TODO Auto-generated method stub
		stackTrace += "\nError: " + exception.getMessage();
	}

	@Override
	public void fatalError(TransformerException exception)
			throws TransformerException {
		// TODO Auto-generated method stub
		System.out.println("Were here");
		stackTrace += "\nFatal error: " + exception.getMessage();
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println(e);
		
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println(e);
	}
}
