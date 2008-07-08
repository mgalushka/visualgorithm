/*
 * RedBlackTreeFile.java v1.00 02/07/08
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

package io;

import model.tree.RedBlackNode;
import model.tree.RedBlackTree;
import model.tree.RedBlackNode.RedBlackNodeColor;

/**
 * Loading and saving red black tree file.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 02/07/08
 * @see TreeFile
 */
class RedBlackTreeFile extends TreeFile<RedBlackNode,
        RedBlackTree> {

    private static final String REGEX_COLOR = "(red|black)";

    private static int COLOR = 4;

    @Override
    protected void initREGEX(int currentNodeNumber,
            int nextNodeNumber) {
        super.initREGEX(currentNodeNumber, nextNodeNumber);
        String lineEnd = REGEX_BLANK + REGEX_COLOR;
        REGEX_2_CHILD += lineEnd;
        REGEX_LEFT_CHILD += lineEnd;
        REGEX_RIGHT_CHILD += lineEnd;
        REGEX_NO_CHILD += lineEnd;
    }

    @Override
    protected void setLeftNode(RedBlackNode node,
            int childNodeNumber) {
        RedBlackNodeColor color =
            (nodeVector.get(childNodeNumber)[COLOR]
                .equals("black")) ? RedBlackNodeColor.BLACK
                : RedBlackNodeColor.RED;
        node.setLeft(new RedBlackNode(Integer.parseInt(nodeVector
                .get(childNodeNumber)[KEY]), color));
    }

    @Override
    protected void setRightNode(RedBlackNode node,
            int childNodeNumber) {
        RedBlackNodeColor color =
            (nodeVector.get(childNodeNumber)[COLOR]
                .equals("black")) ? RedBlackNodeColor.BLACK
                : RedBlackNodeColor.RED;
        node.setRight(new RedBlackNode(Integer.parseInt(nodeVector
                .get(childNodeNumber)[KEY]), color));
    }

    @Override
    protected RedBlackTree createEmptyBinaryTree() {
        return new RedBlackTree();
    }
    
    @Override
    protected RedBlackTree createBinaryTree(int key) {
        return new RedBlackTree(key);
    }

    @Override
    protected String getNode(RedBlackNode node, int currentNodeNumber,
            String rightNodeNumber, String leftNodeNumber) {
        return super.getNode(node, currentNodeNumber, rightNodeNumber,
            leftNodeNumber)
                + SPACE + node.getColor().toString().toLowerCase();
    }
}
