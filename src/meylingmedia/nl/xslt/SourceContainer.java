package meylingmedia.nl.xslt;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Stack;

public class SourceContainer implements Serializable{
	public Stack<SourceVersion> xsltVersions;
	public Stack<SourceVersion> xmlVersions;

	public class SourceVersion implements Serializable{
		private static final long serialVersionUID = 1L;
		public String version;
		public String source;
		public String text;
		
		SourceVersion(String v, String s, String t){
			version = v;
			source = s;
			text = t;
		}
	}

	public SourceVersion getLastXslt(){
		if (xsltVersions.isEmpty()){
			return new SourceVersion("", "", "");
		}
		else{
			return xsltVersions.peek();
		}
	}

	public SourceVersion getLastXml(){
		if (xmlVersions.isEmpty()){
			return new SourceVersion("", "", "");
		}
		else{
			return xmlVersions.peek();
		}
	}
	
	
	public void addToStackIfChanged(Stack<SourceVersion> theStack, String iSource, String iText){
		if (theStack.isEmpty()){
			theStack.push(new SourceVersion(getTimeStampAsString(),iSource, iText));
		}	
		else{
			if (!theStack.peek().source.equals(iSource)){
				theStack.push(new SourceVersion(getTimeStampAsString(),iSource, iText));				
			}
			else if (!theStack.peek().text.equals(iText)){
				theStack.peek().text = iText;
			}
		}
	}
	
	private String getTimeStampAsString(){
		return new Timestamp(System.currentTimeMillis()).toString();
	}
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SourceContainer(){
		xsltVersions = new Stack<SourceVersion>();
		xmlVersions = new Stack<SourceVersion>();
		
	}

}
