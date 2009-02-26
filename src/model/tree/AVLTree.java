/*
 * AVLTree.java v1.00 19/05/08
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
 * This class defines AVL trees with as node <tt>AVLNode</tt>. It is not
 * designed for inheritance.
 *
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchTree
 */
public final class AVLTree extends AbstractBinarySearchTree {

    {
        type = BinaryTreeType.AVLTREE;
    }

    /**
     * Builds an empty AVL tree. The root node is initialized to null.
     */
    public AVLTree() {
        root = null;
    }

    /**
     * Builds an AVL tree with the key given in parameter. The children and the
     * father are initialized to null. If {@code key} is greater than 99 or less
     * than 0 then an IllegalArgumentException is thrown.
     * 
     * @param key the key of the root node
     */
    public AVLTree(int key) throws IllegalArgumentException {
        root = new AVLNode(key);
    }

    @Override
    public AVLNode getRoot() {
        return (AVLNode) root;
    }

    @Override
    public void setRoot(IBinaryNode newNode) throws IllegalArgumentException {
        if (!(newNode instanceof AVLNode)) {
            throw new IllegalArgumentException(
                    "You have to pass an AVLNode");
        }
        root = newNode;
    }

    @Override
    public boolean isWellFormedTree() {
        computeHeightOfEachNode(getRoot());
        return isBalance(getRoot()) && isWellFormedBST(getRoot());
    }

    private void computeHeightOfEachNode(AVLNode node) {
        if (node != null) {
            computeHeightOfEachNode(node.getLeft());
            computeHeightOfEachNode(node.getRight());
            node.computeAndSetHeight();
        }
    }

    private boolean isBalance(AVLNode node) {
        boolean isBalance = true;
        
        if (node != null) {
            isBalance = isBalance(node.getLeft());
            if (isBalance) {
                isBalance = isBalance(node.getRight());
            }
            if (isBalance) {
                int balance = node.computeBalanceFactor();
                if ((balance < -1) || (balance > 1)) {
                    isBalance = false;
                }
            }
        }
        return isBalance;
    }
}
