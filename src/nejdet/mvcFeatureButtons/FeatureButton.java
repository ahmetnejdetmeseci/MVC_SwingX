package nejdet.mvcFeatureButtons;

import java.awt.Component;

import nejdet.mvcFeatureButtons.arayuz.IFeatureButton;
import nejdet.mvcFeatureButtons.gercekleme.FeatureButtonController;
import nejdet.mvcFeatureButtons.gercekleme.FeatureButtonView;
import nejdet.mvcMenu.gercekleme.MapraysMenuController;
import nejdet.mvcMenu.gercekleme.MapraysMenuModel;
import nejdet.mvcMenu.gercekleme.MapraysMenuView;

public class FeatureButton implements IFeatureButton{

	
	private FeatureButtonController controller;
	private FeatureButtonView view;
	
	public FeatureButton() {
		
		
		controller = new FeatureButtonController();
		view = new FeatureButtonView(controller);
	}
	

	@Override
	public Component getComponent() {
		return view.getComponent();
	}

}
