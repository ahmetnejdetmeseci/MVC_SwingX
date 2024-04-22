package nejdet.mvcMenu.arayuz;

import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;


public class UpdateNotification {
	private MapraysMenuItemData newData;
	private MapraysMenuItemData oldData;
	
	public UpdateNotification(MapraysMenuItemData oldData, MapraysMenuItemData newData) {
		super();
		this.newData = newData;
		this.oldData = oldData;
	}

	public MapraysMenuItemData getNewData() {
		return newData;
	}
	
	public MapraysMenuItemData getOldData() {
		return oldData;
	}
}
