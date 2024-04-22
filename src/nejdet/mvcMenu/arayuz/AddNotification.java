package nejdet.mvcMenu.arayuz;

import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;

public class AddNotification {
	private String parentId;
	private MapraysMenuItemData data;
	
	public AddNotification(String parentId, MapraysMenuItemData data) {
		super();
		this.parentId = parentId;
		this.data = data;
	}

	public String getParentId() {
		return parentId;
	}

	public MapraysMenuItemData getData() {
		return data;
	}
}
