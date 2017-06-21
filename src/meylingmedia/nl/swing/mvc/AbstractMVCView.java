package meylingmedia.nl.swing.mvc;

public abstract class AbstractMVCView {
	public AbstractMVCController viewController;
	public AbstractMVCModel viewModel;
	
	private AbstractMVCView(){
		
	}

	AbstractMVCView (AbstractMVCController contr, AbstractMVCModel model){
		viewController = contr;
		viewModel = model;
	}
	
}
