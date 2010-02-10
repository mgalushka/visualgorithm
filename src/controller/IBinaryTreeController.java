/*
 * IBinaryTreeController.java v1.00 06/12/09
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

package controller;

import io.tree.TreeFile;
import view.IBinaryTreeView;

/**
 * This interface defines binary tree controllers. It must be implemented by all
 * the binary tree controllers. It is principally used in the software controller
 * and also in the binary tree view.
 *
 * @author Julien Hannier
 * @version 1.00 06/12/09
 * @see IDataStructureController
 */
public interface IBinaryTreeController extends IDataStructureController {

    /**
     * Definition of the extension of the binary tree files.
     */
    public static final String BINARY_TREE_FILE_EXTENSION = TreeFile.FILE_EXTENSION;

    @Override
    public IBinaryTreeView getView();

    @Override
    public IBinaryTreeController clone();

    /**
     * Adds a node into the binary tree. This method uses the insert
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the insertion. If {@code key} is greater than
     * 99 or less than 0 then an IllegalArgumentException is thrown.
     *
     * @param key the key of the node to add
     * @throws IllegalArgumentException
     */
    public void addNodeToBinaryTreeModel(int key) throws IllegalArgumentException;

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the deletion.
     *
     * @param key the key of the node to delete
     */
    public void deleteNodeFromBinaryTreeModel(int key);

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm from the binary search tree that is to say that the node is
     * deleted without any correction of the binary tree. For instance, the
     * balance of an AVL tree is not corrected after the deletion.
     *
     * @param key the key of the node to delete
     */
    public void deleteNodeFromBinaryTreeModelWithoutCorrection(int key);
}
