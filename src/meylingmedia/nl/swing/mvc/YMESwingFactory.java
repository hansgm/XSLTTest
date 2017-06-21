package meylingmedia.nl.swing.mvc;

import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class YMESwingFactory{
	public static JMenu createMenu(	String name,
									ActionListener controller,
									Object [][] menuItems
									){
		JMenu theMenu = new JMenu(name);
		
		for (Object [] item : menuItems){
	    	JMenuItem menuItem = new JMenuItem(item[0].toString());
	    	if (item.length == 2 && item[1] != null && !item[1].toString().isEmpty()){
	    		menuItem.setText(item[1].toString());
	    	}

	    	if (item.length == 3 && item[2] != null){
	    		menuItem.setAccelerator(KeyStroke.getKeyStroke((Integer) item[2], Event.META_MASK));;
	    	}
	    	
	    	menuItem.addActionListener(controller);
	    	theMenu.add(menuItem);
		}
		return theMenu;
	}
}
