package nejdet.mvcTreeItem.arayuz;

import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;

public class UpdateNotification {
	private MapraysTreeItemData newData;
	private MapraysTreeItemData oldData;
	
	public UpdateNotification(MapraysTreeItemData oldData, MapraysTreeItemData newData) {
		super();
		this.newData = newData;
		this.oldData = oldData;
	}

	public MapraysTreeItemData getNewData() {
		return newData;
	}
	
	public MapraysTreeItemData getOldData() {
		return oldData;
	}
}
