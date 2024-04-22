package nejdet.mvcTreeItem.gercekleme;

import nejdet.mvcTreeItem.arayuz.IMapraysViewListener;
import nejdet.mvcTreeItem.arayuz.IMapraysTreeView;

public class MapraysTreeController implements IMapraysViewListener{
	private IMapraysTreeView view;

	private MapraysTreeModel model;
	
	public MapraysTreeController(IMapraysTreeView view, MapraysTreeModel model) {
		
		this.view = view;
		
		this.model = model;
		
		view.addListener(this);
	}

	@Override
	public void treeItemClicked(String uniqueId) {
		
		
		
		// TODO business logic
		// OR: close menu
		
		
	}
}
