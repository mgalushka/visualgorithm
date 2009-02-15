/*
 * RedBlackNode.java v1.00 19/05/08
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

package model.tree;

/**
 * This class defines the nodes of red black trees. It is not designed for
 * inheritance. These nodes contain a specific attribute which is the color of
 * the node.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IRedBlackNode
 */
public final class RedBlackNode extends AbstractBinarySearchNode implements
        IRedBlackNode {

    /**
     * This enumeration defines the two different colors of red black nodes.
     * 
     * @author Damien Rigoni
     * @version 1.00 19/05/08
     */
    public static enum RedBlackNodeColor {
        RED, BLACK;
    }

    private RedBlackNodeColor color;

    /**
     * Builds a red black node with the key given in parameter. The children and
     * the father are initialized to null and the color is initialized to red by
     * default.
     * 
     * @param key the key of the new red black node
     */
    public RedBlackNode(int key) {
        super(key);
        color = RedBlackNodeColor.RED;
    }

    /**
     * Builds a red black node with the key and the color given in parameters.
     * The children and the father are initialized to null.
     * 
     * @param key the key of the new red black node
     * @param c the color of the new red black node
     */
    public RedBlackNode(int key, RedBlackNodeColor c) {
        super(key);
        color = c;
    }

    @Override
    public RedBlackNode getRight() {
        return (RedBlackNode) right;
    }

    @Override
    public RedBlackNode getLeft() {
        return (RedBlackNode) left;
    }

    @Override
    public RedBlackNode getFather() {
        return (RedBlackNode) father;
    }

    @Override
    public RedBlackNodeColor getColor() {
        return color;
    }

    @Override
    public void setRight(IBinaryNode newNode) {
        if (!(newNode instanceof RedBlackNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a RedBlackNode");
        }
        right = newNode;
    }

    @Override
    public void setLeft(IBinaryNode newNode) {
        if (!(newNode instanceof RedBlackNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a RedBlackNode");
        }
        left = newNode;
    }

    @Override
    public void setFather(IBinaryNode newNode) {
        if (!(newNode instanceof RedBlackNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a RedBlackNode");
        }
        father = newNode;
    }

    @Override
    public boolean isBlack() {
        return color == RedBlackNodeColor.BLACK;
    }

    @Override
    public boolean isRed() {
        return color == RedBlackNodeColor.RED;
    }

    @Override
    public int calculateLeftBlackHeight() {
        if (getLeft() != null) {
            return getLeft().isBlack() ? getLeft().calculateLeftBlackHeight() + 1
                    : getLeft().calculateLeftBlackHeight();
        } else {
            return 0;
        }
    }

    @Override
    public int calculateRightBlackHeight() {
        if (getRight() != null) {
            return getRight().isBlack() ? getRight()
                    .calculateRightBlackHeight() + 1 : getRight()
                    .calculateRightBlackHeight();
        } else {
            return 0;
        }
    }
}
