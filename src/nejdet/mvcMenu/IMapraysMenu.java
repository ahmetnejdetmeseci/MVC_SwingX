package nejdet.mvcMenu;

import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;

public interface IMapraysMenu {
	public void addItem(MapraysMenuItemData menuItem, String parentId);
	public void removeItem(String parentId);
	public void updateItem(MapraysMenuItemData oldData, MapraysMenuItemData newData);
	
	
//	public void get
}
