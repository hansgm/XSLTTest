package meylingmedia.nl.xslt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JTree;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RefC implements MouseListener{
	private RefV theView = null;
	private RefM theModel = null;
	
	public void show(){
		theModel = new RefM();
		theView = new RefV(this);
		theView.createScreen();
	}
	
	public void cleanUp(){
		theView.dispose();
	}
	
	public void setFocus(){
		theView.setVisible(true);
	}	
	
	public Node getTopLevel(){
		return theModel.document.getElementsByTagName("xsltReference").item(0);
	}

	public NodeList getChildren(Node currentNode){
		return currentNode.getChildNodes();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mousePressed(MouseEvent e) {
		JTree tree = (JTree) e.getComponent();
		if (tree.getLastSelectedPathComponent() instanceof TreeViewXMLNode){
			// System.out.println(((TreeViewXMLNode)tree.getLastSelectedPathComponent()).getNode().getTextContent());
            JTextArea a = new JTextArea();    // CREATING A NEW OBJECT
            a.setText("Lodewijk");
            a.setEditable(true);
            a.copy();
		}		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}


	@Override
	public void mouseExited(MouseEvent e) {
	}

}
