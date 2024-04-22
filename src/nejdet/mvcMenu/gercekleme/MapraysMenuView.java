package nejdet.mvcMenu.gercekleme;

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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTree;

import nejdet.mvcMenu.arayuz.IMapraysMenuListener;
import nejdet.mvcMenu.arayuz.IMapraysMenu;
import nejdet.mvcMenu.gercekleme.menuSwingX.JMenuUI_Sample_Swingx;
import nejdet.mvcMenu.gercekleme.menuSwingX.MenuItemCustomized;

public class MapraysMenuView implements Observer, IMapraysMenu{

	private JMenuUI_Sample_Swingx menu = new JMenuUI_Sample_Swingx();
	
	private ArrayList<IMapraysMenuListener> listeners = new ArrayList();
	
	private JButton open_closeMenuBtn = new JButton("Menu");
	
	private boolean isOpened = false;
	
	public MapraysMenuView(MapraysMenuModel model) {
		model.addObserver(this);
		
		//..
		
		//..
	}
	
	@Override
	public void addListener(IMapraysMenuListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IMapraysMenuListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Component getMenuComponent() {

		JPanel panel = new JPanel();
		
		panel.setSize(new Dimension(600, 600));
		
		BorderLayout root = new BorderLayout();
		
		JXPanel xpanel = menu.getMenu();
		panel.setLayout(root);

		//button panel operations:******
		
		JPanel btnPanel =new JPanel();
		FlowLayout btnLayout = new FlowLayout();
		
		btnPanel.setLayout(btnLayout);
		
		btnPanel.add(open_closeMenuBtn);
		
		//*********
		
		panel.add(btnPanel, BorderLayout.EAST);
		
		open_closeMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isOpened) {
					panel.add(xpanel, BorderLayout.CENTER);
					panel.repaint();
					panel.revalidate();
					isOpened = true;
				}else {
					for (Component c : panel.getComponents()) {
						if(c.equals(xpanel)) {
							panel.remove(xpanel);
							panel.repaint();
							panel.revalidate();
							isOpened = false;
						}
					}
				}
			}
		});
		
		
		
		return panel;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof nejdet.mvcMenu.arayuz.AddNotification) {
			
			System.out.println("Add Notification");
			
			nejdet.mvcMenu.arayuz.AddNotification an = (nejdet.mvcMenu.arayuz.AddNotification) arg;
			
			MapraysMenuItemData data = an.getData();
			String parentId = an.getParentId();

//			MenuItemCustomized node = new MenuItemCustomized(data);
			
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

			
			menu.addMenuItem(data, parentId);
			
		} else if (arg instanceof nejdet.mvcMenu.arayuz.UpdateNotification) {
			nejdet.mvcMenu.arayuz.UpdateNotification un = (nejdet.mvcMenu.arayuz.UpdateNotification) arg;
			
			MapraysMenuItemData oldData = un.getOldData();
			MapraysMenuItemData newData = un.getNewData();
					
			menu.updateMenuItem(oldData, newData);
			
			
		} else if (arg instanceof nejdet.mvcMenu.arayuz.DeleteNotification) {
			nejdet.mvcMenu.arayuz.DeleteNotification dn = (nejdet.mvcMenu.arayuz.DeleteNotification) arg;
		
			String parentId = dn.getParentId();
			String itemIdToDelete = dn.getData().getUniqueId();
			
			menu.removeMenuItem(parentId, itemIdToDelete);

		}
		
	}

}
