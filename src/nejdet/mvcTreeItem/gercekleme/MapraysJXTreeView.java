package nejdet.mvcTreeItem.gercekleme;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTree;

import nejdet.mvcTreeItem.arayuz.IMapraysViewListener;
import nejdet.mvcTreeItem.arayuz.IMapraysTreeView;
import nejdet.mvcTreeItem.gercekleme.treeViewSwingX.JTreeUI_Sample_Swingx;
import nejdet.mvcTreeItem.gercekleme.treeViewSwingX.NodeItemCustomized;

public class MapraysJXTreeView implements Observer, IMapraysTreeView{

	private JTreeUI_Sample_Swingx treeView = new JTreeUI_Sample_Swingx();
	
	private ArrayList<IMapraysViewListener> listeners = new ArrayList();
	
	private JButton btnLayerTree = new JButton("Open Tree!");
	
	private Dimension btnDim = new Dimension(400, 30);
	
	public MapraysJXTreeView(MapraysTreeModel model) {
		model.addObserver(this);
		
		//..
		
		//..
	}
	
	@Override
	public void addListener(IMapraysViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IMapraysViewListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Component getTreeViewComponent() {
		JPanel panel = new JPanel();
		//if weight not OK change dimension
		panel.setSize(new Dimension(200, 200));
		BorderLayout root = new BorderLayout();
		panel.setLayout(root);
	
		btnLayerTree.setPreferredSize(btnDim);
		JXTree tv = treeView.getTreeViewComponent();
		JButton closeTreeBtn = new JButton("Close");
		closeTreeBtn.setPreferredSize(btnDim);
		// Tree panel operations:
		JPanel treePanel = new JPanel();
		BorderLayout treeLayout = new BorderLayout();
		treePanel.setLayout(treeLayout);
		JScrollPane scrollPaneTree = new JScrollPane(tv);
		treePanel.add(scrollPaneTree, BorderLayout.CENTER);
		treePanel.add(closeTreeBtn, BorderLayout.NORTH); // Close button on top
	
		panel.add(btnLayerTree, BorderLayout.NORTH);
	
		btnLayerTree.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        for (Component c: panel.getComponents()) {
		            if(c.equals(btnLayerTree)) {
		                panel.remove(btnLayerTree);
		                System.out.println("Removed from the view");
		                panel.add(treePanel, BorderLayout.CENTER);
		                panel.repaint();
		                panel.revalidate();
		            }
		        }
		    }
		});
	
		closeTreeBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        for (Component c: panel.getComponents()) {
		            if(c.equals(treePanel)) {
		                panel.remove(treePanel);
		                System.out.println("Removed from the view 222");
		                panel.add(btnLayerTree, BorderLayout.NORTH);
		                panel.repaint();
		                panel.revalidate();
		            }
		        }
		    }
		});
	
		return panel;
	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof nejdet.mvcTreeItem.arayuz.AddNotification) {
			
			System.out.println("Add Notification");
			
			nejdet.mvcTreeItem.arayuz.AddNotification an = (nejdet.mvcTreeItem.arayuz.AddNotification) arg;
			
			MapraysTreeItemData data = an.getData();
			String parentId = an.getParentId();

			NodeItemCustomized node = new NodeItemCustomized(data);
			
//			treeView.getTreeViewComponent().addTreeSelectionListener(new TreeSelectionListener() {
//				
//				@Override
//				public void valueChanged(TreeSelectionEvent e) {
//					TreePath selectedPath = e.getNewLeadSelectionPath();
//	                if (selectedPath != null) {
//	                    Object selectedNode = selectedPath.getLastPathComponent();
//	                    if (selectedNode instanceof DefaultMutableTreeNode) {
//	                        NodeItemCustomized clickedNode = (NodeItemCustomized) selectedNode;
//	                        System.out.println("Clicked Node: " + clickedNode.getData().getVisibleText());
//	                        data.getRunAction().run();
//	                    }
//	                }
//				}
//			});

			
			treeView.addTreeItem(node, parentId);
			
		} else if (arg instanceof nejdet.mvcTreeItem.arayuz.UpdateNotification) {
			nejdet.mvcTreeItem.arayuz.UpdateNotification un = (nejdet.mvcTreeItem.arayuz.UpdateNotification) arg;
			
			MapraysTreeItemData oldData = un.getOldData();
			MapraysTreeItemData newData = un.getNewData();
					
			treeView.updateTreeNode(oldData, newData);
			
			
		} else if (arg instanceof nejdet.mvcTreeItem.arayuz.DeleteNotification) {
			nejdet.mvcTreeItem.arayuz.DeleteNotification dn = (nejdet.mvcTreeItem.arayuz.DeleteNotification) arg;
		
			String parentId = dn.getParentId();
			String itemIdToDelete = dn.getData().getUniqueId();
			
			treeView.removeTreeNode(parentId, itemIdToDelete);
			//..
//			Platform.runLater(new Runnable() {
//				
//				@Override
//				public void run() {
//					
//				}
//			});
		}
		
	}

}
