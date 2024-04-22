package nejdet.mvcTreeItem.arayuz;

import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;

public class AddNotification {
	private String parentId;
	private MapraysTreeItemData data;
	
	public AddNotification(String parentId, MapraysTreeItemData data) {
		super();
		this.parentId = parentId;
		this.data = data;
	}

	public String getParentId() {
		return parentId;
	}

	public MapraysTreeItemData getData() {
		return data;
	}
}
