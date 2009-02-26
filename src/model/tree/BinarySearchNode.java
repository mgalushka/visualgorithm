/*
 * BinarySearchNode.java v1.00 19/05/08
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
 * This class defines the nodes of binary search trees. It is not designed for
 * inheritance. These nodes do not contain any additional attributes.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchNode
 */
public final class BinarySearchNode extends AbstractBinarySearchNode {

    /**
     * Builds a binary search node with the key given in parameter. The children
     * and the father are initialized to null. If {@code key} is greater than 99
     * or less than 0 then an IllegalArgumentException is thrown.
     * 
     * @param key the key of the new binary search node.
     * @throws IllegalArgumentException
     */
    public BinarySearchNode(int key) throws IllegalArgumentException {
        super(key);
    }

    @Override
    public BinarySearchNode getRight() {
        return (BinarySearchNode) right;
    }

    @Override
    public BinarySearchNode getLeft() {
        return (BinarySearchNode) left;
    }

    @Override
    public BinarySearchNode getFather() {
        return (BinarySearchNode) father;
    }

    @Override
    public void setRight(IBinaryNode newNode) throws IllegalArgumentException {
        if (!(newNode instanceof BinarySearchNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a BinarySearchNode");
        }
        right = newNode;
    }

    @Override
    public void setLeft(IBinaryNode newNode) throws IllegalArgumentException {
        if (!(newNode instanceof BinarySearchNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a BinarySearchNode");
        }
        left = newNode;
    }

    @Override
    public void setFather(IBinaryNode newNode) throws IllegalArgumentException {
        if (!(newNode instanceof BinarySearchNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a BinarySearchNode");
        }
        father = newNode;
    }
}
