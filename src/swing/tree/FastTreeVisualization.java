/*
 * FastTreeVisualization.java v1.00 01/08/08
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

import java.util.List;
import model.tree.IBinaryNode;
import model.tree.RedBlackNode;
import swing.tree.GraphicNode.GraphicNodeColor;
import controller.BinaryTreeTabController;

/**
 * Fast visualization of all types of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 01/08/08
 * @see TreeVisualization
 */
public class FastTreeVisualization extends TreeVisualization {

    private static final long serialVersionUID = 1L;
    
    FastTreeVisualization(BinaryTreeTabController c, int width, int height) {
        super(c, width, height);
    }
    
    @Override
    <N extends IBinaryNode<N>> void calculatePositions(List<N> array) {
        justCalculate = true;
        graphicNodes.clear();
        int length = array.size();
        if (length > 0) {
            int height = length == 1 ? 0 : (int) Math.round(Math
                    .sqrt((length + 1) / 2));
            int width = 1;
            int indexStop = 1;
            int index = 0;
            int key, y, x = getWidth() / 2;
            GraphicNodeColor color;
            int positionDifference = calculateNodePositionDifference(height);

            for (int i = 0; i <= height; i++) {
                while (index < indexStop) {
                    IBinaryNode<?> node = array.get(index);
                    if (node != null) {
                        key = node.getKey();
                        y = yPositionRootNode + i * heightBetweenNodes;
                        if (node instanceof RedBlackNode) {
                            color = ((RedBlackNode) node).isRed() ? GraphicNodeColor.RED
                                    : GraphicNodeColor.BLACK;
                        } else {
                            color = GraphicNodeColor.YELLOW;
                        }
                        graphicNodes.add(new GraphicNode(key, x, y,
                                sizeOfNodes, color));
                    } else {
                        graphicNodes.add(null);
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
            repaint();
        }
    }
}
