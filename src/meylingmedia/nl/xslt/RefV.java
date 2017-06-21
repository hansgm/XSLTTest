package meylingmedia.nl.xslt;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RefV extends JFrame {
	
	private RefC controller;
	private JTree tree;
	
	void setFont(){
		new Font("Monaco", Font.PLAIN, 12);
	}
	
	
	RefV (RefC iController){
		controller = iController;
	}
	
	void createScreen(){
		setFont();
		
		setSize(500, 600);
		setTitle("XSLT Reference");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout());

    	DefaultMutableTreeNode top =
    	        new DefaultMutableTreeNode("XSLT reference");
    	
    	createNodes(top, null);
    	
    	tree = new JTree(top);
    	
    	TransferHandler th = new TreeDragHandler();
     	tree.setTransferHandler(th);
     	tree.setDragEnabled(true);

     	
    	JScrollPane treeView = new JScrollPane(tree); 
    	panel.add(treeView, BorderLayout.CENTER);
    	this.add(panel);
    	this.setVisible(true);
	}
	
	void createNodes(DefaultMutableTreeNode parentNode, Node parent){
		Node currentNode = null;
		NodeList elements = null;
		
		if (parent == null){
			currentNode = controller.getTopLevel();
			DefaultMutableTreeNode level = new TreeViewXMLNode(currentNode);
			parentNode.add(level);
			createNodes(level, currentNode);
			
		}
		else{
			elements = controller.getChildren(parent);
			if (elements != null){
				for (int eCount = 0; eCount < elements.getLength(); eCount++){
					DefaultMutableTreeNode level = null;
					if (!elements.item(eCount).getNodeName().equals("#text")){
						level = new TreeViewXMLNode(elements.item(eCount));
						parentNode.add(level);	
					}
					
					createNodes(level, elements.item(eCount));
				}			
			}				
		}  
	}

}
