package nejdet.mvcMenu;

import java.awt.Component;

import nejdet.mvcMenu.gercekleme.MapraysMenuView;
import nejdet.mvcMenu.gercekleme.MapraysMenuController;
import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;
import nejdet.mvcMenu.gercekleme.MapraysMenuModel;

public class MapraysMenu implements IMapraysMenu{

	//extends Application is removed from the class
	//override start is removed as well from the class.
	
	private MapraysMenuModel model;
	private MapraysMenuView view;
	private MapraysMenuController controller;
	
	public MapraysMenu() {
		model = new MapraysMenuModel();
		view = new MapraysMenuView(model);
		controller = new MapraysMenuController(view, model);
	}
	
	
	public Component getMenuComponent() {
		return view.getMenuComponent();
	}
	
	@Override
	public void addItem(MapraysMenuItemData treeItem, String parentId) {
		model.addItem(treeItem, parentId);
	}

	@Override
	public void removeItem(String itemId) {
		model.removeItem(itemId);
	}


	@Override
	public void updateItem(MapraysMenuItemData oldData, MapraysMenuItemData newData) {
		model.updateItem(oldData, newData);
		
	}
	

}

