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
 * Interface describing the methods of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 */
public interface IBinaryTree extends IDataStructure {

    /**
     * Calculates the height of the tree.
     * 
     * @return the height of the tree
     */
    public int calculateHeight();

    /**
     * Builds an array list corresponding to the tree. The array list will
     * contain null values for the absent nodes.
     * 
     * @return the array list corresponding to the tree
     */
    public List<IBinaryNode> treeToArrayList();

    /**
     * Returns true if the tree is well formed.
     * 
     * @return true if the tree is well formed
     */
    public boolean isGoodTree();

    /**
     * Returns the root of the tree.
     * 
     * @return the root of the tree
     */
    public IBinaryNode getRoot();
    

    /**
     * Replaces the root of the tree by the new node.
     * 
     * @param newNode the new root node
     */
    public void setRoot(IBinaryNode newNode);
}
