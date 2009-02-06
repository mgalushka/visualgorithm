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
 * Abstract class containing common methods of all binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 * @see IBinaryTree
 */
public abstract class AbstractBinaryTree implements IBinaryTree {

    protected BinaryTreeType type;

    protected IBinaryNode root;

    /**
     * Definition of the type of binary trees.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    public enum BinaryTreeType {
        AVLTREE(AVLTree.class), BINARYSEARCHTREE(BinarySearchTree.class),
        REDBLACKTREE(RedBlackTree.class);

        private Class<? extends IBinaryTree> binaryTreeClass;

        private BinaryTreeType(Class<? extends IBinaryTree> treeClass) {
            binaryTreeClass = treeClass;
        }

        /**
         * Builds the binary tree corresponding to the type.
         * 
         * @return the binary tree
         */
        public IBinaryTree getBinaryTree() {
            try {
                return binaryTreeClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void setRoot(IBinaryNode newNode) {
        root = newNode;
    }

    @Override
    public final String getType() {
        return type.toString();
    }

    @Override
    public final int calculateHeight() {
        return recursiveHeight(root);
    }

    @Override
    public final List<IBinaryNode> treeToArrayList() {
        int length = (2 * (int) Math.pow(2, calculateHeight())) - 1;
        List<IBinaryNode> arrayTree = new ArrayList<IBinaryNode>();
        for (int i = 0; i < length; i++) {
            arrayTree.add(null);
        }
        if (length > 0) {
            buildArray(arrayTree, 0, root);
        }
        return arrayTree;
    }

    private int recursiveHeight(IBinaryNode node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(recursiveHeight(node.getLeft()),
                recursiveHeight(node.getRight())) + 1;
        }
    }

    private void buildArray(List<IBinaryNode> array, int index, IBinaryNode node) {
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            array.set(index, node);
        } else {
            if (node.getLeft() == null) {
                buildArray(array, 2 * index + 2, node.getRight());
            } else if (node.getRight() == null) {
                buildArray(array, 2 * index + 1, node.getLeft());
            } else {
                buildArray(array, 2 * index + 1, node.getLeft());
                buildArray(array, 2 * index + 2, node.getRight());
            }
            array.set(index, node);
        }
    }
}
