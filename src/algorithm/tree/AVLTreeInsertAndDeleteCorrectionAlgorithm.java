/*
 * AVLTreeInsertAndDeleteCorrectionAlgorithm.java v0.10 03/04/10
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

import model.tree.AVLNode;
import model.tree.AVLTree;

/**
 * This class defines the AVL tree insert and delete correction algorithm. It is
 * composed by the AVL tree on which the algorithm is applied and the node from
 * which the correction is made.
 *
 * @author Damien Rigoni
 * @version 0.10 03/04/10
 * @see IBinaryTreeAlgorithm
 */
public final class AVLTreeInsertAndDeleteCorrectionAlgorithm implements IBinaryTreeAlgorithm {

    private AVLTree tree;

    private AVLNode node;

    /**
     * Builds the AVL tree insert and delete correction algorithm.
     *
     * @param t the AVL tree on which the algorithm is applied
     * @param n the AVL node from which the correction is made
     */
    public AVLTreeInsertAndDeleteCorrectionAlgorithm(AVLTree t, AVLNode n) {
        tree = t;
        node = n;
    }

    @Override
    public final Object applyAlgorithm() {
        AVLNode x = null;

        if (node != null) {
            if (node.getLeft() != null) {
                x = node.getLeft();
            } else if (node.getRight() != null) {
                x = node.getRight();
            } else {
                x = node;
            }
        }
        
        while (node != null) {
            node.computeAndSetHeight();
            if (node.computeBalanceFactor() == -2) {
                if (x.computeBalanceFactor() == 1) {
                    new AVLTreeLeftRotationAlgorithm(tree, x).applyAlgorithm();
                    new AVLTreeRightRotationAlgorithm(tree, node).applyAlgorithm();
                    x = x.getFather();
                } else {
                    new AVLTreeRightRotationAlgorithm(tree, node).applyAlgorithm();
                }
            } else {
                if (node.computeBalanceFactor() == 2) {
                    if (x.computeBalanceFactor() == -1) {
                        new AVLTreeRightRotationAlgorithm(tree, x).applyAlgorithm();
                        new AVLTreeLeftRotationAlgorithm(tree, node).applyAlgorithm();
                        x = x.getFather();
                    } else {
                        new AVLTreeLeftRotationAlgorithm(tree, node).applyAlgorithm();
                    }
                }
                x = node;
            }
            node = x.getFather();
        }
        
        return x;
    }
}
