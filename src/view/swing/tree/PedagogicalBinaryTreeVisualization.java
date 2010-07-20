/*
 * PedagogicalBinaryTreeVisualization.java v0.10 01/08/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgo@googlegroups.com)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package view.swing.tree;

import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.swing.tree.GraphicNode.GraphicNodeColor;
import controller.IBinaryTreeController;

/**
 * This class defines the pedagogical binary tree visualization. The pedagogical
 * binary tree visualization is composed by graphic nodes that represent a
 * binary tree. This visualization has the same functionnalities than its parent
 * class with also a specific insert mode. This mode allows users to insert a
 * node in the visualization and to place it at the right place in the binary
 * tree. This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 01/08/08
 * @see AbstractBinaryTreeVisualization
 */
final class PedagogicalBinaryTreeVisualization extends AbstractBinaryTreeVisualization {

    private static final long serialVersionUID = 1L;

    private static final int NEW_INSERTED_NODE_POSITION = 10;

    private GraphicNode newInsertedNode;

    private boolean isNewInsertedNodeSelected;

    /**
     * Builds the pedagogical binary tree visualization. It is composed by
     * graphic nodes that represent a binary tree.
     * 
     * @param c the binary tree controller
     */
    PedagogicalBinaryTreeVisualization(IBinaryTreeController c) {
        super(c);
        
        newInsertedNode = null;
        isNewInsertedNodeSelected = false;

        addNewInsertedNodeListeners();
    }

    private void addNewInsertedNodeListeners() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    isNewInsertedNodeSelected = isNewInsertedNodeSelected(event.getX(),
                            event.getY());

                    if (isNewInsertedNodeSelected) {
                        changeNewInsertedNodeColor(GraphicNodeColor.GREEN);
                    }
                    // TODO Stop parent move
                }
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                if (isNewInsertedNodeSelected) {
                    changeNewInsertedNodeColor(GraphicNodeColor.BLUE);
                    isNewInsertedNodeSelected = false;
                }
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent event) {
                if ((event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) ==
                        InputEvent.BUTTON1_DOWN_MASK) {
                    if (isNewInsertedNodeSelected) {
                        moveNewInsertedNode(event.getX(), event.getY());
                    }
                }
            }
        });
    }

    private boolean isNewInsertedNodeSelected(int x, int y) {
        int newInsertedNodeSize = sizeOfNodes.getSizeAsInt();
        
        if (newInsertedNode != null) {
            if ((x < newInsertedNode.getXPosition() + newInsertedNodeSize / 2) &&
                    (x > newInsertedNode.getXPosition() - newInsertedNodeSize / 2) &&
                    (y < newInsertedNode.getYPosition() + newInsertedNodeSize / 2) &&
                    (y > newInsertedNode.getYPosition() - newInsertedNodeSize / 2)) {
                return true;
            }
        }
        return false;
    }

    private void moveNewInsertedNode(int x, int y) {
        int newInsertedNodeSize = sizeOfNodes.getSizeAsInt();
        int xPositionOfNewInsertedNode = newInsertedNode.getXPosition();
        int yPositionOfNewInsertedNode = newInsertedNode.getYPosition();

        repaint(xPositionOfNewInsertedNode - newInsertedNodeSize / 2 - 1,
                yPositionOfNewInsertedNode - newInsertedNodeSize / 2 - 1,
                newInsertedNodeSize + 2, newInsertedNodeSize + 2);

        newInsertedNode.changeNodePosition(x, y);
        xPositionOfNewInsertedNode = newInsertedNode.getXPosition();
        yPositionOfNewInsertedNode = newInsertedNode.getYPosition();

        repaint(xPositionOfNewInsertedNode - newInsertedNodeSize / 2 - 1,
                yPositionOfNewInsertedNode - newInsertedNodeSize / 2 - 1,
                newInsertedNodeSize + 2, newInsertedNodeSize + 2);
    }

    private void changeNewInsertedNodeColor(GraphicNodeColor color) {
        int newInsertedNodeSize = sizeOfNodes.getSizeAsInt();

        newInsertedNode.changeNodeColor(color);
        repaint(newInsertedNode.getXPosition() - newInsertedNodeSize / 2 - 1,
                newInsertedNode.getYPosition() - newInsertedNodeSize / 2 - 1,
                newInsertedNodeSize + 2, newInsertedNodeSize + 2);
    }

    @Override
    protected void paintExtraComponent(Graphics graphics) {
        if (newInsertedNode != null) {
            newInsertedNode.paintNode(graphics);
        }
    }

    /**
     * Creates a new node in the visualization. This node has to be placed by
     * users at the right place in the binary tree. Then, it is automatically
     * inserted in the binary tree.
     * 
     * @param key the key of the new inserted node
     */
    void createNewInsertedNode(int key) {
        int newInsertedNodeSize = sizeOfNodes.getSizeAsInt();

        if (newInsertedNode == null) {
            newInsertedNode = new GraphicNode(key,
                    NEW_INSERTED_NODE_POSITION + newInsertedNodeSize / 2,
                    NEW_INSERTED_NODE_POSITION + newInsertedNodeSize / 2,
                    sizeOfNodes, GraphicNodeColor.BLUE);
            
            repaint(NEW_INSERTED_NODE_POSITION - 1,
                    NEW_INSERTED_NODE_POSITION - 1,
                    newInsertedNodeSize + 2, newInsertedNodeSize + 2);
        }
    }
}
