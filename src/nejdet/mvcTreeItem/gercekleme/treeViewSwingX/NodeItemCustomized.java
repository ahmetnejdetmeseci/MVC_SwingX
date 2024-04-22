package nejdet.mvcTreeItem.gercekleme.treeViewSwingX;

import javax.swing.tree.DefaultMutableTreeNode;

import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;

public class NodeItemCustomized extends DefaultMutableTreeNode{

	private MapraysTreeItemData data;
	private int index = -1;
	
	public NodeItemCustomized(MapraysTreeItemData data) {
		super(data.getVisibleText());
		this.data = data;
	}
	
	public MapraysTreeItemData getData() {
		return this.data;
	}
	
	public int getIndexForUpdate() {
		return index;
	}
	
	public void setIndexForUpdate(int index) {
		this.index = index;
	}
	
}
