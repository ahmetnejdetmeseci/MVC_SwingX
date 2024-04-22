package nejdet.mvcTreeItem.arayuz;

import java.awt.Component;

//import koray.mvc.arayuz.IMapraysTreeViewListener;
import nejdet.mvcTreeItem.arayuz.IMapraysViewListener;

public interface IMapraysTreeView {
	public void addListener(IMapraysViewListener listener);
	public void removeListener(IMapraysViewListener listener);
	public Component getTreeViewComponent();
}
