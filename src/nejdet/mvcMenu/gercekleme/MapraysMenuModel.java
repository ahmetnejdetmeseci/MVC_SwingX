package nejdet.mvcMenu.gercekleme;

import java.util.Enumeration;
import java.util.Observable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class MapraysMenuModel extends Observable{
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	
	public MapraysMenuModel() {
		root = new DefaultMutableTreeNode();
		treeModel = new DefaultTreeModel(root);
	}
	
	public boolean addItem(MapraysMenuItemData data, String parentUID) {
		
		boolean found = false;
		
		if (parentUID == null) {
			root.add(new DefaultMutableTreeNode(data));
//			System.out.println("Added Data : " + data.getUniqueId());
			found = true;
		} else {
			Enumeration<TreeNode> depthFirstEnumeration = root.depthFirstEnumeration();
			while (depthFirstEnumeration.hasMoreElements()) {
				DefaultMutableTreeNode nextElement = (DefaultMutableTreeNode) depthFirstEnumeration.nextElement();
				
				MapraysMenuItemData nextData = (MapraysMenuItemData) nextElement.getUserObject();
//				System.out.println("Added Data : " + nextData.getUniqueId());
				if (nextData.getUniqueId().equals(parentUID)) {
					nextElement.add(new DefaultMutableTreeNode(data));
					found = true;
					break;
				}
			}
		}
		
		this.setChanged();
		this.notifyObservers(new nejdet.mvcMenu.arayuz.AddNotification(parentUID, data));
		
		return found;
	
	}
	
	public void getMenuTree() {
		
	}

//	public boolean removeItem(String uniqueId) {
//		
//		boolean found = false;
//		
//		Enumeration<TreeNode> depthFirstEnumeration = root.depthFirstEnumeration();
//		
//		while (depthFirstEnumeration.hasMoreElements()) {
//			DefaultMutableTreeNode nextElement = (DefaultMutableTreeNode) depthFirstEnumeration.nextElement();
//
////			System.out.println("Data is: " + nextElement + " and ");
////			System.out.println("Child Count of the elements is : " + nextElement.getChildCount());
//
//			MapraysTreeItemData nextData = (MapraysTreeItemData) nextElement.getUserObject();
//			System.out.println("Check the child count for : " + nextData.getUniqueId() + " is : " + nextElement.getChildCount());
//
//			if (nextData.getUniqueId().equals(uniqueId)) {
//				root.remove(nextElement);
//				System.out.println(found);
//				
//				found = true;
//				
//				this.setChanged();
//				this.notifyObservers(new koray.mvcTreeItem.arayuz.DeleteNotification(null, nextData));
//				
//				break;
//			} else {
//				//child gez
//				int childCount = nextElement.getChildCount();
//				if (childCount > 0) {
//					for (int i = 0; i < childCount; i++) {
//						
//						DefaultMutableTreeNode childAt = (DefaultMutableTreeNode) nextElement.getChildAt(i);
//						MapraysTreeItemData childData = (MapraysTreeItemData) childAt.getUserObject();
////						System.out.println("Check the child count for : " + nextData.getUniqueId() + " is : " + nextElement.getChildCount());
//						if (childData.getUniqueId().equals(uniqueId)) {
//							nextElement.remove(i);
//							found = true;
//							
//							this.setChanged();
//							
//							this.notifyObservers(new koray.mvcTreeItem.arayuz.DeleteNotification(nextData.getUniqueId(), childData));
//							
//							break;
//						}
//					}
//				}
//			}
//			
//			if (found) {
//				break;
//			}
//		}
//		
//		return found;
//	}
	
	public boolean removeItem(String uniqueId) {
	    boolean found = false;

	    Enumeration<TreeNode> depthFirstEnumeration = root.depthFirstEnumeration();

	    while (depthFirstEnumeration.hasMoreElements()) {
	        DefaultMutableTreeNode nextElement = (DefaultMutableTreeNode) depthFirstEnumeration.nextElement();
	        MapraysMenuItemData nextData = (MapraysMenuItemData) nextElement.getUserObject();

	        if (nextData.getUniqueId().equals(uniqueId)) {
	            removeNodeAndChildren(nextElement);
	            found = true;
	            break;
	        } else {
	            int childCount = nextElement.getChildCount();
	            if (childCount > 0) {
	                for (int i = 0; i < childCount; i++) {
	                    DefaultMutableTreeNode childAt = (DefaultMutableTreeNode) nextElement.getChildAt(i);
	                    MapraysMenuItemData childData = (MapraysMenuItemData) childAt.getUserObject();

	                    if (childData.getUniqueId().equals(uniqueId)) {
	                        removeNodeAndChildren(childAt);
	                        found = true;
	                        break;
	                    }
	                }
	            }
	        }

	        if (found) {
	            break;
	        }
	    }

	    return found;
	}

	private void removeNodeAndChildren(DefaultMutableTreeNode node) {
	    TreeNode parent = node.getParent();
	    if (parent != null) {
	        ((DefaultMutableTreeNode) parent).remove(node);
	    } else {
	        // This is the root node, clear its children
	        node.removeAllChildren();
	    }

	    this.setChanged();
	    this.notifyObservers(new nejdet.mvcMenu.arayuz.DeleteNotification(null, (MapraysMenuItemData) node.getUserObject()));
	}
	
	
	
	public boolean updateItem(MapraysMenuItemData oldData, MapraysMenuItemData newData) {
	    boolean found = false;

	    Enumeration<TreeNode> depthFirstEnumeration = root.depthFirstEnumeration();

	    while (depthFirstEnumeration.hasMoreElements()) {
	        DefaultMutableTreeNode nextElement = (DefaultMutableTreeNode) depthFirstEnumeration.nextElement();
	        MapraysMenuItemData nextData = (MapraysMenuItemData) nextElement.getUserObject();

	        if (nextData.getUniqueId().equals(oldData.getUniqueId())) {
	            // Create a new instance for updated data
	        	MapraysMenuItemData updatedData = new MapraysMenuItemData(newData.getUniqueId(), newData.getVisibleText(), newData.getMenuIcon(), newData.getRunAction());

	            // Update the node data
	            nextElement.setUserObject(updatedData);
	            found = true;
	            break;
	        } else {
	            int childCount = nextElement.getChildCount();
	            if (childCount > 0) {
	                for (int i = 0; i < childCount; i++) {
	                    DefaultMutableTreeNode childAt = (DefaultMutableTreeNode) nextElement.getChildAt(i);
	                    MapraysMenuItemData childData = (MapraysMenuItemData) childAt.getUserObject();

	                    if (childData.getUniqueId().equals(oldData.getUniqueId())) {
	                        // Create a new instance for updated data
	                    	MapraysMenuItemData updatedData = new MapraysMenuItemData(newData.getUniqueId(), newData.getVisibleText(), newData.getMenuIcon(), newData.getRunAction());

	                        // Update the node data
	                        childAt.setUserObject(updatedData);
	                        found = true;
	                        break;
	                    }
	                }
	            }
	        }

	        if (found) {
	            break;
	        }
	    }

	    if (found) {

	        this.setChanged();
	        // Notify observers about the update
	        this.notifyObservers(new nejdet.mvcMenu.arayuz.UpdateNotification(oldData, newData));
	    }

	    return found;
	}


	
}
