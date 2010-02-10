/*
 * AbstractBinaryTree.java v1.00 19/05/08
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

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class defines all the common attributes and methods of all
 * binary trees. It has been implemented to be inherited by all binary tree
 * classes like BinaryTree.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryTree
 */
public abstract class AbstractBinaryTree implements IBinaryTree {

    /**
     * Enumeration that defines the types of binary trees. This enumeration is
     * also a binary tree factory. In fact, it is possible to create the binary
     * tree corresponding to each type with the method {@code IBinaryTree
     * createBinaryTree()}.
     *
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    public static enum BinaryTreeType {
        AVLTREE(AVLTree.class), BINARYSEARCHTREE(BinarySearchTree.class),
        REDBLACKTREE(RedBlackTree.class);

        private Class<? extends IBinaryTree> classOfTheBinaryTree;

        private BinaryTreeType(Class<? extends IBinaryTree> c) {
            classOfTheBinaryTree = c;
        }

        /**
         * Creates the binary tree corresponding to the type on which the method
         * is applied. If the new instance of the binary tree can not be created
         * then null is returned.
         *
         * @return the new instance of binary tree
         */
        IBinaryTree createBinaryTree() {
            try {
                return classOfTheBinaryTree.newInstance();
            } catch (Exception e) {
            }
            return null;
        }
    }

    /**
     * The type of the binary tree.
     */
    protected BinaryTreeType type;

    /**
     * The root node of the binary tree.
     */
    protected IBinaryNode root;

    @Override
    public final String getType() {
        return type.toString();
    }

    @Override
    public final int computeHeight() {
        return recursiveComputeHeight(root);
    }

    @Override
    public final List<IBinaryNode> buildHeapFromBinaryTree() {
        int heapLength = (2 * (int) Math.pow(2, computeHeight())) - 1;
        List<IBinaryNode> heap = new ArrayList<IBinaryNode>();
        
        for (int i = 0; i < heapLength; i++) {
            heap.add(null);
        }
        if (heapLength > 0) {
            recursiveBuildHeap(heap, 0, root);
        }
        return heap;
    }

    private int recursiveComputeHeight(IBinaryNode node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(recursiveComputeHeight(node.getLeft()),
                    recursiveComputeHeight(node.getRight())) + 1;
        }
    }

    private void recursiveBuildHeap(List<IBinaryNode> array, int index,
            IBinaryNode node) {
        assert(index >= 0);
        
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            array.set(index, node);
        } else {
            if (node.getLeft() == null) {
                recursiveBuildHeap(array, 2 * index + 2, node.getRight());
            } else if (node.getRight() == null) {
                recursiveBuildHeap(array, 2 * index + 1, node.getLeft());
            } else {
                recursiveBuildHeap(array, 2 * index + 1, node.getLeft());
                recursiveBuildHeap(array, 2 * index + 2, node.getRight());
            }
            array.set(index, node);
        }
    }
}
