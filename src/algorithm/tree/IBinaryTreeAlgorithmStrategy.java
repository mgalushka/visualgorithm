/*
 * IBinaryTreeAlgorithmStrategy.java v1.00 18/04/10
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

package algorithm.tree;

/**
 * This interface defines binary tree algorithm strategies. Its goal is to link
 * a type of binary tree to its algorithms. In order to do so, it uses the
 * design pattern strategy. It must be implemented by all the binary tree
 * algorithm strategies.
 * 
 * @author Julien Hannier
 * @version 1.00 18/04/10
 */
public interface IBinaryTreeAlgorithmStrategy {

    /**
     * Inserts a node into the binary tree. This method uses the insert
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the insertion.
     *
     * @param key the key of the node to insert
     */
    public void insertNode(int key);

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the deletion.
     *
     * @param key the key of the node to delete 
     */
    public void deleteNode(int key);
}
