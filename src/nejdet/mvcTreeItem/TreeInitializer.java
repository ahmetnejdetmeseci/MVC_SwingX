package nejdet.mvcTreeItem;

//import javafx.application.Platform;

public class TreeInitializer {

	public static void initializeNewTree(IMapraysTreeYaratilisDinleyici dinleyici) {
		
		MapraysTree treeYoneticisi = new MapraysTree();
		dinleyici.treeYaratildi(treeYoneticisi);
		
	}
	
}
