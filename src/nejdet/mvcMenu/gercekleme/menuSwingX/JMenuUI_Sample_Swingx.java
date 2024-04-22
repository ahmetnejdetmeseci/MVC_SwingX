package nejdet.mvcMenu.gercekleme.menuSwingX;

import org.jdesktop.swingx.JXPanel;

import nejdet.mvcMenu.gercekleme.MapraysMenuItemData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class JMenuUI_Sample_Swingx extends JXPanel{
	
	private MenuItemCustomized rootMenu;
    private MenuItemCustomized selectedMenuItem;
    MapraysMenuItemData data1 = new MapraysMenuItemData("root", "Root", null, null);
    
    public JMenuUI_Sample_Swingx(){
        
    	super();
    	
        rootMenu = new MenuItemCustomized(data1);
        
        addMouseListener(new MenuClickListener());
    }

    public JXPanel getMenu() {
    	return this;
    }
    
    public void addMenuItem(MapraysMenuItemData data, String parentId) {
    	MenuItemCustomized item = new MenuItemCustomized(data);
    	if(parentId == null) {
    		rootMenu.addChild(item);
    	}else {
    		MenuItemCustomized parentItem = findMenuItem(rootMenu, parentId);
    		if(parentItem != null) {
    			parentItem.addChild(item);
    		}else {
    			System.out.println("Parent with ID " + parentId + " not found.");
    		}
    	}
    	
    }

    public void removeMenuItem(String parentItem, String selectedItem) {
        MenuItemCustomized parentNode;

        if (parentItem != null) {
            // Find the parent node by unique ID
            parentNode = findMenuItem(rootMenu, parentItem);
        } else {
            // Remove from the root node
            parentNode = rootMenu;
        }

        if (parentNode != null) {
            // Find the selected node by unique ID
            MenuItemCustomized selectedNode = findMenuItem(parentNode, selectedItem);

            if (selectedNode != null) {
                // Remove the selected node from its parent
                parentNode.removeChild(selectedNode);
                repaint();
            }
        }
    }

    public void updateMenuItem(MapraysMenuItemData oldData, MapraysMenuItemData newData) {
        
    	String oldId = oldData.getUniqueId();
    	MenuItemCustomized item;
    	MenuItemCustomized parentItem;
    	if(oldId != null) {
    		item = findMenuItem(rootMenu, oldId);
    		
    		parentItem = (MenuItemCustomized) item.getParent();
    		//Hangi indexte oldugunu aldik
    		System.out.println("Parent is : " + parentItem + " and place of the child itself is : " + item);
    	}else {
    		item = rootMenu;
    		parentItem = null;
    	}
    	
    	if(item != null) {
    		
    		MenuItemCustomized newMenuItem = new MenuItemCustomized(newData);
    		
    		parentItem.addChild(newMenuItem);
    		
    		parentItem.removeChild(item);
    		
    	}
    }
    
    private MenuItemCustomized findMenuItem(MenuItemCustomized root, String uniqueId) {
        // Base case: If the current root node is null, return null
        if (root == null) {
            return null;
        }

        // Check if the current root node's uniqueId matches the target uniqueId
        if (root.getMenuData().getUniqueId().equals(uniqueId)) {
            return root;
        }

        // Iterate through the children of the current root node and recursively search
        for (MenuItemCustomized child : root.getChildren()) {
            // Recursively search through the child node
            MenuItemCustomized foundItem = findMenuItem(child, uniqueId);

            // If the item is found in the child subtree, return it
            if (foundItem != null) {
                return foundItem;
            }
        }
        // If the item is not found in the current subtree or its children, return null
        return null;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int innerRadius = 50;
        int outerRadius = 100;

        // Draw the annulus
        drawAnnulus(g2d, (selectedMenuItem != null) ? selectedMenuItem.getChildren() : rootMenu.getChildren(),
                width / 2, height / 2, outerRadius, innerRadius);
    }
    
    
    //with image
//    private void drawAnnulus(Graphics2D g2d, List<MenuItemCustomized> menuItems,
//            int centerX, int centerY, int outerRadius, int innerRadius) {
//	if (menuItems.size() != 0) {
//		int startAngle = 0;
//		int arcAngle = (360 - menuItems.size() * 5) / menuItems.size();
//		int imageRadius = (outerRadius + innerRadius) / 2; // Use the average of outer and inner radius for text positioning
//		
//		for (MenuItemCustomized menuItem : menuItems) {
//		    // Draw the annulus items with a slightly increased radius
//		    g2d.setColor(menuItem.getColor());
//		    g2d.fillArc(centerX - (outerRadius + 5), centerY - (outerRadius + 5),
//		           (outerRadius + 5) * 2, (outerRadius + 5) * 2, startAngle, arcAngle);
//		    
//		    // Draw the inner circle (cut-out part)
//		    g2d.setColor(getBackground());
//		    g2d.fillOval(centerX - innerRadius, centerY - innerRadius, innerRadius * 2, innerRadius * 2);
//		    
//		    startAngle += arcAngle + 5;
//		}
//		
//		startAngle = 0; // Reset start angle for drawing text
//		
//		for (MenuItemCustomized menuItem : menuItems) {
//		    // Draw text in the middle of the slice
//			if(menuItem.getMenuData().getMenuIcon() != null	)
//				drawImageInSlice(g2d, (ImageIcon) menuItem.getMenuData().getMenuIcon(), centerX, centerY, imageRadius, startAngle, arcAngle);
//		    
//		    startAngle += arcAngle + 5;
//		}
//		
//		g2d.setColor(Color.BLACK);
//		g2d.fillOval(centerX - 20, centerY - 20, 40, 40);
//		
//		g2d.setColor(Color.WHITE);
//		g2d.drawString("Back", centerX - 15, centerY + 5);
//	} else {
//		// JOptionPane.showMessageDialog(getParent(), "Reached the End");
//	}
//}
    

    private void drawImageInSlice(Graphics2D g2d, ImageIcon icon,
            int centerX, int centerY, int radius, int startAngle, int arcAngle) {
    
    	Image image = icon.getImage();
    	
	    double radians = Math.toRadians(startAngle + arcAngle / 2);
	    int imageWidth = image.getWidth(null);
	    int imageHeight = image.getHeight(null);
	    
	    // Calculate the desired width and height based on the slice angle and radius
	    double scaleFactor = Math.min((double) radius * 2 / imageWidth, (double) radius * 2 / imageHeight);
	    int scaledWidth = (int) (imageWidth * scaleFactor);
	    int scaledHeight = (int) (imageHeight * scaleFactor);
	    
	    // Calculate the position for centering the scaled image
	    int x = (int) (centerX + (radius * Math.cos(radians)) - (scaledWidth / 2));
	    int y = (int) (centerY + (radius * Math.sin(radians)) - (scaledHeight / 2));
	    
	    // Draw the scaled image
	    g2d.drawImage(image, x, y, scaledWidth, scaledHeight, null);
    }

    
    //With text
    private void drawAnnulus(Graphics2D g2d, List<MenuItemCustomized> menuItems,
	            int centerX, int centerY, int outerRadius, int innerRadius) {
		if (menuItems.size() != 0) {
			int startAngle = 0;
			int arcAngle = (360 - menuItems.size() * 5) / menuItems.size();
			int textRadius = (outerRadius + innerRadius) / 2; // Use the average of outer and inner radius for text positioning
			
			for (MenuItemCustomized menuItem : menuItems) {
			    // Draw the annulus items with a slightly increased radius
			    g2d.setColor(menuItem.getColor());
			    g2d.fillArc(centerX - (outerRadius + 5), centerY - (outerRadius + 5),
			           (outerRadius + 5) * 2, (outerRadius + 5) * 2, startAngle, arcAngle);
			    
			    // Draw the inner circle (cut-out part)
			    g2d.setColor(getBackground());
			    g2d.fillOval(centerX - innerRadius, centerY - innerRadius, innerRadius * 2, innerRadius * 2);
			    
			    startAngle += arcAngle + 5;
			}
			
			startAngle = 0; // Reset start angle for drawing text
			
			for (MenuItemCustomized menuItem : menuItems) {
			    // Draw text in the middle of the slice
			    drawTextInSlice(g2d, menuItem.getName(), centerX, centerY, textRadius, startAngle, arcAngle);
			    
			    startAngle += arcAngle + 5;
			}
			
			g2d.setColor(Color.BLACK);
			g2d.fillOval(centerX - 20, centerY - 20, 40, 40);
			
			g2d.setColor(Color.WHITE);
			g2d.drawString("Back", centerX - 15, centerY + 5);
		} else {
			// JOptionPane.showMessageDialog(getParent(), "Reached the End");
		}
	}

    private void drawTextInSlice(Graphics2D g2d, String text,
            int centerX, int centerY, int radius, int startAngle, int arcAngle) {
    	
		FontMetrics fontMetrics = g2d.getFontMetrics();
		Font boldFont = new Font(g2d.getFont().getName(), Font.BOLD, g2d.getFont().getSize()); // Create a bold font
		g2d.setFont(boldFont); // Set the graphics context to use the bold font
		
		int textWidth = fontMetrics.stringWidth(text);
		int textHeight = fontMetrics.getHeight();
		
		double radians = Math.toRadians(startAngle + arcAngle / 2);
		int x = (int) (centerX + (radius * Math.cos(radians)) - (textWidth / 2));
		int y = (int) (centerY + (radius * Math.sin(radians)) + (textHeight / 2));
		
		g2d.setColor(Color.BLACK);
		g2d.drawString(text, x, y);
	}


    private class MenuClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (selectedMenuItem != null) {
                // Check if the click is on the go back button
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int buttonSize = 40;

                if (e.getX() >= centerX - buttonSize && e.getX() <= centerX + buttonSize &&
                        e.getY() >= centerY - buttonSize && e.getY() <= centerY + buttonSize) {
                    // Go back to the parent menu
                    selectedMenuItem = selectedMenuItem.getParent();
                    repaint();
                    return;
                }
            }

            // Check which menu item is clicked
            double angle = Math.toDegrees(Math.atan2(e.getY() - getHeight() / 2, e.getX() - getWidth() / 2));
            if (angle < 0) {
                angle += 360;
            }

            findClickedMenuItem(angle, (selectedMenuItem != null) ? selectedMenuItem.getChildren() : rootMenu.getChildren());
        }

        private void findClickedMenuItem(double angle, List<MenuItemCustomized> menuItems) {
            int startAngle = 0;
            int arcAngle = (360 - menuItems.size() * 5) / menuItems.size();
            int buttonSize = 40;

            for (MenuItemCustomized menuItem : menuItems) {
                double start = startAngle;
                double end = start + arcAngle + 5;

                if (angle >= start && angle <= end) {
                    // Clicked on a menu item
                    selectedMenuItem = menuItem;
                    if (selectedMenuItem.getChildren().isEmpty()) {
                        // do not go further
                        selectedMenuItem = selectedMenuItem.getParent();
                        System.out.println("No Children");
                    } else {
                        repaint();
                    }
                    return;
                }

                if (!menuItem.getChildren().isEmpty()) {
                    findClickedMenuItem(angle, menuItem.getChildren());
                }

                startAngle += arcAngle + 5;
            }
        }
    }

}
