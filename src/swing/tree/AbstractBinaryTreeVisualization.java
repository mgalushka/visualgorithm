/*
 * AbstractBinaryTreeVisualization.java v1.00 16/06/08
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
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import controller.IBinaryTreeController;
import model.tree.IBinaryNode;
import model.tree.RedBlackNode;
import swing.tree.GraphicNode.GraphicNodeColor;
import swing.tree.GraphicNode.GraphicNodeSize;

/**
 * This class defines the binary tree visualization. It is composed by graphic
 * nodes that represent a binary tree. These graphic nodes can be moved on the
 * visualization and always return to there first place. Moreover, the color of
 * the selected node is not the same as the others. This class is designed for
 * inheritance in order to create specific binary tree visualization thanks to
 * the design pattern template method.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 */
abstract class AbstractBinaryTreeVisualization extends JPanel {

    private static final int ROOT_NODE_Y_POSITION_SHIFT = 10;

    private static final int INITIAL_HEIGHT_BETWEEN_NODES = 35;

    private static final int INITIAL_WIDTH_BETWEEN_BROTHER_NODES = 20;

    private static final int INITIAL_WIDTH_BETWEEN_DIFFERENT_NODES = 15;

    private List<GraphicNode> graphicNodes;

    private int indexOfSelectedNode;

    private int xPositionOfSelectedNode;

    private int yPositionOfSelectedNode;
    
    private GraphicNodeColor colorOfSelectedNode;

    /**
     * The y shift of the root node on the visualization.
     */
    protected int yPositionOfRootNode;

    /**
     * The height between two parent nodes.
     */
    protected int heightBetweenNodes;

    /**
     * The width between two nodes that are brothers.
     */
    protected int widthBetweenBrotherNodes;

    /**
     * The width between two nodes that are not brothers.
     */
    protected int widthBetweenDifferentNodes;

    /**
     * The size of the graphic nodes.
     */
    protected GraphicNodeSize sizeOfNodes;

    /**
     * The binary tree controller.
     */
    protected IBinaryTreeController binaryTreeController;

    /**
     * Builds the binary tree visualization. The binary tree visualization is
     * composed by graphic nodes that represent a binary tree.
     * 
     * @param c the binary tree controller
     */
    AbstractBinaryTreeVisualization(IBinaryTreeController c) {
        super();

        binaryTreeController = c;
        graphicNodes = new ArrayList<GraphicNode>();
        sizeOfNodes = GraphicNodeSize.ONE;
        yPositionOfRootNode = ROOT_NODE_Y_POSITION_SHIFT + sizeOfNodes.getSizeAsInt() / 2;
        heightBetweenNodes = INITIAL_HEIGHT_BETWEEN_NODES;
        widthBetweenBrotherNodes = INITIAL_WIDTH_BETWEEN_BROTHER_NODES;
        widthBetweenDifferentNodes = INITIAL_WIDTH_BETWEEN_DIFFERENT_NODES;

        setBackground(Color.WHITE);
        addListeners();
    }

    private void addListeners() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    indexOfSelectedNode = findIndexOfSelectedNode(event.getX(),
                            event.getY());
                    
                    if (indexOfSelectedNode != -1) {
                        GraphicNode selectedNode = graphicNodes.get(indexOfSelectedNode);
                        
                        xPositionOfSelectedNode = selectedNode.getXPosition();
                        yPositionOfSelectedNode = selectedNode.getYPosition();
                        colorOfSelectedNode = selectedNode.getNodeColor();

                        changeGraphicNodeColor(indexOfSelectedNode,
                                GraphicNodeColor.GREEN);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                if (indexOfSelectedNode != -1) {
                    changeGraphicNodeColor(indexOfSelectedNode,
                            colorOfSelectedNode);

                    if (indexOfSelectedNode > 0) {
                        moveGraphicNode(indexOfSelectedNode,
                                xPositionOfSelectedNode, yPositionOfSelectedNode);
                        xPositionOfSelectedNode = -1;
                        yPositionOfSelectedNode = -1;
                    }
                    indexOfSelectedNode = -1;
                    colorOfSelectedNode = null;
                }
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent event) {
                if ((event.getModifiersEx() &
                        InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
                    if (indexOfSelectedNode > 0) {
                        moveGraphicNode(indexOfSelectedNode,
                                event.getX(), event.getY());
                    }
                }
            }
        });
    }

    /**
     * Finds the index of the graphic node that is at the position x and y. If
     * there is no such node then -1 is returned.
     *
     * @param x a x position on the tree visualization
     * @param y a y position on the tree visualization
     * @return the index of the graphic node at the position x and y or else -1
     */
    protected int findIndexOfSelectedNode(int x, int y) {
        int index = -1;

        for (int i = 0; i < graphicNodes.size(); i++) {
            GraphicNode currentNode = graphicNodes.get(i);
            
            if (currentNode != null) {
                int currentNodeSize = currentNode.getNodeSize().getSizeAsInt();
                int currentNodeXPosition = currentNode.getXPosition();
                int currentNodeYPosition = currentNode.getYPosition();

                if ((x < currentNodeXPosition + currentNodeSize / 2) &&
                        (x > currentNodeXPosition - currentNodeSize / 2) &&
                        (y < currentNodeYPosition + currentNodeSize / 2) &&
                        (y > currentNodeYPosition - currentNodeSize / 2)) {
                    index = i;
                }
            }
        }
        
        return index;
    }

    /**
     * Finds the index of the parent graphic node of the node indicated with
     * {@code index}. If {@code index} is out of bounds or there is no node at
     * this index, then -1 is returned.
     *
     * @param index the index of the current node
     * @return the index of the parent graphic node or else -1
     */
    protected int findIndexOfParentNode(int index) {
        if ((index >= 0) && (index < graphicNodes.size()) &&
                (graphicNodes.get(index) != null)) {
            if (index != 0) {
                if (index % 2 == 0) {
                    return (index - 2) / 2;
                } else {
                    return (index - 1) / 2;
                }
            }
        }

        return -1;
    }

    /**
     * Finds the index of the lesser graphic node from the node indicated with
     * {@code index}. If {@code index} is out of bounds or there is no node at
     * this index, then -1 is returned.
     *
     * @param index the index of the current node
     * @return the index of the lesser graphic node or else -1
     */
    protected int findIndexOfMinNode(int index) {
        int currentIndex = index;
        int minNodeIndex = -1;

        while ((currentIndex >= 0) && (currentIndex < graphicNodes.size()) &&
                (graphicNodes.get(currentIndex) != null)) {
            minNodeIndex = currentIndex;
            currentIndex = 2 * currentIndex + 1;
        }

        return minNodeIndex;
    }

    /**
     * Finds the index of the greater graphic node from the node indicated with
     * {@code index}. If {@code index} is out of bounds or there is no node at
     * this index, then -1 is returned.
     *
     * @param index the index of the current node
     * @return the index of the greater graphic node or else -1
     */
    protected int findIndexOfMaxNode(int index) {
        int currentIndex = index;
        int maxNodeIndex = -1;

        while ((currentIndex >= 0) && (currentIndex < graphicNodes.size()) &&
                (graphicNodes.get(currentIndex) != null)) {
            maxNodeIndex = currentIndex;
            currentIndex = 2 * currentIndex + 2;
        }
        
        return maxNodeIndex;
    }

    /**
     * Finds the key of the graphic node indicated with {@code index}. If
     * {@code index} is out of bounds or there is no node at this index, then -1
     * is returned.
     *
     * @param index the index of the current node
     * @return the key of the graphic node or else -1
     */
    protected int findNodeKey(int index) {
        if ((index >= 0) && (index < graphicNodes.size()) &&
                (graphicNodes.get(index) != null)) {
            return graphicNodes.get(index).getNodeKey();
        }

        return -1;
    }

    /**
     * Computes the height of the graphic node indicated with {@code index}. If
     * {@code index} is out of bounds or there is no node at this index, then -1
     * is returned.
     *
     * @param index the index of the current node
     * @return the height of the graphic node or else -1
     */
    protected int computeGraphicNodeHeight(int index) {
        if ((index < 0) || (index >= graphicNodes.size()) ||
                (graphicNodes.get(index) == null)) {
            return -1;
        } else {
            return Math.max(computeGraphicNodeHeight(2 * index + 1),
                    computeGraphicNodeHeight(2 * index + 2)) + 1;
        }
    }

    private void changeGraphicNodeColor(int index, GraphicNodeColor color) {
        GraphicNode node = graphicNodes.get(index);
        
        node.changeNodeColor(color);
        repaint(node.getXPosition() - sizeOfNodes.getSizeAsInt() / 2 - 1,
                node.getYPosition()  - sizeOfNodes.getSizeAsInt() / 2 - 1,
                sizeOfNodes.getSizeAsInt() + 2, sizeOfNodes.getSizeAsInt() + 2);
    }

    private void moveGraphicNode(int index, int x, int y) {
        int nodeSize = sizeOfNodes.getSizeAsInt();
        int indexOfParentNode = findIndexOfParentNode(index);
        int indexOfMinNode = findIndexOfMinNode(index);
        int indexOfMaxNode = findIndexOfMaxNode(index);
        GraphicNode movedNode = graphicNodes.get(index);
        GraphicNode parentNode = graphicNodes.get(indexOfParentNode);
        GraphicNode minNode = graphicNodes.get(indexOfMinNode);
        GraphicNode maxNode = graphicNodes.get(indexOfMaxNode);
        int movedNodeHeight = computeGraphicNodeHeight(index);
        int xPositionOfMinNode = minNode.getXPosition();
        int xPositionOfMaxNode = maxNode.getXPosition();
        int xPositionOfMovedNode = movedNode.getXPosition();
        int xPositionOfParentNode = parentNode.getXPosition();
        int yPositionOfMovedNode = movedNode.getYPosition();
        int yPositionOfParentNode = parentNode.getYPosition();
        int yPositionOfMaxNode = yPositionOfMovedNode + movedNodeHeight * heightBetweenNodes;
        int xMin = (xPositionOfMinNode < xPositionOfParentNode ?
            xPositionOfMinNode : xPositionOfParentNode) - nodeSize / 2 - 1;
        int xMax = (xPositionOfMaxNode > xPositionOfParentNode ?
            xPositionOfMaxNode : xPositionOfParentNode) + nodeSize / 2 + 1;
        int yMin = (yPositionOfMovedNode < yPositionOfParentNode ?
            yPositionOfMovedNode : yPositionOfParentNode) - nodeSize / 2 - 1;
        int yMax = (yPositionOfMaxNode > yPositionOfParentNode ?
            yPositionOfMaxNode : yPositionOfParentNode) + nodeSize / 2 + 1;

        repaint(xMin, yMin, xMax - xMin, yMax - yMin);
        
        movedNode.changeNodePosition(x, y);
        moveGraphicSubNodes(index, x - xPositionOfMovedNode, y - yPositionOfMovedNode);
        xPositionOfMinNode = minNode.getXPosition();
        xPositionOfMaxNode = maxNode.getXPosition();
        xPositionOfMovedNode = movedNode.getXPosition();
        yPositionOfMovedNode = movedNode.getYPosition();
        yPositionOfMaxNode = yPositionOfMovedNode + movedNodeHeight * heightBetweenNodes;
        xMin = (xPositionOfMinNode < xPositionOfParentNode ?
            xPositionOfMinNode : xPositionOfParentNode) - nodeSize / 2 - 1;
        xMax = (xPositionOfMaxNode > xPositionOfParentNode ?
            xPositionOfMaxNode : xPositionOfParentNode) + nodeSize / 2 + 1;
        yMin = (yPositionOfMovedNode < yPositionOfParentNode ?
            yPositionOfMovedNode : yPositionOfParentNode) - nodeSize / 2 - 1;
        yMax = (yPositionOfMaxNode > yPositionOfParentNode ?
            yPositionOfMaxNode : yPositionOfParentNode) + nodeSize / 2 + 1;

        repaint(xMin, yMin, xMax - xMin, yMax - yMin);
    }

    private void moveGraphicSubNodes(int index, int shiftX, int shiftY) {
        if (2 * index + 1 < graphicNodes.size()) {
            GraphicNode leftNode = graphicNodes.get(2 * index + 1);

            if (leftNode != null) {
                moveGraphicSubNodes(2 * index + 1, shiftX, shiftY);
                leftNode.changeNodePosition(leftNode.getXPosition() + shiftX,
                        leftNode .getYPosition() + shiftY);
            }
        }
        if (2 * index + 2 < graphicNodes.size()) {
            GraphicNode rightNode = graphicNodes.get(2 * index + 2);

            if (rightNode != null) {
                moveGraphicSubNodes(2 * index + 2, shiftX, shiftY);
                rightNode.changeNodePosition(rightNode.getXPosition() + shiftX,
                        rightNode.getYPosition() + shiftY);
            }
        }
    }

    private void paintEdges(Graphics graphics) {
        int index = 0;

        while (2 * index + 1 < graphicNodes.size()) {
            GraphicNode fatherNode = graphicNodes.get(index);

            if (fatherNode != null) {
                GraphicNode leftNode = graphicNodes.get(2 * index + 1);

                if (leftNode != null) {
                    graphics.drawLine(fatherNode.getXPosition(), fatherNode.getYPosition(),
                            leftNode.getXPosition(), leftNode.getYPosition());
                }
                if ((2 * index + 2) < graphicNodes.size()) {
                    GraphicNode rightNode = graphicNodes.get(2 * index + 2);

                    if (rightNode != null) {
                        graphics.drawLine(fatherNode.getXPosition(), fatherNode.getYPosition(),
                                rightNode.getXPosition(), rightNode.getYPosition());
                    }
                }
            }
            
            index++;
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        paintEdges(graphics);
        for (GraphicNode node : graphicNodes) {
            if (node != null) {
                node.paintNode(graphics);
            }
        }
        paintExtraComponent(graphics);
    }

    /**
     * Paints extra components on the visualization in the case where there is
     * other nodes to paint for example.
     *
     * @param graphics the graphics to paint an extra component
     */
    protected abstract void paintExtraComponent(Graphics graphics);

    private void updateNodesPosition() {
        int nbNodes = graphicNodes.size();
        
        if (nbNodes > 0) {
            int treeHeight = 0;
            int nodeSize = sizeOfNodes.getSizeAsInt();
            int nbOfNodesOnTheWidth = 1;
            int widthBetweenCurrentNodeChildren = 0;
            int indexOfLastNodeOnTheWidth = 1;
            int indexOfCurrentNode = 0;
            int xPositionOfCurrentNode = getWidth() / 2;
            int yPositionOfCurrentNode;

            if (nbNodes > 1) {
                treeHeight = (int) Math.round(Math.sqrt((nbNodes + 1) / 2));
            }
            widthBetweenCurrentNodeChildren = computeWidthBetweenRootChildren(treeHeight);

            for (int level = 0; level <= treeHeight; level++) {
                while (indexOfCurrentNode < indexOfLastNodeOnTheWidth) {
                    GraphicNode currentNode = graphicNodes.get(indexOfCurrentNode);
                    
                    if (currentNode != null) {
                        yPositionOfCurrentNode = yPositionOfRootNode + level * heightBetweenNodes;
                        currentNode.changeNodePosition(xPositionOfCurrentNode, yPositionOfCurrentNode);
                    }

                    if (level < treeHeight) {
                        if ((level > 0) && (indexOfCurrentNode < indexOfLastNodeOnTheWidth - 1)) {
                            xPositionOfCurrentNode += widthBetweenCurrentNodeChildren;
                        }
                    } else {
                        if ((indexOfCurrentNode % 2) == 0) {
                            xPositionOfCurrentNode += widthBetweenDifferentNodes + nodeSize;
                        } else {
                            xPositionOfCurrentNode += widthBetweenBrotherNodes + nodeSize;
                        }
                    }
                    
                    indexOfCurrentNode++;
                }
                
                xPositionOfCurrentNode -= (nbOfNodesOnTheWidth - 1) * widthBetweenCurrentNodeChildren;
                if (level < treeHeight - 1) {
                    if (level > 0) {
                        widthBetweenCurrentNodeChildren = widthBetweenCurrentNodeChildren / 2;
                    }
                    xPositionOfCurrentNode -= widthBetweenCurrentNodeChildren / 2;
                } else {
                    xPositionOfCurrentNode -= widthBetweenBrotherNodes / 2 + nodeSize / 2;
                }
                nbOfNodesOnTheWidth *= 2;
                indexOfLastNodeOnTheWidth += nbOfNodesOnTheWidth;
            }
        }
    }

    private int computeWidthBetweenRootChildren(int height) {
        if ((height == 0) || (height == 1)) {
            return 0;
        } else if (height == 2) {
            return widthBetweenBrotherNodes + widthBetweenDifferentNodes +
                    2 * sizeOfNodes.getSizeAsInt();
        } else {
            return 2 * computeWidthBetweenRootChildren(height - 1);
        }
    }

    /**
     * Returns true if the tree visualization is empty, that is to say if there
     * is no nodes in the visualization. In the other case, this method returns
     * false.
     *
     * @return true if the tree visualization is empty, or else false
     */
    boolean isTreeVisualizationEmpty() {
        return graphicNodes.size() == 0;
    }

    /**
     * Updates the binary tree visualization with the last version of the binary
     * tree as heap.
     *
     * @param heapRepresentation the last version of the tree as heap
     */
    <N extends IBinaryNode> void updateTreeVisualization(List<N> heapRepresentation) {
        if (heapRepresentation.size() > 0) {
            graphicNodes.clear();

            for (IBinaryNode node : heapRepresentation) {
                if (node != null) {
                    GraphicNodeColor color;
                    
                    if (node instanceof RedBlackNode) {
                        if (((RedBlackNode) node).isRed()) {
                            color = GraphicNodeColor.RED;
                        } else {
                            color = GraphicNodeColor.BLACK;
                        }
                    } else {
                        color = GraphicNodeColor.YELLOW;
                    }
                    
                    graphicNodes.add(new GraphicNode(node.getKey(), 0, 0,
                            sizeOfNodes, color));
                } else {
                    graphicNodes.add(null);
                }
            }

            updateNodesPosition();
            repaint();
        }
    }
}
