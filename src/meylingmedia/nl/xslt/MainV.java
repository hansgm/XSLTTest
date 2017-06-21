package meylingmedia.nl.xslt;

import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import meylingmedia.nl.swing.mvc.*;

public class MainV extends JFrame{
	private static final long serialVersionUID = 1L;
	private MainC controller;
	public final String CLEAR   = "Clear";
	public final String LOAD    = "Load";
	public final String SAVE    = "Save";
	public final String SAVEAS  = "Save as";
	public final String QUIT    = "Quit";
	public final String EXEC    = "Exec.Transformation";
	public final String VALID   = "Validate XSD";
	public final String PURGE   = "Purge";
	public final String PRETTY  = "Pretty Print";
	public final String VERSION = "Versions";
	public final String NEW     = "New page";
	public final String LIB     = "Library";
	public final String REF     = "Reference";

	public final String TAB1    = "Source";
	public final String TAB2    = "XSLT";
	public final String TAB3    = "Result";
	public final String TAB4    = "List";	
	
	JTabbedPane tabbedPannel;
	JTextArea textAreaSource;
	JTextArea textAreaXSLT;
	JTextArea textAreaResult;
	JTextArea textAreaList;
	
	JTextField addTextSource;
	JTextField addTextXSLT;
	
	JMenuBar menuBar;
	
	Font textFont;
	
	MainV(MainC iController){
		controller = iController;
	}
	
	public void dispose() {
		controller.cleanUp();
		super.dispose();
	}
	
	void setFont(){
		textFont = new Font("Monaco", Font.PLAIN, 12);
	}
	
	void createScreen(){
		setFont();
		
		setSize(1400, 800);
		setTitle("XSLT/XSD tester");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
    	JPanel panel = new JPanel();
    	
    	panel.setLayout(null);
    	
    	menuBar = new JMenuBar();
    	
    	menuBar.add(YMESwingFactory.createMenu(
    			"File", controller, 
    			new Object [][] { 
    					{CLEAR},
    					{LOAD, null, KeyEvent.VK_L},
    					{SAVE},
    					{SAVEAS, null, KeyEvent.VK_S},
    			        {NEW},
    					{QUIT, null, KeyEvent.VK_Q}
    					} ));
  
    	menuBar.add(YMESwingFactory.createMenu(
    			"Source", controller, 
    			new Object[][] { 
    					{PURGE},
    					{VERSION},
    					{PRETTY, null, KeyEvent.VK_P},
    					} ));

       	menuBar.add(YMESwingFactory.createMenu(
    			"Tools", controller, 
    			new Object[][] { 
    					{LIB},
    					{REF, null, KeyEvent.VK_R}
    					} ));       	

       	
    	menuBar.add(YMESwingFactory.createMenu(
    			"Goto", controller, 
    			new Object[][] { 
    					{TAB1, null, KeyEvent.VK_1},
    					{TAB2, null, KeyEvent.VK_2},
    					{TAB3, null, KeyEvent.VK_3},
    					{TAB4, null, KeyEvent.VK_4}
    					} ));   
    	
    	menuBar.add(YMESwingFactory.createMenu(
    			"Execute", controller, 
    			new Object[][] { 
    					{EXEC, null, KeyEvent.VK_E},
    					{VALID, null, KeyEvent.VK_T}
    					} ));   
    	
//       	
//    	JMenuItem menuExec = new JMenuItem(EXEC);
//    	menuExec.addActionListener(controller);
//    	menuExec.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.META_MASK));
//
//    	JMenuItem menuValidate = new JMenuItem(VALID);
//    	menuValidate.addActionListener(controller);
//    	menuValidate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.META_MASK));  	
// 
//    	menuBar.add(menuExec);
//    	menuBar.add(menuValidate);
    	
    	
    	this.setJMenuBar(menuBar);
    	
    	tabbedPannel = new JTabbedPane();
    	tabbedPannel.setBounds(0, 0, this.getWidth(), this.getHeight()-40);
    	
    	JPanel panel1 = new JPanel();
    	panel1.setLayout(null);
    	tabbedPannel.add("Source", panel1);
    	
    	JPanel panel2 = new JPanel();
    	panel2.setLayout(null);
    	tabbedPannel.add("XSL Transform/XSD", panel2);

    	
    	JPanel panel3 = new JPanel();
    	panel3.setLayout(null);
    	tabbedPannel.add("Result", panel3);

    	JPanel panel4 = new JPanel();
    	panel4.setLayout(null);
    	tabbedPannel.add("List", panel4);
  	
    	
    	panel.add(tabbedPannel);
       	this.add(panel);
       	
       	panel1.setBounds(0, 0, tabbedPannel.getWidth(), tabbedPannel.getHeight()-20);
       	panel2.setBounds(0, 0, tabbedPannel.getWidth(), tabbedPannel.getHeight()-20);
       	panel3.setBounds(0, 0, tabbedPannel.getWidth(), tabbedPannel.getHeight()-20);
       	panel4.setBounds(0, 0, tabbedPannel.getWidth(), tabbedPannel.getHeight()-20);
       	
       	textAreaSource = new JTextArea();
       	JScrollPane scrollPane1 = new JScrollPane(textAreaSource); 
       	addTextSource = new JTextField();
       	addTextSource.setBounds(60, 0, 660, 25);
       	addTextSource.addKeyListener(controller);
       	
       	JLabel label1 = new JLabel("Notes");
       	label1.setBounds(2, 0, 58, 25);
       	
       	panel1.add(addTextSource);
       	panel1.add(label1);
       	
       	scrollPane1.setBounds(0, 27, panel1.getWidth()-24, panel1.getHeight()-30);
       	scrollPane1.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       	textAreaSource.setEditable(true);
       	textAreaSource.setTabSize(2);
       	textAreaSource.setFont(textFont);
       	textAreaSource.addKeyListener(controller);
       	       	
       	panel1.add(scrollPane1);
       	
       	textAreaXSLT = new JTextArea();
       	JScrollPane scrollPane2 = new JScrollPane(textAreaXSLT); 
       	scrollPane2.setBounds(0, 27, panel2.getWidth()-24, panel2.getHeight()-30);
       	scrollPane2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       	textAreaXSLT.setEditable(true);
       	textAreaXSLT.addKeyListener(controller);
       	
       	textAreaXSLT.setDragEnabled(true);
       	textAreaXSLT.setTabSize(2);
       	textAreaXSLT.setFont(textFont);
       	panel2.add(scrollPane2);
       	JLabel label2 = new JLabel("Notes");

       	addTextXSLT = new JTextField();
       	addTextXSLT.setBounds(60, 0, 660, 25);
       	addTextXSLT.addKeyListener(controller);
       
       	label2.setBounds(2, 0, 58, 25);
       	panel2.add(label2);
       	panel2.add(addTextXSLT);
       	
       	textAreaResult = new JTextArea();
       	JScrollPane scrollPane3 = new JScrollPane(textAreaResult); 
       	scrollPane3.setBounds(0, 0, panel3.getWidth()-24, panel3.getHeight()-30);
       	scrollPane3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       	textAreaResult.setEditable(false);
       	textAreaResult.setFont(textFont);

       	panel3.add(scrollPane3);
		this.setVisible(true);		
		
       	textAreaList = new JTextArea();
       	JScrollPane scrollPane4 = new JScrollPane(textAreaList); 
       	scrollPane4.setBounds(0, 0, panel4.getWidth()-24, panel4.getHeight()-30);
       	scrollPane4.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       	textAreaList.setEditable(false);
       	textAreaList.setFont(textFont);

       	panel4.add(scrollPane4);
       	
		this.setVisible(true);		
		
	}


	void setTabToSource(){
		tabbedPannel.setSelectedIndex(0);
	}
	void setTabToXSLT(){
		tabbedPannel.setSelectedIndex(1);
	}	
	void setTabToResult(){
		tabbedPannel.setSelectedIndex(2);
	}
	void setTabToList(){
		tabbedPannel.setSelectedIndex(3);
	}
	boolean isTabToSource(){
		return tabbedPannel.getSelectedIndex() == 0;
	}
	boolean isTabToXSLT(){
		return tabbedPannel.getSelectedIndex() == 1;
	}
	boolean isTabToResult(){
		return tabbedPannel.getSelectedIndex() == 2;
	}
	boolean isTabToList(){
		return tabbedPannel.getSelectedIndex() == 3;
	}

	
}
