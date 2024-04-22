package nejdet.mvcMenu.gercekleme;

import nejdet.mvcMenu.gercekleme.MapraysMenuModel;
import nejdet.mvcMenu.arayuz.IMapraysMenu;
import nejdet.mvcMenu.arayuz.IMapraysMenuListener;

public class MapraysMenuController implements IMapraysMenuListener{
	private MapraysMenuView view;

	private MapraysMenuModel model;
	
	private boolean visible = false;
	
	public MapraysMenuController(MapraysMenuView view, MapraysMenuModel model) {
		
		this.view = view;
		
		this.model = model;
		
		view.addListener(this);
	}

	@Override
	public void menuItemClicked(String uniqueId) {
		// TODO business logic
		// OR: close menu
		
	}

//	@Override
//	public void toggleMenu() {
//		
//		this.visible = !this.visible;
//		
//		this.view.getMenuComponent().setVisible(this.visible);
//	}
	
	
	
}
