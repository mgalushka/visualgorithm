/*
 * RedBlackTree.java v1.00 19/05/08
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
 * This class defines red black trees with as node <tt>RedBlackNode</tt>. It is
 * not designed for inheritance.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchTree
 */
public final class RedBlackTree extends AbstractBinarySearchTree {

    {
        type = BinaryTreeType.REDBLACKTREE;
    }

    /**
     * Builds an empty red black tree. The root node is initialized to null.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Builds a red black tree with the key given in parameter. The children and
     * the father are initialized to null.
     *
     * @param key the key of the root node
     */
    public RedBlackTree(int key) {
        root = new RedBlackNode(key, RedBlackNode.RedBlackNodeColor.BLACK);
    }

    @Override
    public RedBlackNode getRoot() {
        return (RedBlackNode) root;
    }

    @Override
    public void setRoot(IBinaryNode newNode) throws IllegalArgumentException {
        if ((newNode != null) && !(newNode instanceof RedBlackNode)) {
            throw new IllegalArgumentException(
                    "You have to pass a RedBlackNode");
        }
        root = newNode;
    }

    @Override
    public boolean isWellFormedTree() {
        return (getRoot() == null)
                || ((getRoot()).isBlack() && isWellFormedBST(getRoot())
                        && hasRedNodeWithBlackChild(getRoot())
                        && hasGoodHeight(getRoot()));
    }

    private boolean hasGoodHeight(RedBlackNode node) {
        boolean hasGoodHeight = true;

        if (node != null) {
            hasGoodHeight = hasGoodHeight(node.getLeft());
            if (hasGoodHeight) {
                hasGoodHeight = hasGoodHeight(node.getRight());
            }
            if (hasGoodHeight) {
                int leftHeight = node.calculateLeftBlackHeight();
                int rightHeight = node.calculateRightBlackHeight();
                hasGoodHeight = (leftHeight == rightHeight);
            }
        }
        return hasGoodHeight;
    }

    private boolean hasRedNodeWithBlackChild(RedBlackNode node) {
        boolean hasRedNodeWithBlackChild = true;

        if (node != null) {
            hasRedNodeWithBlackChild = hasRedNodeWithBlackChild(node.getLeft());
            if (hasRedNodeWithBlackChild) {
                hasRedNodeWithBlackChild =
                        hasRedNodeWithBlackChild(node.getRight());
            }
            if (hasRedNodeWithBlackChild) {
                if (node.isRed()) {
                    if (node.getLeft() != null) {
                        if (node.getLeft().isRed()
                                || (node.getRight() == null)) {
                            hasRedNodeWithBlackChild = false;
                        }
                    }
                    if (node.getRight() != null) {
                        if (node.getRight().isRed()
                                || (node.getLeft() == null)) {
                            hasRedNodeWithBlackChild = false;
                        }
                    }
                }
            }
        }
        return hasRedNodeWithBlackChild;
    }
}
