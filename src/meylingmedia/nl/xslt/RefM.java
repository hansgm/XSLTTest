package meylingmedia.nl.xslt;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;



public class RefM {
	public Document document = null;
	private static final String fileName = "src/meylingmedia/nl/xslt/XSLTReference.xml";
	//  /Users/hansgmeligmeyling/Documents/workspace/TestHGM/./XSLTReference.xml
	//	/Users/hansgmeligmeyling/Documents/workspace/TestHGM/src/meylingmedia/nl/xslt/XSLTReference.xml
	
	RefM(){
		try{
			File fi = new File(fileName);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(new FileInputStream(fi));
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
		}
	}
}
