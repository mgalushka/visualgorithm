/*
 * AVLTreeInsertAlgorithm.java v0.10 03/04/10
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
 * This class defines the AVL tree insert algorithm. It is composed by the AVL
 * tree on which the algorithm is applied and the node to insert. This class is
 * not designed for inheritance.
 *
 * @author Damien Rigoni
 * @version 0.10 24/03/08
 * @see AbstractBinarySearchTreeInsertAlgorithm
 */
public final class AVLTreeInsertAlgorithm
        extends AbstractBinarySearchTreeInsertAlgorithm<AVLNode, AVLTree> {

    /**
     * Builds the AVL tree insert algorithm.
     *
     * @param t the AVL tree on which the algorithm is applied
     * @param n the AVL node to insert
     */
    public AVLTreeInsertAlgorithm(AVLTree t, AVLNode n) {
        super(t, n);
    }

    @Override
    protected void specificCorrectionProcess(AVLTree t, AVLNode x) {
        new AVLTreeInsertAndDeleteCorrectionAlgorithm(t, x).applyAlgorithm();
    }
}
