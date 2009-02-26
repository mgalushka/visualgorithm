/*
 * IBinaryTree.java v1.00 19/05/08
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

import java.util.List;
import model.IDataStructure;

/**
 * This interface contains all the methods in order to use binary trees. It must
 * be implemented by all types of binary trees directly or through one defined
 * abstract class. The method {@code IBinaryNode getRoot()} must be defined with
 * the concrete type of node corresponding to the tree. For instance, it will
 * look like {@code AVLNode getRoot()} for the class AVLTree. The method
 * {@code void setRoot(IBinaryNode newNode)} must be used with the same type of
 * node that the type corresponding to the tree on which it is used. If not, an
 * IllegalArgumentException is thrown. Keys are only integers.
 *
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 */
public interface IBinaryTree extends IDataStructure {

    /**
     * Returns the root of the tree.
     *
     * @return the root of the tree
     */
    public IBinaryNode getRoot();

    /**
     * Replaces the root of the tree by {@code newNode}. If {@code newNode} does
     * not have the same type that the type of node corresponding to the tree on
     * which the method is applied then an IllegalArgumentException is thrown.
     *
     * @param newNode the new root node
     * @throws IllegalArgumentException
     */
    public void setRoot(IBinaryNode newNode) throws IllegalArgumentException;

    /**
     * Returns true if the tree is well formed, or else false. A well formed
     * tree is a tree that corresponds to its properties.
     *
     * @return true if the tree is well formed, or else false
     */
    public boolean isWellFormedTree();

    /**
     * Computes the height of the tree.
     * 
     * @return the height of the tree
     */
    public int computeHeight();

    /**
     * Builds a heap corresponding to the binary tree. The heap is contained in
     * an array list. The array list will contain null values for the absent
     * nodes.
     * 
     * @return the array list containing the heap
     */
    public List<IBinaryNode> buildHeapFromBinaryTree();
}
