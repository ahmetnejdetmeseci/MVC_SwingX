package nejdet.mvcTreeItem;

import java.awt.Component;


import nejdet.mvcTreeItem.gercekleme.MapraysJXTreeView;
import nejdet.mvcTreeItem.gercekleme.MapraysTreeController;
import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;
import nejdet.mvcTreeItem.gercekleme.MapraysTreeModel;

public class MapraysTree implements IMapraysTree{

	//extends Application is removed from the class
	//override start is removed as well from the class.
	
	private MapraysTreeModel model;
	private MapraysJXTreeView view;
	private MapraysTreeController controller;
	
	public MapraysTree() {
		model = new MapraysTreeModel();
		view = new MapraysJXTreeView(model);
		controller = new MapraysTreeController(view, model);
	}
	
	
	public Component getTreeComponent() {
		return view.getTreeViewComponent();
	}
	
	@Override
	public void addItem(MapraysTreeItemData treeItem, String parentId) {
		model.addItem(treeItem, parentId);
	}

	@Override
	public void removeItem(String parentId) {
		model.removeItem(parentId);
	}


	@Override
	public void updateItem(MapraysTreeItemData oldData, MapraysTreeItemData newData) {
		model.updateItem(oldData, newData);
		
	}

}

