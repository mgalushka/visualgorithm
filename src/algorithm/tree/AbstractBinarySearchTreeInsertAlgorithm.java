/*
 * AbstractBinarySearchTreeInsertAlgorithm.java v1.00 03/04/10
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

import model.tree.IBinarySearchNode;
import model.tree.IBinarySearchTree;

/**
 * This class defines the binary search tree insert algorithm. It is composed
 * by the binary search tree on which the algorithm is applied and the node to
 * insert.
 *
 * @author Damien Rigoni
 * @version 1.00 03/04/10
 * @see IBinaryTreeAlgorithm
 */
public abstract class AbstractBinarySearchTreeInsertAlgorithm implements IBinaryTreeAlgorithm {

    /**
     * The binary search tree on which the algorithm is applied.
     */
    private IBinarySearchTree tree;

    /**
     * The binary search node to insert.
     */
    private IBinarySearchNode insertNode;

    /**
     * Builds the binary search tree insert algorithm.
     *
     * @param t the binary search tree on which the algorithm is applied
     * @param n the binary search node to insert
     */
    protected AbstractBinarySearchTreeInsertAlgorithm(IBinarySearchTree t, IBinarySearchNode n) {
        tree = t;
        insertNode = n;
    }

    /**
     * Handle to perform specific correction process at the end of the
     * algorithm. It depends on the type of the binary search tree.
     *
     * @param t the tree on which the corretion is applied
     * @param x the node from which the correction is made
     */
    protected abstract void specificCorrectionProcess(IBinarySearchTree t, IBinarySearchNode x);

    @Override
    public final Object applyAlgorithm() {
        IBinarySearchNode y = null;
        IBinarySearchNode z = tree.getRoot();

        while (z != null) {
            y = z;
            if (insertNode.getKey() < z.getKey()) {
                z = z.getLeft();
            } else {
                z = z.getRight();
            }
        }
        insertNode.setFather(y);
        
        if (y == null) {
            tree.setRoot(insertNode);
        } else {
            if (insertNode.getKey() < y.getKey()) {
                y.setLeft(insertNode);
            } else {
                y.setRight(insertNode);
            }
        }
        specificCorrectionProcess(tree, insertNode);

        return insertNode;
    }
}
