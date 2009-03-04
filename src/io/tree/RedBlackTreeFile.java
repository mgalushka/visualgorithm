/*
 * RedBlackTreeFile.java v1.00 02/07/08
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

package io.tree;

import model.tree.IBinaryNode;
import model.tree.RedBlackNode;
import model.tree.RedBlackTree;
import model.tree.RedBlackNode.RedBlackNodeColor;

/**
 * This class defines the file input and output operations for red black trees.
 * It is not designed for inheritance. The format of the file is a little bit
 * different from the format defined in <tt>TreeFile</tt>. Here it is :
 * {@literal [index] [key] [index of left child] [index of right child] [color]}
 * 
 * @author Damien Rigoni
 * @version 1.00 02/07/08
 * @see TreeFile
 */
final class RedBlackTreeFile extends TreeFile {

    private static final String REGEX_COLOR = "(red|black)";

    private static int COLOR = 4;

    RedBlackTreeFile() {
        super();
    }

    @Override
    protected String transformNodeToString(IBinaryNode node, int currentNodeNumber,
            String leftNodeNumber, String rightNodeNumber) {
        assert(node instanceof RedBlackNode);

        return super.transformNodeToString(node, currentNodeNumber,
                leftNodeNumber, rightNodeNumber) + SPACE +
            ((RedBlackNode) node).getColor().toString().toLowerCase();
    }

    @Override
    protected void initREGEX(int currentNodeNumber, int nextNodeNumber) {
        super.initREGEX(currentNodeNumber, nextNodeNumber);
        String lineEnd = REGEX_BLANK + REGEX_COLOR;
        REGEX_2_CHILD += lineEnd;
        REGEX_LEFT_CHILD += lineEnd;
        REGEX_RIGHT_CHILD += lineEnd;
        REGEX_NO_CHILD += lineEnd;
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
    protected void setLeftNode(IBinaryNode node, int childNodeNumber) {
        assert(node instanceof RedBlackNode);

        RedBlackNodeColor color =
                nodeVector.get(childNodeNumber)[COLOR].equals("black") ?
                    RedBlackNodeColor.BLACK : RedBlackNodeColor.RED;
        ((RedBlackNode) node).setLeft(
                new RedBlackNode(Integer.parseInt(
                nodeVector.get(childNodeNumber)[KEY]), color));
    }

    @Override
    protected void setRightNode(IBinaryNode node, int childNodeNumber) {
        assert(node instanceof RedBlackNode);

        RedBlackNodeColor color =
                nodeVector.get(childNodeNumber)[COLOR].equals("black") ?
                    RedBlackNodeColor.BLACK : RedBlackNodeColor.RED;
        ((RedBlackNode) node).setRight(
                new RedBlackNode(Integer.parseInt(
                nodeVector.get(childNodeNumber)[KEY]), color));
    }
}
