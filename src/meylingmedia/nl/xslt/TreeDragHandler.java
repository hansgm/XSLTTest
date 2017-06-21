package meylingmedia.nl.xslt;

import javax.swing.TransferHandler;
import java.awt.datatransfer.StringSelection; 
import java.awt.datatransfer.Transferable; 
import javax.swing.JComponent; 
import javax.swing.JTree; 

public class TreeDragHandler extends TransferHandler {

	private static final long serialVersionUID = -6612873748207139747L;

	public TreeDragHandler() 
	{ 
		super(); 
	} 

	public int getSourceActions(JComponent comp) 
	{ 
		return COPY; 
	} 

	
	public Transferable createTransferable(JComponent c)  
	{ 
		if (!(c instanceof JTree)) return null;
		JTree tree = (JTree) c;
		if (!(tree.getSelectionPath().getLastPathComponent() instanceof TreeViewXMLNode)) return null;
		return new StringSelection(((TreeViewXMLNode) tree.getSelectionPath().getLastPathComponent()).getNodeXML());
	} 
	

	public void exportDone(JComponent c, Transferable t, int action)  
	{ 
		return;
	}

	public boolean canImport(TransferHandler.TransferSupport info)  {
		return false;

		
	}
	public boolean importData(TransferHandler.TransferSupport info){
		return true;
	}
}
