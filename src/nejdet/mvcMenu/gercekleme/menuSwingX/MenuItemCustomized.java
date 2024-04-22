package nejdet.mvcMenu.gercekleme.menuSwingX;

import javax.swing.*;

import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuItemCustomized {
    private String name;
    private Color color;
    private List<MenuItemCustomized> children;
    private MenuItemCustomized parent;

    private MapraysMenuItemData data;
    
    public MenuItemCustomized(MapraysMenuItemData data) {
		this.data = data;
    	
        this.name = name;
        this.color = Color.BLUE;  // Default color
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public String getName() {
    	return data.getVisibleText();
    }
    

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<MenuItemCustomized> getChildren() {
        return children;
    }

    public void addChild(MenuItemCustomized child) {
        child.setParent(this);
        children.add(child);
    }
    
    public void removeChild(MenuItemCustomized childItem) {
        children.remove(childItem);
    }

    public MenuItemCustomized getParent() {
        return parent;
    }

    public void setParent(MenuItemCustomized parent) {
        this.parent = parent;
    }
    
    public MapraysMenuItemData getMenuData() {
    	return this.data;
    }
    
}
