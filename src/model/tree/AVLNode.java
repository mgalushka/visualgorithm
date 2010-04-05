/*
 * AVLNode.java v1.00 19/05/08
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
 * This class defines the nodes of AVL trees. It is not designed for inheritance.
 * These nodes contain a specific attribute which is the height of the node.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IAVLNode
 */
public final class AVLNode extends AbstractBinarySearchNode implements IAVLNode {

    private int height;

    /**
     * Builds an AVL node with the key given in parameter. The children and the
     * father are initialized to null and the height is initialized to 0.
     * 
     * @param key the key of the new AVL node
     */
    public AVLNode(int key) {
        super(key);
        height = 0;
    }

    @Override
    public AVLNode getRight() {
        return (AVLNode) right;
    }

    @Override
    public AVLNode getLeft() {
        return (AVLNode) left;
    }

    @Override
    public AVLNode getFather() {
        return (AVLNode) father;
    }

    @Override
    public int getAVLHeight() {
        return height;
    }

    @Override
    public void setRight(IBinaryNode newNode) throws IllegalArgumentException {
        if ((newNode != null) && !(newNode instanceof AVLNode)) {
            throw new IllegalArgumentException("You have to pass an AVLNode");
        }
        right = newNode;
    }

    @Override
    public void setLeft(IBinaryNode newNode) throws IllegalArgumentException {
        if ((newNode != null) && !(newNode instanceof AVLNode)) {
            throw new IllegalArgumentException("You have to pass an AVLNode");
        }
        left = newNode;
    }

    @Override
    public void setFather(IBinaryNode newNode) throws IllegalArgumentException {
        if ((newNode != null) && !(newNode instanceof AVLNode)) {
            throw new IllegalArgumentException("You have to pass an AVLNode");
        }
        father = newNode;
    }

    @Override
    public void computeAndSetHeight() {
        int leftHeight = 0;
        int rightHeight = 0;

        if (getLeft() != null) {
            leftHeight = getLeft().getAVLHeight() + 1;
        }
        if (getRight() != null) {
            rightHeight = getRight().getAVLHeight() + 1;
        }
        height = Math.max(leftHeight, rightHeight);
    }

    @Override
    public int computeBalanceFactor() {
        int leftBalance = 0;
        int rightBalance = 0;

        if (getLeft() != null) {
            leftBalance = getLeft().getAVLHeight() + 1;
        }
        if (getRight() != null) {
            rightBalance = getRight().getAVLHeight() + 1;
        }
        return rightBalance - leftBalance;
    }
}
