package meylingmedia.nl.xslt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;

public class MainC implements ActionListener, KeyListener{
	private MainV theView = null;
	private MainM theModel = null;
	private RefC  referenceApp;
	
	private JFileChooser fcdia = null;	
	
	public void show(){
		theModel = new MainM();
		theView = new MainV(this);
		theView.createScreen();
	}
	
	public void cleanUp(){
		if (referenceApp != null) {
			referenceApp.cleanUp();
		}
	}
	
	MainM getModel(){
		return theModel;
	}
	
	public void setModelFromView(){
		theModel.setFields( 
				theView.textAreaXSLT.getText(), 
				theView.textAreaSource.getText(), 
				theView.addTextXSLT.getText(), 
				theView.addTextSource.getText()
				);		
	}
	
	public void setViewFromModel(){
		theView.textAreaXSLT.setText(theModel.sourceContainer.getLastXslt().source);
		theView.textAreaSource.setText(theModel.sourceContainer.getLastXml().source);
		theView.addTextXSLT.setText(theModel.sourceContainer.getLastXslt().text);
		theView.addTextSource.setText(theModel.sourceContainer.getLastXml().text);
	}
	
	private String getSaveAsFileName(){
		if (fcdia == null){
			fcdia = new JFileChooser();
		}
		
		fcdia.setDialogTitle("Save as");
		
		int ret = fcdia.showSaveDialog(null);

		String sRet = "";
		if (ret == 0 ){
			sRet = fcdia.getSelectedFile().getAbsolutePath();
		}
		
		return sRet;
	}

	private String getLoadFileName(){
		if (fcdia == null){
			fcdia = new JFileChooser();
		}
		
		fcdia.setDialogTitle("Load");
		
		int ret = fcdia.showOpenDialog(null);

		String sRet = "";
		if (ret == 0 ){
			sRet = fcdia.getSelectedFile().getAbsolutePath();
		}
		
		return sRet;
	}	
	
		
	public void actionPerformed(ActionEvent e) {
		String fileName = "";
		
		if (e.getActionCommand().equals(theView.EXEC)){
			setModelFromView();
			theView.textAreaResult.setText(theModel.transform());
			theView.textAreaList.setText(theModel.stackTrace);
			theView.setTabToResult();		
		}
				
		else if (e.getActionCommand().equals(theView.VALID)){
			setModelFromView();
			theView.textAreaResult.setText(theModel.XSDvalidate());
			theView.textAreaList.setText(theModel.stackTrace);
			theView.setTabToResult();					
		}
		
		else if(e.getActionCommand().equals(theView.TAB1)){
			theView.setTabToSource();
		}
		else if(e.getActionCommand().equals(theView.TAB2)){
			theView.setTabToXSLT();
		}
		else if(e.getActionCommand().equals(theView.TAB3)){
			theView.setTabToResult();
		}
		else if(e.getActionCommand().equals(theView.TAB4)){
			theView.setTabToList();
		}

		
		else if (e.getActionCommand().equals(theView.SAVEAS)){
			if (!(fileName = getSaveAsFileName()).isEmpty()){
				setModelFromView();
				try{
					theModel.saveAs(fileName);	
				} catch (Exception f){
					f.printStackTrace();
				}
			}
		}

		else if (e.getActionCommand().equals(theView.SAVE)){
			if (theModel.filePath == null || theModel.filePath.isEmpty()){
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), theView.SAVEAS));
			}
			else{
				try{
					theModel.saveAs(theModel.filePath);	
				} catch (Exception f){
					f.printStackTrace();
				}				
			}

		}
		
		
		else if (e.getActionCommand().equals(theView.LOAD)){
			if (!(fileName = getLoadFileName()).isEmpty()){
					
				try{
					theModel.load(fileName);
					setViewFromModel();
					theView.setTabToSource();
				} catch (Exception f){
					f.printStackTrace();
				}
			}
		}
		
		else if (e.getActionCommand().equals(theView.PRETTY)){
			if (theView.isTabToResult()){
				theView.textAreaResult.setText(theModel.prettyPrint(theView.textAreaResult.getText()));
			}
			
			if (theView.isTabToXSLT()){
				theView.textAreaXSLT.setText(theModel.prettyPrint(theView.textAreaXSLT.getText())); 
			}
			
			if (theView.isTabToSource()){
				theView.textAreaSource.setText(theModel.prettyPrint(theView.textAreaSource.getText())); 
			}
			
		}
		else if (e.getActionCommand().equals(theView.NEW)){
			MainC newApp = new MainC();
			newApp.show();
		}
		else if (e.getActionCommand().equals(theView.REF)){
			if (referenceApp == null){
				referenceApp = new RefC();
				referenceApp.show();
			}
			else{
				referenceApp.setFocus();	
			}
		}

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}


}
