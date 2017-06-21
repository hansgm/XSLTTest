package meylingmedia.nl.xslt;

import java.io.StringWriter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class TreeViewXMLNode extends DefaultMutableTreeNode{

	private static final long serialVersionUID = 1L;
	Node xmlNode;
	
	private static String concatNameAndAttributes(Node treeNode){
		NamedNodeMap attributes = null;
		
		String retString = treeNode.getNodeName();
		
		attributes = treeNode.getAttributes();
		if (attributes != null){
			for (int aCount = 0; aCount <= attributes.getLength() - 1; aCount++){
				retString 	+= " " 
			            	+ attributes.item(aCount).getNodeName() 
			            	+ "=" 
			            	+ attributes.item(aCount).getNodeValue();
			}
		}		
		return retString;
	}
		
	TreeViewXMLNode(Node treeNode){
		super(concatNameAndAttributes(treeNode));
		xmlNode = treeNode;
	}
	
	Node getNode(){
		return xmlNode;	
	}
	
	String getNodeXML(){
		DOMSource source = new DOMSource(xmlNode);
		StringWriter xmlStringWriter = new StringWriter();
		StreamResult result = new StreamResult(xmlStringWriter);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		
		try {
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			transformer.transform(source, result);
		} catch (Exception e) {
			// Ignore
		}
		return xmlStringWriter.getBuffer().toString();		
	}
}
