package nejdet.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import nejdet.mvcTreeItem.IMapraysTreeYaratilisDinleyici;
import nejdet.mvcFeatureButtons.FeatureButton;
import nejdet.mvcFeatureButtons.FeatureButtonInitializer;
import nejdet.mvcFeatureButtons.arayuz.IFeatureButtonYaratilisDinleyici;
import nejdet.mvcMenu.IMepRaysMenuYaratilisDinleyici;
import nejdet.mvcMenu.MapraysMenu;
import nejdet.mvcTreeItem.MapraysTree;
import nejdet.mvcMenu.MenuInitializer;
import nejdet.mvcTreeItem.TreeInitializer;
import nejdet.mvcMenu.gercekleme.MapraysMenuController;
import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;
import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;

public class SwingMain {
	public static void main(String[] args) {

		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set width and height
        int width = 800;
        int height = 600;
        frame.setSize(new Dimension(width, height));

        frame.getContentPane().setLayout(new BorderLayout());

        // Create panel for WEST area (FeatureButton and MapraysTree)
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		FeatureButtonInitializer.initializeNewMenu(new IFeatureButtonYaratilisDinleyici() {
			
			@Override
			public void featureButtonYaratildi(FeatureButton featureButon) {
				Component buttonComponent = featureButon.getComponent();
				westPanel.add(buttonComponent);
				
			}
		});
		
		
		MenuInitializer.initializeNewMenu(new IMepRaysMenuYaratilisDinleyici() {
		
			@Override
			public void menuYaratildi(MapraysMenu menuYoneticisi) {
				
				Component menuComponent = menuYoneticisi.getMenuComponent();
				frame.getContentPane().add(menuComponent, BorderLayout.CENTER);
				Runnable runAction = new Runnable() {

					@Override
					public void run() {
						System.out.println("buton basildi");						
					}
				};
				//layer 1 - home
				MapraysMenuItemData data2 = new MapraysMenuItemData("item1", "Genel Islemler", new ImageIcon("C:\\warmUps\\IntroductionToJavaFX\\NejdetSwingxUI\\src\\nejdet\\mvcMenu\\gercekleme\\menuSwingX\\make_it_bigger.png"), null);
		    	MapraysMenuItemData data3 = new MapraysMenuItemData("item2", "Altlik Haritalar", null, null);
		    	MapraysMenuItemData data4 = new MapraysMenuItemData("item3", "Nesne Islemleri", null, null);
		    	MapraysMenuItemData dataa = new MapraysMenuItemData("item3", "Analiz Islemleri", null, null);
		    	MapraysMenuItemData dataaa = new MapraysMenuItemData("item3", "Donusum Islemleri", null, null);
		    	MapraysMenuItemData dataaaa	 = new MapraysMenuItemData("item3", "Disa Aktar/Ice Al", null, null);
		    	
		    	MapraysMenuItemData data5 = new MapraysMenuItemData("subitem11", "Subitem 1-1", null, null);
		    	MapraysMenuItemData data6 = new MapraysMenuItemData("subitem111", "Subitem 1-1-1", null, null);
		    	MapraysMenuItemData data7 = new MapraysMenuItemData("subitem112", "Subitem 1-1-2", null, null);
		    	MapraysMenuItemData data8 = new MapraysMenuItemData("subitem1111", "Subitem 1-1-1-1", null, null);
		    	MapraysMenuItemData data9 = new MapraysMenuItemData("subitem12", "Subitem 1-2", null, null);
		    	MapraysMenuItemData data10 = new MapraysMenuItemData("subitem21", "Subitem 2-1", null, null);
		    	MapraysMenuItemData data11 = new MapraysMenuItemData("subitem22", "Subitem 2-2", null, null);
		    	MapraysMenuItemData data12 = new MapraysMenuItemData("dede", "dedevis", null, null);
		    	
		    	menuYoneticisi.addItem(data2, null);
		    	menuYoneticisi.addItem(data3, null);
		    	menuYoneticisi.addItem(data4, null);
		    	menuYoneticisi.addItem(dataa, null);
		    	menuYoneticisi.addItem(dataaa, null);
		    	menuYoneticisi.addItem(dataaaa, null);
		    	
		    	menuYoneticisi.addItem(data5, "item1");
		    	menuYoneticisi.addItem(data6, "subitem11");
		    	menuYoneticisi.addItem(data7, "subitem11");
		    	menuYoneticisi.addItem(data8, "subitem111");
		    	menuYoneticisi.addItem(data9, "item2");
		    	menuYoneticisi.addItem(data10, "item1");
		    	menuYoneticisi.addItem(data11, "item2");
		    	
//		    	menuYoneticisi.removeItem("item3");
		    	menuYoneticisi.updateItem(data3, data4);
		    	menuYoneticisi.addItem(data12, "item3");
				frame.setVisible(true);
			}
		});
		
		TreeInitializer.initializeNewTree(new IMapraysTreeYaratilisDinleyici() {
			
			@Override
			public void treeYaratildi(MapraysTree treeYoneticisi) {
				Component treeComponent = treeYoneticisi.getTreeComponent();
				System.out.println("Null Check : " + treeComponent);
				westPanel.add(treeComponent);
				
				Runnable runAction = new Runnable() {

					@Override
					public void run() {
						System.out.println("buton basildi");
					}
				};
				
				MapraysTreeItemData treeItem0 = new MapraysTreeItemData("aliId", "aliVisible", null, runAction);
				MapraysTreeItemData treeItem1 = new MapraysTreeItemData("sakirId", "sakirVisible", null, runAction);
				MapraysTreeItemData treeItem2 = new MapraysTreeItemData("lütfüId", "lütfüVisible", null, runAction);
				MapraysTreeItemData treeItem3 = new MapraysTreeItemData("yugosId", "yugosVisible", null, runAction);
				
				treeYoneticisi.addItem(treeItem0, null);
				treeYoneticisi.addItem(treeItem1, null);
				treeYoneticisi.addItem(treeItem2, null);
				treeYoneticisi.addItem(treeItem3, null);
				
				MapraysTreeItemData treeItem01 = new MapraysTreeItemData("evrenId", "evrenVisible", null, runAction);
				MapraysTreeItemData treeItem02 = new MapraysTreeItemData("korayId", "korayVisible", null, runAction);
				MapraysTreeItemData treeItem03 = new MapraysTreeItemData("ahmetId", "ahmetVisible", null, runAction);
				MapraysTreeItemData treeItem04 = new MapraysTreeItemData("nejdetId", "nejdetVisible", null, runAction);
				
				treeYoneticisi.addItem(treeItem01, "sakirId");
				treeYoneticisi.addItem(treeItem02, "sakirId");
				treeYoneticisi.addItem(treeItem03, "korayId");
				treeYoneticisi.addItem(treeItem04, "ahmetId");
				
				MapraysTreeItemData treeItem_update = new MapraysTreeItemData("kamranId", "kamranVisible", null, runAction);
				
				treeYoneticisi.updateItem(treeItem0, treeItem_update);
				frame.getContentPane().add(westPanel, BorderLayout.WEST);
				frame.setVisible(true);
			}
		});
		
	}
}
