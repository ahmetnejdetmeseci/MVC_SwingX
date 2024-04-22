package nejdet.mvcTreeItem;

import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;

public interface IMapraysTree {
	public void addItem(MapraysTreeItemData treeItem, String parentId);
	public void removeItem(String parentId);
	public void updateItem(MapraysTreeItemData oldData, MapraysTreeItemData newData);
}
