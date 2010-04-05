/*
 * IBinaryTreeModel.java v1.00 06/12/09
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

import model.IDataStructureModel;

/**
 * This interface defines binary tree models. It must be implemented by all the
 * binary tree models. It is principally used in the binary tree controller.
 *
 * @author Julien Hannier
 * @version 1.00 06/12/09
 * @see IDataStructureModel
 */
public interface IBinaryTreeModel extends IDataStructureModel {

    /**
     * Inserts {@code nbNode} random nodes into the binary tree. This method
     * uses the insert algorithm corresponding to the type of the binary tree.
     * If it is necessary, the binary tree is corrected. For instance, the
     * balance of an AVL tree is corrected after multiple insertions.
     *
     * @param nbNode the number of random nodes to insert
     */
    public void insertRandomNodes(int nbNode);

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

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm from the binary search tree that is to say that the node is
     * deleted without any correction of the binary tree. For instance, the
     * balance of an AVL tree is not corrected after the deletion.
     *
     * @param key the key of the node to delete
     */
    public void deleteNodeWithoutCorrection(int key);
}
