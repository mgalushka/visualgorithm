/*
 * BinarySearchTree.java v0.10 19/05/08
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
 * This class defines binary search trees with as node
 * <tt>BinarySearchNode</tt>. It is not designed for inheritance.
 *
 * @author Damien Rigoni
 * @version 0.10 19/05/08
 * @see IBinarySearchTree
 */
public final class BinarySearchTree extends
        AbstractBinarySearchTree {

    {
        type = BinaryTreeType.BINARYSEARCHTREE;
    }

    /**
     * Builds an empty binary search tree. The root node is initialized to null.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Builds a binary search tree with the key given in parameter. The children
     * and the father are initialized to null.
     * 
     * @param key the key of the root node
     */
    public BinarySearchTree(int key) {
        root = new BinarySearchNode(key);
    }

    @Override
    public BinarySearchNode getRoot() {
        return (BinarySearchNode) root;
    }

    @Override
    public void setRoot(IBinaryNode newNode) throws IllegalArgumentException {
        if ((newNode != null) && !(newNode instanceof BinarySearchNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a BinarySearchNode");
        }
        root = newNode;
    }

    @Override
    public boolean isWellFormedTree() {
        return isWellFormedBST(getRoot());
    }
}
