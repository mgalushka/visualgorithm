/*
 * PedagogicalTreeVisualization.java v1.00 01/08/08
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

package swing.tree;

import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import swing.tree.GraphicNode.GraphicNodeColor;
import controller.BinaryTreeController;

/**
 * Pedagogical visualization of all types of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 01/08/08
 * @see AbstractTreeVisualization
 */
public class PedagogicalTreeVisualization extends AbstractTreeVisualization {

    private static final long serialVersionUID = 1L;

    private GraphicNode newNode;

    private boolean isNewNodeSelected;

    /**
     * Builds the pedagogical tree visualization.
     * 
     * @param c the binary tree tab controller
     * @param width the width of the panel
     * @param height the height of the panel
     */
    PedagogicalTreeVisualization(BinaryTreeController c, int width,
            int height) {
        super(c, width, height);
        newNode = null;
        isNewNodeSelected = false;

        addNewNodeListeners();
    }

    private void addNewNodeListeners() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // TODO stop parent move
                    isNewNodeSelected(e.getX(), e.getY());
                    if (isNewNodeSelected) {
                        changeNewNodeColor(GraphicNodeColor.GREEN);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isNewNodeSelected) {
                    changeNewNodeColor(GraphicNodeColor.BLUE);
                    isNewNodeSelected = false;
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
                    if (isNewNodeSelected) {
                        moveNewNode(e.getX(), e.getY());
                    }
                }
            }
        });
    }

    private void isNewNodeSelected(int x, int y) {
        if (newNode != null) {
            if ((x < newNode.getXPosition() + newNode.getNodeSize() / 2)
                    && (x > newNode.getXPosition() - newNode.getNodeSize() / 2)
                    && (y < newNode.getYPosition() + newNode.getNodeSize() / 2)
                    && (y > newNode.getYPosition() - newNode.getNodeSize() / 2)) {
                isNewNodeSelected = true;
            }
        }
    }

    private void moveNewNode(int x, int y) {
        justCalculate = true;
        int xPosition = newNode.getXPosition();
        int yPosition = newNode.getYPosition();

        repaint(xPosition - sizeOfNodes / 2 - 1, yPosition - sizeOfNodes / 2
                - 1, sizeOfNodes + 2, sizeOfNodes + 2);
        newNode.changeNodePosition(x, y);
        xPosition = newNode.getXPosition();
        yPosition = newNode.getYPosition();
        repaint(xPosition - sizeOfNodes / 2 - 1, yPosition - sizeOfNodes / 2
                - 1, sizeOfNodes + 2, sizeOfNodes + 2);
    }

    private void changeNewNodeColor(GraphicNodeColor color) {
        justCalculate = true;
        newNode.changeNodeColor(color);
        repaint(newNode.getXPosition() - sizeOfNodes / 2 - 1, newNode
                .getYPosition()
                - sizeOfNodes / 2 - 1, sizeOfNodes + 2, sizeOfNodes + 2);
    }

    /**
     * Creates the newNode.
     * 
     * @param key the key of the newNode
     */
    void createNode(int key) {
        if (newNode == null) {
            justCalculate = true;
            newNode = new GraphicNode(key, 10 + sizeOfNodes / 2,
                    10 + sizeOfNodes / 2, sizeOfNodes, GraphicNodeColor.BLUE);
            repaint(9, 9, sizeOfNodes + 2, sizeOfNodes + 2);
        }
    }

    /**
     * Test if the graphic tree is empty.
     * 
     * @return true if the graphic tree is empty
     */
    boolean isEmpty() {
        return graphicNodes.size() == 0;
    }

    @Override
    void changeSize(int sizeFactor) {
        // TODO
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (newNode != null) {
            newNode.paint(g);
        }
    }
}
