/*
 * AbstractBinarySearchTree.java v1.00 19/05/08
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
 * Abstract class containing common methods of all binary search trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchNode
 * @see IBinarySearchTree
 */
public abstract class AbstractBinarySearchTree extends AbstractBinaryTree
        implements IBinarySearchTree {

    /**
     * Returns true if the tree is a well formed binary search tree.
     * 
     * @param node a node of the tree
     * @return true if node is a well formed binary search tree
     */
    protected boolean isBST(IBinarySearchNode node) {
        if (node != null) {
            if ((node.getLeft() == null) && (node.getRight() == null)) {
                return true;
            } else {
                if (node.getLeft() == null) {
                    return (node.getRight().getKey() > node.getKey())
                            && isBST(node.getRight());
                } else if (node.getRight() == null) {
                    return (node.getLeft().getKey() < node.getKey())
                            && isBST(node.getLeft());
                } else {
                    return (node.getLeft().getKey() < node.getKey())
                            && isBST(node.getLeft())
                            && (node.getRight().getKey() > node.getKey())
                            && isBST(node.getRight());
                }
            }
        }
        return true;
    }
}
