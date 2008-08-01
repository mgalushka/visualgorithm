/*
 * TreeVisualization.java v1.00 16/06/08
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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import controller.BinaryTreeTabController;
import model.tree.IBinaryNode;
import swing.tree.GraphicNode.GraphicNodeColor;

/**
 * Abstract class containing the common methods of all tree visualizations.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
abstract class TreeVisualization extends JPanel {

    private static final long serialVersionUID = 1L;

    private BinaryTreeTabController controller;

    protected List<GraphicNode> graphicNodes;

    protected int sizeOfNodes;

    protected int heightBetweenNodes;

    protected int widthBetweenBrotherNodes;

    protected int widthBetweenNodes;

    protected int yPositionRootNode;

    private int indexOfSelectedNode;

    private int xPositionOfSelectedNode;

    private int yPositionOfSelectedNode;

    private GraphicNodeColor colorOfSelectedNode;

    private boolean deleteMode;
    
    protected boolean justCalculate;

    /**
     * Builds the tree visualization.
     * 
     * @param c the controller
     * @param width the width of the panel
     * @param height the height of the panel
     */
    TreeVisualization(BinaryTreeTabController c, int width, int height) {
        controller = c;
        graphicNodes = new ArrayList<GraphicNode>();
        sizeOfNodes = 30;
        heightBetweenNodes = 35;
        widthBetweenBrotherNodes = 20;
        widthBetweenNodes = 15;
        yPositionRootNode = 10 + sizeOfNodes / 2;
        justCalculate = false;
        deleteMode = false;

        setSize(width, height);
        setBackground(Color.WHITE);
        addListeners();
    }

    private void addListeners() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (deleteMode) {
                    indexOfSelectedNode = indexOfSelectedNode(e.getX(), e
                            .getY());
                    if (indexOfSelectedNode > -1) {
                        GraphicNode node = graphicNodes
                                .get(indexOfSelectedNode);
                        controller.deleteNode(node.getNodeKey());
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
                deleteMode = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!deleteMode) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        int mouseX = e.getX();
                        int mouseY = e.getY();
                        indexOfSelectedNode = indexOfSelectedNode(mouseX,
                            mouseY);
                        if (indexOfSelectedNode != -1) {
                            GraphicNode node = getGraphicNode(indexOfSelectedNode);
                            xPositionOfSelectedNode = node.getXPosition();
                            yPositionOfSelectedNode = node.getYPosition();
                            colorOfSelectedNode = node.getNodeColor();
                            changeGraphicNodeColor(GraphicNodeColor.BLUE);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!deleteMode) {
                    if (indexOfSelectedNode != -1) {
                        changeGraphicNodeColor(colorOfSelectedNode);
                        if (indexOfSelectedNode > 0) {
                            moveGraphicNode(xPositionOfSelectedNode,
                                yPositionOfSelectedNode);
                            xPositionOfSelectedNode = -1;
                            yPositionOfSelectedNode = -1;
                        }
                        indexOfSelectedNode = -1;
                        colorOfSelectedNode = null;
                    }
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
                    if (!deleteMode) {
                        if (indexOfSelectedNode > 0) {
                            moveGraphicNode(e.getX(), e.getY());
                        }
                    }
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        int widthSize = 0, heightSize = 0;
        int length = graphicNodes.size();
        if (length > 0) {
            int height = length == 1 ? 0 : (int) Math.round(Math
                    .sqrt((length + 1) / 2));
            int nbNodes = (int) Math.pow(2, height);
            int nbWidthBetweenBrotherNodes = nbNodes / 2;
            int nbWidthBetweenNodes = nbNodes / 2 - 1;
            widthSize = nbNodes * sizeOfNodes + nbWidthBetweenBrotherNodes
                    * widthBetweenBrotherNodes + nbWidthBetweenNodes
                    * widthBetweenNodes + 40;
            heightSize = height * heightBetweenNodes + yPositionRootNode
                    + sizeOfNodes / 2 + 10;
        } else {
            widthSize = 0;
            heightSize = 0;
        }
        return new Dimension(widthSize, heightSize);
    }

    private List<GraphicNode> getGraphicNodes() {
        return graphicNodes;
    }

    private GraphicNode getGraphicNode(int index) {
        return graphicNodes.get(index);
    }

    private int getGraphicNodeHeight(int index) {
        if ((index >= graphicNodes.size()) || (getGraphicNode(index) == null)) {
            return -1;
        } else {
            return Math.max(getGraphicNodeHeight(2 * index + 1),
                getGraphicNodeHeight(2 * index + 2)) + 1;
        }
    }

    private int indexOfSelectedNode(int x, int y) {
        int index = -1;
        for (int i = 0; i < graphicNodes.size(); i++) {
            GraphicNode node = graphicNodes.get(i);
            if (node != null) {
                if ((x < node.getXPosition() + node.getNodeSize() / 2)
                        && (x > node.getXPosition() - node.getNodeSize() / 2)
                        && (y < node.getYPosition() + node.getNodeSize() / 2)
                        && (y > node.getYPosition() - node.getNodeSize() / 2)) {
                    index = i;
                }
            }
        }
        return index;
    }

    private int indexOfParentNode(int i) {
        if (i == 0) {
            return 0;
        } else {
            if (i % 2 == 0) {
                return (i - 2) / 2;
            } else {
                return (i - 1) / 2;
            }
        }
    }

    private int indexOfMinNode(int index) {
        int i = index, j = 0;
        while ((i < graphicNodes.size()) && (graphicNodes.get(i) != null)) {
            j = i;
            i = 2 * i + 1;
        }
        return j;
    }

    private int indexOfMaxNode(int index) {
        int i = index, j = 0;
        while ((i < graphicNodes.size()) && (graphicNodes.get(i) != null)) {
            j = i;
            i = 2 * i + 2;
        }
        return j;
    }

    /**
     * Launches the deleteMode.
     */
    void launchDeleteMode() {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        deleteMode = true;
    }

    /**
     * Replaces the graphic nodes by the new ones.
     * 
     * @param treeVisualisation the tree visualization containing the new
     *            graphic nodes
     */
    void copyGraphicNodes(TreeVisualization treeVisualisation) {
        graphicNodes = treeVisualisation.getGraphicNodes();
        for (GraphicNode node : graphicNodes) {
            if (node != null) {
                node.changeNodeSize(sizeOfNodes);
            }
        }
    }

    /**
     * Changes the size of the graphic tree. Size factor must be between 0 and 3
     * included.
     * 
     * @param sizeFactor the size factor
     */
    void changeSize(int sizeFactor) {
        justCalculate = true;
        sizeOfNodes = 30 + sizeFactor * 15;
        heightBetweenNodes = 35 + sizeFactor * 15;
        widthBetweenBrotherNodes = 20 + sizeFactor * 15;
        widthBetweenNodes = 15 + sizeFactor * 15;
        yPositionRootNode = 10 + sizeOfNodes / 2;
        for (GraphicNode node : graphicNodes) {
            if (node != null) {
                node.changeNodeSize(sizeOfNodes);
            }
        }
        Rectangle visibleArea = getVisibleRect();
        Dimension visualizationArea = getPreferredSize();
        if (visualizationArea.width <= visibleArea.width) {
            setSize(visibleArea.width, visibleArea.height);
        } else {
            setSize(visualizationArea);
        }
        updatePositions();
    }

    private void changeGraphicNodeColor(GraphicNodeColor color) {
        justCalculate = true;
        GraphicNode node = getGraphicNode(indexOfSelectedNode);
        node.changeNodeColor(color);
        repaint(node.getXPosition() - sizeOfNodes / 2 - 1, node.getYPosition()
                - sizeOfNodes / 2 - 1, sizeOfNodes + 2, sizeOfNodes + 2);
    }

    private void moveGraphicNode(int x, int y) {
        justCalculate = true;
        GraphicNode movedNode = getGraphicNode(indexOfSelectedNode);
        GraphicNode parentNode = getGraphicNode(indexOfParentNode(indexOfSelectedNode));
        GraphicNode minNode = getGraphicNode(indexOfMinNode(indexOfSelectedNode));
        GraphicNode maxNode = getGraphicNode(indexOfMaxNode(indexOfSelectedNode));
        int height = getGraphicNodeHeight(indexOfSelectedNode);
        int xMinNode = minNode.getXPosition();
        int xMaxNode = maxNode.getXPosition();
        int xMovedNode = movedNode.getXPosition();
        int xParentNode = parentNode.getXPosition();
        int yMovedNode = movedNode.getYPosition();
        int yParentNode = parentNode.getYPosition();
        int yMaxNode = yMovedNode + height * heightBetweenNodes;
        int xMin = (xMinNode < xParentNode ? xMinNode : xParentNode)
                - sizeOfNodes / 2 - 1;
        int xMax = (xMaxNode > xParentNode ? xMaxNode : xParentNode)
                + sizeOfNodes / 2 + 1;
        int yMin = (yMovedNode < yParentNode ? yMovedNode : yParentNode)
                - sizeOfNodes / 2 - 1;
        int yMax = (yMaxNode > yParentNode ? yMaxNode : yParentNode)
                + sizeOfNodes / 2 + 1;

        repaint(xMin, yMin, xMax - xMin, yMax - yMin);
        movedNode.changeNodePosition(x, y);
        moveGraphicSubNodes(indexOfSelectedNode, x - xMovedNode, y - yMovedNode);
        xMinNode = minNode.getXPosition();
        xMaxNode = maxNode.getXPosition();
        xMovedNode = movedNode.getXPosition();
        yMovedNode = movedNode.getYPosition();
        yMaxNode = yMovedNode + height * heightBetweenNodes;
        xMin = (xMinNode < xParentNode ? xMinNode : xParentNode) - sizeOfNodes
                / 2 - 1;
        xMax = (xMaxNode > xParentNode ? xMaxNode : xParentNode) + sizeOfNodes
                / 2 + 1;
        yMin = (yMovedNode < yParentNode ? yMovedNode : yParentNode)
                - sizeOfNodes / 2 - 1;
        yMax = (yMaxNode > yParentNode ? yMaxNode : yParentNode) + sizeOfNodes
                / 2 + 1;
        repaint(xMin, yMin, xMax - xMin, yMax - yMin);
    }

    private void moveGraphicSubNodes(int nodeIndex, int shiftX, int shiftY) {
        if (2 * nodeIndex + 1 < graphicNodes.size()) {
            GraphicNode left = getGraphicNode(2 * nodeIndex + 1);
            if (left != null) {
                moveGraphicSubNodes(2 * nodeIndex + 1, shiftX, shiftY);
                left.changeNodePosition(left.getXPosition() + shiftX, left
                        .getYPosition()
                        + shiftY);
            }
        }
        if (2 * nodeIndex + 2 < graphicNodes.size()) {
            GraphicNode right = getGraphicNode(2 * nodeIndex + 2);
            if (right != null) {
                moveGraphicSubNodes(2 * nodeIndex + 2, shiftX, shiftY);
                right.changeNodePosition(right.getXPosition() + shiftX, right
                        .getYPosition()
                        + shiftY);
            }
        }
    }

    private void drawEdges(Graphics g) {
        int i = 0;
        while ((2 * i + 1) < graphicNodes.size()) {
            GraphicNode father = graphicNodes.get(i);
            if (father != null) {
                GraphicNode left = graphicNodes.get(2 * i + 1);
                if (left != null) {
                    g.drawLine(father.getXPosition(), father.getYPosition(),
                        left.getXPosition(), left.getYPosition());
                }
                if ((2 * i + 2) < graphicNodes.size()) {
                    GraphicNode right = graphicNodes.get(2 * i + 2);
                    if (right != null) {
                        g.drawLine(father.getXPosition(),
                            father.getYPosition(), right.getXPosition(), right
                                    .getYPosition());
                    }
                }
            }
            i++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!justCalculate) {
            updatePositions();
        } else {
            justCalculate = false;
        }
        drawEdges(g);
        for (GraphicNode node : graphicNodes) {
            if (node != null) {
                node.paint(g);
            }
        }
    }

    private void updatePositions() {
        int length = graphicNodes.size();
        if (length > 0) {
            int height = length == 1 ? 0 : (int) Math.round(Math
                    .sqrt((length + 1) / 2));
            int width = 1;
            int indexStop = 1;
            int index = 0;
            int y, x = getWidth() / 2;
            int positionDifference = calculateNodePositionDifference(height);

            for (int i = 0; i <= height; i++) {
                while (index < indexStop) {
                    GraphicNode node = graphicNodes.get(index);
                    if (node != null) {
                        y = yPositionRootNode + i * heightBetweenNodes;
                        node.changeNodePosition(x, y);
                    }
                    if (i < height) {
                        if ((i > 0) && (index < indexStop - 1)) {
                            x += positionDifference;
                        }
                    } else {
                        if ((index % 2) == 0) {
                            x += widthBetweenNodes + sizeOfNodes;
                        } else {
                            x += widthBetweenBrotherNodes + sizeOfNodes;
                        }
                    }
                    index++;
                }
                x -= (width - 1) * positionDifference;
                if (i < height - 1) {
                    if (i > 0) {
                        positionDifference = positionDifference / 2;
                    }
                    x -= positionDifference / 2;
                } else {
                    x -= widthBetweenBrotherNodes / 2 + sizeOfNodes / 2;
                }
                width *= 2;
                indexStop += width;
            }
        }
    }

    /**
     * Calculates the position of the nodes.
     * 
     * @param array the array
     */
    abstract <N extends IBinaryNode<N>> void calculatePositions(List<N> array);

    protected int calculateNodePositionDifference(int height) {
        if ((height == 0) || (height == 1)) {
            return 0;
        } else if (height == 2) {
            return widthBetweenBrotherNodes + widthBetweenNodes + 2
                    * sizeOfNodes;
        } else {
            return 2 * calculateNodePositionDifference(height - 1);
        }
    }
}
