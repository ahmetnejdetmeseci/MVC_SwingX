package nejdet.mvcTreeItem.gercekleme;

import javax.swing.Icon;

public class MapraysTreeItemData {
	private String uId;
	private String visibleText;
	private Icon menuIcon;
	private Runnable runAction;
	
	public MapraysTreeItemData(String uId, String visibleText, Icon menuIcon, Runnable runAction) {
		super();
		this.uId = uId;
		this.visibleText = visibleText;
		this.menuIcon = menuIcon;
		this.runAction = runAction;
	}
	
	public String getUniqueId() {
		return uId;
	}

	public String getVisibleText() {
		return visibleText;
	}

	public Icon getMenuIcon() {
		return menuIcon;
	}

	public Runnable getRunAction() {
		return runAction;
	}

	public void setUniqueId(String uId) {
		this.uId = uId;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
	}

	public void setMenuIcon(Icon menuIcon) {
		this.menuIcon = menuIcon;
	}

	public void setRunAction(Runnable runAction) {
		this.runAction = runAction;
	}
	
	
	
}
