/*
 * TreeVisualization.java v1.00 16/06/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import model.tree.IBinaryNode;
import model.tree.RedBlackNode;

import swing.tree.GraphicNode.GraphicNodeColor;

/**
 * Visualization of all types of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class TreeVisualization extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private List<GraphicNode> graphicNodes;

    private static final int nodeSize = 30;
    
    private static final int heightBetweenNodes = 35;
    
    private static final int widthBetweenBrotherNodes = 20;
    
    private static final int widthBetweenNodes = 15;
    
    private static final int yPositionRootNode = 20 + nodeSize/2;
    
    private static int indexOfSelectedNode;
    
    private static int xPositionOfSelectedNode;
    
    private static int yPositionOfSelectedNode;
    
    private static GraphicNodeColor colorOfSelectedNode;
    
    /**
     * Builds the tree visualization.
     */
    public TreeVisualization() {
        graphicNodes = new ArrayList<GraphicNode>();
        indexOfSelectedNode = -1;
        
        setBackground(Color.WHITE);
        //TODO resize of the window - repaint
        setSize(new Dimension(950,400));
        addListeners();
    }
    
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    int nodeIndex = indexOfSelectedNode(
                        mouseX, mouseY);
                    if (nodeIndex != -1) {
                        GraphicNode node = getNode(nodeIndex);
                        indexOfSelectedNode = nodeIndex;
                        xPositionOfSelectedNode = 
                            node.getXPosition();
                        yPositionOfSelectedNode = 
                            node.getYPosition();
                        colorOfSelectedNode = node.getNodeColor();
                        changeGraphicNodeColor(nodeIndex,
                            GraphicNodeColor.BLUE);
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (indexOfSelectedNode != -1) {
                    changeGraphicNodeColor(
                        indexOfSelectedNode,
                        colorOfSelectedNode);
                    moveGraphicNode(indexOfSelectedNode,
                        xPositionOfSelectedNode,
                        yPositionOfSelectedNode);
                    xPositionOfSelectedNode = -1;
                    yPositionOfSelectedNode = -1;
                    indexOfSelectedNode = -1;
                    colorOfSelectedNode = null;
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if((e.getModifiersEx() &
                        InputEvent.BUTTON1_DOWN_MASK) ==
                        InputEvent.BUTTON1_DOWN_MASK) {
                    if (indexOfSelectedNode > 0) {
                        moveGraphicNode(indexOfSelectedNode,
                            e.getX(), e.getY());
                    }
                }
            }
        });
    }
    
    private int indexOfSelectedNode(int x, int y) {
        int index = -1;
        for(int i = 0 ; i < graphicNodes.size() ; i++) {
            GraphicNode node = graphicNodes.get(i);
            if (node != null) {
                if ((x < node.getXPosition()+
                        node.getNodeSize()/2) &&
                        (x > node.getXPosition()-
                        node.getNodeSize()/2) &&
                        (y < node.getYPosition()+
                        node.getNodeSize()/2) &&
                        (y > node.getYPosition()-
                        node.getNodeSize()/2)) {
                    index = i;
                }
            }
        }
        return index;
    }
        
    private void changeGraphicNodeColor(int nodeIndex,
            GraphicNodeColor color) {
        getNode(nodeIndex).changeColor(color);
        //TODO optimization repaint
        repaint();
    }

    private void moveGraphicNode(int nodeIndex, int x,
            int y) {
        GraphicNode movedNode = getNode(nodeIndex);
        int shiftX = x-movedNode.getXPosition();
        int shiftY = y-movedNode.getYPosition();
        movedNode.move(x, y);
        //TODO speed of the shift
        moveGraphicSubNodes(nodeIndex, shiftX, shiftY);
        //TODO optimization repaint
        repaint();
    }

    private void moveGraphicSubNodes(int nodeIndex,
            int shiftX, int shiftY) {
        if (2*nodeIndex+1 < graphicNodes.size()) {
            GraphicNode left = getNode(2*nodeIndex+1);
            if (left != null) {
                moveGraphicSubNodes(2*nodeIndex+1, shiftX, shiftY);
                left.move(left.getXPosition()+shiftX,
                    left.getYPosition()+shiftY);
            }
        }
        if (2*nodeIndex+2 < graphicNodes.size()) {
            GraphicNode right = getNode(2*nodeIndex+2);
            if (right != null) {
                moveGraphicSubNodes(2*nodeIndex+2, shiftX, shiftY);
                right.move(right.getXPosition()+shiftX,
                    right.getYPosition()+shiftY);
            }
        }
    }
    
    private void drawEdges(Graphics g) {
        int i = 0;
        while ((2*i + 1) < graphicNodes.size()) {
            GraphicNode father = graphicNodes.get(i);
            if (father != null) {
                GraphicNode left = graphicNodes.get(2*i + 1);
                if (left != null) {
                    g.drawLine(father.getXPosition(),
                        father.getYPosition(),
                        left.getXPosition(),
                        left.getYPosition());
                }
                if ((2*i + 2) < graphicNodes.size()) {
                    GraphicNode right = graphicNodes.get(2*i + 2);
                    if (right != null) {
                        g.drawLine(father.getXPosition(),
                            father.getYPosition(),
                            right.getXPosition(),
                            right.getYPosition());
                    }
                }
            }
            i++;
        }
    }
    
    /**
     * Returns the wanted graphic node thanks to index.
     * 
     * @param index the index of the graphic node
     * @return the wanted graphic node
     */
    public GraphicNode getNode(int index) {
        return graphicNodes.get(index);
    }
    
    @Override
    public Dimension getPreferredSize() {
        //TODO resize of the window - repaint
        return new Dimension(950,400);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEdges(g);
        for(GraphicNode node : graphicNodes) {
            if (node != null) {
                node.paint(g); 
            }
        }
    }
    
    /**
     * Calculates the position of the nodes.
     *  
     * @param array the array
     */
    public void calculatePositions(List<IBinaryNode<?>> array) {
        graphicNodes.clear();
        int length = array.size();
        int height = length == 1 ? 0 : (int)Math.round(Math.sqrt((length+1)/2));
        int width = 1;
        int indexStop = 1;
        int index = 0;
        int key, y, x = getWidth()/2;
        GraphicNodeColor color;
        int positionDifference = calculateNodePositionDifference(height);
        
        for (int i = 0 ; i <= height ; i++) {
            while (index < indexStop) {
                IBinaryNode<?> node = array.get(index);
                if (node != null) {
                    key = node.getKey();
                    y = yPositionRootNode+i*heightBetweenNodes;
                    if (node instanceof RedBlackNode) {
                        if (((RedBlackNode)node).isRed()) {
                            color = GraphicNodeColor.RED;
                        } else {
                            color = GraphicNodeColor.BLACK;
                        }
                    } else {
                        color = GraphicNodeColor.YELLOW;
                    }
                    graphicNodes.add(new GraphicNode(key, x, y, nodeSize, color));
                } else {
                    graphicNodes.add(null);
                }
                if (i < height) {
                    if ((i > 0) && (index < indexStop-1)) {
                        x += positionDifference;
                    }
                } else {
                    if ((index % 2) == 0) {
                        x += widthBetweenNodes+nodeSize;
                    } else {
                        x += widthBetweenBrotherNodes+nodeSize;
                    }
                }
                index++;
            }
            x -= (width-1)*positionDifference;
            if (i < height-1) {
                if (i > 0) {
                    positionDifference = positionDifference/2;
                }
                x -= positionDifference/2;
            } else {
                x -= widthBetweenBrotherNodes/2+nodeSize/2;
            }
            width *= 2;
            indexStop += width;
        }
        //TODO optimization repaint
        repaint();
    }
    
    private int calculateNodePositionDifference(int height) {
        if ((height == 0) || (height == 1)) {
            return 0;
        } else if (height == 2) {
            return widthBetweenBrotherNodes+widthBetweenNodes+2*nodeSize;
        } else {
            return 2*calculateNodePositionDifference(height-1);
        }
    }
}
