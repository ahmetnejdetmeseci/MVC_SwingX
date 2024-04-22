package nejdet.mvcTreeItem.gercekleme.treeViewSwingX;

import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;

import nejdet.mvcTreeItem.gercekleme.MapraysTreeItemData;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JTreeUI_Sample_Swingx {
    private JXTree tree;
    
    MapraysTreeItemData rootData = new MapraysTreeItemData("root", "root", null, null);
    
    private NodeItemCustomized root = new NodeItemCustomized(rootData);

    private DefaultTreeModel treeModel = new DefaultTreeModel(root);
    
    public JTreeUI_Sample_Swingx() {
    	
        this.tree = new JXTree(treeModel);
        this.tree.setRootVisible(false);
        
        this.tree.setDragEnabled(true);
        this.tree.setDropMode(DropMode.ON_OR_INSERT);
        
//        tree.setEditable(true);
//        tree.setCellEditor(new DefaultTreeCellEditor(tree, new DefaultTreeCellRenderer()));
        
        this.tree.setCellRenderer(new CustomTreeCellRenderer());
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                if (node.getChildCount() > 0) {
                    animateNode(node, tree);
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                // Do nothing on collapse
            }
        });
        addNodeClickListener(tree);
        
    }

    public void addTreeItem(NodeItemCustomized treeItem, String parentId) {
    	if (parentId == null) {
//        	System.out.println("Null Check : " + parentId);
            root.add(treeItem);
            treeModel.reload();
            
        } else {
        	NodeItemCustomized parentItem = findTreeNode(root, parentId);

            if (parentItem != null) {
                parentItem.add(treeItem);
            } else {
            	
                System.out.println("Parent with ID " + parentId + " not found.");
            }
        }
        
        // Notify the tree model about the changes
        DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        treeModel.reload();
    }
    
    
    //Recursive arama!
    private NodeItemCustomized findTreeNode(NodeItemCustomized root, String uniqueId) {
        // Base case: If the current root node is null, return null
        if (root == null) {
            return null;
        }

        // Check if the current root node's uniqueId matches the target uniqueId
        if (root.getData().getUniqueId().equals(uniqueId)) {
            return root;
        }

        // Iterate through the children of the current root node and recursively search
        for (int i = 0; i < root.getChildCount(); i++) {
            NodeItemCustomized child = (NodeItemCustomized) root.getChildAt(i);

            
            // Recursively search through the child node
            NodeItemCustomized foundNode = findTreeNode(child, uniqueId);

            // If the node is found in the child subtree, return it
            if (foundNode != null) {
            	foundNode.setIndexForUpdate(i);
                return foundNode;
            }
        }

        // If the node is not found in the current subtree or its children, return null
        return null;
    }
    
    public void removeTreeNode(String parentItem, String selectedItem) {
    	NodeItemCustomized parentNode;

        if (parentItem != null) {
            // Find the parent node by user object
            parentNode = findTreeNode(root, parentItem);
        } else {
            // Remove from the root node
            parentNode = root;
        }

        if (parentNode != null) {
            // Find the selected node by user object
        	NodeItemCustomized selectedNode = findTreeNode(parentNode, selectedItem);

            if (selectedNode != null) {
                // Remove the selected node from its parent
//                DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
                treeModel.removeNodeFromParent(selectedNode);
            }
        }
    }
    
    public void updateTreeNode(MapraysTreeItemData oldData, MapraysTreeItemData newData) {
    	
    	String oldId = oldData.getUniqueId();
    	NodeItemCustomized node;
    	NodeItemCustomized parentNode;
    	if(oldId != null) {
    		node = findTreeNode(root, oldId);
    		
    		parentNode = (NodeItemCustomized) node.getParent();
    		//Hangi indexte oldugunu aldik
    		System.out.println("Parent is : " + parentNode + " and place of the child itself is : " + node.getIndexForUpdate());
    	}else {
    		node = root;
    		parentNode = null;
    	}
    	
    	if(node != null) {
    		
    		NodeItemCustomized newNodeItem = new NodeItemCustomized(newData);
    		
    		parentNode.insert(newNodeItem, node.getIndexForUpdate());
    		
    		parentNode.remove(node);
    		
    		treeModel.reload();
    	}
    }
    
    
    public JXTree getTreeViewComponent() {
    	return this.tree;
    }

    
    class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        private static final int CUSTOM_NODE_HEIGHT = 40; // Set your custom height here

        private Color backgroundColor = Color.WHITE; // Set your desired background color
        private Color textColor = Color.BLACK; // Set your desired text color

        private Icon openIcon = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                // Draw the eye shape (a circle)
                g.setColor(Color.BLACK);
                g.drawOval(x, y, getIconWidth(), getIconHeight());

                // Draw the pupil (a smaller filled circle)
                int pupilSize = 6; // Size of the pupil
                int pupilX = x + (getIconWidth() - pupilSize) / 2;
                int pupilY = y + (getIconHeight() - pupilSize) / 2;
                g.fillOval(pupilX, pupilY, pupilSize, pupilSize);
            }

            @Override
            public int getIconWidth() {
                return 20;
            }

            @Override
            public int getIconHeight() {
                return 12;
            }
        };

        private Icon closedIcon = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.BLACK);

                // Draw the closed eye shape (a curved line)
                int eyeWidth = getIconWidth();
                int eyeHeight = getIconHeight();
                int centerX = x + eyeWidth / 2;
                int centerY = y + eyeHeight / 2;
                int radius = Math.min(eyeWidth, eyeHeight) / 2;

                // Draw a curved line to represent the closed eye
                int startAngle = 180; // Start angle of the curve (looking towards the bottom)
                int arcAngle = 180; // Angle of the curve
                g2d.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, startAngle, arcAngle);

                // Add eyelashes
                int numEyelashes = 9; // Number of eyelashes
                int eyelashLength = 5; // Length of each eyelash
                for (int i = 0; i < numEyelashes; i++) {
                    int angle = 180 + i * 20; // Angle of each eyelash (starting from the bottom)
                    double radians = Math.toRadians(angle);
                    int startX = (int) (centerX + radius * Math.cos(radians));
                    int startY = (int) (centerY - radius * Math.sin(radians));
                    int endX = (int) (startX + eyelashLength * Math.cos(radians));
                    int endY = (int) (startY - eyelashLength * Math.sin(radians));
                    g2d.drawLine(startX, startY, endX, endY);
                }

                g2d.dispose();
            }

            @Override
            public int getIconWidth() {
                return 20;
            }

            @Override
            public int getIconHeight() {
                return 12;
            }
        };

        public CustomTreeCellRenderer() {
            // Add mouse listener to the label to toggle between icons
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toggleIcon();
                }
            });
        }
        
        private void toggleIcon() {
            Icon currentIcon = getIcon();
            setIcon(currentIcon == openIcon ? closedIcon : openIcon);
        }
        
        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.height = CUSTOM_NODE_HEIGHT;
            return size;
        }
        
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                      boolean expanded, boolean leaf, int row,
                                                      boolean hasFocus) {
            JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            
            // Set background color
            label.setBackground(backgroundColor);
            
            // Set initial icon
            label.setIcon(closedIcon);
            label.setIconTextGap(5);
            
            // Set text color
            label.setForeground(textColor);
            
            return label;
        }
    }
    
    class ToggleLabel extends JLabel {
        private Icon openIcon;
        private Icon closedIcon;
        private boolean isOpen = false;

        public ToggleLabel(Icon openIcon, Icon closedIcon) {
            super(closedIcon); // Start with the closed icon
            this.openIcon = openIcon;
            this.closedIcon = closedIcon;
            
            // Add mouse listener to toggle between icons
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toggleIcon();
                }
            });
        }

        private void toggleIcon() {
            isOpen = !isOpen;
            setIcon(isOpen ? openIcon : closedIcon);
        }
    }
    
    
    private static void addNodeClickListener(JXTree tree) {
    	tree.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    TreePath path = tree.getPathForLocation(evt.getX(), evt.getY());
                    if (path != null) {
                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (selectedNode.getChildCount() > 0) {
                            animateNode(selectedNode, tree);
                        }
                    }
                }
            }
        });
    }
    private static void animateNode(DefaultMutableTreeNode node, JXTree tree) {
    	Timer timer = new Timer(10, new ActionListener() {
            int currentHeight = 0;
            int targetHeight = tree.getRowBounds(tree.getRowForPath(new TreePath(node.getPath()))).height;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentHeight < targetHeight) {
                    tree.repaint();
                    currentHeight += 5; // You can adjust the speed here
                } else {
                    ((Timer) e.getSource()).stop();
                    tree.repaint();
                }
            }
        });

        timer.start();
    }
}
