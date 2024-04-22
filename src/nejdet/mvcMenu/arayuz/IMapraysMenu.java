package nejdet.mvcMenu.arayuz;

import java.awt.Component;

//import koray.mvc.arayuz.IMapraysTreeViewListener;

public interface IMapraysMenu {
	public void addListener(IMapraysMenuListener listener);
	public void removeListener(IMapraysMenuListener listener);
	public Component getMenuComponent();
}
