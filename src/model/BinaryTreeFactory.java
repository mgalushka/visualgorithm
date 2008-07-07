/*
 * DataStructureFactory.java v1.00 16/06/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
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

package model;

import model.tree.AVLTree;
import model.tree.BinarySearchTree;
import model.tree.IBinaryTree;
import model.tree.RedBlackTree;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Factory of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class BinaryTreeFactory {

    /**
     * Builds a binary tree thanks to its type.
     * 
     * @param type the type of the binary tree
     * @return the binary tree
     */
    public static IBinaryTree<?> createBinaryTree(BinaryTreeType type) {
        switch(type) {
        case AVLTREE:
            return new AVLTree();
        case BINARYSEARCHTREE:
            return new BinarySearchTree();
        case REDBLACKTREE:
            return new RedBlackTree();
        }
        return null;
    }
}
